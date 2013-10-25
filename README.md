# Clojure Quick Start Guide

This guide will walk you through deploying a Clojure application on Deis.

## Prerequisites

* A [User Account](http://docs.deis.io/en/latest/client/register/) on a [Deis Controller](http://docs.deis.io/en/latest/terms/controller/).
* A [Deis Formation](http://docs.deis.io/en/latest/gettingstarted/concepts/#formations) that is ready to host applications

If you do not yet have a controller or a Deis formation, please review the [Deis installation](http://docs.deis.io/en/latest/gettingstarted/installation/) instructions.

## Setup your workstation

* Install [RubyGems](http://rubygems.org/pages/download) to get the `gem` command on your workstation
* Install [Foreman](http://ddollar.github.com/foreman/) with `gem install foreman`
* Install [Clojure](http://clojure.org/downloads) if you don't have one already

## Clone your Application

If you want to use an existing application, no problem.  You can also use the Deis sample application located at <https://github.com/bengrunfeld/example-clojure-ring>.  Clone the example application to your local workstation:

	$ git clone https://github.com/bengrunfeld/example-clojure-ring.git
	$ cd example-clojure-ring

## Prepare your Application

To use a Clojure application with OpDemand, you will need to conform to 3 basic requirements:

 1. Use [Leiningen](https://github.com/technomancy/leiningen#installation) to manage dependencies
 2. Use [Foreman](http://ddollar.github.com/foreman/) to manage processes
 3. Use [Environment Variables](https://help.ubuntu.com/community/EnvironmentVariables) to manage configuration inside your application

If you're deploying the example application, it already conforms to these requirements.

#### 1. Use Leiningen to manage dependencies

Leiningen requires that you explicitly declare your dependencies using a [project.clj](https://github.com/technomancy/leiningen/blob/stable/doc/TUTORIAL.md#projectclj) file.  Here is a very [basic example](https://github.com/bengrunfeld/example-clojure-ring/blob/master/project.clj). You can then use `lein deps` to install dependencies, compile and package your application on your local workstation:

    $ lein deps
    Retrieving org/clojure/clojure/1.2.1/clojure-1.2.1.pom from central
    Retrieving compojure/compojure/1.0.1/compojure-1.0.1.pom from clojars                                                                       
    ...
    Retrieving ring/ring-core/1.0.0/ring-core-1.0.0.jar from clojars


#### 2. Use Foreman to manage processes

Deis relies on a [Foreman](http://ddollar.github.com/foreman/) `Procfile` that lives in the root of your repository.  This is where you define the command(s) used to run your application.  Here is an example `Procfile`:

    web: lein trampoline run -m helloworld.web $PORT

This tells Deis to run `web` workers using the command `lein trampoline run`. You can test this locally by running `foreman start`.

	$ foreman start
	12:31:55 web.1  | started with pid 36247
	12:31:59 web.1  | 2013-10-25 12:31:59.533:INFO::Logging to STDERR via org.mortbay.log.StdErrLog
	12:31:59 web.1  | 2013-10-25 12:31:59.535:INFO::jetty-6.1.25
	12:31:59 web.1  | 2013-10-25 12:31:59.671:INFO::Started SocketConnector@0.0.0.0:5000

You should now be able to access your application locally at <http://localhost:5000>.

#### 3. Use Environment Variables to manage configuration

Deis uses environment variables to manage your application's configuration. For example, your application listener must use the value of the `PORT` environment variable. The following code snippet demonstrates how this can work inside your application:

	(get (System/getenv) "PORT" 5000)  ; fallback to 5000


## Create a new Application

Per the prerequisites, we assume you have access to an existing Deis formation. If not, please review the Deis [installation instuctions](http://docs.deis.io/en/latest/gettingstarted/installation/).

Use the following command to create an application on an existing Deis formation.

	$ deis create --formation=<formationName> --id=<appName>
	Creating application... done, created <appName>
	Git remote deis added
	
If an ID is not provided, one will be auto-generated for you.

## Deploy your Application

Use `git push` to deploy your application.

    $ git push deis master
          Clojure app detected
    -----> Installing OpenJDK 1.6...done
    -----> Using cached Leiningen 1.7.1
          To use Leiningen 2.x, add this to project.clj: :min-lein-version "2.0.0"
          Downloading: rlwrap-0.3.7
          Writing: lein script

Once your application has been deployed, use `deis open` to view it in a browser. To find out more info about your application, use `deis info`.

## Scale your Application

To scale your application's [Docker](http://docker.io) containers, use `deis scale`.

	$ deis scale web=8
	Scaling containers... but first, coffee!
	done in 19s
	
	=== unbent-occupant Containers
	
	--- web: `lein trampoline run -m helloworld.web $PORT`
	web.1 up 2013-10-25T18:44:58.731Z (clojureFormation-runtime-1)
	web.2 up 2013-10-25T18:48:27.867Z (clojureFormation-runtime-1)
	web.3 up 2013-10-25T18:48:27.883Z (clojureFormation-runtime-1)
	web.4 up 2013-10-25T18:48:27.896Z (clojureFormation-runtime-1)
	web.5 up 2013-10-25T18:48:27.912Z (clojureFormation-runtime-1)
	web.6 up 2013-10-25T18:48:27.928Z (clojureFormation-runtime-1)
	web.7 up 2013-10-25T18:48:27.945Z (clojureFormation-runtime-1)
	web.8 up 2013-10-25T18:48:27.963Z (clojureFormation-runtime-1)


## Configure your Application

Deis applications are configured using environment variables. The example application includes a special `POWERED_BY` variable to help demonstrate how you would provide application-level configuration. 

	$ curl -s http://yourapp.com
	Powered by Deis
	$ deis config:set POWERED_BY=Clojure
	=== unbent-occupant
	POWERED_BY: Clojure
	$ curl -s http://yourapp.com
	Powered by Clojure

This method is also how you connect your application to backing services like databases, queues and caches.

To experiment in your application environment, use `deis run` to execute one-off commands against your application.

	$ deis run ls -la
	drwxr-xr-x  9 root root 4096 Oct 25 18:47 .
	drwxr-xr-x 57 root root 4096 Oct 25 18:53 ..
	-rw-r--r--  1 root root   59 Oct 25 18:46 .gitignore
	drwxr-xr-x  6 root root 4096 Oct 25 18:46 .jdk
	drwxr-xr-x  3 root root 4096 Oct 25 18:46 .lein
	-rw-r--r--  1 root root   40 Oct 25 18:46 .lein-deps-sum
	drwxr-xr-x  2 root root 4096 Oct 25 18:46 .profile.d
	-rw-r--r--  1 root root  154 Oct 25 18:46 .release
	-rw-r--r--  1 root root  553 Oct 25 18:46 LICENSE
	-rw-r--r--  1 root root   49 Oct 25 18:46 Procfile
	-rw-r--r--  1 root root 8985 Oct 25 18:46 README.md
	drwxr-xr-x  2 root root 4096 Oct 25 18:47 classes
	drwxr-xr-x  2 root root 4096 Oct 25 18:46 lib
	-rw-r--r--  1 root root  414 Oct 25 18:46 project.clj
	drwxr-xr-x  3 root root 4096 Oct 25 18:46 src
	drwxr-xr-x  3 root root 4096 Oct 25 18:46 test

## Troubleshoot your Application

To view your application's log output, including any errors or stack traces, use `deis logs`.

	$ deis logs
	<show output>

## Additional Resources

* [Get Deis](http://deis.io/get-deis/)
* [GitHub Project](https://github.com/opdemand/deis)
* [Documentation](http://docs.deis.io/)
* [Blog](http://deis.io/blog/)

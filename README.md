Clojure Quick Start Guide
=========================

This guide will walk you through deploying a Clojure application on AWS using OpDemand.

Prerequisites
--------------
* A [free OpDemand account](https://app.opdemand.com/signup) with
  * Valid AWS credentials
  * Linked GitHub account
* The OpDemand Command Line Interface
* A Clojure application that is **hosted on GitHub**

Clone your Application
----------------------
The simplest way to get started is by forking OpDemand's sample application located at:
<https://github.com/opdemand/example-clojure-ring>

After forking the project, clone it to your local workstation using the SSH-style URL:

    $ git clone git@github.com:mygithubuser/example-clojure-ring.git example-clojure-ring
    $ cd example-clojure-ring

If you want to use an existing application, no problem -- just make sure you've cloned it from GitHub.

Prepare your Application
------------------------
To use a Clojure application with OpDemand, you will need to conform to 3 basic requirements:

 * Use [**leiningen**](https://github.com/technomancy/leiningen) to manage dependencies
 * Use [**foreman**](http://ddollar.github.com/foreman/) to manage processes
 * Use **Environment Variables** to manage configuration

If you're deploying the example application, it already conforms to these requirements.  If you're in a rush, skip to [Create a new Service](#create-a-new-service).

### Use Leiningen to manage dependencies

On every deploy action, OpDemand will run `lein deps` on all application workers to ensure dependencies are up to date.  Dependencies are specified in a `project.clj` file in the root of your repository.  Here is an example `project.clj`:

    (defproject opdemand-helloworld "1.0.0-SNAPSHOT"
      :description "OpDemand Example Application"
      :dependencies [
            [org.clojure/clojure "1.2.1"]
            [compojure "1.0.1"]
            [ring/ring-core "1.0.0"]
            [ring/ring-jetty-adapter "1.0.0"]
            [lein-ring "0.5.4"]
            ]
      :dev-dependencies [
        [org.clojure/clojure-contrib "1.2.0"]
        ]
      :ring {:handler helloworld.web/handler}
            )

Dependencies will be installed into the `lib` directory, but should not be included in version control.  Make sure `lib` is in your `.gitignore` file.

### Use Foreman to manage processes

OpDemand uses a Foreman Procfile to manage the processes that serve up your application.  The `Procfile` is how you define the command(s) used to run your application.  Here is an example `Procfile`:

	web: lein trampoline run -m helloworld.web $APPLICATION_PORT

This tells OpDemand to run one web process using `lein trampoline` and make it listen on `APPLICATION_PORT`.  You can test this out locally by running setting the `APPLICATION_PORT` environment variable and calling `foreman start`.

    $ export APPLICATION_PORT=8080
	$ foreman start
    15:03:33 web.1     | started with pid 28211
    15:03:39 web.1     | 2012-05-10 15:03:39.099:INFO::Logging to STDERR via org.mortbay.log.StdErrLog
    15:03:39 web.1     | 2012-05-10 15:03:39.100:INFO::jetty-6.1.25
    15:03:39 web.1     | 2012-05-10 15:03:39.127:INFO::Started SocketConnector@0.0.0.0:8080

### Use Environment Variables to manage configuration

OpDemand uses environment variables to manage your application's configuration.  For example, the application listener must use the value of the `APPLICATION_PORT` environment variable.  The following code snippets demonstrates how this can work inside your application:

	(get (System/getenv) "APPLICATION_PORT" 8080)  ; fallback to 8080

The same is true for external services like databases, caches and queues.  Here is an example in that shows how to connect to a MongoDB database using the `DATABASE_HOST` and `DATABASE_PORT` environment variables:

	(ns my-mongo-app
	  (:use somnium.congomongo))

    (def conn
      (make-connection "mydb"
                   :host (get (System/getenv) "DATABASE_HOST" "localhost")
                   :port (get (System/getenv) "DATABASE_PORT" 27017)))

<a id="create-a-new-service"></a>
Create a new Service
--------------------
Use the `opdemand list` command to list the available infrastructure templates:

	$ opdemand list | grep clojure
    app/clojure/1node: Clojure Application (1-node)
    app/clojure/2node: Clojure Application (2-node with ELB)
    app/clojure/4node: Clojure Application (4-node with ELB)
    app/clojure/Nnode: Clojure Application (Auto Scaling)

Use the `opdemand create` command to create a new service based on one of the templates listed.  To create an `app/clojure/1node` service with `app` as its handle/nickname.

	$ opdemand create app --template=app/clojure/1node

Configure the Service
----------------------
To quickly configure a service from the command-line use `opdemand config [handle] --repository=detect`.  This will attempt to detect and install repository configuration including:

* Detecting your GitHub repository URL, project and username
* Generating and installing a secure SSH Deploy Key

More detailed configuration can be done using:

	$ opdemand config app					   # the entire config wizard (all sections)
	$ opdemand config app --section=provider   # only the "provider" section

Detailed configuration changes are best done via the web console, which exposes additional helpers, drop-downs and overrides.

Start the Service
------------------
To start your service use the `opdemand start` command:

	$ opdemand start app

You will see real-time streaming log output as OpDemand orchestrates the service's infrastructure and triggers the necessary SSH deployments.  Once the service has finished starting you can access its services using an `opdemand show`.

    $ opdemand show app

	Application URL (URL used to access this application)
	http://ec2-23-20-231-188.compute-1.amazonaws.com

Open the URL and you should see "Powered by OpDemand" in your browser.  To check on the status of your services, use the `opdemand status` command:

	$ opdemand status
	app: Clojure Application (1-node) (status: running)

Deploy the Service
----------------------
As you make changes to your application code, push those to GitHub as you would normally.  When you're ready to deploy those changes, use the `opdemand deploy` command:

	$ opdemand deploy app

This will trigger an OpDemand deploy action which will -- among other things -- update configuration settings, pull down the latest source code, install new dependencies and restart services where necessary.


Additional Resources
====================
* <http://www.opdemand.com>

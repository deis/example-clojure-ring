# Clojure Quick Start Guide

This guide will walk you through deploying a Clojure application on Deis.

## Usage

```console
$ git clone https://github.com/deis/example-clojure-ring
$ cd example-clojure-ring
$ deis create
Creating application... done, created velvet-jerrycan
Git remote deis added
$ git push deis master
Counting objects: 127, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (59/59), done.
Writing objects: 100% (127/127), 22.80 KiB | 0 bytes/s, done.
Total 127 (delta 48), reused 107 (delta 39)
-----> Clojure (Leiningen 2) app detected
-----> Installing OpenJDK 1.8...done
-----> Installing Leiningen
       Downloading: leiningen-2.5.1-standalone.jar
       Writing: lein script
-----> Building with Leiningen
       Running: lein with-profile production compile :all
       Retrieving org/clojure/clojure/1.7.0/clojure-1.7.0.pom from central
...
       Retrieving clj-time/clj-time/0.6.0/clj-time-0.6.0.jar from clojars
       Retrieving ring/ring-servlet/1.3.2/ring-servlet-1.3.2.jar from clojars
       Compiling helloworld.web
-----> Discovering process types
       Procfile declares types -> web
       Default process types for Clojure (Leiningen 2) -> web
-----> Compiled slug size is 73M

-----> Building Docker image
remote: Sending build context to Docker daemon 70.35 MB
remote: build context to Docker daemon
Step 0 : FROM deis/slugrunner
 ---> 4e3f871d77a2
Step 1 : RUN mkdir -p /app
 ---> Using cache
 ---> 38b9675e88c6
Step 2 : WORKDIR /app
 ---> Using cache
 ---> 4690a490e732
Step 3 : ENTRYPOINT /runner/init
 ---> Using cache
 ---> f12d814fba64
Step 4 : ADD slug.tgz /app
 ---> 6ad55e9e503d
Removing intermediate container d6b9d398e590
Step 5 : ENV GIT_SHA 11017a293f756f9de3d5422df0b6ba1d1e66d59a
 ---> Running in 35725edc5e58
 ---> b909d8ef1139
Removing intermediate container 35725edc5e58
Successfully built b909d8ef1139
-----> Pushing image to private registry

-----> Launching...
       done, velvet-jerrycan:v2 deployed to Deis

       http://velvet-jerrycan.local3.deisapp.com

       To learn more, use `deis help` or visit http://deis.io

To ssh://git@deis.local3.deisapp.com:2222/velvet-jerrycan.git
 * [new branch]      master -> master
$ curl http://velvet-jerrycan.local3.deisapp.com
Powered by Deis
$ deis config:set POWERED_BY="Engine Yard"
Creating config... done, v3

=== velvet-jerrycan
DEIS_APP: velvet-jerrycan
POWERED_BY: Engine Yard
$ curl http://velvet-jerrycan.local3.deisapp.com
Powered by Engine Yard
```

## Additional Resources

* [Get Deis](http://deis.io/get-deis/)
* [GitHub Project](https://github.com/deis/deis)
* [Documentation](http://docs.deis.io/)
* [Blog](http://deis.io/blog/)

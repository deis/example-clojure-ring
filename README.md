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
Counting objects: 134, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (53/53), done.
Writing objects: 100% (134/134), 24.36 KiB | 0 bytes/s, done.
Total 134 (delta 54), reused 128 (delta 52)
-----> Clojure (Leiningen 2) app detected
-----> Installing OpenJDK 1.8... done
-----> Installing Leiningen
       Downloading: leiningen-2.6.1-standalone.jar
       Writing: lein script
-----> Building with Leiningen
       Running: lein with-profile production compile :all
       "Retrieving" "org/clojure/clojure/1.8.0/clojure-1.8.0.pom" "from" "central"
...
       "Retrieving" "ring/ring-jetty-adapter/1.4.0/ring-jetty-adapter-1.4.0.jar" "from" "clojars"
       "Retrieving" "ring/ring-servlet/1.4.0/ring-servlet-1.4.0.jar" "from" "clojars"
       Compiling helloworld.web
       2016-03-22 22:27:02.406:INFO::main: Logging initialized @8351ms
-----> Discovering process types
       Procfile declares types -> web
       Default process types for Clojure (Leiningen 2) -> web
-----> Compiled slug size is 67M

-----> Building Docker image
remote: Sending build context to Docker daemon 70.12 MB
Step 0 : FROM deis/slugrunner
# Executing 3 build triggers
Trigger 0, RUN mkdir -p /app
Step 0 : RUN mkdir -p /app
 ---> Running in 476207d4b615
Trigger 1, WORKDIR /app
Step 0 : WORKDIR /app
 ---> Running in bcde6c0b43ce
Trigger 2, ADD slug.tgz /app
Step 0 : ADD slug.tgz /app
 ---> d8a0a81b9ddc
Removing intermediate container 476207d4b615
Removing intermediate container bcde6c0b43ce
Removing intermediate container aac959a322ba
Step 1 : ENV GIT_SHA f1de81a9f9bb151b8843c1650baedece547a2429
 ---> Running in d4c4c226e9d7
 ---> 0eaa4fe602f3
Removing intermediate container d4c4c226e9d7
Successfully built 0eaa4fe602f3
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

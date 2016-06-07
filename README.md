# Clojure Quick Start Guide

This guide will walk you through deploying a Clojure application on Deis Workflow.

## Usage

```console
$ git clone https://github.com/deis/example-clojure-ring
$ cd example-clojure-ring
$ deis create
Creating Application... done, created haptic-duckling
Git remote deis added
remote available at ssh://git@deis-builder.deis.rocks:2222/haptic-duckling.git
$ git push deis master
Counting objects: 136, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (55/55), done.
Writing objects: 100% (136/136), 25.16 KiB | 0 bytes/s, done.
Total 136 (delta 55), reused 127 (delta 52)
Starting build... but first, coffee!
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
       2016-06-07 16:05:50.783:INFO::main: Logging initialized @4618ms
-----> Discovering process types
       Procfile declares types -> web
       Default process types for Clojure (Leiningen 2) -> web
-----> Compiled slug size is 67M
Build complete.
Launching App...
Done, haptic-duckling:v2 deployed to Deis

Use 'deis open' to view this application in your browser

To learn more, use 'deis help' or visit http://deis.io

To ssh://git@deis-builder.deis.rocks:2222/haptic-duckling.git
 * [new branch]      master -> master
$ curl http://haptic-duckling.deis.rocks
Powered by Deis
```

## Additional Resources

* [Get Deis](http://deis.io/get-deis/)
* [GitHub Project](https://github.com/deis/deis)
* [Documentation](http://docs.deis.io/)
* [Blog](http://deis.io/blog/)

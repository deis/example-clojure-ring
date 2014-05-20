# Clojure Quick Start Guide

This guide will walk you through deploying a Clojure application on Deis.

## Usage

    $ deis create
    Creating application... done, created uphill-crawfish
    Git remote deis added
    $ git push deis master
    Counting objects: 107, done.
    Delta compression using up to 8 threads.
    Compressing objects: 100% (42/42), done.
    Writing objects: 100% (107/107), 19.68 KiB | 0 bytes/s, done.
    Total 107 (delta 38), reused 107 (delta 38)
    -----> Clojure app detected
    -----> Installing OpenJDK 1.6...done
           Warning: no :min-lein-version found in project.clj; using 1.7.1.
    -----> Installing Leiningen
           Downloading: leiningen-1.7.1-standalone.jar
           To use Leiningen 2.x, add this to project.clj: :min-lein-version "2.0.0"
           Downloading: rlwrap-0.3.7
           Writing: lein script
    -----> Building with Leiningen
           Running: lein deps
           [...]
           Copying 22 files to /tmp/build/lib
    -----> Discovering process types
           Procfile declares types -> web
           Default process types for Clojure -> web
    -----> Compiled slug size is 62M
    -----> Building Docker image
    Uploading context 64.06 MB
    Uploading context
    Step 0 : FROM deis/slugrunner
     ---> 5567a808891d
    Step 1 : RUN mkdir -p /app
     ---> Using cache
     ---> 928145890a08
    Step 2 : ADD slug.tgz /app
     ---> 5d8f5d76e813
    Removing intermediate container 9a698dfde966
    Step 3 : ENTRYPOINT ["/runner/init"]
     ---> Running in f2d020bde4b6
     ---> 32a163ee8b6a
    Removing intermediate container f2d020bde4b6
    Successfully built 32a163ee8b6a
    -----> Pushing image to private registry

           Launching... done, v2

    -----> uphill-crawfish deployed to Deis
           http://uphill-crawfish.local.deisapp.com

           To learn more, use `deis help` or visit http://deis.io

    To ssh://git@local.deisapp.com:2222/uphill-crawfish.git
     * [new branch]      master -> master
    $ curl http://uphill-crawfish.local.deisapp.com
    Powered by Deis

## Additional Resources

* [Get Deis](http://deis.io/get-deis/)
* [GitHub Project](https://github.com/opdemand/deis)
* [Documentation](http://docs.deis.io/)
* [Blog](http://deis.io/blog/)

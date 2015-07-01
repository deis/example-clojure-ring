(defproject deis-helloworld "1.0.2-SNAPSHOT"
  :description "Deis Example Application"
  :url "https://github.com/deis/example-clojure-ring"
  :license {:name "Apache v2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [org.clojure/clojure "1.7.0"]
    [compojure "1.3.4"]
    [ring/ring-jetty-adapter "1.3.2"]
  ]
  :min-lein-version "2.0.0"
  :main ^:skip-aot helloworld.web
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

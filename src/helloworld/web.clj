(ns helloworld.web
    (:use ring.adapter.jetty))

(defn handler [request]
  {:status 200
     :headers {"Content-Type" "text/html"}
        :body "Powered by OpDemand"})

       


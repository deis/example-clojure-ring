(ns helloworld.web 
    (:use compojure.core [ring.adapter.jetty :only [run-jetty]] )
    (:require [compojure.route :as route]
              [compojure.handler :as handler]))


(defroutes main-routes
  ; what's going on

    (GET "/" [] (str "Powered by OpDemand" ) )
    (route/resources "/")
    (route/not-found "Page not found")   )


(def app
    (handler/api main-routes))
       
(defn -main [port]
    (run-jetty app {:port (Integer. port)}))

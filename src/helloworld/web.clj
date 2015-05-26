(ns helloworld.web
  (:use compojure.core [ring.adapter.jetty :only [run-jetty]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))

(def message (get (System/getenv) "POWERED_BY" "Deis"))

(defroutes main-routes
  (GET "/" [] (apply str "Powered by " message))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (handler/api main-routes))

(defn -main []
  (def port (get (System/getenv) "PORT" 5000))
  (run-jetty app {:port (Integer. port)}))

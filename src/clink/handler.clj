(ns clink.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] "Cheers!")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

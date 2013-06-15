(ns clink.handler
  (:use compojure.core)
  (:require [hiccup.page :as page])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(def welcome-view
  (page/html5 
   [:head 
    [:title "(defclink)"]
    ] 
   [:body 

    "Cheers!"
    (page/include-js "jquery/jquery.js")]))

(defroutes app-routes
  (GET "/" [] welcome-view)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))


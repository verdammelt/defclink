(ns clink.handler
  (:use compojure.core)
  (:require [hiccup.page :as page])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defn layout [title & body]
  (page/html5 
   [:head 
    [:title title]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
    (page/include-css "bootstrap/css/bootstrap.css")] 
   [:body body
    (page/include-js "jquery/jquery.js" "bootstrap/js/bootstrap.js")]))

(defn alert [type & body]
  [:div {:class (str "alert alert-" type)}
   [:button.close {:type "button" :data-dismiss "alert"} "&times;"]
   body])

(def welcome-view
  (layout "(defclink)" 
          [:div.navbar
           [:div.navbar-inner
            [:a.brand {:href "#"} "(defclink)"]
            [:ul.nav
             [:li.active [:a {:href "#"} "Home"]]
             [:li [:a {:href "#"} "About"]]]]]
          (alert "danger" 
                 [:strong "Warning"] 
                 " This website is incredibly (dangeously) unfinished")
          [:div.hero-unit 
           "Welcome to (defclink) the premiere remote glass-clinking website."
           ]
          ))

(defroutes app-routes
  (GET "/" [] welcome-view)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))


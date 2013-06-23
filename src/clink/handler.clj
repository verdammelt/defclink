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
   [:body [:div.container body]
    (page/include-js "jquery/jquery.js" "bootstrap/js/bootstrap.js")]))

(defn alert [type & body]
  [:div {:class (str "alert alert-" type)}
   [:button.close {:type "button" :data-dismiss "alert"} "&times;"]
   body])

(def welcome-view
  (layout "(defclink)" 

          (alert "danger" 
                 [:strong "Warning"] 
                 " This website is incredibly (dangeously) unfinished")

          [:div.page-header
           [:h1 "Welcome to (defclink)" 
            [:small " the premiere remote glass-clinking website."]]]

          [:div.row.span12
          [:div 
           [:p "Have you ever been having a drink and wanted to clink
           glasses but there was no one around?"]

           [:img.img-rounded.offset1 {:src "lonely-drink.jpg" :alt "So sad."}]

           [:p]
           [:p "Well " [:code "(defclink)"] " can help you. All you need is a
           drink and an internet connection."]

           [:p]
           [:p "Whenever you want a clink you can request one with a
           simple button press."  ]

           ]]
          ))

(defroutes app-routes
  (GET "/" [] welcome-view)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))


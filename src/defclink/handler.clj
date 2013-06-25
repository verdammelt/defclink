(ns defclink.handler
  (:use compojure.core)
  (:require [hiccup.page :as page]
            [hiccup.element :as element])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(def title "(defdrink)")
(def views ["about"])

(defn navbar [current-view]
  [:div.navbar.navbar-fixed-top
   [:div.navbar-inner
    [:a.brand {:href "/"} title]
    [:ul.nav
     (map (fn [view]
            (let [tag (if (= view current-view) :li.active :li) ]
              [tag [:a {:href (str "/" view)} (clojure.string/capitalize view)]]))
          views)]]])

(defn layout [current-view & body]
  (page/html5 
   [:head 
    [:title title]
    [:meta {:name "viewport" 
            :content "width=device-width, initial-scale=1.0"}]
    [:style "body { padding: 50px }"]
    (page/include-css "bootstrap/css/bootstrap.min.css" 
                      "bootstrap/css/bootstrap-responsive.min.css")
    ] 
   [:body 
    (navbar current-view)

    [:div.container body]

    (page/include-js "jquery/jquery.min.js" 
                     "bootstrap/js/bootstrap.min.js"
                     "js/cheers.js")
    (element/javascript-tag "defclink.cheers.cheers()")]))

(defn alert [type & body]
  [:div {:class (str "alert alert-" type)}
   [:button.close {:type "button" :data-dismiss "alert"} "&times;"]
   body])

(def welcome-view
  (layout "welcome" 
          (alert "danger" 
                 [:strong "Warning"] 
                 " This website is incredibly (dangeously) unfinished")

          [:div.page-header.span12
           [:h1 "Welcome to (defclink)!" 
            [:small " the premiere remote glass-clinking website."]]]

          [:div.row.span12
           [:p "Have you ever been having a drink and wanted to clink
           glasses but there was no one around?"]

           [:img.img-rounded {:src "lonely-drink.jpg" 
                                      :alt "So sad."}]

           [:p]
           [:p "Well " [:code "(defclink)"] " can help you. All you
           need is a drink and an internet connection."]

           [:p "Whenever you want a clink you can request one with a
           simple button press."  ]

           [:div.cheers "wait for it..."]]))

(def about-view
  (layout "about"
          [:div.row.span12 "So you want to know about (defdrink) do ya?"]
          [:div.row.span12 "version: " (System/getProperty "defclink.version")]))


(defroutes app-routes
  (GET "/" [] welcome-view)
  (GET "/about" [] about-view)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))


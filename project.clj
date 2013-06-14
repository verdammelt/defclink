(defproject clink "0.1.0-SNAPSHOT"
  :description "Clink glasses virtually"
  :url "http://clink.heroku.com"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.3"]
                 [ring/ring-jetty-adapter "1.1.0"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler clink.handler/app}
  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]]}})

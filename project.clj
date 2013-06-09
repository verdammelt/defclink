(defproject clink "0.1.0-SNAPSHOT"
  :description "clink glasses virtually"
  :url "http://clink.heroku.com"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler clink.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})

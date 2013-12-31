(defproject defclink "0.1.0-SNAPSHOT"
  :description "Clink glasses virtually"
  :url "http://clink.heroku.com"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2138"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.4"]
                 [ring/ring-jetty-adapter "1.2.1"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.8.8"]
            [lein-cljsbuild "1.0.1"]]
  :hooks [leiningen.cljsbuild]
  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]
                                  [midje "1.6.0"]]}}

  :cljsbuild {
              :builds [{:source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/js/cheers.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]
              }
  
  :ring {:handler defclink.handler/app})

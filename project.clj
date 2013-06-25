(defproject clink "0.1.0-SNAPSHOT"
  :description "Clink glasses virtually"
  :url "http://clink.heroku.com"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.3"]
                 [ring/ring-jetty-adapter "1.1.0"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.8.5"]
            [ccfontes/lein-cljsbuild "0.3.2-no-exit"]]
  :hooks [leiningen.cljsbuild]
  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]]}}

  :cljsbuild {
              :builds [{:source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/js/cheers.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}]
              }

    :ring {:handler clink.handler/app})

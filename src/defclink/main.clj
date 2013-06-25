(ns defclink.main
  (:use [defclink.handler :only [app]])
  (:use [ring.adapter.jetty :only [run-jetty]]))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))



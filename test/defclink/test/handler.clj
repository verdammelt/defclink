
(ns defclink.test.handler
  (:use midje.sweet
        ring.mock.request  
        defclink.handler))

(defn- get-url [url] (app (request :get url)))

(facts "defclink route handling"
       (fact "main route"
             (:status (get-url "/")) => 200
             (:body (get-url "/")) => (contains "Welcome to (defclink)!"))
       (fact "not-found route"
             (:status (get-url "/invalid")) => 404))

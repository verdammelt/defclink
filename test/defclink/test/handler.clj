
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

(defn- find-all-tag [tag html-vec]
  (let [tags (atom '())]
    (clojure.walk/postwalk 
     #(if (and (vector? %)
               (keyword? (first %))
               (= (name tag)
                  (first (clojure.string/split (name (first %)) #"\."))))
        (swap! tags conj %)
        %) html-vec)
    (reverse @tags)))

(defn- get-href-for-anchor [anchor-vec]
  (filter (complement nil?) (map :href anchor-vec)))

(defn- all-anchor-hrefs [html-vec]
  (flatten (map get-href-for-anchor (find-all-tag :a html-vec))))

(facts "navbar"
  (fact "contains link to main page"
    (all-anchor-hrefs (navbar "/")) => (contains "/")
    ))

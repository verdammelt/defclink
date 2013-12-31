
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
               (or (= (first %) tag)
                   (= (name tag)
                  (first (clojure.string/split (name (first %)) #"\.")))))
        (swap! tags conj %)
        %) html-vec)
    (reverse @tags)))

(defn- get-href-for-anchor [anchor-vec]
  (:href (second anchor-vec)))

(defn- get-text-for-anchor [anchor-vec]
  (nth anchor-vec 2))

(defn- all-anchor-hrefs [html-vec]
  (flatten (map get-href-for-anchor (find-all-tag :a html-vec))))

(defn- active-list-items [nav]
  (all-anchor-hrefs (find-all-tag :li.active nav)))

(facts "navbar"
  (fact "puts the title in the brand link"
    (first (map get-text-for-anchor
                (find-all-tag :a.brand
                              (navbar "the-title" "welcome" '())))) => "the-title")
  (fact "contains link to main page"
    (all-anchor-hrefs (navbar "title" "/" '())) => (contains "/"))
  (fact "marks the active view when found"
    (active-list-items (navbar "title" "about" '("about"))) => (list "/about")
    (active-list-items (navbar "title" "this" '("that"))) => empty?))

(fact "layout"
  (fact "sets up bootstrap properly"
    (layout "test" '()) => (contains "<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">")
    (layout "test" '()) => (contains "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"><link href=\"bootstrap/css/bootstrap-responsive.min.css\" rel=\"stylesheet\" type=\"text/css\">"))
  (fact "sets up jquery properly"
    (layout "test" '()) => (contains  "<script src=\"jquery/jquery.min.js\" type=\"text/javascript\"></script><script src=\"bootstrap/js/bootstrap.min.js\" type=\"text/javascript\"></script>"))

  (fact "lets user add scripts to the footer"
    (layout "test" '("body-div") '("footer-scripts")) =>
    #"body-div.*footer-scripts")
  ()
  )

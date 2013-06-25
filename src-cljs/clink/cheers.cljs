(ns clink.cheers)

(def jquery (js* "$"))

(defn ^:export cheers []
  (jquery (fn []
            (-> (jquery "div.cheers")
                (.html "Clojurescript says: Cheers!")))))

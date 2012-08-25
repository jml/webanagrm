(ns webanagrm.views.welcome
  (:require [webanagrm.views.common :as common]
            [webanagrm.anagrm :refer [smh-target-puzzle dictionary-file]]
            [clojure.java.io :refer [reader]]
            [noir.core :refer [defpage defpartial]]
            [noir.response :refer [json]]
            [hiccup.page :refer [include-js]]
            [hiccup.form :refer [label text-field form-to submit-button]]))


(defn solve-puzzle [word letter]
  (with-open [dict (reader dictionary-file)]
    (doall (filter (smh-target-puzzle word (first letter) 4) (line-seq dict)))))

;; XXX - handle empty results
;; XXX - ensure the compulsory letter is in the word
;;
;; XXX - really, the HTML here doesn't need to be in Clojure -- it's just
;; static HTML.  I wonder if there's a way to serve HTML via noir. (I guess I
;; could just make the front-end a separate app)

(defpartial puzzle-fields [{:keys [word letter]}]
  (label "word" "Letters in the puzzle: ")
  (text-field "word" word)
  (label "letter" "The compulsory letter: ")
  (text-field "letter" letter))

(defpage "/" {:as puzzle}
  (common/layout
   [:h1 "webanagrm"]
   [:p "Solve a classic 9 letter puzzle"]
   (form-to {:id "puzzle"} [:get "/"]
            (puzzle-fields puzzle)
            (submit-button "Solve the puzzle!"))
   [:div#solutions]
   (include-js "js/anagrm.js")))


;; XXX - does it stream? how can I make it stream?
(defpage [:get "/search"] {:keys [word letter]}
  (json (solve-puzzle word letter)))

(ns webanagrm.views.welcome
  (:require [webanagrm.views.common :as common]
            [webanagrm.anagrm :refer [smh-target-puzzle dictionary-file]]
            [clojure.java.io :refer [reader]]
            [noir.core :refer [defpage defpartial]]
            [noir.response :refer [json]]
            [hiccup.page :refer [include-css html5]]
            [hiccup.form :refer [label text-field form-to submit-button]]))


(defpartial puzzle-fields [{:keys [word letter]}]
  (label "word" "Letters in the puzzle: ")
  (text-field "word" word)
  (label "letter" "The compulsory letter: ")
  (text-field "letter" letter))


(defpage "/" []
  (common/layout
   [:h1 "webanagrm"]
   [:p "This will be a thing that solves Target puzzles"]))


;; XXX - form doesn't look super-great.
(defpage "/puzzle" []
  (common/layout
   (form-to [:get "/search"]
            (puzzle-fields {})
            (submit-button "Solve the puzzle!"))))


;; XXX - AJAX thing to render
;; XXX - does it stream? how can I make it stream?
(defpage [:get "/search"] {:keys [word letter]}
  (with-open [dict (reader dictionary-file)]
    (doall (json (filter (smh-target-puzzle word (first letter) 4) (line-seq dict))))))

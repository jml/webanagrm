(ns webanagrm.views.welcome
  (:require [webanagrm.views.common :as common]
            [webanagrm.anagrm :refer [smh-target-puzzle dictionary-file]]
            [clojure.java.io :refer [reader]]
            [noir.core :refer [defpage defpartial]]
            [noir.response :refer [json]]
            [hiccup.page :refer [include-css html5]]
            [hiccup.form :refer [label text-field form-to submit-button]]))


(defn solve-puzzle [word letter]
  (with-open [dict (reader dictionary-file)]
    (doall (filter (smh-target-puzzle word (first letter) 4) (line-seq dict)))))


(defpartial puzzle-fields [{:keys [word letter]}]
  (label "word" "Letters in the puzzle: ")
  (text-field "word" word)
  (label "letter" "The compulsory letter: ")
  (text-field "letter" letter))


(defpage "/" []
  (common/layout
   [:h1 "webanagrm"]
   [:p "This will be a thing that solves Target puzzles"]))


(defpartial named-list [name items]
  [:h2 name]
  [:ul (for [item items] [:li item])])


(defpartial solutions [{:keys [word letter]}]
  (named-list "Solutions" (solve-puzzle word letter)))


(defpage "/puzzle" {:as puzzle}
  (common/layout
   (form-to [:get "/puzzle"]
            (puzzle-fields puzzle)
            (submit-button "Solve the puzzle!"))
   (if (not (empty? puzzle))
     (solutions puzzle))))


;; XXX - AJAX thing to render
;; XXX - does it stream? how can I make it stream?
(defpage [:get "/search"] {:keys [word letter]}
  (json (solve-puzzle word letter)))

(ns webanagrm.views.welcome
  (:require [webanagrm.views.common :as common]
            [webanagrm.anagrm :refer [smh-target-puzzle dictionary-file]]
            [clojure.java.io :refer [reader]])
  (:use [noir.core :only [defpage]]
        [noir.response :only [json]]
        [hiccup.page :only [include-css html5]]))


(defpage "/" []
  (common/layout
   [:h1 "webanagrm"]
   [:p "This will be a thing that solves Target puzzles"]))


(defpage [:get "/search"] {:keys [word letter]}
  (with-open [dict (reader dictionary-file)]
    (doall (json (filter (smh-target-puzzle word (first letter) 4) (line-seq dict))))))

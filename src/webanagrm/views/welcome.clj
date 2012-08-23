(ns webanagrm.views.welcome
  (:require [webanagrm.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.page :only [include-css html5]]))


(defpage "/" []
  (common/layout
   [:h1 "webanagrm"]
   [:p "This will be a thing that solves Target puzzles"]))

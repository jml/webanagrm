(ns webanagrm.views.welcome
  (:require [webanagrm.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.page :only [include-css html5]]))


(defpage "/welcome" []
         (common/layout
           [:p "Welcome to webanagrm"]))


(defpage "/" []
  (common/layout [:h1 "This is my first page!"]))

(ns webanagrm.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css html5]])
  (:require [hiccup.bootstrap.page :refer [include-bootstrap fixed-layout]]))



(defpartial layout [& content]
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:title "webanagrm"]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
    [:meta {:name "description" :content ""}]
    [:meta {:name "author" :content ""}]
    (include-bootstrap)]
   [:body
    (fixed-layout content)]))

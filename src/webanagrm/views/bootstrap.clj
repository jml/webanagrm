(ns webanagrm.views.bootstrap
  (:use [noir.core :only [defpartial]]
        [hiccup.core :only [html]]
        [hiccup.page :only [include-css include-js html5]])
  (:require [hiccup.bootstrap.page :refer [include-bootstrap fixed-layout]]))


;; navbar from the bootstrap starter template
(defn navbar
  "Navigation bar from bootstrap starter template"
  ;; XXX: Parametrize with links
  [project-name]
  (html
   [:div.navbar.navbar-fixed-top
    [:div.navbar-inner
     [:div.container
      [:a.btn.btn-navbar {:data-toggle "collapse" :data-target ".nav-collapse"}
       [:span.icon-bar]
       [:span.icon-bar]
       [:span.icon-bar]]
      [:a.brand {:href "#"} project-name]
      [:div.nav-collapse
       [:ul.nav
        [:li.active [:a {:href "#"} "Home"]]
        [:li [:a {:href "#about"} "About"]]
        [:li [:a {:href "#contact"} "Contact"]]]]
      "<!--/.nav-collapse -->"
      ]]]))


(defonce ie-html5-shim
  (str "<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->"
       "<!--[if lt IE 9]>"
       "<script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>"
       " <![endif]-->"))


;; bootstrap starter template layout
(defpartial layout [& content]
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:title "webanagrm"]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
    [:meta {:name "description" :content ""}]
    [:meta {:name "author" :content ""}]
    (include-js "https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js")
    (include-bootstrap)
    ie-html5-shim
    [:style "body { padding-top: 60px; padding-bottom: 40px;}"]
    ]
   [:body
    (navbar "webanagrm")
    (fixed-layout content)]))

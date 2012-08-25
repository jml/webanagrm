(ns webanagrm.views.api
  (:require [noir.core :refer [defpage]]
            [noir.response :refer [json]]
            [webanagrm.anagrm :refer [solve-puzzle]]))


;; XXX - does it stream? how can I make it stream?
(defpage [:get "/search"] {:keys [word letter]}
  (json (solve-puzzle word letter)))

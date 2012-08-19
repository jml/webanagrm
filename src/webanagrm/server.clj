(ns webanagrm.server
  (:require [noir.server :as server]
            [hiccup.bootstrap.middleware :refer [wrap-bootstrap-resources]]))

(server/load-views-ns 'webanagrm.views)

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/add-middleware wrap-bootstrap-resources)
    (server/start port {:mode mode
                        :ns 'webanagrm})))



(ns client.main
  (:require [respo.core
             :refer
             [render! clear-cache! falsify-stage! render-element gc-states!]]
            [client.comp.container :refer [comp-container]]
            [cljs.reader :refer [read-string]]))

(defonce ref-states (atom {}))

(defonce ref-store (atom ""))

(defn dispatch! [op op-data] (reset! ref-store op-data))

(defn render-app! []
  (let [target (.querySelector js/document "#app")]
    (render! (comp-container @ref-store) target dispatch! ref-states)))

(defn listen-to-errors! []
  (.log js/console "Listen to errors...")
  (.addEventListener js/window "error" (fn [error] (dispatch! :error error.error.stack))))

(def ssr-stages
  (let [ssr-element (.querySelector js/document "#ssr-stages")
        ssr-markup (.getAttribute ssr-element "content")]
    (read-string ssr-markup)))

(defn -main! []
  (enable-console-print!)
  (if (not (empty? ssr-stages))
    (let [target (.querySelector js/document "#app")]
      (falsify-stage!
       target
       (render-element (comp-container @ref-store ssr-stages) ref-states)
       dispatch!)))
  (render-app!)
  (add-watch ref-store :gc (fn [] (gc-states! ref-states)))
  (add-watch ref-store :changes render-app!)
  (add-watch ref-states :changes render-app!)
  (listen-to-errors!)
  (println "App started!"))

(defn on-jsload! [] (clear-cache!) (render-app!) (println "Code updated."))

(set! (.-onload js/window) -main!)

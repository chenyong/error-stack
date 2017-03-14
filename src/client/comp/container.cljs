
(ns client.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.alias :refer [create-comp div button span pre]]
            [respo.comp.space :refer [comp-space]]
            [respo.comp.text :refer [comp-text]]))

(def style-error
  {:background-color (hsl 0 0 97), :padding 8, :font-family "Menlo", :font-size 13})

(defn on-break [e dispatch!] (js/X))

(def style-container {:padding 16})

(def comp-container
  (create-comp
   :container
   (fn [store]
     (fn [state mutate!]
       (div
        {:style (merge ui/global style-container)}
        (div
         {}
         (comp-text "Click to generate error" nil)
         (comp-space 8 nil)
         (button {:style ui/button, :event {:click on-break}} (comp-text "Do it" nil)))
        (pre {:style style-error, :attrs {:inner-text store}}))))))

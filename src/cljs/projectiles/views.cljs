(ns projectiles.views
  (:require
   [re-frame.core :as re-frame]
   [projectiles.subs :as subs]
   [clojure.string :refer [join]]
   [projectiles.events :as events]
   ))

(def dt 0.1)

(def g 9.8)

(defn sfx [s v]
  (let [[six siy] s
        [vix viy] v]
    (+ six
       (* vix dt))))

(defn sfy [s v]
  (let [[six siy] s
        [vix viy] v]
    (+ siy
       (* viy dt)
       (- (* 0.5 g dt dt)))))

(defn vfx [s v]
  (let [[six siy] s
        [vix viy] v]
    vix))

(defn vfy [s v]
  (let [[six siy] s
        [vix viy] v]
    (- viy (* g dt))))

(defn sim-trajectory [si vi]
  (loop [s si
         v vi
         result []]
    (if (>= (second s) 0)
      (recur [(sfx s v) (sfy s v)]
             [(vfx s v) (vfy s v)]
             (conj result [s v]))
      result)))

(defn projectile-view []
  (let [[s v] @(re-frame/subscribe [::subs/initial-state])
        trajectory (sim-trajectory s v)
        s-list (for [state trajectory] (first state))
        v-list (for [state trajectory] (second state))]
    [:div
     [:svg {:width "100%" :height 400 :stroke "black" :fill "none" :transform "scale(1,-1)"}
      [:path {:d (str "M " (first s) " " (second s) " L " (join " " (flatten s-list)))}]]]))

(defn control-view []
  [:div
   [:div
    [:p "Initial Position"]
    [:button {:on-click #(re-frame/dispatch [::events/dec-six])} "←"]
    [:button {:on-click #(re-frame/dispatch [::events/inc-siy])} "↑"]
    [:button {:on-click #(re-frame/dispatch [::events/dec-siy])} "↓"]
    [:button {:on-click #(re-frame/dispatch [::events/inc-six])} "→"]]
   [:div
    [:p "Initial Velocity"]
    [:button {:on-click #(re-frame/dispatch [::events/dec-vix])} "←"]
    [:button {:on-click #(re-frame/dispatch [::events/inc-viy])} "↑"]
    [:button {:on-click #(re-frame/dispatch [::events/dec-viy])} "↓"]
    [:button {:on-click #(re-frame/dispatch [::events/inc-vix])} "→"]]])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [projectile-view]
     [control-view]]))



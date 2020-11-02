(ns projectiles.events
  (:require
   [re-frame.core :as re-frame]
   [projectiles.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   ))


(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(re-frame/reg-event-db
 ::inc-six
 (re-frame/path [:initial-state])
 (fn-traced [b v]
            (update-in b [0 0] inc)))

(re-frame/reg-event-db
 ::dec-six
 (re-frame/path [:initial-state])
 (fn-traced [b v]
            (update-in b [0 0] dec)))

(re-frame/reg-event-db
 ::inc-siy
 (re-frame/path [:initial-state])
 (fn-traced [b v]
            (update-in b [0 1] inc)))

(re-frame/reg-event-db
 ::dec-siy
 (re-frame/path [:initial-state])
 (fn-traced [b v]
            (update-in b [0 1] dec)))

(re-frame/reg-event-db
 ::inc-vix
 (re-frame/path [:initial-state])
 (fn-traced [b v]
            (update-in b [1 0] inc)))

(re-frame/reg-event-db
 ::dec-vix
 (re-frame/path [:initial-state])
 (fn-traced [b v]
            (update-in b [1 0] dec)))

(re-frame/reg-event-db
 ::inc-viy
 (re-frame/path [:initial-state])
 (fn-traced [b v]
            (update-in b [1 1] inc)))

(re-frame/reg-event-db
 ::dec-viy
 (re-frame/path [:initial-state])
 (fn-traced [b v]
            (update-in b [1 1] dec)))


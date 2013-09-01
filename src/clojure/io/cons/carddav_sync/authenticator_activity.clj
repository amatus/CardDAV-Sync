(ns io.cons.carddav_sync.authenticator_activity
  (:use [neko.activity :only [defactivity set-content-view!]]
        [neko.threading :only [on-ui]]
        [neko.ui :only [make-ui]])
  (:gen-class
   :extends android.app.Activity
   :exposes-methods {onCreate superOnCreate}))

(defn -onCreate
  [this savedInstanceState]
  (.superOnCreate this savedInstanceState)
  (on-ui
   (set-content-view! this
    (make-ui [:linear-layout {}
              [:text-view {:text "Hello from Clojure!"}]]))))

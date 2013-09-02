(ns io.cons.carddav_sync.authenticator_activity
  (:use [neko.activity :only [defactivity set-content-view!]]
        [neko.resource :only [get-resource]]
        [neko.threading :only [on-ui]]
        [neko.ui :only [make-ui]]
        [neko.ui.mapping :only [defelement]])
  (:import android.text.InputType
           android.widget.LinearLayout)
  (:gen-class
   :extends android.app.Activity
   :exposes-methods {onCreate superOnCreate}))

(defelement :frame-layout
            :classname android.widget.FrameLayout
            :inherits :view-group)

(defelement :scroll-view
            :classname android.widget.ScrollView
            :inherits :frame-layout)

(defn -onCreate
  [this savedInstanceState]
  (.superOnCreate this savedInstanceState)
  (on-ui
   (set-content-view! this
    (make-ui [:scroll-view {}
              [:linear-layout {:orientation :vertical}
               [:edit-text {:hint (get-resource :string :prompt-user)}]
               [:edit-text {:hint (get-resource :string :prompt-password)
                            :input-type
                            (bit-or InputType/TYPE_CLASS_TEXT
                                    InputType/TYPE_TEXT_VARIATION_PASSWORD)}]
               [:edit-text {:hint (get-resource :string :prompt-url)
                            :input-type
                            (bit-or InputType/TYPE_CLASS_TEXT
                                    InputType/TYPE_TEXT_VARIATION_URI)}]
               ]]))))

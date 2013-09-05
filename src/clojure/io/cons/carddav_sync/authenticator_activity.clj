(ns io.cons.carddav_sync.authenticator_activity
  (:use io.cons.carddav_sync.log
        [neko.activity :only [defactivity set-content-view!]]
        [neko.resource :only [get-resource]]
        [neko.threading :only [on-ui]]
        [neko.ui :only [make-ui]]
        [neko.ui.mapping :only [defelement]])
  (:import android.text.InputType
           android.widget.LinearLayout)
  (:gen-class
   :extends android.app.Activity
   :exposes-methods {onCreate superOnCreate}))

(gen-class
  :name io.cons.carddav_sync.login_task
  :extends android.os.AsyncTask
  :prefix "login-")

(defn login-onPreExecute
  [this]
  (log-i "onPreExecute"))

(defn login-doInBackground
  [this args]
  (let [[user password url] args]
    (log-i (str "doInBackground " user " " password " " url))))

(declare ^android.widget.EditText user)
(declare ^android.widget.EditText password)
(declare ^android.widget.EditText url)

(defn login
  [_]
  (let [user (.toString (.getText user))
        password (.toString (.getText password))
        url (.toString (.getText url))]
    (.execute (io.cons.carddav_sync.login_task.)
              (into-array Object [user password url]))))

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
               [:edit-text {:def 'io.cons.carddav_sync.authenticator_activity/user
                            :hint (get-resource :string :prompt-user)}]
               [:edit-text {:def 'io.cons.carddav_sync.authenticator_activity/password
                            :hint (get-resource :string :prompt-password)
                            :input-type
                            (bit-or InputType/TYPE_CLASS_TEXT
                                    InputType/TYPE_TEXT_VARIATION_PASSWORD)}]
               [:edit-text {:def 'io.cons.carddav_sync.authenticator_activity/url
                            :hint (get-resource :string :prompt-url)
                            :input-type
                            (bit-or InputType/TYPE_CLASS_TEXT
                                    InputType/TYPE_TEXT_VARIATION_URI)}]
               [:button {:text (get-resource :string :button-sign-in)
                         :on-click login}]
               ]]))))

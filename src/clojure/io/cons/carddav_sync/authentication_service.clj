(ns io.cons.carddav_sync.authentication_service
  (:use io.cons.carddav_sync.log)
  (:require io.cons.carddav_sync.authenticator)
  (:gen-class
   :extends android.app.Service
   :exposes-methods {onCreate superOnCreate
                     onDestroy superOnDestroy}
   :state state
   :init init))

(defn -init
  []
  [[] (atom nil)])

(defn -onCreate
  [this]
  (.superOnCreate this)
  (log-i "Authentication Service created")
  (swap! (.state this)
         #(when-not %
            (io.cons.carddav_sync.authenticator. this))))

(defn -onDestroy
  [this]
  (.superOnDestroy this)
  (log-i "Authentication Service destroyed"))

(defn -onBind
  [this intent]
  (log-i "Authentication onBind")
  (.getIBinder @(.state this)))

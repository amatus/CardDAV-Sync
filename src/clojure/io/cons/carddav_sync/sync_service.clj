(ns io.cons.carddav_sync.sync_service
  (:use io.cons.carddav_sync.log)
  (:import io.cons.carddav_sync.sync_adapter)
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
  (log-i "Service created")
  (swap! (.state this)
         #(when-not % (sync_adapter. (.getApplicationContext this) true))))

(defn -onDestroy
  [this]
  (.superOnDestroy this)
  (log-i "Service destroyed"))

(defn -onBind
  [this intent]
  (.getSyncAdapterBinder @(.state this)))

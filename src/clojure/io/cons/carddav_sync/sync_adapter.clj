(ns io.cons.carddav_sync.sync_adapter
  (:use io.cons.carddav_sync.log)
  (:gen-class
   :extends android.content.AbstractThreadedSyncAdapter
   :state state
   :init init))

(defn -init
  ([context autoInitialize]
   [[context autoInitialize] nil])
  ([context autoInitialize allowParallelSyncs]
   [[context autoInitialize allowParallelSyncs] nil]))

(defn -onPerformSync
  [this account extras authority provider syncResult]
  (log-i "Started sync"))

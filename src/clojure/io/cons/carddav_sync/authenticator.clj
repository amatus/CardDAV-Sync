(ns io.cons.carddav_sync.authenticator
  (:use io.cons.carddav_sync.log)
  (:gen-class
   :extends android.accounts.AbstractAccountAuthenticator
   :state state
   :init init))

(defn -init
  [context]
  [[context] nil])

(defn -addAccount
  [this response accountType authTokenType requiredFeatures options]
  (log-i "addAccount")
  nil)

(defn -confirmCredentials
  [this response account options]
  (log-i "confirmCredentials")
  nil)

(defn -editProperties
  [this response accountType]
  (log-i "editProperties")
  nil)

(defn -getAuthToken
  [this response account authTokenType options]
  (log-i "getAuthToken")
  nil)

(defn -getAuthTokenLabel
  [this authTokenType]
  (log-i "getAuthTokenLabel")
  nil)

(defn -hasFeatures
  [this response account features]
  (log-i "hasFeatures")
  nil)

(defn -updateCredentials
  [this response account authTokenType options]
  (log-i "updateCredentials")
  nil)

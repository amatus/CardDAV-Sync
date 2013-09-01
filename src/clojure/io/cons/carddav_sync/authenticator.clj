(ns io.cons.carddav_sync.authenticator
  (:use io.cons.carddav_sync.log)
  (:import android.accounts.AccountManager
           android.content.Intent
           android.os.Bundle
           io.cons.carddav_sync.authenticator_activity)
  (:gen-class
   :extends android.accounts.AbstractAccountAuthenticator
   :state context
   :init init))

(defn -init
  [context]
  [[context] context])

(defn -addAccount
  [this response accountType authTokenType requiredFeatures options]
  (log-i "addAccount")
  (let [intent (Intent. (.context this) authenticator_activity)
        bundle (Bundle.)]
    (.putExtra intent AccountManager/KEY_ACCOUNT_AUTHENTICATOR_RESPONSE
               response)
    (.putParcelable bundle AccountManager/KEY_INTENT intent)
    bundle))

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

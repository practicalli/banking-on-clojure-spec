(ns practicalli.banking-on-clojure
  (:gen-class))

;; failing function
#_(defn register-account-holder
    "Register a new customer with the bank
  Arguments:
  - hash-map of customer-details
  Return:
  - hash-map of an account-holder (adds account id)"

    [customer-details]

    customer-details)

;; passing function

(defn register-account-holder
  "Register a new customer with the bank
  Arguments:
  - hash-map of customer-details
  Return:
  - hash-map of an account-holder (adds account id)"
  [customer-details]

  (assoc customer-details
         :practicalli.bank-account-spec/account-id
         (java.util.UUID/randomUUID)))

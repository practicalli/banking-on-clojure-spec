(ns practicalli.banking-on-clojure-test
  (:require
   [clojure.test :refer [deftest is testing]]

   [clojure.spec.alpha :as spec]
   [clojure.spec.test.alpha :as spec-test]
   [clojure.spec.gen.alpha :as spec-gen]

   [practicalli.banking-on-clojure :as SUT]
   [practicalli.banking-specifications]))


;; Generative data
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; requires custom generators as custom predicates are used?
(def customer-mock
  (spec-gen/generate (spec/gen :practicalli.banking-specifications/customer-details)))


(def account-holder-mock
  (spec-gen/generate (spec/gen :practicalli.banking-specifications/account-holder)))


(deftest register-account-holder-test
  (testing "Basic registration - happy path"

    (is (= (set (keys (SUT/register-account-holder customer-mock)))
           (set (keys account-holder-mock))))

    (is (spec/valid? :practicalli.banking-specifications/account-holder
                     (SUT/register-account-holder customer-mock)))

    )) ;; End of register-account-holder-test


(def customer-mock
  {:first-name          (spec-gen/generate (spec/gen :practicalli.banking-specifications/first-name))
   :last-name           (spec-gen/generate (spec/gen :practicalli.banking-specifications/last-name))
   :email-address       (spec-gen/generate (spec/gen :practicalli.banking-specifications/email-address)) ;; needs a custom generator
   :residential-address (spec-gen/generate (spec/gen :practicalli.banking-specifications/residential-address))
   :postal-code         (spec-gen/generate (spec/gen :practicalli.banking-specifications/post-code))
   :social-security-id  (spec-gen/generate (spec/gen :practicalli.banking-specifications/social-security-id-uk))})

(spec/gen :practicalli.banking-specifications/email-address)

customer-mock

#_(def customer-mock
    {:first-name          "Jenny"
     :last-name           "Jetpack"
     :email-address       "jenny@jetpack.org"
     :residential-address "42 meaning of life street, Earth"
     :postal-code         "AB3 0EF"
     :social-security-id  "123456789"})
;; => #'practicalli.banking-on-clojure-test/customer-mock


(def account-holder-mock
  {:acount-id           #uuid "97bda55b-6175-4c39-9e04-7c0205c709dc"
   :first-name          "Jenny"
   :last-name           "Jetpack"
   :email-address       "jenny@jetpack.org"
   :residential-address "42 meaning of life street, Earth"
   :postal-code         "AB3 0EF"
   :social-security-id  "123456789"})



(deftest register-account-holder-test
  (testing "Basic registration - happy path"
    (is (= (set (keys (SUT/register-account-holder customer-mock)))
           (set (keys account-holder-mock))))))

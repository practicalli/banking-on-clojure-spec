;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Specifications for banking on clojure application
;;
;; Author: practicalli
;;
;; Description:
;; Data and function specifications using clojure.spec.alpha
;; and instrumentation helper functions
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns practicalli.banking-specifications
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as spec-gen]
            [clojure.spec.test.alpha :as spec-test]

            [clojure.string]

            [practicalli.specifications]
            [practicalli.banking-on-clojure :as SUT]))



;; Banking data specifications
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(spec/def ::first-name string?)
(spec/def ::last-name string?)
(spec/def ::email-address string?)
#_(spec/def ::email-address (spec/and string? #(clojure.string/includes? % "@")))
#_(spec/def ::email-address
    (spec/and string?
              #(re-matches #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$"
                           %)))


;; residential address values
(spec/def ::house-name-number (spec/or :string string?
                                       :number int?))
(spec/def ::street-name string?)
(spec/def ::post-code string?)
(spec/def ::county string?)
(spec/def ::country :practicalli.specifications/countries-of-the-world)

(spec/def ::residential-address (spec/keys :req [::house-name-number ::street-name ::post-code]
                                           :opt [::county ::country]))



;; Social security id specifications - not working reliably with built in generators
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
#_(defn social-security-number-usa? [value] (= 9 (count value)))
#_(defn social-security-number-uk? [value] (= 11 (count value)))

;; Social Security number - one of the regional specifications
#_(spec/def ::social-security-id-usa (spec/and string? social-security-number-usa?))

#_(spec/def ::social-security-id-uk (spec/and string? social-security-number-uk?))


(spec/def ::social-security-id-uk string?)
(spec/def ::social-security-id-usa string?)

(spec/def ::social-security-id (spec/or ::social-security-id-uk
                                        ::social-security-id-usa))

;; composite customer details specification
(spec/def ::customer-details
  (spec/keys
    :req [::first-name ::last-name ::email-address ::residential-address ::social-security-id]))


;; Account holder values
(spec/def ::account-id uuid?)

;; Account holder - composite specification
(spec/def ::account-holder
  (spec/keys
    :req [::account-id ::first-name ::last-name ::email-address ::residential-address ::social-security-id]))




;; Banking function specifications
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(spec/fdef SUT/register-account-holder
  :args (spec/cat :customer ::customer-details)
  :ret ::account-holder)


;; Banking function instrumentation
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(spec-test/instrument `SUT/register-account-holder)


;; test the instrumentation of the function
;; use bad data, should return spec error

#_(SUT/register-account-holder {})

;; Use specs to generate test data, should evaluate correctly
#_ (SUT/register-account-holder
     (spec-gen/generate
       (spec/gen ::customer-details)))


#_(spec-test/unstrument `register-account-holder)


;; spec check
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(spec-test/check `register-account-holder)

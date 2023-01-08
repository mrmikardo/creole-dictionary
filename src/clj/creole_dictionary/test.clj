(ns creole-dictionary.test
  (:require  [clojure.test :as t]
             [creole-dictionary.core :as core]
             [creole-dictionary.samples :as samples])
  (:import   [creole_dictionary.core DictionaryEntry]))

;; Test parser

;; Simple example
(t/is (= (core/entry-from-hickory samples/simple)
         (DictionaryEntry. "abatwa"
                           ["n."]
                           "Slaughterhouse; abattoir"
                           [{:example "Ye menen bèf-ye pou tchwe ye dan labatwa."
                             :translation "They took the bulls to slaughter them in the slaughterhouse."
                             :attestation "CA"}])))
;; Simple example - as vector
(t/is (= (mapv core/entry-from-hickory samples/simple)
         [(DictionaryEntry. "abatwa"
                           ["n."]
                           "Slaughterhouse; abattoir"
                           [{:example "Ye menen bèf-ye pou tchwe ye dan labatwa."
                             :translation "They took the bulls to slaughter them in the slaughterhouse."
                             :attestation "CA"}])]))
;; Complex example
;; TODO
;;

(ns creole-dictionary.test
  (:require  [clojure.test :as t]
             [creole-dictionary.core :as core]
             [creole-dictionary.samples :as samples])
  (:import   [creole_dictionary.core DictionaryEntry]))

;; Simple example
;; "<p>abatwa <em>n.</em> (PC); labatwa (CA). Slaughterhouse; abattoir. <em>Ye menen bèf-ye pou tchwe ye dan labatwa.</em> They took the bulls to slaughter them in the slaughterhouse. (CA)</p>")
(t/is (= (core/entry-from-hickory samples/simple)
         (DictionaryEntry. "abatwa"
                           ["n."]
                           ["labatwa (CA)"]
                           "Slaughterhouse; abattoir"
                           [{:example "Ye menen bèf-ye pou tchwe ye dan labatwa."
                             :translation "They took the bulls to slaughter them in the slaughterhouse."
                             :attestation "CA"}])))

;; Test can parse variants -- from a sample with many variants
;;
(t/is (= (core/variants (core/useful-fragment samples/multiple-variants)) [
                                                                           "labitid (CA; PC)"
                                                                           "abitchid, abitchud (CA)"
                                                                           "labitchid, abitid, abitye, abitche (NE)"
                                                                           "labitud (PC)"
                                                                           ]))

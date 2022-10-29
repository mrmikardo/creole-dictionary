(ns creole-dictionary.core
  (:require [hickory.core :as h]
            [clojure.string :as str]))

;;
;; Records & constants
;;

(defrecord DictionaryEntry [headword part-of-speech variations translation examples])

;; TODO a neat way of pulling the attestation code out of the example would be to
;; map the variant-codes into a set of RegEx patterns that could be lazily applied
;; to the example.
(def variant-codes
  ["gen."
   "AN"
   "BT"
   "CA"
   "DC"
   "DT 77"
   "GY"
   "HW"
   "MO 60"
   "MO 69"
   "MO 72"
   "NE"
   "PC"
   "ST"
   "BD"
   "BI"
   "BO"
   "DU"
   "FO 1887"
   "GC1"
   "GC2"
   "JR"
   "KB"
   "LA"
   "MO"
   "ME"
   "ME 90"
   "ME 91"
   "PE"
   "TM"
   "TN"
   "TP"
   "WO"])

(def parts-of-speech
  ["adj.phr."
   "art.indef."
   "adj.poss."
   "adj."
   "adv.phr."
   "adv."
   "art."
   "coll."
   "conj."
   "def."
   "fig."
   "impers."
   "indef."
   "int."
   "interr."
   "n.phr."
   "n.vulg."
   "n.pl."
   "n."
   "neg."
   "onom."
   "past part."
   "pej."
   "pl."
   "place n."
   "prep.phr."
   "prep."
   "pron."
   "pron.emph."
   "pron.indef."
   "pron.poss."
   "prop.n."
   "rel.pro."
   "sing."
   "v.aux."
   "v.cop."
   "v.intr."
   "v.mod."
   "v.refl."
   "v.tr."
   "v.phr."
   "vulg."
   "v."
   "preverbal_marker"
   "place_n."])

;;
;; Methods
;;

(defn variations
  "Takes a useful fragment (see below) and pulls out variations.

  Variations are stored as a vector of maps, which contain the
  variation and the place of attestation (see place codes above).

  If there are no variations, returns an empty vector."
  [useful-fragment]
  (let [headword      (first useful-fragment)
        variants      (-> (nth useful-fragment 2)
                          (str/split #"\.")
                          (first)
                          (str/split #";")
                          (str/trim))
        first-variant (str headword " " (first variants))]
    first-variant))

(defn translation
  "Takes a useful fragment (see below) and pulls out translation."
  [useful-fragment]
  (-> (nth useful-fragment 2)
      (str/split #"\.")
      (second)
      (str/trim)))

(defn- parse-example
  [example]
  (let [parsed-ex     (-> example
                          (first)
                          (:content)
                          (first))
        trans         (str/trim (last example))
        attestation   (first (re-find #"\([A-Z][A-Z]( [0-9]+)?\)|gen\." trans))]  ;; TODO strip parens off of attestation code.
    {:example parsed-ex
     :translation trans  ;; TODO drop the attestation code from the trans.
     :attestation attestation}))

(defn examples-and-attestations
  "Takes a useful fragment (see below) and returns a map of examples and the code/place of attestation."
  [useful-fragment]
  (-> (drop 3 useful-fragment)  ;; Anything after the 3rd element is an examples. There can be many examples per headword.
      (mapv parse-example)))

(defn entry-from-hickory
  "Takes a fragment parsed into hickory and munges to a DictionaryEntry."
  [hickory-fragment]
  (let [useful-fragment (-> hickory-fragment
                            (:content)
                            (first)
                            (:content)
                            (second)
                            (:content)
                            (first)
                            (:content))
        headword        (str/trim (first useful-fragment))
        part-of-speech  (:content (second useful-fragment))  ;; TODO convert POS strings to something more meaningful.
        variations      (variations useful-fragment)
        translation     (translation useful-fragment)
        examples        (examples-and-attestations useful-fragment)]
    (DictionaryEntry. headword part-of-speech variations translation examples)))

(defn parse [fragment]
  (-> (h/parse fragment)
      (h/as-hickory)
      (entry-from-hickory))
)

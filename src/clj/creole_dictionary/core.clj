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

;; TODO rename to `variants`.
(defn variations
  "Takes a useful fragment (see below) and pulls out variations.

  Variations are stored as a vector of maps, which contain the
  variation and the place of attestation (see place codes above).

  If there are no variations, returns an empty vector."
  [useful-fragment]
  (let [headword      (str/trim (first useful-fragment))
        variants      (-> (nth useful-fragment 2)
                          (str/split #"\.")
                          (first)
                          (str/split #";")
                          (str/trim))
        first-variant (str headword " " (first variants))]
    first-variant))  ;; TODO return all variants.

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
        match         (re-seq #"(?s)[\sA-Za-z\.,'’;:]+|\(.*\)" (second example))
        trans         (str/trim (first match))
        attestation   (str/replace (second match)  #"\(|\)" "")]
    {:example parsed-ex
     :translation trans
     :attestation attestation}))

(defn examples-and-attestations
  "Takes a useful fragment (see below) and returns a map of examples and the code/place of attestation.

  Each example is given in Creole with an English translation."
  [useful-fragment]
  (->> (drop 3 useful-fragment)  ;; Anything after the 3rd element is an example. There may be multiple examples per headword.
       (partition 2)  ;; Group each example with its English rendering.
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
        ;;variations      (variations useful-fragment)  ;; TODO fix this.
        translation     (translation useful-fragment)
        examples        (examples-and-attestations useful-fragment)]
    (DictionaryEntry. headword part-of-speech variations translation examples)))

;; TODO it would be nicer just to do this in the boey of `entry-from-hickory`
;; using `letfn`.
(defn parse [fragment]
  (map entry-from-hickory fragment))

;;
;; Main entry point
;;

(def dictionary-file "/Users/jackie/Documents/creole_dictionary/src/data/dlc.html")

(defn load-dictionary
  "Returns a vector of maps where each map is a complete dictionary entry."
  []
  (let [data (-> (slurp dictionary-file)
                 (h/parse)
                 (h/as-hickory))]
    ;; Although the dictionary HTML isn't correct HTML (doesn't feature e.g. enclosing
    ;; <html> or <body> tags, amongst others), hickory automatically adds in a bunch
    ;; of meta tags that we strip out below.
    (-> data
        (second)
        (second)
        (first)
        (:content)
        (second)
        (:content))))

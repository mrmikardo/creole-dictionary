(ns creole-dictionary.samples
  (:require [hickory.core :as h]))

;;
;; Samples from the Creole dictionary, parsed into hickory.
;;

;; A simple sample has only one sense and a single example.
(def simple
  (-> (h/parse
       "<p>abatwa <em>n.</em> (PC); labatwa (CA). Slaughterhouse; abattoir. <em>Ye menen bèf-ye pou tchwe ye dan labatwa.</em> They took the bulls to slaughter them in the slaughterhouse. (CA)</p>")
      (h/as-hickory)))

;; A sample with multiple usage examples.
(def multiple-examples
  (-> (h/parse
       "<p>aflije <em>adj.</em> (CA). Paralyzed, crippled; paralysé, estropié. <em>Li tou aflije. Li pa kapab sèrvi so lamen. Li pa kapab leve. Aryen travay pa.</em> He is completely paralyzed. He can’t use his hands. He can’t stand up. Nothing works. (CA); <em>Mo okipe apre mo pèr. Li aflije.</em> I take care of my dad. He’s crippled. (CA)</p>")
      (h/as-hickory)))

;; A sample containing usage examples dated prior to 1960. In the print dictionary, such examples
;; are separated from more modern ones with the use of a "lonzenge" or diamond-like symbol.
(def pre-1960-examples
  ())

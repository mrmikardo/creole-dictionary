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

;; Oliver describes this as "the most complex" entry in the dictionary o_0
(def complex
  (-> (h/parse
       "<p>a<sup>2</sup> <em>prep.</em> (gen.; MO 60); o (NE). 1. [Time]. At; à<em>. Nou komonch a set-er le maten.</em> We start at seven o’clock in the morning. (NE); <em>A mo-tchen laj.</em> At my age. (NE) <em>♦ Apepre a midi, nonm-la rive.</em> Around noon, the man arrived. (JR) 2. [Location]. At, in; a, dans, chez. <em>A ki magazen lantour isit moun-ye se achte lez òt zafè?</em> At what store around here did people used to buy other things? (PC); <em>Se pa move kom sa a Sesilya, me a Pon Bro...</em> It’s not bad like that in Cecilia, but in Breaux Bridge... (NE); <em>Mo jwenn avek li o Fontno e Gidri.</em> I met him at Fontenot’s and Guidry’s place. (NE) <em>♦Li taché patte desrat-la à soquenne lapatte.</em> He tied the rat’s paw to his own paw. (BD); <em>Com yé va ri quand yé va oi nou à Caddo.</em> How they will laugh when they see us in Caddo. (T32) 3. [Direction]. To, toward, on; à, vers. <em>Li kouri a lekol.</em> He goes to school. (NE); <em>Mo te mene sa laba o chalon.</em> I had brought that down to the boat. (NE) ♦<em>Et cher ti nange la halé fourmi à ter.</em> And the dear little angel pulled the ant towards land. (T18) 4. [Origin]. Of, from; de. <em>Le gayar a Park.</em> The folks from Parks. (NE); <em>Ye te sorti a la Frons.</em> They had come from France. (NE) 5. [Beneficiary, Indirect Object]. To; a. <em>Mo gen pou mene en SIX-PACK a Msye Brousar.</em> I have to take a six-pack to Mr. Broussard. (NE); <em>Mo di a mo padna.</em> I say to my friend. (NE) ♦<em>A</em> <em>tou manman li se di a so vyey fanm,</em> ‘<em>Kofè Bon-dje pran pa mwen!</em> He was always saying to his wife, ‘Why doesn’t the Good Lord take me?’ (JR) 6. [Possession]. Of; de. <em>La mezon a le Gilbo.</em> The Guilbeaus’ house. (NE) <em>♦Se pou sa tche a Lapen kourt jiska zordi.</em> That is why Rabbit’s tail is short even today. (JR) 7. [Distribution]. Per, a, every, each; par, le, chaque. <em>Si nou travay a deu, se pa osi dur.</em> If both of us work, it’s not as hard. (NE); <em>A di sou l son liv.</em> At ten cents per hundred pounds. (NE); <em>No p ape DRIVE tront-senk mil a ler ondon l BUS.</em> We don’t drive 35 miles an hour by bus. (NE) <em>♦Nou te a set, e nou jis a sis astè.</em> There were seven of us, and now there are only six of us. (JR) 8. [Manner, means]. With; avec, à. <em>Mo adousi ye a la kòrd e o bosal, mo adousi li byen.</em> I tame them with a rope and a bridle, I tame them well. (BT) <em>♦ Ye tonbe on li a kou baton.</em> They descended on him with heavy sticks. (JR) 9. [In adjectival phrases]. For; à. <em>La to pran en legwiy a matla. En gron lògwiy lonng kòm sa.</em> Then you take your mattress needle, a big needle this long. (PC); <em>Ye tache li e en lepengn a kouch.</em> They attached it with a diaper pin. (CA) <em>♦[Pou] di son ho, mele la krenm tart avek di souf, di sel a pirje e di mjel.</em> For high blood pressure, mix cream of tartar with sulfur, purging salt and honey. (BI)</p>")
      (h/as-hickory)))

;; A sample with multiple usage examples.
(def multiple-examples
  (-> (h/parse
       "<p>aflije <em>adj.</em> (CA). Paralyzed, crippled; paralysé, estropié. <em>Li tou aflije. Li pa kapab sèrvi so lamen. Li pa kapab leve. Aryen travay pa.</em> He is completely paralyzed. He can’t use his hands. He can’t stand up. Nothing works. (CA); <em>Mo okipe apre mo pèr. Li aflije.</em> I take care of my dad. He’s crippled. (CA)</p>")
      (h/as-hickory)))

;; A sample with multiple different lexicographic variants.
(def multiple-variants
  (-> (h/parse
       "<p>abitud<sup>2</sup> <em>v.aux.</em> (PC; BT); labitid (CA; PC); abitchid, abitchud (CA), labitchid, abitid, abitye, abitche (NE); labitud (PC). 1. To be used to, accustomed to; avoir l’habitude de. <em>Ye pa abitchud tande moun parle kreyol.</em> They are not used to hearing people speak Creole. (CA) 2. [Past habitual]. Used to; (verbe à l’imparfait). <em>Ondon le ton-la nou t abitchud kouri peche.</em> In those days we used to go fishing. (NE); <em>Mo t abitchid fime me mo fe pi sa.</em> I used to smoke but I don’t do that anymore. (NE) REM: With the preverbal marker ‘te’, this indicates past habitual.</p><p>abitudmon <em>adv.</em> (BT); habitudman, bitudmon, bitudman (BT). Usually; d’habitude. <em>Abitudmon le vyey fonm lonton pase te bouyi de chavrèt.</em> Usually, old women long ago would boil shrimp. (BT); <em>Bitudman</em>, <em>leu lanm a enn pyòch e drwòt konm sa.</em> Usually, the blade of a hoe is straight like that. (BT)</p>")
      (h/as-hickory)))

;; A sample containing usage examples dated prior to 1960. In the print dictionary, such examples
;; are separated from more modern ones with the use of a "lonzenge" or diamond-like symbol.
(def pre-1960-examples
  ())

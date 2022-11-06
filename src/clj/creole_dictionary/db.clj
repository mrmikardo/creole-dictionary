(ns creole-dictionary.db
  (:require [honey.sql :as sql]))

;;
;; Constants
;;

(def db-connection-string "postgresql://postgres:[YOUR-PASSWORD]@db.jwuhhshddaquoccvyexf.supabase.co:5432/postgres")

;;
;; Methods
;;

(defn construct-sql-for-entry
  "Given a `row`, construct an insert statement suitable for the `entries` table."
  [{:keys [headword part-of-speech translation examples]}]
  (sql/format {:insert-into :entries
               :columns [:headword :part_of_speech :translation :examples]
               :values [[headword
                         part-of-speech
                         translation
                         [:array examples]]]}))

(defn construct-sql-for-variation
  "Constructs an insert statement suitable for the `variations` table.

  `id` should be a valid id of a record in the `entries` table and will be used
  as a foreign key back to the parent entry."
  [id headword variation]
  (sql/format {:insert-into :variations
               :columns [:headword :variant :entry]
               :values [[headword variation id]]}))

;;
;; Scratchpad
;;

(def example {:headword "foobar" :part-of-speech "n." :variations [] :translation "Foobar" :examples ["I'm foobared" "Foobar!"]})
;; => {:headword "foobar", :part-of-speech "n.", :variations [], :translation "Foobar", :examples ["I'm foobared" "Foobar!"]}

(construct-sql-statement example)
;; => ["INSERT INTO entries (headword, part_of_speech, translation, examples) VALUES (?, ?, ?, ARRAY[?, ?])" "foobar" "n." "Foobar" "I'm foobared" "Foobar!"]

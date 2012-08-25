(ns webanagrm.anagrm
  (:require [clojure.java.io :refer [reader]]
            [clojure.string :refer [lower-case]]))

(defonce ^:const dictionary-file "/usr/share/dict/words")

(defn anagram?
  "Is x an anagram of y?"
  [x y]
  (= (sort x) (sort y)))

(defn sub-frequency? [a b]
  (every? #(>= (get a (key %) -1) (val %)) b))

(defn sub-anagram? [base-word sub-word]
  (sub-frequency?
   (frequencies (lower-case base-word))
   (frequencies (lower-case sub-word))))

(defn find-anagrams [base-word word-list]
  (filter (partial anagram? base-word) word-list))

(defn word-search [search?]
  (with-open [dict (reader dictionary-file)]
    (filter search? (line-seq dict))))

(defn smh-target-puzzle
  [letters compulsory-letter min-length]
  #(and (>= (count %)
            min-length)
        (contains? (set %) compulsory-letter)
        (sub-anagram? letters %)))

(defn solve-puzzle [word letter]
  (with-open [dict (reader dictionary-file)]
    (doall (filter (smh-target-puzzle word (first letter) 4) (line-seq dict)))))

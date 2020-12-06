(ns advent-of-code.day-6
  (:require [clojure.string :as str]))

(defn parse-input [input] 
  (map #(str/replace % #" " "")
       (str/split
        (str/join " " input)
        #"  ")))

(defn sum-of-counts [input]
  (reduce + (map (fn [i] (count (frequencies i))) (parse-input input))))

(comment


  (def input 
    ["abc"
     ""
     "a"
     "b"
     "c"
     ""
     "ab"
     "ac"
     ""
     "a"
     "a"
     "a"
     "a"
     ""
     "b"])

  (sum-of-counts input)

  (def real-input (str/split (slurp "input.txt") #"\n"))

  (sum-of-counts real-input)

  )


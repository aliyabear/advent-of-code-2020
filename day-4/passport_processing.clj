(ns advent-of-code.day-4
  (:require [clojure.string :as str]))

(def required-fields ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"])

(defn do-hacky-parse [input]
  (str/split (str/replace (str/replace input #"\n\n" "--") #"\n" " ") #"--"))

(defn count-valid-passports [input required-fields]
  (let [i (do-hacky-parse input)]
    (count (filter (fn[x] (every? #(str/includes? x %) required-fields)) i))))

(comment

  (def input (slurp "sample-input.txt"))

  (def real-input (slurp "input.txt"))

  (count-valid-passports input required-fields)
  (count-valid-passports real-input required-fields)
 
  )

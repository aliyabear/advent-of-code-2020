(ns advent-of-code.day-6
  (:require [clojure.string :as str]))

(defn parse-input [input] 
  (map #(str/replace % #" " "")
       (str/split
        (str/join " " input)
        #"  ")))

(defn sum-of-counts [input]
  (reduce + (map (fn [i] (count (frequencies i))) (parse-input input))))

(defn partition-input "For Part Two" [input] 
  (filter (fn [i] (not (empty? (first i)))) (partition-by (partial empty?) (str/split input #"\n"))))

(defn everyone-says-yes [input]
  ;; I'm so hacky.. oh so hacky and happy and freeeeeee!
  (quot (count (flatten (map (fn [i]
                               (let [num-passengers (count i)
                                     freq (frequencies(str/join #"" i))]
                                 (filter (fn [[k v]] (= num-passengers v) )freq))) (partition-input input))))
        ;; temp hack to divide by 2 as flatten will return a sequence like this (\a 1 \b 2 \c 3)
        2))

(comment

  (def input (slurp "sample-input.txt"))
  (def real-input-part-2 (slurp "input.txt"))

  (everyone-says-yes input)
  (everyone-says-yes real-input-part-2)

  (sum-of-counts (str/split input #"\n"))
  
  (def real-input (str/split (slurp "input.txt") #"\n"))

  (sum-of-counts real-input)

  )


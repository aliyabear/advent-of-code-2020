(ns advent-of-code.day-5
  (:require '[clojure.string :as str]))

(comment
  ;; let's hardcode some defaults
  (def seats 127)
  (def columns 7)
  (def input "BBFFBBFRLL")


  ;; Let's just leave everything in the comment section!
  ;; that's how hacky we feel today!
  (defn get-row [input x y low]
    (let [current (first input)
          z  (+ x(quot (- y x) 2))
          lower (if (= low current)
                  x
                  (inc z))
          upper (if (= low current)
                      z
                      y)]
          (if (empty? (rest input))
            (if (= low current)                         
              lower
              upper
              )
            (recur (rest input) lower upper low))))


(+ (* (get-row (take 7 input) 0 seats \F) 8) (get-row (drop 7 input) 0 columns \L))

(def real-input (str/split (slurp "input.txt") #"\n"))

(map #( (+ (* (get-row (take 7 %) 0 seats \F) 8)
           (get-row (drop 7 %) 0 columns \L)) ) real-input )

_)

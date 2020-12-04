(require '[clojure.string :as str])

  (def input ["1-3 a: abcde"
              "1-3 b: cdefg"
              "2-9 c: ccccccccccccccccc"
              "6-7 z: dqzzzjbzz"
              "13-16 j: jjjvjmjjkjjjjjjj"
              "7-15 p: rpjvppwfppsppptppqb"
              
              "10-12 w: wwwbwwjwnwqwbwswwwg"])

  ;; let the hackery begin!!!!!!!
  (defn valid-password? [input]
    (let [split (str/split input #": ")
          policy (str/split (first split) #" ")
          password (second split)
          range (str/split (first policy) #"-")
          maximum (Integer/parseInt (last range))
          minimum (Integer/parseInt (first range))
          character (second policy)
          count (get(frequencies password) (first character) 0)]
      (if (and (>= count minimum) (>= maximum count))
          true
          false)))

(comment

  (count (filter valid-password? input ))

  (def real-input (str/split (slurp "input.txt") #"\n"))

  (count (filter valid-password? real-input))
  )

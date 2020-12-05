(require '[clojure.string :as str])

  (def input ["1-3 a: abcde"
              "1-3 b: cdefg"
              "2-9 c: ccccccccccccccccc"
              "6-7 z: dqzzzjbzz"
              "13-16 j: jjjvjmjjkjjjjjjj"
              "7-15 p: rpjvppwfppsppptppqb"
              "2-3 c: ccccc"
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

;; Each policy actually describes two positions in the password, where 1 means the first character, 2 means the second character, and so on. (Be careful; Toboggan Corporate Policies have no concept of "index zero"!) Exactly one of these positions must contain the given letter. Other occurrences of the letter are irrelevant for the purposes of policy enforcement.
(defn valid-password-2? [input]
  (let [split (str/split input #": ")
        policy (str/split (first split) #" ")
        password (second split)
        range (str/split (first policy) #"-")
        maximum (dec (Integer/parseInt (last range)))
        minimum (dec (Integer/parseInt (first range)))
        character (first (second policy))
        first (nth password minimum)
        second (nth password maximum)
        ]
    (cond
      (= first second) false
      (some #(= character %) [first second]) true
      :else false)))

(comment

  (map valid-password? input)

  (def real-input (str/split (slurp "input.txt") #"\n"))
  (count (filter valid-password-2? real-input))
  )

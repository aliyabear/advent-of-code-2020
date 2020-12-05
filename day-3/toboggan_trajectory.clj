(require '[clojure.string :as str])

(def input ["..##......."
            "#...#...#.."
            ".#....#..#."
            "..#.#...#.#"
            ".#...##..#."
            "..#.##....."
            ".#.#.#....#"
            ".#........#"
            "#.##...#..."
            "#...##....#"
            ".#..#...#.#"])
 
(defn big-input [input n] (map #(apply str (repeat n %)) (rest input)))

  (defn count-trees [input n acc]
    (let [new-acc (if (= \# (nth (first input) n))
                    (inc acc)
                    acc)]
      (if (empty? (rest input))
        new-acc
        (recur (rest input) (+ 3 n) new-acc))))

(comment

  (count-trees (big-input input 15) 3 0)

  (def real-input (str/split (slurp "input.txt") #"\n"))

  (count-trees (big-input real-input 500) 3 0)

  )

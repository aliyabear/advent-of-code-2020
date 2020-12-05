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
 
(defn big-input [input n] (map #(apply str (repeat n %)) input))

(defn count-trees [input n acc]
  (let [new-acc (if (= \# (nth (first input) n))
                  (inc acc)
                  acc)]
    (if (empty? (rest input))
      new-acc
      (recur (rest input) (+ 3 n) new-acc))))

(defn count-trees-2 [input right down n acc]
  (let [x (drop down input)
        new-acc (if (= \# (nth (first x) right))
                  (inc acc)
                  acc)]
    (if (empty? x)
      new-acc
      (recur x (+ right n) down n new-acc))))

(comment

  ;; embracing that hacky hacky life
  (count-trees (big-input (rest input) 15) 3 0)
  
  (def real-input (str/split (slurp "input.txt") #"\n"))
  
  (count-trees (big-input (rest real-input) 500) 3 0)
  (* (count-trees-2 (big-input input 1) 1 1 1 0)
     (count-trees-2 (big-input input 15) 3 1 3 0)
     (count-trees-2 (big-input input 15) 5 1 5 0)
     (count-trees-2 (big-input input 15) 7 1 7 0)
     (count-trees-2 (big-input input 15) 1 2 1 0))
  
  (println  (* (count-trees-2 (big-input real-input 20) 1 1 1 0)
               (count-trees-2 (big-input real-input 150) 3 1 3 0)
               (count-trees-2 (big-input real-input 150) 5 1 5 0)
               (count-trees-2 (big-input real-input 150) 7 1 7 0)
               (count-trees-2 (big-input real-input 150) 1 2 1 0)))
)

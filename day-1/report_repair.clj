;; Before you leave, the Elves in accounting just need you to fix your expense report (your puzzle input); apparently, something isn't quite adding up.
;;
;; Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together.
;;
;; For example, suppose your expense report contained the following:
;;
;; 1721
;; 979
;; 366
;; 299
;; 675
;; 1456
;; In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying them together produces 1721 * 299 = 514579, so the correct answer is 514579.
;;
;; Of course, your expense report is much larger. Find the two entries that sum to 2020; what do you get if you multiply them together?
(defn sum-product [input sum]
  (let [find-entries (fn[input curr]
                       (let [diff (- sum curr)]
                         (if (some #(= diff %) input)
                           (* diff curr)
                           (recur (drop 1 input) (second input)))))]

    (find-entries input (first input))))

;; Using the above example again, the three entries that sum to 2020 are 979, 366, and 675. Multiplying them together produces the answer, 241861950.
;;
;; In your expense report, what is the product of the three entries that sum to 2020?
(defn triplet-product [input sum]
  (doseq [meow input]
    (doseq [fox (drop 1 input)]
      (doseq [rulez (drop 2 input)]
        (when (= sum (+ meow fox rulez))
          (println (* meow fox rulez)))))))


(comment

  (def input [123 1721 979 366 299 675 1456])

  (sum-product input 2020)
  (triplet-product input 2020)
  )


(ns advent-of-code.day-4
  (:require [clojure.string :as str]))

(def required-fields ["byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"])

(defn do-hacky-parse [input]
  (str/split (str/replace (str/replace input #"\n\n" "--") #"\n" " ") #"--"))

(defn count-valid-passports [input required-fields]
  (let [i (do-hacky-parse input)]
    (count (filter (fn[x] (every? #(str/includes? x %) required-fields)) i))))

(defn get-valid-passports [input required-fields]
  (let [i (do-hacky-parse input)]
    (filter (fn[x] (every? #(str/includes? x %) required-fields)) i)))

(defn valid-date? [value from to]
  (and (digits? value)
       (<= from (Integer/parseInt value) to)))

(defn digits? [value]
  (every? #(Character/isDigit %) value))

(defn valid-height? [value]
  (let [height-str (str/replace (str/replace value #"cm" "") #"in" "")]
    (if (digits? height-str)
      (let [height (Integer/parseInt height-str)]
        (if (str/ends-with? value "cm")
          (<= 150 height 193)
          (<= 59 height 76))))))

(defn valid-color? [value]
  (and (str/starts-with? value "#")
       (= 7 (count value))
       (every? (fn [v] (str/includes? "#0123456789abcdef" (str v))) value)))

(defn validate-passport-data [input required-fields]
  (let [passports (get-valid-passports input required-fields)]
    (map  (fn [p] (every? true? (map
                         (fn [x] (let [field-map (str/split x #":")
                                      field (first field-map)
                                      value (second field-map)]
                                  (cond (= "byr" field)
                                        (valid-date? value 1920 2002)
                                        (= "iyr" field)
                                        (valid-date? value 2010 2020)
                                        (= "eyr" field)
                                        (valid-date? value 2020 2030)
                                        (= "hgt" field)
                                        (valid-height? value)
                                        (= "hcl" field)
                                        (valid-color? value)
                                        (= "ecl" field)
                                        (some #(= value %) ["amb" "blu" "brn" "gry" "grn" "hzl" "oth"])
                                        (= "pid" field)
                                        (and (= 9 (count value)) (digits? value))
                                        :else true))) (str/split p #" "))))
          passports)))

(comment

  (def input (slurp "sample-input.txt"))

  (def real-input (slurp "input.txt"))

  (count-valid-passports input required-fields)
  (count-valid-passports real-input required-fields)

  (get-valid-passports input required-fields)

  (count (filter true? (validate-passport-data input required-fields)))
  (count (filter true? (validate-passport-data real-input required-fields)))
  (count (filter true? (validate-passport-data (slurp "bad-input.txt") required-fields)))

  (count (filter true? (validate-passport-data
                        (str/join "\n\n" [(slurp "good-input.txt") (slurp "bad-input.txt")])
                        required-fields)))

  (count real-input)

  )

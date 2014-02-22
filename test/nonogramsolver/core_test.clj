(ns nonogramsolver.core-test
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.fd :as fd]
            [nonogramsolver.core :as nono]
            [clojure.test :refer :all]))

(defn solve-row [len ns]
  (let [vars (l/lvars (inc len))]
    (set
     (l/run* [q]
             (l/everyg #(fd/in % (fd/domain 0 1)) vars)
             (nono/solve-row ns vars)
             (l/appendo q [0] vars)))))

(deftest solve-row-test
  (is (= #{[1 0 1]} (solve-row 3 [1 1])))
  (is (= #{[1 1 1 1 1]} (solve-row 5 [5])))
  (is (= #{[1 1 0 1 1]} (solve-row 5 [2 2])))
  (is (= #{[1 1 0 1 1 0]
           [1 1 0 0 1 1]
           [0 1 1 0 1 1]} (solve-row 6 [2 2])))
  (is (= #{} (solve-row 6 [3 3])))
  (is (= #{} (solve-row 6 [1 1 1 1])))
  (is (= #{[1 0 1 0 1 0 1]} (solve-row 7 [1 1 1 1])))
  (is (= #{[1 1 1 0 0]
           [0 1 1 1 0]
           [0 0 1 1 1]}
         (solve-row 5 [3]))))

#_(deftest solve-nono-test
  (is (= #{[[1 1 1 1]
            [1 1 1 1]
            [1 1 1 1]
            [1 1 1 1]]}
         (nono/solve-nono [[4] [4] [4] [4]]
                          [[4] [4] [4] [4]])))
  
  (is (= #{[[1 0 0 0]
            [0 0 0 0]
            [0 0 0 0]
            [0 0 0 0]]}
         (nono/solve-nono [[1] [0] [0] [0]] [[1] [0] [0] [0]])))
  
  (is (= #{[[1 1 1 0]
            [1 0 1 1]
            [0 1 0 1]
            [1 1 0 1]]}
         (nono/solve-nono [[3] [1 2] [1 1] [2 1]]
                          [[2 1] [1 2] [2] [3]])))
  
  #_(is (= (nono/solve-nono [[2] [2 1] [1 1] [3] [1 1] [1 1] [2] [1 1] [1 2] [2]]
                            [[2 1] [2 1 3] [7] [1 3] [2 1]])))
  
  #_(is (get (nono/solve-nono [[1 1] [3 3] [9] [9] [9] [7] [5] [3] [1]]
                              [[3] [5] [7] [7] [7] [7] [7] [5] [3]])
             [[0 0 1 0 0 0 1 0 0]
              [0 1 1 1 0 1 1 1 0]
              [1 1 1 1 1 1 1 1 1]
              [1 1 1 1 1 1 1 1 1]
              [1 1 1 1 1 1 1 1 1]
              [0 1 1 1 1 1 1 1 0]
              [0 0 1 1 1 1 1 0 0]
              [0 0 0 1 1 1 0 0 0]
              [0 0 0 0 1 0 0 0 0]])))

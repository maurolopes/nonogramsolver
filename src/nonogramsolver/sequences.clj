(ns nonogramsolver.sequences
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.fd :as fd]))

(l/defne sumo [lst sum]
  ([[] 0])
  ([[fst . rst] sum]
     (l/fresh [rsum]
              (fd/+ fst rsum sum)
              (sumo rst rsum))))

(l/defne dropo [n xs dxs]
  ([0 _ xs])
  ([n [_ . rxs] dxs]
     (l/fresh [n1]
              (fd/+ n1 1 n)
              (dropo n1 rxs dxs))))

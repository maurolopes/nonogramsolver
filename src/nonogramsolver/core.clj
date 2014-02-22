(ns nonogramsolver.core
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.fd :as fd]
            [nonogramsolver.sequences :as nseq]))

(l/defnu all-equal [x vars]
  ([_ []])
  ([x [x . rst]]
     (all-equal x rst)))

(l/defne solve-row [nums_ vars_]
  ([[] vars]
     (all-equal 0 vars))
  ([[n . ns] vars]
     (l/fresh [nsum vsum after n-after]
              (nseq/sumo ns nsum)
              (nseq/sumo vars vsum)
              (fd/+ nsum n vsum)
              (nseq/dropo n vars n-after)
              (l/conso 0 after n-after)
              (solve-row ns after)))
  ([nums [0 . rvars]]
     (l/fresh [sum]
              (nseq/sumo nums sum)
              (nseq/sumo rvars sum)
              (solve-row nums rvars))))

(l/defne solve-rows [row-spec rows]
  ([[] []])
  ([[first-row-nums . other-rows-nums] [first-row-vars . rest-grid]]
     (l/fresh [new-row]
              (l/appendo first-row-vars [0] new-row)
              (solve-row first-row-nums new-row)
              (solve-rows other-rows-nums rest-grid))))

(defn solve-nono [row-spec col-spec]
  (let [[x y] [(count row-spec) (count col-spec)]
        cells (repeatedly (* x y) l/lvar)
        rows (mapv vec (partition y cells))
        cols (apply map vector rows)]
    (set
     (l/run 1 [q]
            (l/everyg #(fd/in % (fd/domain 0 1)) cells)
            (l/== q rows)
            (solve-rows row-spec rows)
            #_(solve-rows col-spec cols)))))

(ns battleship.battlefield)

(def bf (into (sorted-map) 
          (zipmap 
            (for [x (take 10 (iterate inc 1)) y (take 10 (iterate inc 1))] (vector x y))
            (repeat :unknown))))


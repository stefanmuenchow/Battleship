(ns battleship.communication)

(defn convert-coord [x y]
  (vector (char (+ x 64)) y))

(defn send-join [room player]
  nil)

(defn send-lineup [player ships]
  nil)

(defn send-ready [player]
  nil)

(defn send-shot [player coord]
  nil)

(defn send-bye [player]
  nil)
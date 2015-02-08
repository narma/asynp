(ns asynp.core-test
  (:require [clojure.test :refer :all]
            [clojure.core.async :as async :refer [<! >! <!! >!! timeout chan alts! alt!! alts!! go close!]]
            [asynp.core :refer :all]))



(deftest simple-tests
  (let [process (run-process ["/bin/echo" "-n" "test"])
        [ret ch] (alts!! [(timeout 500) (:out process)])]
    (is (= ch (:out process)))
    (is (= (String. ret) "test"))))




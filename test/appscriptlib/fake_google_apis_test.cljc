(ns appscriptlib.fake-google-apis-test
  (:require [appscriptlib.fake-google-apis :as sut]
            [clojure.test :refer :all]
            [appscriptlib.protocols :refer :all]
            [appscriptlib.fake-google-apis :refer :all]))

(deftest get-sheets-by-name
  (testing "Can access a sheet by name"
    (let [sh (fake-sheet [] "foo")
          shdoc (fake-spreadsheet-document "testurl" [(fake-sheet [] "foo")])]
      (is (= "foo" (getName (getSheetByName shdoc "foo")))))))

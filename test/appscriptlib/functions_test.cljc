(ns appscriptlib.functions-test
  (:require
   [appscriptlib.functions :as f]
   [appscriptlib.protocols :refer :all]
   [appscriptlib.fake-google-apis :refer :all]
   [clojure.test :refer :all]))

(deftest mail-merge-basics
  (testing "Can send email"
    (let [log-sheet   (fake-sheet [["Date" "Message"]] "Log")
          queue-sheet (fake-sheet
                       [["Dest" "Subject" "Message"]
                        ["foo@bar.com" "test" "msg"]
                        ["foo2@bar.com" "test2" "msg2"]] "Queue")
          url         "mailmerge"
          shdoc       (fake-spreadsheet-document url [queue-sheet log-sheet])
          date        "2018-12-21"
          allfiles    (atom {url shdoc})
          gmail (fake-gmail)
          sheetapp    (fake-document-app-backed-by-store allfiles)]
      (f/mail-merge {:gmail gmail
                     :sheet sheetapp
                     :transformer identity
                     :url   url
                     :date  date})
      (is (= [[date "Processed 2 email"]]
             (getSheetValues log-sheet 2 1 20 20) ))
      (is (= 2 (count @(:emailqueue gmail)))))))

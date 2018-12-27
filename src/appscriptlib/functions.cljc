(ns appscriptlib.functions
  (:require [clojure.string :as str]
            [appscriptlib.protocols :refer [openByUrl getSheetValues getSheetByName sendEmail clearSheet appendRow]]))

(defn mail-merge [{:keys [gmail sheet url date transformer]}]
  (let [mail-sheet      (openByUrl sheet url)
        log-sheet       (getSheetByName mail-sheet "Log")
        mail-list-sheet (getSheetByName mail-sheet "Queue")
        orders          (getSheetValues mail-list-sheet 2 1 100 100)
        num-emails      (count (for [[email subject message] orders
                                     :when                   (not (str/blank? email))]
                                 (sendEmail gmail email subject message)))]
    (appendRow log-sheet (transformer [date (str "Processed " num-emails " email")]))
    (clearSheet mail-list-sheet)
    (appendRow mail-list-sheet (transformer ["Dest" "Subject" "Message"]))))

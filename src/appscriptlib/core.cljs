(ns appscriptlib.core
  (:require [appscriptlib.entry-points]
            [appscriptlib.real-google-apis :refer [google-sheet-app google-mail-app]]
            [appscriptlib.functions :as f]))

(defn ^:export mail_merge [url]
  (f/mail-merge {:sheet (google-sheet-app js/SpreadsheetApp)
                 :gmail (google-mail-app js/GmailApp)
                 :url url
                 :transformer clj->js
                 :date (.toString (js/Date.))}))

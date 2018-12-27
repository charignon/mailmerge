(ns appscriptlib.real-google-apis
  (:require [appscriptlib.protocols :refer
             [Sheet SpreadsheetDocument appendRow GmailApp clearSheet
              getName getSheetValues DocumentApp openByUrl]]))

(defrecord GoogleSheet [k]
  Sheet
  (appendRow [{:keys [k]} row] (.appendRow k row))
  (getName [{:keys [k]}] (.getName k))
  (clearSheet [{:keys [k]}] (.clear k))
  (getSheetValues [{:keys [k]} x1 y1 x2 y2] (.getSheetValues k x1 y1 x2 y2)))

(defrecord GoogleSpreadsheetDocument [k]
  SpreadsheetDocument
  (getSheetByName [{:keys [k]} name] (GoogleSheet. (.getSheetByName k name))))

(defrecord RealGmailApp [k]
  GmailApp
  (sendEmail [{:keys [k]} dest subject message] (.sendEmail k dest subject message)))

(defrecord GoogleSheetApp [k]
  DocumentApp
  (openByUrl [{:keys [k]} url] (GoogleSpreadsheetDocument. (.openByUrl k url))))

(defn google-sheet-app [k] (GoogleSheetApp. k))
(defn google-mail-app [k] (RealGmailApp. k))

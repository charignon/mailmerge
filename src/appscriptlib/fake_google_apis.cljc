(ns appscriptlib.fake-google-apis
  (:require [appscriptlib.protocols :refer
             [GmailApp Sheet SpreadsheetDocument appendRow clearSheet
              DocumentApp getName getSheetValues openByUrl]]))

(defrecord FakeDocumentApp [url_to_doc]
  DocumentApp
  (openByUrl [{:keys [url_to_doc]} url] (get @url_to_doc url)))

(defrecord FakeSpreadsheetDocument [url sheets]
  SpreadsheetDocument
  (getSheetByName [{:keys [sheets]} name]
    (first (filter #(= (getName %) name) sheets))))

(defrecord FakeGmailApp [emailqueue]
  GmailApp
  (sendEmail [{:keys [emailqueue] :as this} recipient subject body]
    (do (swap! emailqueue
               #(conj % {:recipient recipient :subject subject :body body}))
        this)))

(defn doGetSheetValues [m x y xlen ylen]
  (let [rows (take xlen (drop (dec x) m))
        cols (map #(apply vector (take ylen (drop (dec y) %))) rows)]
    (apply vector cols)))

(defrecord FakeSheet [content name]
  Sheet
  (appendRow [{:keys [content]} row]
    (swap! content (fn [content] (if (= content [[]]) [row] (conj content row)))))
  (getName [{:keys [name]}] name)
  (clearSheet [{:keys [content]}] (reset! content [(vector)]))
  (getSheetValues
    [{:keys [content]} x1 y1 x2 y2]
    (doGetSheetValues @content x1 y1 x2 y2)))

(defn fake-gmail []
  (FakeGmailApp. (atom [])))

(defn fake-document-app-backed-by-store [url-to-doc]
  (FakeDocumentApp. url-to-doc))

(defn fake-spreadsheet-document [url sheets]
  (FakeSpreadsheetDocument. url sheets))

(defn fake-sheet [data name]
  (FakeSheet. (atom data) name))

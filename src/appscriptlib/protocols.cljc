(ns appscriptlib.protocols)

(defprotocol GmailApp
  (sendEmail [_ recipient subject body] "Send an email"))

(defprotocol SpreadsheetDocument
  (getSheetByName [_ n] "Get a sheet by name"))

(defprotocol Sheet
  (appendRow [_ row] "Append a row [a b c ...] to this sheet")
  (clearSheet [_] "Remove all the elements of this sheet")
  (getName [_] "Return the name of the sheet")
  (getSheetValues [_ x1 x2 y1 y2] "Get the value of a square identified by coordinates"))

(defprotocol DocumentApp
  (openByUrl [_ url] "Returns a document matching <url> or nil"))

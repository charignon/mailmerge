(defproject appscriptlib "1.0.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.439"]]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :clean-targets ^{:protect false} [:target-path :compile-path "target" "export"]

  :cljsbuild {:builds
              {:main {:source-paths ["src"]
                      :compiler {:main appscriptlib.core
                                 :optimizations :simple
                                 :output-to "export/Code.gs"
                                 :output-dir "target"
                                 :parallel-build true
                                 :pretty-print true
                                 :verbose true
                                 :compiler-stats true
                                 :externs ["resources/gas.ext.js"]
                                 :foreign-libs [{:file "src/entry_points.js"
                                                 :provides ["appscriptlib.entry-points"]}]}}}})

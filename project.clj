(defproject gardengrids "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [garden "1.2.5"]]
  :plugins [[lein-garden "0.2.5"]]
  :garden {:builds [{:source-paths ["src/styles"]
                     :stylesheet gardengrids.core/screen
                     :compiler {:output-to "resources/screen.css"
                                :pretty-print? true}}]})

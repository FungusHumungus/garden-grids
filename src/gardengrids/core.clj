(ns gardengrids.core
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.units :refer [px]]))

(def clearfix
  [:&:before :&:after {:content "\" \""
                       :display "table"}
   :&:after {:clear "both"}])

(defn make-row
  [gutter]
  [clearfix
   [:& {:margin-left (/ gutter -2)
        :margin-right (/ gutter -2)}]])

(defn make-column
  [columns gutter]
  {:position "relative"
   :min-height "1px"
   :padding-left (/ gutter 2)
   :padding-right (/ gutter 2)
   :float "left"
   :width (str (double (* 100 (/ columns 12))) "%")})

(defstyles screen
  []
  [[:* {:box-sizing "border-box"}]
   [:.row (make-row 10) {:border [["solid" "1px" "#000"]]}]
   [:.col-md-1 (make-column 1 10)]
   [:.col-md-4 (make-column 4 10)]
   [:.col-md-6 (make-column 6 10)]
   [:.col-md-8 (make-column 8 10)]])

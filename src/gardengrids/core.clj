(ns gardengrids.core
  (:require ;; [garden.core :refer [css]]
            [garden.def :refer [defstylesheet defstyles]]
            [garden.units :refer [px]]
            [garden.stylesheet :refer [at-media]]))

(def ^:dynamic *grid-gutter* 20)
(def ^:dynamic *breakpoints* {:sm 768
                              :md 992
                              :lg 1200})

(def ^:dynamic *screen-sm-min* (px 768))
(def ^:dynamic *screen-md-min* (px 992))
(def ^:dynamic *screen-lg-min* (px 1200))
(def ^:dynamic *container-sm-min* (+ 720 *grid-gutter*))
(def ^:dynamic *container-md-min* (+ 940 *grid-gutter*))
(def ^:dynamic *container-lg-min* (+ 1140 *grid-gutter*))

(def clearfix
  [:&:before :&:after {:content "\" \""
                       :display "table"}
   :&:after {:clear "both"}])


(defn container-fixed
  ([] (container-fixed *grid-gutter*))
  ([gutter]
     [clearfix
      [:& {:margin-right "auto"
           :margin-left "auto"
           :padding-left (px (/ gutter 2))
           :padding-right (px (/ gutter 2))
           }]]))

(defn container
  ([] (container *grid-gutter*))
  ([gutter]
     (into 
      [(container-fixed gutter)]
      (map (fn [break] 
             (let [width (break *breakpoints*)]
               (at-media {:min-width width}
                         [:& {:width (px (- width (* gutter 2)))}])))
           (keys *breakpoints*)))))

(defn make-row
  ([] (make-row *grid-gutter*))
  ([gutter]
     [clearfix
      [:& {:margin-left (px (/ gutter -2))
           :margin-right (px (/ gutter -2))}]]))

(defn make-column
  ([columns] (make-column columns *grid-gutter*))
  ([columns gutter]
     {:position "relative"
      :min-height "1px"
      :padding-left (px (/ gutter 2))
      :padding-right (px (/ gutter 2))
      :float "left"
      :width (str (double (* 100 (/ columns 12))) "%")}))

(defstyles screen
  []
  (binding [*grid-gutter* 10]
    [
     [:* {:box-sizing "border-box"}]
     [:.container (container)]
     [:.row (make-row) {:border [["solid" "1px" "#000"]]}]
     [:.col-md-1 (make-column 1)]
     [:.col-md-4 (make-column 4)]
     [:.col-md-6 (make-column 6)]
     [:.col-md-8 (make-column 8)]]))

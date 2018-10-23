(ns tobby.core)

(enable-console-print!)

(defn get-dom-attrs [props]
  (map (fn [[key value]]
         (str key "=" value)) props))

(defn create-tag [{:keys [tag-name children props]}]
  (let [attrs (get-dom-attrs props)]
    (str "<" tag-name " " attrs ">" children "</" tag-name ">")))

(defn query [q]
  (document.querySelector q))

(defn render [component root]
  (set! (.-innerHTML root) component))

(defn node [attr]
  (let [{:keys [tagName textContent & rest] :as all} attr]
    (if textContent
      (create-tag {:tag-name tagName :children textContent})
      (create-tag {:tag-name tagName :props all}))))

(defn component []
  (node {:tagName "input" :type "text" :value "Hello"}))

(defn on-js-reload []
  (let [root (query "#app")]
    (render (component) root)))

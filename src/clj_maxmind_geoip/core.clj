; A thin clojure wrapper around Maxmind's java APIs which access its geoip database.
; Currently this handles only countries.
(ns clj-maxmind-geoip.core
  (:import com.maxmind.geoip.LookupService
           java.util.Locale))

(def ^:private geoip-access-modes {:memory 1 :check 2 :index 4})

(def ^:private lookup-service (atom nil))

(defn init-geoip
  "- database-path: the country edition of the maxmind geoip database."
  [database-path]
  (let [service (LookupService. database-path (:memory geoip-access-modes))]
    (swap! lookup-service (constantly service))))

(defn lookup-country
  "nil if no country could be found, or {:name, :code}."
  [ip]
  (when-let [result (.getCountry @lookup-service ip)]
    {:code (.getCode result) :name (.getName result)}))

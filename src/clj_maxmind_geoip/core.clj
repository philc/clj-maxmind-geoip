; A thin clojure wrapper around Maxmind's java APIs which access its geoip database.
; Currently this handles only countries.
(ns clj-maxmind-geoip.core
  (:import com.maxmind.geoip.LookupService
           com.maxmind.geoip.regionName
           java.util.Locale))

(def ^:private geoip-access-modes {:memory 1 :check 2 :index 4})

(def ^:private lookup-service (atom nil))

(defn init-geoip
  "- database-path: the country edition of the maxmind geoip database."
  [database-path]
  (let [service (LookupService. database-path (:memory geoip-access-modes))]
    (swap! lookup-service (constantly service))))

(defn lookup-country
  "Lookup country information for an IP address. Only available when querying a GeoIP Country database.
  Returns a map of the following country info, or nil if none found:
  {:name, :code}"
  [ip]
  (when-let [result (.getCountry @lookup-service ip)]
    {:code (.getCode result) :name (.getName result)}))

(defn lookup-location
  "Lookup location information for an IP address. Only available when querying a GeoIP City database.
  Returns a map of the following location info, or nil if none found:
  {:country-code, :country-name, :region-code, :region-name, :city, :postal-code, :latitude, :longitude}"
  [ip]
  (when-let [result (.getLocation @lookup-service ip)]
    {:country-code (.countryCode result)
     :country-name (.countryName result)
     :region-code (.region result)
     :region-name (regionName/regionNameByCode (.countryCode result) (.region result))
     :city (.city result)
     :postal-code (.postalCode result)
     :latitude (.latitude result)
     :longitude (.longitude result)}))

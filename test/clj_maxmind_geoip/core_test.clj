(ns clj-maxmind-geoip.core-test
  (:use midje.sweet
        clj-maxmind-geoip.core)
  (:import java.io.File))

(defn ensure-db-exists [path]
  (when-not (-> (File. path) .exists)
    (println "Missing required file:" path)
    (println "Download from http://dev.maxmind.com/geoip/geolite")
    (System/exit 1)))

(def geoip-country-db "data/GeoIp.dat")
(def geoip-city-db "data/GeoLiteCity.dat")

(ensure-db-exists geoip-country-db)
(ensure-db-exists geoip-city-db)

(init-geoip geoip-country-db)
(facts "lookup-country works"
  (lookup-country "127.0.0.1") => {:code "--" :name "N/A"}
  (lookup-country "64.233.160.0") => {:code "US" :name "United States"})

(init-geoip geoip-city-db)
(facts "lookup-location works"
  (lookup-location "127.0.0.1") => nil
  (lookup-location "64.233.160.0") => (just {:country-code "US"
                                             :country-name "United States"
                                             :region-code "CA"
                                             :region-name "California"
                                             :city "Mountain View"
                                             :postal-code "94043"
                                             :latitude number?
                                             :longitude number?
                                             :dma-code 807}))

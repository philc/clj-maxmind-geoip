# Deprecated

See [liftoffio/geoip](https://github.com/liftoffio/geoip).

# clj-maxmind-geoip

At the moment this is a very trivial Clojure wrapper around [Maxmind's GeoIP Java
API](https://github.com/maxmind/geoip-api-java). It supports the country edition of their database.

The main reason this project exists is to make it easy to invoke Maxmind's API without having to include the
Java sdk source files into your clojure project.

# Usage
Add this dependency to your `:dependencies` array in your Lein .project file:

    [clj-maxmind-geoip "0.1.0-SNAPSHOT"]

```clojure
(require '[clj-maxmind-geoip.core :as geoip])

(geoip/init-geoip "path/to/GeoIp.dat")
(geoip/lookup-country "12.207.22.244")
  => {:code "US", :name "United States"}

(geoip/init-geoip "path/to/GeoLiteCity.dat")
(geoip/lookup-location "12.207.22.244")
  => {:country-code "US", :country-name "United States", :region-code "CA", :region-name "California",
      :city "Palo Alto", :postal-code "94301", :latitude 37.441895, :longitude -122.143005}
```

# Developing
The Maxmind Java API source is a dependency of this project. To get it and build it:

    git submodule init && git submodule update
    lein javac

## License

Distributed under the Eclipse Public License, the same as Clojure.

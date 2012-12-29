# clj-maxmind-geoip

At the moment this is a very trivial Clojure wrapper around [Maxmind's GeoIP Java
API](https://github.com/maxmind/geoip-api-java). It supports the country edition of their database.

The main reason this project exists is to make it easy to invoke Maxmind's API without having to include the
Java sdk source files into your clojure project.

# Usage
Add this dependency to your `:dependencies` array in your Lein .project file:

    [clj-maxmind-geoip "0.1.0-SNAPSHOT"]

    (require '[clj-maxmind-geoip.core :as geoip])
    (geoip/init-geoip "path-to-your-database.dat")
    (geoip/lookup-country "12.207.22.244")
      => {:code "USA", :name "United States"}

# Developing
The Maxmind Java API source is a dependency of this project. To get it and build it:

    git submodule init && git submodule udpate
    lein javac

## License

Distributed under the Eclipse Public License, the same as Clojure.

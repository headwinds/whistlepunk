(defproject clojure-getting-started "1.0.0-SNAPSHOT"
  :description "a simple secure store"
  :url "http://clojure-getting-started.herokuapp.com"
  :license {:name "Eclipse Public License v1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.4.0"]
                 [ring/ring-json "0.3.1"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring-cors "0.1.12"]
                 [environ "1.1.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [postgre-types "0.0.4"]
                 [cheshire "5.8.1"]
                 [hiccup "1.0.5"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-environ "1.1.0"]]
  :uberjar-name "pilot.jar"
  :main app.web
  :profiles { :dev [:project/dev :profiles/dev]
              :test [:project/test :profiles/test]
              :production [:project/production :profiles/production]
           ;; only edit :profiles/* in profiles.clj
              :profiles/dev  {}
              :profiles/test {}
              :profiles/production {}
              :project/dev {:source-paths ["src" "tool-src"]
                         :dependencies [[midje "1.6.3"]]
                         :plugins [[lein-auto "0.1.3"]]}
              :project/test {}})

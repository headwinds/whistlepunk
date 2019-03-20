(ns app.heroku-config)

(def en {:env
          {:production true
           :secret-key ""
           :open-dota-key ""
           :database-dbname ""
           :database-subprotocol "postgresql"
           :database-host ""
           :database-user ""
           :database-password ""
           :database-sslmode "require"
           :database-sslfactory "org.postgresql.ssl.NonValidatingFactory"
           :strava-secret ""
           :strava-id ""
           :strava-public-token ""}})

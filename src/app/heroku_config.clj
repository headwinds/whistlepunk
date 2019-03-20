(ns app.heroku-config)

(def en {:env
          {:production true
           :secret-key ""
           :open-dota-key ""
           :database-dbname "d4iq187en6e304"
           :database-subprotocol "postgresql"
           :database-host "ec2-54-163-227-253.compute-1.amazonaws.com"
           :database-user "dbjkxonyffsqew"
           :database-password "264fc9ae7364207800598c45d2694cad88e65f6197b1caa1c416c195b593c484"
           :database-sslmode "require"
           :database-sslfactory "org.postgresql.ssl.NonValidatingFactory"
           :strava-secret "0ea43d67520d24ffb89fe8fb362c1ac3e3ebe298"
           :strava-id "28964"
           :strava-public-token "2ee5a5d82a2abf47274dd3f2b3657d5912b13341"}})

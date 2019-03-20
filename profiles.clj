;; rename to profiles.clj with your details and be careful to include profiles.clj in your .gitignore

{:dev
  {:env
    { :production "false"
      :database-dbname "shed"
      :database-host "localhost"
      :database-user "headwinds"
      :database-password "KingLear22"
      :database-sslmode "disable"}
  }
 :production
  {:env
    { :production "true"
      :database-dbname "d4iq187en6e304"
      :database-host "ec2-54-163-227-253.compute-1.amazonaws.com"
      :database-user "dbjkxonyffsqew"
      :database-password "264fc9ae7364207800598c45d2694cad88e65f6197b1caa1c416c195b593c484"
      :database-sslmode "require"}
  }
}

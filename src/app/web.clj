(ns app.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :as handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.jdbc :as j]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [ring.util.response :refer [response resource-response]]
            [app.heroku-config :as heroku-config]
            [app.local-config :as local-config]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.middleware.json :as middleware]
            [cheshire.core :refer [generate-string parse-string]]
            [postgre-types.json :refer [add-json-type]]
            [environ.core :refer [env]]))

  ;; https://github.com/siscia/postgres-type
  (add-json-type generate-string parse-string)

  ;; (defn get-config [] heroku-config/en)
  ;; (defn get-config [] heroku-config/en)

  ;; heroku is not picking up my env uberjar setting but when I hardcode it works!
  ;; so this is a bandaid until we sort out that issue
  (def valid-enva (if
                    (nil? (env :database-password))
                      heroku-config/en
                      env))

  (def valid-env (:env heroku-config/en))

   (def pg-db-l { :dbtype "postgresql"
                :dbname (:database-dbname valid-env)
                :host (:database-host valid-env)
                :port "5432"
                :user (:database-user valid-env)
                :password (:database-password valid-env)
                })

  (def pg-db {:dbtype "postgresql"
              :dbname (:database-dbname valid-env)
              :host (:database-host valid-env)
              :port "5432"
              :user (:database-user valid-env)
              :password (:database-password valid-env)
              :sslmode (:database-sslmode valid-env)
              :sslfactory (:database-sslfactory valid-env)
              })

(def limit 10)

;; TODO - Add buddy auth
;; https://adambard.com/blog/clojure-auth-with-buddy/ 
;; https://github.com/metosin/compojure-api/wiki/Authentication-and-Authorization
;; https://adambard.com/blog/buddy-password-auth-example/
;; authenticate routes!

(defn get-colonists
  [offset]
  (j/query pg-db
    [(str "SELECT * FROM colonist ORDER BY created_at DESC OFFSET " offset " LIMIT " limit)]))

(defn get-colonist
  [colonist-name]
    (let [query  (str "SELECT * FROM colonist WHERE colonist_name = '" colonist-name "';")]
  (j/query pg-db
    [query])))

(defn get-latest-colonist-profile
      [colonist-id]
        (let [query  (str "SELECT * FROM profile WHERE colonist_id = '" colonist-id "' order by updated_at desc limit 1;")]
      (j/query pg-db
        [query])))

 ;;-- Answers

(defn get-colonist-answers
  [colonist-id]
    (let [query  (str "SELECT * FROM answer WHERE colonist_id = '" colonist-id "';")]
  (j/query pg-db
    [query])))

(defn get-answers
  [offset]
  (j/query pg-db
    [(str "SELECT * FROM answer ORDER BY created_at DESC OFFSET " offset " LIMIT " limit)]))

(defn delete-all-my-answers
  [colonist_id]
  (j/delete! pg-db :answer ["colonist_id = ?" colonist_id])
  (j/query pg-db ["SELECT * FROM answer WHERE colonist_id = ?" colonist_id]))

;;-- Questions

(defn get-questions
  [offset]
  (j/query pg-db
    [(str "SELECT * FROM question ORDER BY created_at DESC OFFSET " offset " LIMIT " limit)]))

;;-- Events

(defn get-events
  [offset]
  (j/query pg-db
    [(str "SELECT * FROM events ORDER BY created_at DESC OFFSET " offset " LIMIT " limit)]))

; answer is a json string like
; '{"name": "Paint house", "tags": ["Improvements", "Office"], "finished": true}'
; instead of creating json here, it would be easier to create it from the client!!!!
(defn insert-answer
  [question-label answer colonist-id]
  (let [row {:question_label question-label
             :answer {"answer" answer}
             :colonist_id colonist-id}]
  (j/insert! pg-db :answer row)))

(defn insert-question
  [question answer colonist-id]
  (let [row {:question question
             :answer answer
             :colonist_id colonist-id}]
  (j/insert! pg-db :question row)))

(defn insert-event
  [event_label event_device event_app event_who event_description event_json] 
  (let [row {:event_label event_label
             :event_device event_device
             :event_app event_app
             :event_who event_who
             :event_description event_description
             :event_json event_json}]
  (j/insert! pg-db :events row)))

(defn update-profile-experience
  [colonist_experience colonist_id]
  (j/update! pg-db :profile {:colonist_experience colonist_experience} ["colonist_id = ?" colonist_id])
  (get-latest-colonist-profile colonist_id))

(defn get-content [] )

(defn get-result [] )

  (defn get-win-rate []
    (if-let [content (get-content)]
      (let [result (get-result)]
        (if bytes
          {:status 200
           :headers {"Content-Type" "text/plain"}
           :body (str result "%")}
           {:status 404
            :body "Result not found"}))
       {:status 406
        :body "Not Acceptable"}
     ))

  (defn get-longest-win-streak []
   {:status 200
    :headers {"Content-Type" "text/plain"}
    :body "6"})

  (defn get-win-rate []
    {:status 200
     :headers {"Content-Type" "text/plain"}
     :body "8"})

   (defn get-wins-against-my-rival []
     {:status 200
      :headers {"Content-Type" "text/plain"}
      :body "3"})

(defroutes app-routes
  (GET "/" []
        (route/not-found (slurp (io/resource "index.html"))))
  (route/resources "/")

  ;; get-events?page=1
  (GET "/get-events" request
    (let [offset (* (- (read-string (get-in request [:params :page])) 1) limit)]
       (get-events offset)))

  (POST "/post-event" request
   (let [event_label (get-in request [:body :event_label])
         event_device (get-in request [:body :event_device])
         event_app (get-in request [:body :event_app])
         event_who (get-in request [:body :event_who])
         event_description (get-in request [:body :event_description])
         event_json (get-in request [:body :event_json])]
         (insert-event event_label event_device event_app event_who event_description event_json)))

 (POST "/post-answer" request
   (let [question-label (get-in request [:body :question_label])
         answer (get-in request [:body :answer])
         colonist-id (get-in request [:body :colonist_id])]
         (insert-answer question-label answer colonist-id)))

 (POST "/post-question" request
   (let [question (get-in request [:body :question])
         answer (get-in request [:body :answer])
         colonist-id (get-in request [:body :colonist_id])]
         (insert-question question answer colonist-id)))

 (PUT "/put-profile-experience" request
   (let [colonist_experience (get-in request [:body :colonist_experience])
         colonist_id (get-in request [:body :colonist_id])]
         (update-profile-experience  colonist_experience colonist_id)))

  (GET "/lesson-2" []
       (resource-response "lesson-2.html"))

  (GET "/lego-blockchain" []
        (route/not-found (slurp (io/resource "lego-blockchain.html"))))

  (GET "/get-colonist" request
    (let [colonist-name (get-in request [:params :name])]
       (get-colonist colonist-name)
       ))

 (GET "/get-latest-colonist-profile" request
   (let [colonist-id (get-in request [:params :id])]
      (get-latest-colonist-profile colonist-id)
      ))

 (GET "/get-colonist-answers" request
   (let [colonist-id (get-in request [:params :id])]
      (get-colonist-answers colonist-id)
      ))

  ;; get-colonists?page=1
  (GET "/get-colonists" request
    (let [offset (* (- (read-string (get-in request [:params :page])) 1) limit)]
       (get-colonists offset)))

  (GET "/get-answers" []
        (get-answers))

  (GET "/get-questions" []
       (get-questions))

  (GET "/get-win-rate" []
       (get-win-rate))

  (GET "/get-longest-win-streak" []
      (get-longest-win-streak))

   (GET "/get-wins-against-my-rival" []
      (get-wins-against-my-rival))

  (DELETE "/delete-all-my-answers" request
    (let [colonist-id (get-in request [:params :id])]
       (delete-all-my-answers colonist-id)
       ))

  (ANY "*" []
       (route/not-found (slurp (io/resource "404.html")))))

(def app
  (-> (site app-routes)
      (middleware/wrap-json-body {:keywords? true})
      (wrap-cors  :access-control-allow-origin [#"http://localhost:3000"
                                                #"https://rivalry-profile.now.sh"
                                                #"http://localhost:8000"
                                                #"https://cabinquest.now.sh"
                                                #"https://golds.now.sh"]
                  :access-control-allow-methods [:get :put :post :patch :delete])
      middleware/wrap-json-response))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

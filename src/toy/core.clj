(ns toy.core
  (:require [honey.sql :as sql]
            [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection]
            [mount.core :refer [defstate]]
            [next.jdbc.result-set :as rs])
  (:import (com.zaxxer.hikari HikariDataSource)))

;;1. a helper function for build sql query by honeysql
(defn select-table-by-id [table & {:keys [ks id]}]
  (first
   (sql/format
    (cond->
        {:select (or ks [:*])
         :from   [(keyword table)]}
      id
      (assoc :where [:= (keyword (str table ".id")) id]))
    {:inline true})))

;;(select-table-by-id "table-name" :id 1001 :ks [:id :name :age])
;;"SELECT id, name, age FROM table_name WHERE table_name.id = 1001"


;;2. define your mysql config
(defonce db-spec
  (or
   (:mysql (read-string
            (slurp "./config/dev.edn")))
   {:host     "localhost",
    :username "root",
    :port     3306
    :dbtype   "mysql"
    :dbname   "dbname"
    :password "pass"}))

;;3. use Hikari as jdbc connection pool for reuse
(defstate conn1
  :start (connection/->pool HikariDataSource db-spec)
  :stop (.close conn1))


;;4. after execute (mount.core/start), you can do it in repl or in your bootstraping code

;;5. query some data
#_
(jdbc/execute! conn1
               [(select-table-by-id "Users")])


(defn main [& args]
  (println "welcome to clojure toy playgournd!")
  ;;put any useful function here to launch your journey!
  )

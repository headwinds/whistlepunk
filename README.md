# Lesson 2 Clojure & PostgreSQL

In a future of driverless vehicles, the concept of a pilot is certainly shifting from someone who operates to someone who guides.

In this lesson, we'll look at how Clojure talks to PostgreSQL, and create a database of pilots who maintain their ships more than actually drive them.

You are in charge of a mixed fleet of mechs, piloted by miners, and each mech is assisted by several unmanned drones. Motivate each pilot by awarding them achievements based on their harvest so that you can build up vast resources of cannibalized machines, stripped of their precious metals, and appease your anxious overlords.

Since you are building an API, there are two apps in play here split between front and backend. The frontend app is built with [nuxtjs](https://nuxtjs.org/guide/installation) while the backend app is all Clojure. In lesson 3, you can play with an app built with Clojure & ClojureScript and learn how you can use Clojure across front and backend but this lesson I wanted to keep them separate to show that you can choose a different JS framework like Angular, React, or even vanilla JS like the first lesson.

## dependencies

- [clojure](https://blog.venanti.us/why-clojure/)
- [compojure](https://github.com/weavejester/compojure/wiki)
- [ring](https://github.com/ring-clojure/ring/wiki)
- [postgreSQL](https://www.infoworld.com/article/3240064/sql/why-old-school-postgresql-is-so-hip-again.html)
- [environ](https://yobriefca.se/blog/2014/04/29/managing-environment-variables-in-clojure/)
- [hiccup](https://github.com/weavejester/hiccup)

# Getting Started

## Backend - Trophy Shed

Before you run, you need to create your own config files that will talk to postgres on Heroku. Locate 
the heroku_config_sample.clj and rename it to heroku_config.clj with your settings. Repeat this process for profiles_sample.clj.


```
$ lein run
```

## Frontend - Trophy Creator

To get started:

```
  cd trophy-creator
  yarn run dev
```

To build & start for production:

```
  cd trophy-creator
  yarn run build
  yarn start
```

## Deploy to Heroku

Copy this lesson into a new directory outside of this git repo so that it has it own git repo. We don't want to have nested repos.

This order is important:

```
heroku login
git init
git add .
git commit -m "first commit"
heroku create
git push heroku master
heroku ps:scale web=1
heroku open
```

Then open your browser to whatever url it created ie: https://glacial-badlands-20785.herokuapp.com/

## Inspiration

Watch [Obvillion](https://www.wired.com/2013/04/oblivion-movie-questions/) then watch [the making of...](https://www.youtube.com/watch?v=iYryNL1FlPk)

## Reading

 * [postgres clojure java jbdc](http://peterstratton.com/posts-output/2017-01-28-postgres-and-clojure-using-clojure-java-jdbc/) (*highly recommend*)
* [An Animated Introduction to Clojure](https://ourcodestories.com/markm208/Playlist/4)  
* [this Rich Hickey thread](https://twitter.com/richhickey/status/1057970957040660480)
* [understanding lein run](http://www.flyingmachinestudios.com/programming/how-clojure-babies-are-made-lein-run/)
* [clojure postgres](https://web.archive.org/web/20161024231548/http://hiim.tv/clojure/2014/05/15/clojure-postgres-json/)
* [clj-postgresql](https://github.com/remodoy/clj-postgresql)
* [hugsql](https://www.compose.com/articles/embrace-sql-with-hugsql-clojure-and-postgresql/)

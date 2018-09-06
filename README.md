# CaseStudy

Technologies:
-Java
-Spring Boot
-Maven
-MongoDB


Set Up:
-Clone repository
-In your favorite IDE set environment variable DATABASE_NAME to whatever your DB name will be.
-Create DB with same name as environment variable.
-Create collection within DB named "prices". (If you'd like a different collection name, change in ProductPrice.java line 9)
    -Seed with data
      - Example: {id:13860424,value:12.99,currency_code:"USD"}
-Run a server that serves your MongoDB instance on port 27017 (Default)
      -If you'd like to run on non-default port, set an environment variable MONGODB_PORT
      -In application.properties add "/${MONGODB_PORT}" to end of sping.data.mongodb.uri
-Run CaseStudyApplication
-In your favorite browser, navigate to http://localhost:8080/{productId}

List of known product ids in RedSkyApi (use these to seed MongoDB):
-13860421
-13860424
-13860428
-13860429

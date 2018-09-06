# CaseStudy

Technologies: <br>
-Java <br>
-Spring Boot <br>
-Maven <br>
-MongoDB <br>
<br>

Set Up:<br>
-Clone repository <br>
-In your favorite IDE set environment variable DATABASE_NAME to whatever your DB name will be <br>
-Create DB with same name as environment variable <br>
-Create collection within DB named "prices" (If you'd like a different collection name, change in ProductPrice.java line 9) <br>
    -Seed with data <br>
      - Example: {id:13860424,value:12.99,currency_code:"USD"} <br>
-Run a server that serves your MongoDB instance on port 27017 (Default) <br>
      -If you'd like to run on non-default port, set an environment variable MONGODB_PORT <br>
      -In application.properties add "/${MONGODB_PORT}" to end of sping.data.mongodb.uri <br>
-Run CaseStudyApplication <br>
<br>
-In your favorite browser, navigate to http://localhost:8080/{productId} <br>
<br>
List of known product ids in RedSkyApi (use these to seed MongoDB): <br>
-13860421 <br>
-13860424 <br>
-13860428 <br>
-13860429 <br>

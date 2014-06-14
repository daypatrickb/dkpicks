dkpicks
=======

NFL Pickem Scoring 

Setup
=====
In order to get your local environment configured for DKPicks, you will need the following:

1. JDK 1.7 or higher
2. Eclipse
3. Jetty Web Server
4. MySQL


After pulling down the project from GitHub, import it into your Eclipse IDE. It is recommended to use EGit to integrate Eclipse with GitHub.

Copy the contents of the project's lib directory into the ./lib/ext directory of Jetty. Then delete servlet-api.jar in ./lib/ext of Jetty

In the pickem workspace, set the build.xml to point to your Jetty webapps directory (at the bottom of the file). It is recommended to use JDK 7

Run the build.xml as an ant process. Start Jetty. Login at http://localhost:8080


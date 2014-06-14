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

Copy the contents of the project's lib directory into the ./lib/ext directory of Jetty. 
Delete servlet-api.jar in ./lib/ext of Jetty

In the pickem workspace, set the build.xml to point to your Jetty webapps directory (at the bottom of the file). It is recommended to use JDK 7



Set an Ant property in Eclipse for the deploy location:

Preferences > Ant > Runtime > Properties

Name: PICKS_DEPLOY_DIR

Value: [put the webapps directory of jetty, for example C:\jetty\webapps\ or /home/dh/jetty/webapps/   ]


Run the build.xml as an ant process, to deploy the .WAR file.




Start Jetty via the console using command "java -jar start.jar" within the Jetty directory.

Login at http://localhost:8080


SciBite assignment submission

For my submission I have created a RESTful API which can save, update, get and delete Person objects. The data is stored in a MongoDB database so you will need to setup MongoDB on your machine.

I completed tasks 1. Web interface client and 3. Integration tests.


Setting up MongoDB – Install and run MongoDB by following the quick steps 

https://docs.mongodb.com/manual/administration/install-community/


Steps to use the application:

1. Inside this directory run: ./gradlew build
This will build a jar file and run the tests created.

2. Locate the jar file in build/libs/ and run the jar file: java -jar build/libs/<filename>.jar

3. Open http://localhost:8080/ in your web browser

Read the instructions in the web page. You may need to add some entries to the database before you can play around.

4. To view the entires in the database open http://localhost:8080/person/all 



Additional notes:

If you don't have Gradle then please install it following the instructions

https://gradle.org/install/



You may need the Lombok plugin for some of the annotations used e.g. @Getter so please follow the instructions for Eclipse or Intellij

https://www.baeldung.com/lombok-ide


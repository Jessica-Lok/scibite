SciBite assignment submission

For my submission I have created a RESTful API which can save, update, get and delete Person objects. The data is stored in a MongoDB database so you will need to setup MongoDB on your machine.

I completed tasks 1. Web interface client and 3. Integration tests.


Setup with docker-compose

You may need to install docker https://docs.docker.com/docker-for-mac/install/

Clone the repo and in the project folder run 

docker-compose up 

You should then be able to open localhost:8080

To stop the app and mongo

docker-compose down

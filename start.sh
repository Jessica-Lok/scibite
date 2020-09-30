#!/bin/bash

#Build the assignment container image
docker build -t assignment .

#Get the mongo container running 
docker run -d --network net --network-alias mymongo -p 27017:27017 mongo:4.4

#Get application running and use the network alias 
docker run -dp 3000:8080 --network net -e MONGO_HOST=mymongo -t assignment

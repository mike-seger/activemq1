#!/bin/bash

cd ..
mvn clean package
cd -
rm var/lib/postgresql*
unzip -d var/lib/ -j ../target/activemq1*jar  BOOT-INF/lib/postgresql*

docker-compose down
docker-compose up --build

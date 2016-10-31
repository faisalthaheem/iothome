# IoT Home

The project aims to provide a central & intelligent control system for house automation which includes controling appliances, supporting scheduled events, reading sensor data and taking appropriate actions etc. The project is currently in nascent stages and I hope to spend some time maturing it for production use. It is currently in a state favorable for hobbyist use. Currently covered:

 * Standalone web services using apache spark support listing, adding and deleting scheduled jobs.
 * Embedded H2 database using sql2o for querying.
 * Built in job scheduler inspired by (mqtt google calendar github project) based on quartz library.

Soon to come
* Standardization of sensor and control devices, naming conventions and protocols.
* Embedded Rule Engine for sensor network collaboration.
* Built in analytics.
* Firebase alike framework for home IoT automation.
* Dockerized containers for ready to use service.
* Better version of this Readme!

### To use you need:
* JDK 1.8, I used oracle JDK, you can see if other flavors work.
* Maven, latest version will do.

Once you have the environment setup checkout the code from this repo and execute the following command in the root folder
mvn package

then to run
java -jar brain-1.0-SNAPSHOT-jar-with-dependencies.jar

By default the web server listens on port 4567 this will be addressed as a command line argument in the near future. Refer to the following file on which parameters can be passed when running the jar

CommandLineOptions.java

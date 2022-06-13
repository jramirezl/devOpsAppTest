FROM openjdk:11
MAINTAINER eidherjulian61
COPY target/UserStoriesNLPApplication-0.0.1-SNAPSHOT.jar UserStoriesNLPApplication-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/UserStoriesNLPApplication-0.0.1-SNAPSHOT.jar"]
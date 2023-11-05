FROM openjdk:1.8-jre-slim

COPY target/gestion-station-ski-2.0-SNAPSHOT.jar .
EXPOSE 3307
ENV IMAGE_NAME="abdelkerimdassi_5ds1-g2-skistation"
ENTRYPOINT ["java", "-jar", "gestion-station-ski-2.0-SNAPSHOT.jar"]
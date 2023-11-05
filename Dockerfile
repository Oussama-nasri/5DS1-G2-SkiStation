FROM openjdk:11-jre-slim

COPY target/gestion-station-ski-3.0-SNAPSHOT.jar .
EXPOSE 8089
ENV IMAGE_NAME="abdelkerimdassi_5ds1-g2-skistation"
ENTRYPOINT ["java", "-jar", "gestion-station-ski-3.0-SNAPSHOT.jar"]
#FROM openjdk:11-jre-slim
FROM openjdk:8-jre-slim
COPY /home/5DS1-G2-SkiStation/target/gestion-station-ski-3.0-SNAPSHOT.jar .
EXPOSE 8089
ENV IMAGE_NAME="abdelkerimdassi_5ds1-g2-skistation"
ENTRYPOINT ["java", "-jar", "gestion-station-ski-3.0-SNAPSHOT.jar"]
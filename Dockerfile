#FROM openjdk:11-jre-slim
FROM openjdk:8-jdk-alpine
ADD target/5DS1_G2_SkiStation-1.0-RELEASE.jar gestion-station-ski.jar
EXPOSE 8089
ENV IMAGE_NAME="abdelkerimdassi_5ds1-g2-skistation"
ENTRYPOINT ["java", "-jar", "gestion-station-ski.jar"]
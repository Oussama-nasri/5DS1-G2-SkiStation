FROM openjdk:11-jre-slim

COPY target/gestion-station-ski-1.0.jar .
EXPOSE 8089
ENV IMAGE_NAME="wajdihajji_5ds1-g2-skistation"
ENTRYPOINT ["java", "-jar", "gestion-station-ski-1.0.jar"]

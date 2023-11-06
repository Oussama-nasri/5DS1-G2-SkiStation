FROM openjdk:11-jre-slim

COPY target/gestion-station-ski-1.0-SNAPSHOT.jar .
EXPOSE 8089
ENV IMAGE_NAME="mohamedsalahchafrouda_g2_skistation"
ENTRYPOINT ["java", "-jar", "gestion-station-ski-1.0-SNAPSHOT.jar"]

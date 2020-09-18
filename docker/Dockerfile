FROM java:8u111

VOLUME /tmp

ADD *.jar app.jar

EXPOSE 80 8080 8090

ENTRYPOINT ["java","-jar","/app.jar"]

# Ubuntu 时区
#RUN java -jar /app.jar
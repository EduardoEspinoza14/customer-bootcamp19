FROM openjdk:8
#VOLUME /tmp
EXPOSE 8081
ADD target/customer.jar customer.jar
ENTRYPOINT ["java", "-jar", "customer.jar"]

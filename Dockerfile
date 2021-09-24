FROM openjdk:16
ADD target/users-mysql.jar users-mysql.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "users-mysql.jar"]
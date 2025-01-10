FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

ADD ./target/microservicio-product-0.0.1-SNAPSHOT.jar microservicio-product.jar

ENTRYPOINT [ "java", "-jar", "microservicio-product.jar" ]
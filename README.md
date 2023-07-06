# Bookstore Demo

Quick, short and simple standalone demo project focus on showing different bits of spring components usage for bookstore service using the following:

1. Springboot 3
2. Spring Web
3. Spring JPA
4. Spring Validation
5. Spring Security
6. H2 database
7. Spring Actuator (Micrometer.io)
8. Graalvm (AOT building)

Unfortunately, there are a few new libraries unable to showcase. The usage of H2 database and many to many JPA support lacks in reactive. (Hopefully in the future)

1. Spring Webflux (Reactive)
2. Java 17 - Records (Immutable classes)

## Building and Running bookstore service

1. Run `gradlew clean build bootrun`
2. Navigate to `.\curl`
3. Execute the any of the `*cmd` script.

## Build OCI container

1. Run `gradlew bootBuildImage`

### Using bookstore service 

1. **Anonymous** and **User** users - able to get, update, create book.
2. **User** users - able to delete book.

## Spring Acutator

1. [Spring Actuator](localhost:8080/actuator)

Run any of the curl multiple time and take a look at the endpoint's measures shown in 

- [Spring Actuactor - findAllByTitleOrAuthorsName](http://localhost:8080/actuator/metrics/findAllByTitleOrAuthorsName)
- [Spring Actuactor - findAllByTitle](http://localhost:8080/actuator/metrics/findAllByTitle)
- [Spring Actuactor - findAllByAuthorsName](http://localhost:8080/actuator/metrics/findAllByAuthorsName)
- [Spring Actuactor - create](http://localhost:8080/actuator/metrics/create)
- [Spring Actuactor - update](http://localhost:8080/actuator/metrics/update)
- [Spring Actuactor - delete](http://localhost:8080/actuator/metrics/delete)

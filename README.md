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

1. **User** - able to get, patch, post book.
2. **Admin** - able to get, delete book.
3. **User** and **Admin** - able to get author info.

## H2 In-memory database

1. Change **application.properties** - spring.h2.console.enabled=true
2. Open this: [H2 console](localhost:8080/h2-console)

## Spring Acutator

1. [Spring Actuator](localhost:8080/actuator)

Run `get-book-by-isbn-as-admin.cmd` or `get-book-by-isbn-as-user` multiple time and take a look at the measures taken shown in 

2. [Spring Actuactor - getBookByIsbn](http://localhost:8080/actuator/metrics/getBookByIsbn)

# Spring microservices movie ratings

providing a simple app to track user movie ratings with java spring, using micro-services architecture 
(communicating through REST-API) with auto discovery (eureka) and fault tolerance (hystrix).

Basically, the main service, movie-catalog-service, gets a request from the user, to get its' saved movies and user ratings for these movies.
movie-catalog-service queries ratings-data-service for user ratings, and movie-info-service for movie details, via REST-API.
It then combines the results, outputting the movies the user has watched and rated.

The microservice architecture uses Auto-discovery via Eureka, through another microservice, and combines mechanisms for fault tolerance and resilliance (Hystrix).

Micro-service system architecture:
![Alt text](MS_architecture.png?raw=true "system architecture")

## Usage

1. run all microservices (Either by making a jar out of each one and running, or importing the projects to an IDE an running them).
2. query the system via Localhost:8081/catalog/*user_id* to get a specific user's movies.

## Credits

credit to Koushik Kothagal and JavaBrains channel on Youtube.com on [link to Google!](https://www.youtube.com/user/koushks) for the detailed course on Spring Boot Microservices.

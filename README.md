# Spring-Microservices-Showcase
This is a spring microserver showcase based on the Udemy course "Master Microservices with Spring Boot and Spring Cloud" by in28Minutes Official.  
https://www.udemy.com/course/microservices-with-spring-boot-and-spring-cloud  
Java Spring Boot Microservices 5-in-1 - Spring Boot, Spring Cloud, Docker, Kubernetes and REST API (REST Web Services)

## Limits Service
- http://localhost:8080/limits

## Cloud Config Server
- http://localhost:8888/limits-service/default
- http://localhost:8888/limits-service/qa
- http://localhost:8888/limits-service/dev

## Currency Exchange Service
- http://localhost:8000/currency-exchange/from/USD/to/INR
- http://localhost:8000/currency-exchange-feign/from/USD/to/INR

## Currency Conversion Service
- http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

## Eureka
- http://localhost:8761/

## Spring Cloud Api Gateway
- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-exchange/from/USD/to/INR
- http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10
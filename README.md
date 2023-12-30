# Spring-Microservices-Showcase
This is a spring microservice showcase based on the Udemy course "Master Microservices with Spring Boot and Spring Cloud" by in28Minutes Official.  
https://www.udemy.com/course/microservices-with-spring-boot-and-spring-cloud  
Java Spring Boot Microservices 5-in-1 - Spring Boot, Spring Cloud, Docker, Kubernetes and REST API (REST Web Services)

## Limits Service 
The Limits-Service is a simple microservice to get properties from the Spring-Cloud-Config-Server.
- http://localhost:8080/limits

## Spring Cloud Config Server 
The Spring Cloud Config Server offers properties for all the services.  
The properties can be found in the same folder as the README file.
- http://localhost:8888/limits-service/default
- http://localhost:8888/limits-service/qa
- http://localhost:8888/limits-service/dev

## Currency Exchange Service
The Currency-Exchange-Service is a microservice to get currency-exchange-objects from a database. 
It also shows how to implement a rest-controller with the fault tolerance api "resilience4j". 
Options for telemetry are also implemented. 
### Local-URLs:
- http://localhost:8000/currency-exchange/from/USD/to/INR
### Kubernetes-URLs:
- http://34.34.139.203:8000/currency-exchange/from/USD/to/INR

## Currency Conversion Service
The Currency-Exchange-Service is a microservice to get currency-exchange-objects from the currency-exchange-service and calculate the result of the currency conversion.
This is implemented in 2 ways: with rest-template and with open-feign. 
Options for telemetry are also implemented.
### Local-URLs:
- http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
### Kubernetes-URLs:
- http://104.155.95.150:8100/currency-conversion/from/USD/to/INR/quantity/10
- http://104.155.95.150:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

## Naming-Server (Eureka)
The Eureka-Naming-Server gives the other microservices the option to register as a client with a service-name.
The benefit of the naming-server is that you can connect to other microservices by name and not by port-number so that loadbalancing is possible.
- http://localhost:8761/

## Spring Cloud Api Gateway
The Api-Gateway gives an general entry-point of the application. For that routes are registered to route request to the right microservice.
- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-exchange/from/USD/to/INR
- http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10

## Zipkin
- http://localhost:9411/zipkin

## Docker Commands
Build Docker Image with Maven (in the pom.xml in the plugin-section at the spring-boot-maven-plugin has to be set a image-name and a pullPolicy): 
```shell
mvn spring-boot:build-image -DskipTests
```
Upload Docker Image to Dockerhub:
```shell 
docker push blueirongirl/currency-exchange-service-kubernetes:0.0.2-SNAPSHOT
docker push blueirongirl/currency-conversion-service-kubernetes:0.0.2-SNAPSHOT
```

## Kubernetes Commands
Create Deployments:
```shell
kubectl create deployment currency-exchange --image=blueirongirl/currency-exchange-service-kubernetes:0.0.1-SNAPSHOT
kubectl create deployment currency-conversion --image=blueirongirl/currency-conversion-service-kubernetes:0.0.1-SNAPSHOT
```

Expose Deployments:
```shell
kubectl expose deployment currency-exchange --type=LoadBalancer --port=8000
kubectl expose deployment currency-conversion --type=LoadBalancer --port=8100
```

Creating Declarative Configuration Kubernetes YAML for Microservices (in each root of module-folder):
```shell
kubectl get deployment currency-exchange -o yaml >> deployment.yaml
kubectl get service currency-exchange -o yaml >> service.yaml ### extract all and put into deployment.yaml after "---" as divider
kubectl delete all -l app=currency-exchange ### deletes pods, deployment and service
kubectl apply -f deployment.yaml ### apply the local deployment.yaml

kubectl get deployment currency-conversion -o yaml >> deployment.yaml
kubectl get service currency-conversion -o yaml >> service.yaml ### extract all and put into deployment.yaml after "---" as divider
kubectl delete all -l app=currency-conversion ### deletes pods, deployment and service
kubectl apply -f deployment.yaml ### apply the local deployment.yaml
```

Create Centralized Configuration
```shell
kubectl create configmap currency-conversion --from-literal=CURRENCY_EXCHANGE_URI=http://currency-exchange
kubectl get configmap currency-conversion -o yaml >> configmap.yaml ### extract all and put into deployment.yaml after "---" as divider. Reference it with "envFrom" in the container-section
kubectl apply -f deployment.yaml ### apply the local deployment.yaml
```


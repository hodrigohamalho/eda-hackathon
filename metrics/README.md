# event-consumer Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

### Using a Strimzi Broker in Openshift

Set the variables:

```
PROJECT=kafka-cluster
EVENTCONSUMER_PWD=$(oc get secret event-consumer -n $PROJECT  -o jsonpath='{.data.password}' | base64 -d )
BOOTSTRAP_URL=$(oc get route my-cluster-kafka-bootstrap -n $PROJECT -o jsonpath='{.spec.host}'):443
```

Run: 

```
mvn clean quarkus:dev -Dbootstrap.url=$BOOTSTRAP_URL  -Deventconsumer.pwd=$EVENTCONSUMER_PWD
```

### Local

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
```shell script
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/event-consumer-jvm .
```

```shell script
docker tag quarkus/event-consumer-jvm:latest viniciusfcf/one-tap-soccer-event-consumer:latest
```

```shell script
docker push viniciusfcf/one-tap-soccer-event-consumer:latest
```

or

```shell script
docker tag quarkus/event-consumer-jvm:latest quay.io/vflorent/one-tap-soccer-event-consumer:latest
```

```shell script
docker push quay.io/vflorent/one-tap-soccer-event-consumer:latest
```

## Images

Game: ```viniciusfcf/one-tap-soccer:latest```

Event consumer: ```viniciusfcf/one-tap-soccer-event-consumer:latest```

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/event-consumer-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)


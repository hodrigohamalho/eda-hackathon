quarkus build
docker build -f src/main/docker/Dockerfile.jvm -t quay.io/hodrigohamalho/order-generator .
docker push quay.io/hodrigohamalho/order-generator

### simple spring-rest application:
1. java 11
1. maven as a build tool

### run & deploy
run maven build  
```bash
mvn clean install
```

run in docker 
* docker-image will be created automatically during maven-build)
* assume that there is MongoDB on default host / port (see MongoConfig class for more info)   
```bash
docker run --network="host" -P -t spring/unicorn-shop
```

run in minikube ([minikube installation](https://kubernetes.io/docs/tasks/tools/install-minikube/))
```bash
minikube start --vm-driver=none
kubectl run spring-unicorn-shop --image=spring/unicorn-shop --image-pull-policy=Never
kubectl expose deployment spring-unicorn-shop --type=LoadBalancer --port=8080
kubectl scale --replicas=3 deployment/spring-unicorn-shop
```

check minikube deployment
```bash
kubectl get deployments
kubectl get pods
kubectl get services
```

### check
```bash
# get list of unicorns:
curl "http://localhost:8080/unicorn-shop/unicorn"

# get unicorn by id:
curl "http://localhost:8080/unicorn-shop/unicorn?id=1"

# create new unicorn:
curl -X POST "http://localhost:8080/unicorn-shop/unicorn" -H "Content-type: application/json" -d '{"description":"fluffy"}'

# update existed unicorn: 
curl -X PUT "http://localhost:8080/unicorn-shop/unicorn" -H "Content-type: application/json" -d '{"id":4, "description":"very fluffy"}'

# delete existed unicorn:
curl -X DELETE  "http://localhost:8080/unicorn-shop/unicorn?id=3"
```
   
### REST-API description

see [swagger-ui](http://localhost:8080/swagger-ui.html)   

### Travis CI build   

see [travis-ci](https://travis-ci.org/Semernitskaya/unicorn-shop-backend)

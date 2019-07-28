### simple spring-rest application:
1. java 11
1. maven as a build tool

### run & deploy
run maven build  
```bash
mvn clean install
```

run in docker (docker-image will be created automatically in maven-build)   
```bash
docker run -P -t spring/template
```

run in minikube ([minikube installation](https://kubernetes.io/docs/tasks/tools/install-minikube/))
```bash
minikube start --vm-driver=none
kubectl run spring-template --image=spring/template --image-pull-policy=Never
kubectl expose deployment spring-template --type=LoadBalancer --port=8080
kubectl scale --replicas=3 deployment/spring-template
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

# create new unicorn:
curl -X POST "http://localhost:8080/unicorn-shop/unicorn" -H "Content-type: application/json" -d '{"description":"fluffy"}'

# update existed unicorn 
curl -X PUT "http://localhost:8080/unicorn-shop/unicorn" -H "Content-type: application/json" -d '{"id":4, "description":"very fluffy"}'
```
   
### REST-API description

see [swagger-ui](http://localhost:8080/swagger-ui.html)

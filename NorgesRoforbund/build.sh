docker run --rm -it --name mavenbuild -v maven-repo:/root/.m2 -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven mvn clean install

docker cp target/NorgesRoforbund-1.0-SNAPSHOT.war payara:/opt/payara/deployments


docker exec -it payara asadmin --user=admin --passwordFile /opt/payara/passwordFile undeploy NorgesRoforbund-1.0-SNAPSHOT
docker exec -it payara asadmin --user=admin --passwordFile /opt/payara/passwordFile deploy deployments/NorgesRoforbund-1.0-SNAPSHOT.war

echo ""
echo "Link: http://localhost:8080/NorgesRoforbund-1.0-SNAPSHOT/"
echo ""

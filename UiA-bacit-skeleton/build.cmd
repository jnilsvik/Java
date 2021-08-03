docker run --rm -it --name mavenbuild -v maven-repo:/root/.m2 -v "%cd%":/usr/src/mymaven -w /usr/src/mymaven maven mvn clean install
docker image build -t trym/tomcat .
docker container run --rm -it --publish 8081:8080 trym/tomcat
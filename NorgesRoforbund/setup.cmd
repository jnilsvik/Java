docker run --name mariadb -p 3309:3306/tcp -v "%cd%/database":/var/lib/mysql -e MYSQL_ROOT_PASSWORD=mypass -d mariadb

docker volume create --name maven-repo

docker run --name payara -p 8080:8080 -p 4848:4848 -d had96dad/java
pause

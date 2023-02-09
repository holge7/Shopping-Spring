cd .\commons
mvn package
cd ..

SET VERSION=0.0.1

cd .\api-gateway
mvn install:install-file -Dfile=.\..\commons\target\commons-%VERSION%.jar -DgroupId=com -DartifactId=commons -Dversion="%VERSION%" -Dpackaging=jar -DgeneratePom=true
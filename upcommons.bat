cd .\commons
mvn package
cd ..

SET VERSION=1.0.1

cd .\shopping\user-service
mvn install:install-file -Dfile=.\..\..\commons\target\commons-1.0.1.jar -DgroupId=com -DartifactId=commons -Dversion="1.0.1" -Dpackaging=jar -DgeneratePom=true
cd .\shopping\user-service
mvn install:install-file -Dfile=.\..\..\commons\target\commons-%VERSION%.jar -DgroupId=com -DartifactId=commons -Dversion="%VERSION%" -Dpackaging=jar -DgeneratePom=true
cd ..

cd .\shopping\api-gateway
mvn install:install-file -Dfile=.\..\commons\target\commons-%VERSION%.jar -DgroupId=com -DartifactId=commons -Dversion="%VERSION%" -Dpackaging=jar -DgeneratePom=true


mvn install:install-file -Dfile=D:\ProgrammingEnviroment\Java\Projects\Shopping\commons\target\commons-1.0.1.jar -DgroupId=com -DartifactId=commons -Dversion="1.0.1" -Dpackaging=jar -DgeneratePom=true
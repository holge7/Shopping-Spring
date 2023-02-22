cd .\commons
mvn package
cd ..

SET VERSION=1.0.2

cd .\shopping\user-service
mvn install:install-file -Dfile=.\..\..\commons\target\commons-%VERSION%.jar -DgroupId=com -DartifactId=commons -Dversion="%VERSION%" -Dpackaging=jar -DgeneratePom=true
cd ..

cd .\shopping\product-service
mvn install:install-file -Dfile=.\..\commons\target\commons-%VERSION%.jar -DgroupId=com -DartifactId=commons -Dversion="%VERSION%" -Dpackaging=jar -DgeneratePom=true
cd ..

cd .\shopping\doc-service
mvn install:install-file -Dfile=.\..\commons\target\commons-%VERSION%.jar -DgroupId=com -DartifactId=commons -Dversion="%VERSION%" -Dpackaging=jar -DgeneratePom=true
cd ..

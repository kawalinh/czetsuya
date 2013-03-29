mvn install:install-file -Dfile=merchantsdk-2.1.96.jar -DgroupId=com.paypal.sdk -DartifactId=merchant-java-sdk -Dversion=2.1.96 -Dpackaging=jar
mvn install:install-file -Dfile=paypal-core-1.1.jar -DgroupId=com.paypal.sdk -DartifactId=paypal-core-1.1 -Dversion=1.1 -Dpackaging=jar
mvn install:install-file -Dfile=permissionssdk-2.1.96 -DgroupId=com.paypal.sdk -DartifactId=permissionssdk -Dversion=2.1.96 -Dpackaging=jar
mvn install:install-file -Dfile=paypal-auth-signature-1.0.jar -DgroupId=com.paypal.sdk -DartifactId=paypal-auth-signature -Dversion=1.0 -Dpackaging=jar


http://code.google.com/p/javaee6-crud-example/source/browse/src/
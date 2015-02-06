Welcome to the Data Importer Application!

As you may have gathered our application provides data importing services.

To get your application upp and running enter this in the command line in the current directory: 
java -jar dataMigrator-0.0.1.jar <feed file location>(eg.d:\test.csv)

After this you will find all your lovely data from the feed file stored into the in-memory database and printed for your reading pleasure in the console.

You can take advantage of our open source policy and modify the source code.
After that run these commands in the project directory:
mvn clean package
then
java -jar dataMigrator-0.0.1-SNAPSHOT.jar <feed file location>(eg.d:\test.csv)

or

mvn spring-boot:run

Thank you for using our services!
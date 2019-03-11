-----------------------------------------------------------------------------------------
CSVParser
-----------------------------------------------------------------------------------------

This is a restful web service that takes a string of CSV data as input and returns the data reformatted as shown below without using regular expressions or a 3rd party CSV parser library. 

It is implemented as a Microservice compatible with container. It is written in SpringBoot and provide a REST endpoint htttp://localhost:8080/parse.

-----------------------------------------------------------------------------------------
System requirements.
-----------------------------------------------------------------------------------------

Java 8 or above
Maven 3 if running locally

-----------------------------------------------------------------------------------------
Install, uninstall, configuration, and operating instructions.
-----------------------------------------------------------------------------------------

mvn clean install
cd target
java -jar csvservice-0.0.1-SNAPSHOT.jar

Test it using a curl command as below. See next section for file. 
curl -i -X POST  -F file=@"c:\Users\wawa\My Documents\csv.txt" http://localhost:8080/parser/parse

-----------------------------------------------------------------------------------------
Files list
-----------------------------------------------------------------------------------------
File locally stored: c:\Users\wawa\My Documents\csv.txt:

"Patient Name","SSN","Age","Phone Number","Status"

"Prescott, Zeke","542-51-6641",21,"801-555-2134","Opratory=2,PCP=1"

"Goldstein, Bucky","635-45-1254",42,"435-555-1541","Opratory=1,PCP=1"

"Vox, Bono","414-45-1475",51,"801-555-2100","Opratory=3,PCP=2"

-----------------------------------------------------------------------------------------
Help/Swagger
-----------------------------------------------------------------------------------------

http://localhost:8080/parser/swagger-ui.html

-----------------------------------------------------------------------------------------
Credit, acknowledgments, contact information, and copyright.
-----------------------------------------------------------------------------------------

Versioning: 0.0.1

Authors: Arul J

License: Of site hosting it





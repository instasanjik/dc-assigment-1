#Report on task 1

**Name: Sanzhar Koshkarbayev**

**Group: IT-2004**

**E-mail: 201456@astanait.edu.kz**

**Main part:**
# Step 1: study web server example
Repository: <https://github.com/georgy-schukin/dc-course> 

Web server: <https://github.com/georgy-schukin/dc-course/tree/master/examples/WebServer> 

This is a simple web server which accepts HTTP requests, written in Java. 

You can use curl command to send a request to it (start the server first):

curl <http://localhost:8080/index.html>

![](Aspose.Words.0dd4f67a-2122-4a4f-8475-520a441d548d.001.png)
# Step 2: modify the web server to be able to recognize different commands
Parse request line of HttpRequest (HttpRequest.getRequestLine()). The first part of the line is a method (GET), the second part is a path to a resource. Use this path to pass some commands to the server and make the server react to them. 

If user will try to get the main page of our server, he will get 404 error, because server doesn’t contains this case

![](Aspose.Words.0dd4f67a-2122-4a4f-8475-520a441d548d.002.png)

/create/test.txt

![](Aspose.Words.0dd4f67a-2122-4a4f-8475-520a441d548d.003.png)

/delete/itemid

![](Aspose.Words.0dd4f67a-2122-4a4f-8475-520a441d548d.004.png)

` `In requests as:

- /delete/’file\_name’ - user will try to delete file in our main directory
- /create/’file\_name’ - user will try to create file in our main directory

Make sure that one command will take some long time to execute (~ several seconds). Examples of operation to perform by the server with this command:

- Processing all files in all directories, starting from some root directory. You can count the total number of lines in files or the total number of occurrences of some symbol.
- Mathematical computation, i.e. finding all prime numbers up to the given number.
- Running some script or external program.
- Thread.Sleep() to simulate a delay, if nothing else works.

Return result of each command as simple HTML page with text (modify Processor class).
# Step 3: study performance of the web server
Use some benchmark utility to send many concurrent requests to the server (pass a command that will take a long time to process on the server). Study results of the benchmark. Pay attention to such values as number of requests per second, average request processing time, etc. Include results of benchmarking in the report.

My server in normal mode:

![](Aspose.Words.0dd4f67a-2122-4a4f-8475-520a441d548d.005.png)

My server in stress-test mode: 

P.s. be attention in 2nd and 3rd treads

![](Aspose.Words.0dd4f67a-2122-4a4f-8475-520a441d548d.006.png)


Examples of benchmark utilities:

- hey: <https://github.com/rakyll/hey> 
- ApacheBench: <https://httpd.apache.org/docs/2.4/programs/ab.html> 

Example of benchmark command:

hey -n 50 -c 50 -t 0 <http://localhost:8080/command>


# Step 4: create git repository on Github for this course, upload your project files for Task 1 to it, include a link to the repository in the report

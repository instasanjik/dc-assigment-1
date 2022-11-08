package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

/**
 * Processor of HTTP request.
 */
public class Processor {
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public double test() {
        double answer = 0;
        for(long i = 0; i <= Integer.MAX_VALUE; i+=2) {
            answer += new Random().nextDouble();
            if(i == Integer.MAX_VALUE/10) {
                return answer;
            }
        }
        return 1;
    }
    public void process() throws IOException {
        System.out.println("Got request:");
        String newrequest = request.toString().split(" ")[1];
        System.out.println(newrequest);
        Integer threadId = 0;
        if (newrequest.contains("stress-test/")) {
            PrintWriter output = new PrintWriter(socket.getOutputStream());

            // We are returning a simple web page now.
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("Stress test has been started: " + dtf.format(now));
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Test</title></head>");
            output.println("<body><p>Stress test has been started: " + dtf.format(now) + "</p></body>");
            output.println("</html>");
            output.flush();
            // We are returning a simple web page now.
            output.println();
            Double i = test();
            LocalDateTime after = LocalDateTime.now();
            System.out.println(i);
            System.out.println("Stress test has been ended: " + dtf.format(now));
            output.println("<body><p> " + "Stress test has been ended: " + dtf.format(after) + " </p></body>");
            output.flush();
            socket.close();
        }
        else if(newrequest.contains("stress-fix-test/")) {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Test</title></head>");
            output.println("<body><p>Stress test activated" + now + "</p></body>");
            output.println("</html>");
            output.flush();
            // We are returning a simple web page now.
            output.println();
        }
        else if (newrequest.contains("create/")) {
            try {
                String path = newrequest;
                String segments[] = path.split("/");
                String document = segments[segments.length - 1];
                File myObj = new File(document);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                    PrintWriter output = new PrintWriter(socket.getOutputStream());

                    // We are returning a simple web page now.
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println(dtf.format(now));
                    output.println("HTTP/1.1 200 OK");
                    output.println("Content-Type: text/html; charset=utf-8");
                    output.println();
                    output.println("<html>");
                    output.println("<head><title>Test</title></head>");
                    output.println("<body><p>Test file created, 204 OK </p></body>");
                    output.println("</html>");
                    output.flush();
                    // We are returning a simple web page now.
                    output.println();
                    output.flush();
                    socket.close();
                } else {
                    PrintWriter output = new PrintWriter(socket.getOutputStream());
                    // We are returning a simple web page now.
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println(dtf.format(now));
                    output.println("HTTP/1.1 400 BAD REQUEST");
                    output.println("Content-Type: text/html; charset=utf-8");
                    output.println();
                    output.println("<html>");
                    output.println("<head><title>Test</title></head>");
                    output.println("<body><p>Test file did not create, 400 Bad request </p></body>");
                    output.println("</html>");
                    output.flush();
                    // We are returning a simple web page now.
                    output.flush();
                    socket.close();
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        else if (newrequest.contains("delete/")) {
            String path = newrequest;
            String segments[] = path.split("/");
            String document = segments[segments.length - 1];
            File file = new File(document);

            if (file.delete()) {
                System.out.println("File deleted successfully");
                PrintWriter output = new PrintWriter(socket.getOutputStream());

                // We are returning a simple web page now.
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                output.println("HTTP/1.1 200 OK");
                output.println("Content-Type: text/html; charset=utf-8");
                output.println();
                output.println("<html>");
                output.println("<head><title>Test</title></head>");
                output.println("<body><p>File deleted successfully, 200 OK </p></body>");
                output.println("</html>");
                output.flush();
                // We are returning a simple web page now.
                output.flush();
                socket.close();
            }
            else {
                System.out.println("Failed to delete the file");
                PrintWriter output = new PrintWriter(socket.getOutputStream());

                // We are returning a simple web page now.
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                output.println("HTTP/1.1 200 OK");
                output.println("Content-Type: text/html; charset=utf-8");
                output.println();
                output.println("<html>");
                output.println("<head><title>Test</title></head>");
                output.println("<body><p>File deleting error, 400 OK </p></body>");
                output.println("</html>");
                output.flush();
                // We are returning a simple web page now.
                output.flush();
                socket.close();
            }
        }
        else {
            PrintWriter output = new PrintWriter(socket.getOutputStream());

            output.println("HTTP/1.1 404 Not found");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();
            output.println("<html>");
            output.println("<head><title>Error</title></head>");
            output.println("<body><p>404 Not Found</p></body>");
            output.println("</html>");
            output.flush();
            socket.close();
        }
    }

}

package org.example;

import java.io.PrintWriter;
import java.util.Random;

public class FixThread extends Thread{
    PrintWriter printWriterOutput;
    public FixThread(PrintWriter printWriterOutput) {
        this.printWriterOutput = printWriterOutput;
    }
    public double test() {
        double answer = 0;
        for(long i = 0; i <= Integer.MAX_VALUE; i+=2) {
            answer += new Random().nextDouble();
            if(i == Integer.MAX_VALUE/2) {
                return answer;
            }
        }
        return 1;
    }
    @Override
    public void run() {
        System.out.println("thread has been started");
        this.test();
        printWriterOutput.println("<h1>Success</h1>");
    }
}

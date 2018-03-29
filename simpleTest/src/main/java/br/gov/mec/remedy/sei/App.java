package br.gov.mec.remedy.sei;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Instant;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        String message = "Hello World!";

        try {

            Instant startTime = Instant.now();
            String filename = startTime.toString().replace(":", "-").replace(".", "-");
            FileWriter file = new FileWriter(filename + ".log", true);
            BufferedWriter buffer = new BufferedWriter(file);
            PrintWriter printer = new PrintWriter(buffer);
            printer.append(message);
            printer.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        System.out.println( message );

    }
}

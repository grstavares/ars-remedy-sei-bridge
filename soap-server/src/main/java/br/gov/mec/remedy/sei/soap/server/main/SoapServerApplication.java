package br.gov.mec.remedy.sei.soap.server.main;

import javax.xml.ws.Endpoint;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.gov.mec.remedy.sei.soap.server.service.impl.SeiBridgeImpl;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.Instant;

@SpringBootApplication
public class SoapServerApplication implements CommandLineRunner {

	private String defaultPort = "9001"; //Default Port
	private String defaultProtocol = "http://";

	@Override
	public void run(String... args) {

        BufferedWriter buffer = null;
        PrintWriter printer = null;

        try {

            Instant startTime = Instant.now();
            String filename = startTime.toString().replace(":", "-").replace(".", "-");
            FileWriter file = new FileWriter(filename + ".log", true);
            buffer = new BufferedWriter(file);
            printer = new PrintWriter(buffer);

        } catch (IOException e) {System.out.println(e.getLocalizedMessage());}

        if (printer != null) {printer.append("Application started at: " + Instant.now() + "\n");}

        String serviceHost = "";
	    String servicePort = "";

	    try {

	        if (args.length != 0) {

	            if (printer != null) {printer.append("Parsing parameters..." + "\n");}
                serviceHost = this.parse(RuntimeParameter.SERVER, args);
                servicePort = this.parse(RuntimeParameter.PORT, args);

            }

	        if (serviceHost.isEmpty()) {serviceHost = this.getHostName();}
            if (servicePort.isEmpty()) {servicePort = defaultPort;}

            String address = defaultProtocol + serviceHost + ":" + servicePort + "/sei/bridge";
            if (printer != null) {printer.append("Server and Port defined:" + address + "\n");}

            URL url = new URL(address);

            System.out.println("Service available at: " + url.toString());
            if (printer != null) {printer.append("WSDL available at: " + url.toString() + "?wsdl\n");}
            printer.close();

            Endpoint.publish(address, new SeiBridgeImpl());

        } catch (Exception e) {

            if (printer != null) {printer.append("SystemError: " + e.getLocalizedMessage());}
            System.out.println(" =============================== xxxx =============================== ");
	        System.out.println("SystemError: " + e.getLocalizedMessage());
            System.out.println(" =============================== xxxx =============================== \n");

	        System.exit(-1);

        }

	}

	public static void main(String[] args) {SpringApplication.run(SoapServerApplication.class, args);}

	public String parse(RuntimeParameter type, String... args) {

        String informed = "";

	    for (String param: args) {

	        String value = type.asString();
	        if (param.contains(value)) {

	            int index = param.indexOf("=") + 1;
                informed = param.substring(index);
                return informed;

            }

        }

        String protocol = "http://";
	    int index = informed.indexOf(protocol);
        if (index > 0) {informed = informed.substring(index);}
        return informed;

    }

    public String getHostName() throws UnknownHostException {

        InetAddress localhost = java.net.InetAddress.getLocalHost();
        return localhost.getCanonicalHostName();

    }

}

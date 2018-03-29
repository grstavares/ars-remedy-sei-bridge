package br.gov.mec.remedy.sei.soap.server.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SoapServerApplicationTest {

    @Before public void setUp() throws Exception {}

    @Test public void testParseParameters() {

        String[] args = {"--server=localhost", "--port=9000"};

        SoapServerApplication sut = new SoapServerApplication();
        String server = sut.parse(RuntimeParameter.SERVER, args);
        String port = sut.parse(RuntimeParameter.PORT, args);

        System.out.println(server);
        System.out.println(port);

        assertFalse(server.contains("="));
        assertFalse(port.contains("="));

    }
}
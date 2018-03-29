package br.gov.mec.bridge;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApplicationTest {

    @Test
    public void parseParameters() {

        String[] parameters = {"-srv",  "teste.mec.gov.br", "-usr", "username"};
        HashMap<String, String> options = StaticUtils.parseArgs(parameters);
        for (String key: options.keySet()) {
            System.out.println(key + ":" + options.get(key));
        }

        assertEquals(options.size(), parameters.length / 2);

    }

    @Test
    public void loadCredentials() {

        HashMap<String, String> properties = StaticUtils.loadCredentials("credentials.properties");
        assertTrue(properties.size() == 6);

    }

    @Test
    @Ignore
    public void testApplication_TestARS() {

        String[] empty = {"-testars", "yes"};
        Application.main(empty);

    }

    @Test
    @Ignore
    public void testApplication_TestSEI() {

        String[] empty = {"-testsei", "yes"};
        Application.main(empty);

    }

    @Test
    @Ignore
    public void testApplication_TestAll() {

        String[] empty = {"-testall" , "yes"};
        Application.main(empty);

    }

}

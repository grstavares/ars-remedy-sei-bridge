package br.gov.mec.remedy.sei.soap.server.main;

import br.gov.mec.remedy.sei.client.Andamento;
import br.gov.mec.remedy.sei.client.SeiClient;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class SoapClientTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testClient() {

        String numeroSEI = "23000.015796/2015-95";
        SeiClient client = new SeiClient(numeroSEI);

        try {

            Andamento[] returned = client.getAndamentos();
            assertNotNull(returned);

        } catch (RemoteException e) {fail(e.getLocalizedMessage());}

    }

}

package br.gov.mec.remedy;

import br.gov.mec.bridge.StaticUtils;
import com.bmc.arsys.api.ARException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class RemedyInterfaceTest {

    RemedyInterface server;

    @Before public void instantiateServer() {

        HashMap<String, String> properties = StaticUtils.loadCredentials("credentials.properties");
        String serverName = properties.get(PARAM_SRV);
        String port = properties.get(PARAM_PRT);
        String username = properties.get(PARAM_USR);
        String password = properties.get(PARAM_PWD);

        server = new RemedyInterface(serverName, Integer.parseInt(port), username, password);

    }

    @Ignore
    @Test public void testARSConnection() {

        assertTrue(server.connect());
        assertTrue(server.disconnect());

    }

    @Ignore
    @Test public void testGetContratos() {

        assertTrue(server.connect());
        try {

            List<ContractBase> lista = server.getContratos();
            assertTrue(lista.size() > 0);

            for(ContractBase item: lista) {System.out.println("ContractId:" + item.getContractId() + " Número SEI:" + item.getNumeroSEI());}

        } catch (ARException e) {fail(e.getLocalizedMessage());}

        assertTrue(server.disconnect());

    }

    private static final String PARAM_SRV = "server";
    private static final String PARAM_PRT = "port";
    private static final String PARAM_USR = "username";
    private static final String PARAM_PWD = "password";

}

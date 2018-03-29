package br.gov.mec.sei;

import static org.junit.Assert.*;

import br.gov.mec.sei.wsdl.Andamento;
import org.junit.Ignore;
import org.junit.Test;

public class TestBridge {

    @Ignore
	@Test
	public void testGetLancamentos() {

	    String endpoint = "http://hmg-sei3.mec.gov.br/sei/ws/SeiWS.php";
		String numero = "23000.015796/2015-95";
		Bridge bridge = new Bridge(endpoint);
		
		Andamento[] result = bridge.getLancamentos(numero);
		assertNotNull(result);

	}

	@Ignore
	@Test
	public void testGetLancamentosWithNoValues() {

        String endpoint = "http://hmg-sei3.mec.gov.br/sei/ws/SeiWS.php";
		String numero = "TESTE";
		Bridge bridge = new Bridge(endpoint);

		Andamento[] result = bridge.getLancamentos(numero);
		assertNotNull(result);

	}
}

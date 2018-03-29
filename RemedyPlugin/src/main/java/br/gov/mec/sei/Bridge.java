package br.gov.mec.sei;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import br.gov.mec.sei.wsdl.Andamento;
import br.gov.mec.sei.wsdl.SeiPortType;
import br.gov.mec.sei.wsdl.SeiServiceLocator;

public class Bridge {

    String server;

	public Bridge(String serverAddress) {

	    this.server =  serverAddress;

    }
	
	public Andamento[] getLancamentos(String numeroSEI) {

	    if (this.server == null) {
            System.out.println("SEI Endpoint not defined!");
            return null;
        }

		Andamento[] andamentos = null;
		
    	String sistema = "REMEDY";
    	String servico = "INTEGRACAO-REMEDY-SEI";
    	String unidade = "110000023";
    	String[] tarefas = {"28", "29"};
    	String[] empty = null;

		try {
			
			SeiServiceLocator locator = new SeiServiceLocator();
			locator.setSeiPortServiceEndpointAddress(this.server);
			SeiPortType port = locator.getSeiPortService();
			andamentos = port.listarAndamentos(sistema, servico, unidade, numeroSEI, "S", empty, tarefas, empty);

		} catch (ServiceException e) {e.printStackTrace();

		} catch (RemoteException e) {

            if (e.getLocalizedMessage().contains("não encontrado")) {
                return new Andamento[]{};
            } else {
                e.printStackTrace();
            }

		}
		
		return andamentos;
		
	}
	
}

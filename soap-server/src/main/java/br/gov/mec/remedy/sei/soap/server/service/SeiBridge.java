package br.gov.mec.remedy.sei.soap.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.gov.mec.remedy.sei.soap.server.model.ApplicationCredentials;
import br.gov.mec.remedy.sei.soap.server.model.Lancamento;

@WebService
public interface SeiBridge {

	@WebMethod(operationName = "getStatus", action = "https://arsint-wa-01.mec.gov.br/remedy/sei")
	String getStatus(final String name,
			@WebParam(header = true) final ApplicationCredentials credential);
	
	@WebMethod(operationName = "getLancamentos", action = "https://arsint-wa-01.mec.gov.br/remedy/sei")
	Lancamento[] getLancamentos(final String name,
			@WebParam(header = true) final ApplicationCredentials credential);
	
}

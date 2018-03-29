package br.gov.mec.remedy.sei.soap.server.service.impl;

import javax.jws.WebService;

import br.gov.mec.remedy.sei.client.Andamento;
import br.gov.mec.remedy.sei.client.SeiClient;
import br.gov.mec.remedy.sei.soap.server.model.ApplicationCredentials;
import br.gov.mec.remedy.sei.soap.server.model.Lancamento;
import br.gov.mec.remedy.sei.soap.server.service.SeiBridge;

import java.rmi.RemoteException;

@WebService(endpointInterface = "br.gov.mec.remedy.sei.soap.server.service.SeiBridge")
public class SeiBridgeImpl implements SeiBridge {

	@Override
	public String getStatus(String name, ApplicationCredentials credential) {
		return "getStatus Working for " + name;
	}

	@Override
	public Lancamento[] getLancamentos(String name, ApplicationCredentials credential) {

        SeiClient client = new SeiClient(name);

        try {

            Andamento[] andamentos = client.getAndamentos();
            Lancamento[] lancamentos = new Lancamento[andamentos.length];

            for (int i =0; i < andamentos.length; i++) {lancamentos[i] = this.parseAndamento(andamentos[i]);}
            return lancamentos;

        } catch (RemoteException e) {

            System.out.println(e.getLocalizedMessage());
            Lancamento[] empty = new Lancamento[0];
            return empty;
        }

	}

	private Lancamento parseAndamento(Andamento andamento) {

	    Lancamento novo = new Lancamento();
	    novo.setDataHora(andamento.getDataHora());
	    novo.setDescricao(andamento.getDescricao());
	    novo.setUnidade(andamento.getUnidade().getSigla());
	    novo.setUsuario(andamento.getUsuario().getNome());
	    novo.setIdAndamento(andamento.getIdAndamento());
	    novo.setIdTarefa(andamento.getIdTarefa());
	    return novo;

    }

}

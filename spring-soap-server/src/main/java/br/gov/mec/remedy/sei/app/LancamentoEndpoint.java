package br.gov.mec.remedy.sei.app;

import br.gov.mec.remedy.sei.GetLancamentoRequest;
import br.gov.mec.remedy.sei.GetLancamentoResponse;
import br.gov.mec.remedy.sei.Lancamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.rmi.RemoteException;
import java.util.ArrayList;

@Endpoint
public class LancamentoEndpoint {
	private static final String NAMESPACE_URI = "http://sei.remedy.mec.gov.br";

	private LancamentoRepository countryRepository;

	@Autowired
	public LancamentoEndpoint(LancamentoRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLancamentoRequest")
	@ResponsePayload
	public GetLancamentoResponse getCountry(@RequestPayload GetLancamentoRequest request) {

		GetLancamentoResponse response = new GetLancamentoResponse();

		String key = request.getNumeroSEI();
        if (validateNumeroSEI(key)) {

            ArrayList<Lancamento> returned = countryRepository.getAllItens();
            response.getLancamento().addAll(returned);

//            try {
//
//                SeiClient client = new SeiClient(key);
//                Andamento[] andamentos = client.getAndamentos();
//                response.getLancamento().addAll((this.parseAndamentos(andamentos)));
//
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }

        }

		return response;

	}

	private boolean validateNumeroSEI(String queryValue) {
	    return true;
    }

//    private ArrayList<Lancamento> parseAndamentos(Andamento[] andamentos) {
//
//	    ArrayList<Lancamento> lista = new ArrayList<>();
//
//	    for (Andamento andamento: andamentos) {
//
//	        Lancamento parsed = new Lancamento();
//	        parsed.setDataHora(andamento.getDataHora());
//	        parsed.setIdAndamento(andamento.getIdAndamento());
//	        parsed.setIdTarefa(andamento.getIdTarefa());
//	        parsed.setDescricao(andamento.getDescricao());
//	        parsed.setUnidade(andamento.getUnidade().getSigla());
//	        parsed.setUsuario(andamento.getUsuario().getNome());
//	        lista.add(parsed);
//
//        }
//
//        return lista;
//
//    }

}

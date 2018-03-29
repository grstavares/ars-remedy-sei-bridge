package br.gov.mec.remedy.sei.app;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.gov.mec.remedy.sei.Lancamento;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class LancamentoRepository {
	private static final Map<String, Lancamento> database = new HashMap<>();

	@PostConstruct
	public void initData() {

	    for (int i=0; i < 3; i++) {

            Lancamento mock = new Lancamento();
            mock.setNumeroSEI("numSEI00" + i);
            mock.setUnidade("Unidade-" + i);
            mock.setUsuario("usuario-" + i);
            mock.setOperacao("Oper-" + i);
            mock.setIdTarefa("idTar-" + i);
            mock.setIdAndamento("idAnda-" + i);
            mock.setDescricao("desc-" + i);
            mock.setDataHora("dataHora" + i);
	        database.put(mock.getNumeroSEI(), mock);

        }

	}

	public ArrayList<Lancamento> getAllItens() {

	    return new ArrayList<>(database.values());

    }

	public Lancamento getByNumeroSEI(String numeroSEI) {
		Assert.notNull(numeroSEI, "The country's name must not be null");
		return database.get(numeroSEI);
	}
}

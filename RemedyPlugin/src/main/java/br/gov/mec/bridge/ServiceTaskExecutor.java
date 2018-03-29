package br.gov.mec.bridge;

import br.gov.mec.remedy.ContractBase;
import br.gov.mec.remedy.ContractSEI;
import br.gov.mec.remedy.RemedyInterface;
import br.gov.mec.sei.Bridge;
import br.gov.mec.sei.wsdl.Andamento;
import com.bmc.arsys.api.ARException;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class ServiceTaskExecutor extends TimerTask {

    String seiendpoint;
    String serverName;
    String serverUser;
    String serverPwd;
    int serverPort;

    public ServiceTaskExecutor(String endpoint, String serverName, int serverPort, String serverUser, String serverPwd) {
        this.seiendpoint = endpoint;
        this.serverName = serverName;
        this.serverUser = serverUser;
        this.serverPwd = serverPwd;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {

        Instant begin = Instant.now();

        RemedyInterface server = new RemedyInterface(serverName, serverPort, serverUser, serverPwd);

        if (server.connect()) {

            int updateCount = 0;

            try {

                System.out.println("Buscando contratos no ARS...");
                List<ContractBase> contratos = server.getContratos();
                System.out.println(contratos.size() + " contratos encontrados do ARS!");

                System.out.println("Filtrando contratos com número do SEI...");
                List<ContractBase> filtrados = contratos.stream().filter(c -> c.getNumeroSEI() != null).collect(Collectors.toList());
                System.out.println(filtrados.size() + " contratos com número do SEI preenchidos!");

                Bridge bridge = new Bridge(this.seiendpoint);

                for (ContractBase contrato: filtrados) {

                    System.out.println("Iniciando processamento do contrato " + contrato.getContractId());

                    System.out.println("Buscando lançamentos do Contrato no ARS...");
                    List<ContractSEI> lancamentos = server.getLancamentos(contrato);
                    System.out.println(lancamentos.size() + " lançamentos já cadastrados no Remedy!");

                    String numSEI = contrato.getNumeroSEI();
                    System.out.println("Recuperando andamentos do SEI para o número " + numSEI + "...");
                    Andamento[] andamentos = bridge.getLancamentos(numSEI);
                    System.out.println(andamentos.length + " Andamentos recuperados para o contrato " + numSEI + " no SEI!");

                    System.out.println("Buscando novos lancamentos...");
                    Set<String> cadastrados = lancamentos.stream().map(l -> l.getIdAndamento()).collect(Collectors.toSet());
                    List<Andamento> novos = Arrays.stream(andamentos).filter(a -> !cadastrados.contains(a.getIdAndamento())).collect(Collectors.toList());

                    System.out.println(novos.size() + " Lançamentos a serem adicionados!");

                    System.out.println("Adicionando novos registros no ARS para o contrato " + contrato.getContractId() + "...");
                    novos.forEach(n -> server.addLancamento(contrato, new ContractSEI(n)));
                    System.out.println("Novos Registros Adicionados!");
                    updateCount =+ 1;

                }

                System.out.println("Fechando conexão...");
                server.disconnect();

                Duration elapsedTime = Duration.between(begin, Instant.now());
                System.out.println("Conexão fechada!! " + updateCount + " contratos atualizados... Tempo de Processamento:" + elapsedTime.getSeconds() + " segundos!");
                System.out.println("\n");


            } catch (ARException e) {
                System.out.println(e.getLocalizedMessage());
            }

        } else {

            System.out.println("Não foi possível realizar a conexão com o banco de dados!");

        }

    }
}

package br.gov.mec.remedy;

import com.bmc.arsys.api.*;

import java.util.List;
import java.util.stream.Collectors;

public class RemedyInterface {

    private String serverName;
    private int serverPort;
    private String username;
    private String password;

    private ARServerUser server;

    public RemedyInterface(String server, int serverPort, String user, String password) {

        this.serverName = server;
        this.serverPort = serverPort;
        this.username = user;
        this.password = password;

        this.server = new ARServerUser();

    }

    public boolean connect() {

        System.out.println("conectando ao servidor ARSystem: " + this.serverName);

        this.server.setServer(this.serverName);
        this.server.setPort(this.serverPort);
        this.server.setUser(this.username);
        this.server.setPassword(this.password);
        try {

            this.server.login();
            boolean connected = this.checkConnection();
            if (connected) {System.out.println("Conectado!");}
            return connected;

        } catch (ARException e) {e.printStackTrace();return false;}

    }

    public List<ContractBase> getContratos() throws ARException {

        if (!this.checkConnection()) {throw new RuntimeException("Conexão ao servidor inválida!");}

        String formName = ContractBase.formName;
        int[] fieldList = ContractBase.allFields;
        QualifierInfo query = server.parseQualification(formName, "'Contract ID+' != $NULL$");
        List<Entry> entries = this.server.getListEntryObjects(formName, query, 0, 0, null, fieldList, false, null);
        List<ContractBase> contratos = entries.stream().map(ContractBase::new).collect(Collectors.toList());
        return contratos;

    }

    public List<ContractSEI> getLancamentos(ContractBase contrato) throws ARException {

        if (!this.checkConnection()) {throw new RuntimeException("Conexão ao servidor inválida!");}

        String formName = ContractSEI.formName;
        int[] fieldList = ContractSEI.allFields;
        QualifierInfo query = server.parseQualification(formName, "'ContractId' = \"" + contrato.getContractId() + "\"");
        List<Entry> entries = this.server.getListEntryObjects(formName, query, 0, 0, null, fieldList, false, null);
        List<ContractSEI> lancamentos = entries.stream().map(ContractSEI::new).collect(Collectors.toList());
        return lancamentos;

    }

    public boolean addLancamento(ContractBase contract, ContractSEI lancamento) {

        lancamento.setContractId(contract.getContractId());
        lancamento.setNumeroSEI(contract.getNumeroSEI());

        try {

            Entry arsEntry = new Entry();
            arsEntry.put(Constants.AR_CORE_SUBMITTER, new Value(server.getUser()));
            arsEntry.put(Constants.AR_CORE_STATUS, new Value(4, DataType.ENUM));
            arsEntry.put(Constants.AR_CORE_SHORT_DESCRIPTION, new Value(lancamento.getDescTarefa()));
            arsEntry.put(ContractSEI.FIELD_ContractID, new Value(lancamento.getContractId()));
            arsEntry.put(ContractSEI.FIELD_NumeroSEI, new Value(lancamento.getNumeroSEI()));
            arsEntry.put(ContractSEI.FIELD_DataHora, new Value(lancamento.getDataHora()));
            arsEntry.put(ContractSEI.FIELD_Descricao, new Value(lancamento.getDescricao()));
            arsEntry.put(ContractSEI.FIELD_IdAndamento, new Value(lancamento.getIdAndamento()));
            arsEntry.put(ContractSEI.FIELD_IdTarefa, new Value(lancamento.getIdTarefa()));
            arsEntry.put(ContractSEI.FIELD_DescTarefa, new Value(lancamento.getDescTarefa()));
            arsEntry.put(ContractSEI.FIELD_IdUnidade, new Value(lancamento.getIdUnidade()));
            arsEntry.put(ContractSEI.FIELD_SiglaUnidade, new Value(lancamento.getSiglaUnidade()));
            arsEntry.put(ContractSEI.FIELD_NomeUnidade, new Value(lancamento.getNomeUnidade()));
            arsEntry.put(ContractSEI.FIELD_IdUsuario, new Value(lancamento.getIdUsuario()));
            arsEntry.put(ContractSEI.FIELD_SiglaUsuario, new Value(lancamento.getSiglaUsuario()));
            arsEntry.put(ContractSEI.FIELD_NomeUsuario, new Value(lancamento.getNomeUsuario()));

            String entryId = server.createEntry(ContractSEI.formName, arsEntry);
            lancamento.setArsEntryId(entryId);
            return true;

        } catch (ARException e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean checkConnection() {

        try {this.server.verifyUser();
        } catch (ARException e) {

            e.printStackTrace();
            System.out.println("Conexão não foi possível - " + e.getLocalizedMessage());
            return false;
        }

        return true;

    }

    public boolean disconnect() {

        server.logout();
        System.out.println("Desconectado!");
        return true;

    }
}

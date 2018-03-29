package br.gov.mec.remedy;

import br.gov.mec.bridge.StaticUtils;
import br.gov.mec.sei.wsdl.Andamento;
import com.bmc.arsys.api.Entry;

/**
 * Representa um objeto de Lancamento do SEI dentro do ARS Remedy.
 * <p>
 * Esta classe representa dentro do sistema um Lançamento do SEI que
 * esta presente como um formulário customizado do ARS Remedy. Ela
 * encapsula os campos a serem expostos bem como controla o nome
 * do formulário a ser consultado.
 */
public class ContractSEI {

    private String arsEntryId;
    private String contractId;
    private String numeroSEI;
    private String dataHora;
    private String idAndamento;
    private String idTarefa;
    private String descTarefa;
    private String descricao;
    private String idUnidade;
    private String siglaUnidade;
    private String nomeUnidade;
    private String idUsuario;
    private String siglaUsuario;
    private String nomeUsuario;

    public ContractSEI(Entry arsEntry) {

        this.parseEntry(arsEntry);

    }

    public ContractSEI(Andamento andamento) {

        this.parseAndamento(andamento);

    }

    private void parseEntry(Entry arsEntry) {

        for (int i:arsEntry.keySet()) {

            switch (i) {
                case FIELD_RequestID: this.arsEntryId = arsEntry.get(i).toString();break;
                case FIELD_ContractID: this.contractId = arsEntry.get(i).toString();break;
                case FIELD_IdAndamento: this.idAndamento = arsEntry.get(i).toString();break;
                case FIELD_IdTarefa: this.idTarefa = arsEntry.get(i).toString();break;
                case FIELD_DescTarefa: this.descTarefa = arsEntry.get(i).toString();break;
                case FIELD_DataHora: this.dataHora = arsEntry.get(i).toString();break;
                case FIELD_NumeroSEI: this.numeroSEI = arsEntry.get(i).toString();break;
                case FIELD_Descricao: this.descricao = arsEntry.get(i).toString();break;
                case FIELD_IdUnidade: this.idUnidade = arsEntry.get(i).toString();break;
                case FIELD_SiglaUnidade: this.siglaUnidade = arsEntry.get(i).toString();break;
                case FIELD_NomeUnidade: this.nomeUnidade = arsEntry.get(i).toString();break;
                case FIELD_IdUsuario: this.idUsuario = arsEntry.get(i).toString();break;
                case FIELD_SiglaUsuario: this.siglaUsuario = arsEntry.get(i).toString();break;
                case FIELD_NomeUsuario: this.nomeUsuario = arsEntry.get(i).toString();break;

                default: break;
            }

        }

    }

    private void parseAndamento(Andamento andamento) {

        String idTarefa = StaticUtils.trim(andamento.getIdTarefa());
        String descricao = this.parseDescricao(idTarefa);

        this.setIdAndamento(andamento.getIdAndamento());
        this.setIdTarefa(idTarefa);
        this.setDescTarefa(descricao);
        this.setDescricao(descricao);
        this.setDataHora(andamento.getDataHora());
        this.setDescricao(andamento.getDescricao());
        this.setIdUnidade(andamento.getUnidade().getIdUnidade());
        this.setSiglaUnidade(andamento.getUnidade().getSigla());
        this.setNomeUnidade(andamento.getUnidade().getDescricao());
        this.setIdUsuario(andamento.getUsuario().getIdUsuario());
        this.setSiglaUsuario(andamento.getUsuario().getSigla());
        this.setNomeUsuario(andamento.getUsuario().getNome());

    }

    private String parseDescricao(String idTarefa) {

        switch (idTarefa) {
            case "28": return "Processo Concluído na Unidade";
            case "29": return "Processo Reaberto na Unidade";
            default: return "undefined";
        }

    }

    public String getArsEntryId() {return arsEntryId;}
    public void setArsEntryId(String arsEntryId) {this.arsEntryId = arsEntryId;}
    public String getContractId() {return contractId;}
    public void setContractId(String contractId) {this.contractId = contractId;}
    public String getNumeroSEI() {return numeroSEI;}
    public void setNumeroSEI(String numeroSEI) {this.numeroSEI = numeroSEI;}
    public String getDataHora() {return dataHora;}
    public void setDataHora(String dataHora) {this.dataHora = dataHora;}
    public String getIdAndamento() {return idAndamento;}
    public void setIdAndamento(String idAndamento) {this.idAndamento = idAndamento;}
    public String getIdTarefa() {return idTarefa;}
    public void setIdTarefa(String idTarefa) {this.idTarefa = idTarefa;}
    public String getDescTarefa() {return descTarefa;}
    public void setDescTarefa(String descTarefa) {this.descTarefa = descTarefa;}
    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
    public String getIdUnidade() {return idUnidade;}
    public void setIdUnidade(String idUnidade) {this.idUnidade = idUnidade;}
    public String getSiglaUnidade() {return siglaUnidade;}
    public void setSiglaUnidade(String siglaUnidade) {this.siglaUnidade = siglaUnidade;}
    public String getNomeUnidade() {return nomeUnidade;}
    public void setNomeUnidade(String nomeUnidade) {this.nomeUnidade = nomeUnidade;}
    public String getIdUsuario() {return idUsuario;}
    public void setIdUsuario(String idUsuario) {this.idUsuario = idUsuario;}
    public String getSiglaUsuario() {return siglaUsuario;}
    public void setSiglaUsuario(String siglaUsuario) {this.siglaUsuario = siglaUsuario;}
    public String getNomeUsuario() {return nomeUsuario;}
    public void setNomeUsuario(String nomeUsuario) {this.nomeUsuario = nomeUsuario;}

    public static final String formName = "CTR:MEC:LancamentoSEI";
    public static final int FIELD_RequestID = 1;
    public static final int FIELD_ContractID = 536870913;
    public static final int FIELD_NumeroSEI = 536870914;
    public static final int FIELD_IdAndamento = 536870915;
    public static final int FIELD_IdTarefa = 536870916;
    public static final int FIELD_DescTarefa = 536870917;
    public static final int FIELD_Descricao = 536870918;
    public static final int FIELD_IdUnidade = 536870919;
    public static final int FIELD_SiglaUnidade = 536870920;
    public static final int FIELD_NomeUnidade = 536870921;
    public static final int FIELD_IdUsuario = 536870922;
    public static final int FIELD_SiglaUsuario = 536870923;
    public static final int FIELD_NomeUsuario = 536870924;
    public static final int FIELD_DataHora = 536870925;

    public static final int[] allFields  = {FIELD_RequestID, FIELD_ContractID, FIELD_NumeroSEI,
            FIELD_IdAndamento, FIELD_IdTarefa, FIELD_DescTarefa, FIELD_Descricao, FIELD_DataHora,
            FIELD_IdUnidade, FIELD_SiglaUnidade, FIELD_NomeUnidade,
            FIELD_IdUsuario, FIELD_SiglaUsuario, FIELD_NomeUsuario};

}

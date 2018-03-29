package br.gov.mec.remedy;

import com.bmc.arsys.api.Entry;

/**
 * Representa um objeto de Contrato dentro do ARS Remedy.
 * <p>
 * Esta classe representa dentro do sistema um objeto de Contrato que
 * pertence ao ARS Remedy. Ela encapsula os campos a serem expostos bem
 * como controla o nome do formulário a ser consultado.
 */
public class ContractBase {

    private String arsEntryId;
    private String contractId;
    private String summary;
    private String status;
    private String statusReason;
    private String numeroSEI;

    public ContractBase() {}

    public ContractBase(Entry arsEntry) {

        this.parseEntry(arsEntry);

    }

    private void parseEntry(Entry arsEntry) {

        for (int i:arsEntry.keySet()) {

            switch (i) {
                case FIELD_EntryId: this.arsEntryId = arsEntry.get(i).toString();break;
                case FIELD_ID: this.contractId = arsEntry.get(i).toString();break;
                case FIELD_Summary: this.summary = arsEntry.get(i).toString();break;
                case FIELD_Status: this.status = arsEntry.get(i).toString();break;
                case FIELD_StatusReason: this.statusReason = arsEntry.get(i).toString();break;
                case FIELD_NumeroSEI: this.numeroSEI = arsEntry.get(i).toString();break;
                default: break;
            }

        }

    }

    public String getArsEntryId() {return arsEntryId;}
    public void setArsEntryId(String arsEntryId) {this.arsEntryId = arsEntryId;}
    public String getContractId() {return contractId;}
    public void setContractId(String contractId) {this.contractId = contractId;}
    public String getSummary() {return summary;}
    public void setSummary(String summary) {this.summary = summary;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getStatusReason() {return statusReason;}
    public void setStatusReason(String statusReason) {this.statusReason = statusReason;}
    public String getNumeroSEI() {return numeroSEI;}
    public void setNumeroSEI(String numeroSEI) {this.numeroSEI = numeroSEI;}

    public static final String formName = "CTR:ContractBase";
    public static final int FIELD_EntryId = 1;
    public static final int FIELD_Summary = 8;
    public static final int FIELD_Status = 7;
    public static final int FIELD_ID = 260000000;
    public static final int FIELD_Type = 200000103;
    public static final int FIELD_StatusReason = 1000000881;
    public static final int FIELD_StartDate = 260000001;
    public static final int FIELD_UASG = 536870913;
    public static final int FIELD_OrigemPregao = 536870915;
    public static final int FIELD_AtaRegistro = 536870916;
    public static final int FIELD_Objeto = 536870917;
    public static final int FIELD_NumeroSEI = 536870920;
	public static final int FIELD_Preposto = 536870914;

	public static final int[] allFields  = {FIELD_EntryId, FIELD_Summary, FIELD_Status,
            FIELD_ID, FIELD_Type, FIELD_StatusReason, FIELD_StartDate, FIELD_UASG,
            FIELD_OrigemPregao, FIELD_AtaRegistro, FIELD_Objeto, FIELD_NumeroSEI, FIELD_Preposto};

}

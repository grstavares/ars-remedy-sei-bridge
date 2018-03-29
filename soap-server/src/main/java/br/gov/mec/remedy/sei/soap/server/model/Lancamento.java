package br.gov.mec.remedy.sei.soap.server.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Lancamento")
public class Lancamento {

    @XmlElement(name = "idAndamento", required = true)	private String idAndamento;
    @XmlElement(name = "idTarefa", required = true)		private String idTarefa;
	@XmlElement(name = "dataHora", required = true)		private String dataHora;
	@XmlElement(name = "descricao", required = true)	private String descricao;
	@XmlElement(name = "unidade", required = true) 		private String unidade;
	@XmlElement(name = "usuario", required = true) 		private String usuario;
	
	public String getDataHora() {return this.dataHora;}
	public String getDescricao() {return this.descricao;}
	public String getUnidade() {return this.unidade;}
	public String getUsuario() {return this.usuario;}
    public String getIdAndamento() {return idAndamento;}
    public String getIdTarefa() {return idTarefa;}

	public void setDataHora(String dataHora) {this.dataHora = dataHora;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public void setUnidade(String unidade) {this.unidade = unidade;}
	public void setUsuario(String usuario) {this.usuario = usuario;}
    public void setIdAndamento(String idAndamento) {this.idAndamento = idAndamento;}
    public void setIdTarefa(String idTarefa) {this.idTarefa = idTarefa;}

    @Override
    public String toString() {
        return "Lancamento{" +
                "idAndamento='" + idAndamento + '\'' +
                ", idTarefa='" + idTarefa + '\'' +
                ", dataHora='" + dataHora + '\'' +
                ", descricao='" + descricao + '\'' +
                ", unidade='" + unidade + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}

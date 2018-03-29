//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2018.03.23 às 01:53:34 PM BRT 
//


package br.gov.mec.remedy.sei;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroSEI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idAndamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idTarefa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="unidade" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataHora" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operacao" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "numeroSEI",
    "idAndamento",
    "idTarefa",
    "unidade",
    "usuario",
    "dataHora",
    "descricao",
    "operacao"
})
@XmlRootElement(name = "Lancamento")
public class Lancamento {

    @XmlElement(required = true)
    protected String numeroSEI;
    @XmlElement(required = true)
    protected String idAndamento;
    @XmlElement(required = true)
    protected String idTarefa;
    @XmlElement(required = true)
    protected String unidade;
    @XmlElement(required = true)
    protected String usuario;
    @XmlElement(required = true)
    protected String dataHora;
    @XmlElement(required = true)
    protected String descricao;
    @XmlElement(required = true)
    protected String operacao;

    /**
     * Obtém o valor da propriedade numeroSEI.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroSEI() {
        return numeroSEI;
    }

    /**
     * Define o valor da propriedade numeroSEI.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroSEI(String value) {
        this.numeroSEI = value;
    }

    /**
     * Obtém o valor da propriedade idAndamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAndamento() {
        return idAndamento;
    }

    /**
     * Define o valor da propriedade idAndamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAndamento(String value) {
        this.idAndamento = value;
    }

    /**
     * Obtém o valor da propriedade idTarefa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTarefa() {
        return idTarefa;
    }

    /**
     * Define o valor da propriedade idTarefa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTarefa(String value) {
        this.idTarefa = value;
    }

    /**
     * Obtém o valor da propriedade unidade.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * Define o valor da propriedade unidade.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidade(String value) {
        this.unidade = value;
    }

    /**
     * Obtém o valor da propriedade usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define o valor da propriedade usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtém o valor da propriedade dataHora.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataHora() {
        return dataHora;
    }

    /**
     * Define o valor da propriedade dataHora.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataHora(String value) {
        this.dataHora = value;
    }

    /**
     * Obtém o valor da propriedade descricao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define o valor da propriedade descricao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricao(String value) {
        this.descricao = value;
    }

    /**
     * Obtém o valor da propriedade operacao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperacao() {
        return operacao;
    }

    /**
     * Define o valor da propriedade operacao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperacao(String value) {
        this.operacao = value;
    }

}

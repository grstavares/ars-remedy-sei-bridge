//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.7 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2018.03.23 às 01:53:34 PM BRT 
//


package br.gov.mec.remedy.sei;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.gov.mec.remedy.sei package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.gov.mec.remedy.sei
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetLancamentoRequest }
     * 
     */
    public GetLancamentoRequest createGetLancamentoRequest() {
        return new GetLancamentoRequest();
    }

    /**
     * Create an instance of {@link Lancamento }
     * 
     */
    public Lancamento createLancamento() {
        return new Lancamento();
    }

    /**
     * Create an instance of {@link GetLancamentoResponse }
     * 
     */
    public GetLancamentoResponse createGetLancamentoResponse() {
        return new GetLancamentoResponse();
    }

}

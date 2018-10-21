/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Carlos
 */
public class ControlDom {

    public ControlDom() {
    }

    /**
     * Lee de un archivo XML y devuelve un Document listo para ser tratado
     * @param archivo
     * @return
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException 
     */
    public Document deXMLaDOM(File archivo) throws SAXException, IOException, ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo);
        doc.normalize();
        return doc;
    }

    /**
     * Lee de la clase Document y pasa su contenido a archivo xml con arbol de nodos
     * @param doc
     * @param file
     * @throws TransformerException 
     */
    public void deDOMaXML(Document doc, File file) throws TransformerException {
        Transformer trans = TransformerFactory.newInstance().newTransformer();
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(file);
        DOMSource source = new DOMSource(doc);
        trans.transform(source, result);
    }

    /**
     * Crea una instancia de documento vacio, listo para poder escribir en el
     * @return
     * @throws ParserConfigurationException
     * @throws ParserConfigurationException 
     */
    public Document instanciarDocumento() throws ParserConfigurationException, ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        return doc;
    }

    /**
     * Metodo que devuelve el contenido de una etiqueta
     * @param etiqueta
     * @param item
     * @return Valor devuelto en forma de string con el contenido de una etiqueta
     */
    public static String getValorEtiqueta(String etiqueta, Element item) {
        Node nValue = item.getElementsByTagName(etiqueta).item(0);
        return nValue.getChildNodes().item(0).getNodeValue();
    }

    /**
     * Metodo que devuelve el contenido de un atributo de un etiqueta
     * @param atributo Atributo que queremos leer
     * @param item Elemento que contiene al atributo
     * @return Valor en forma de string que sera devuelto
     */
    public static Element getElementEtiqueta(String atributo, Element item) {
        return (Element) item.getElementsByTagName(atributo).item(0);
    }

}

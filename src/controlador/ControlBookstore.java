/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import modelo.Book;
import modelo.Bookstore;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Carlos
 */
public class ControlBookstore extends ControlDom {

    ArrayList<Book> listaLibros;
    Bookstore bs;

    public Document recuperar(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
        Document doc = null;
        doc = deXMLaDOM(xmlFile);
        return doc;
    }

    public void almacenar(Document doc, File archivoDestino) throws TransformerException {
        deDOMaXML(doc, archivoDestino);
    }

    public Bookstore leerBookstore(Document doc) {
        Element raiz = doc.getDocumentElement();
        NodeList listaLibros = raiz.getChildNodes();
        Bookstore bs = new Bookstore();
        ControlBook cb = new ControlBook();
        for (int i = 0; i < listaLibros.getLength(); i++) {
            if (listaLibros.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Book b = cb.leerBook(doc, (Element) listaLibros.item(i));
                System.out.println("Leido book " + b.toString());
                bs.add(b);
            }
        }
        System.out.println("Se devuelve bookstore con " + bs.size() + " libros");
        return bs;
    }

    public Document escribirBookstore(Document doc, Bookstore bs, Element raiz) {
        ControlBook cb = new ControlBook();
        for (int i = 0; i < bs.size(); i++) {
            Element etiquetaBook = doc.createElement("book");
            raiz.appendChild(etiquetaBook);
            etiquetaBook.setAttribute("category", bs.get(i).getCategoria());
            if (bs.get(i).getCover() != "") {
                etiquetaBook.setAttribute("cover", bs.get(i).getCover());
            }
            cb.escribirBook(doc, etiquetaBook, bs.get(i));
        }
        return doc;
    }

}

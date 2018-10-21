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
    Bookstore bStore;

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
        NodeList listaDeLibros = raiz.getChildNodes();
        Bookstore bookStore = new Bookstore();
        ControlBook cb = new ControlBook();
        for (int i = 0; i < listaDeLibros.getLength(); i++) {
            if (listaDeLibros.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Book b = cb.leerBook(doc, (Element) listaDeLibros.item(i));
                System.out.println("Leido book " + b.toString());
                bookStore.add(b);
            }
        }
        System.out.println("Se devuelve bookstore con " + bookStore.size() + " libros");
        return bookStore;
    }

    public Document escribirBookstore(Document doc, Bookstore bs, Element raiz) {
        ControlBook cBook = new ControlBook();
        for (int i = 0; i < bs.size(); i++) {
            Element etiquetaBook = doc.createElement("book");
            raiz.appendChild(etiquetaBook);
            etiquetaBook.setAttribute("category", bs.get(i).getCategoria());
            if (bs.get(i).getCover() != "") {
                etiquetaBook.setAttribute("cover", bs.get(i).getCover());
            }
            cBook.escribirBook(doc, etiquetaBook, bs.get(i));
        }
        return doc;
    }

}

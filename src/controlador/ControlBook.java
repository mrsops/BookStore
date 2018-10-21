/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import javax.xml.soap.Node;
import modelo.Autor;
import modelo.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Carlos
 */
public class ControlBook extends ControlDom {

    public ControlBook() {
    }

    public Book leerBook(Document doc, Element book) {
        Book libro = new Book();
        Constantes cons = new Constantes();
        String titulo, autor, año, precio;
        libro.setAño(getValorEtiqueta(cons.ET_AUTOR, book));
        libro.setPrecio(getValorEtiqueta(cons.ET_PRECIO, book));
        libro.setTitulo(getValorEtiqueta(cons.ET_TITULO, book));

        Element eAutores = getElementEtiqueta(cons.ET_AUTORES, book);
        System.out.println("Le paso la etiqueta " + eAutores.getNodeName() + " con " + eAutores.getChildNodes().getLength());
        libro.setAutores(leerAutores(eAutores));

        libro.setCategoria(book.getAttribute("category"));
        libro.setCover(book.getAttribute("cover"));
        return libro;
    }

    public void escribirBook(Document doc, Element book, Book libro) {
        Constantes cons = new Constantes();

        Element title = doc.createElement(cons.ET_TITULO);
        title.appendChild(doc.createTextNode(libro.getTitulo()));
        book.appendChild(title);

        Element eAutores = doc.createElement(cons.ET_AUTORES);
        escribirAutores(doc, eAutores, libro);
        book.appendChild(eAutores);

        Element year = doc.createElement(cons.ET_AÑO);
        year.appendChild(doc.createTextNode(libro.getAño()));
        book.appendChild(year);

        Element price = doc.createElement(cons.ET_PRECIO);
        price.appendChild(doc.createTextNode(libro.getPrecio()));
        book.appendChild(price);
    }

    public ArrayList<Autor> leerAutores(Element eAutores) {
        Constantes cons = new Constantes();
        ArrayList<Autor> listaAutoresArray = new ArrayList<>();

        NodeList listaAutores = eAutores.getElementsByTagName(cons.ET_AUTOR);
        for (int i = 0; i < listaAutores.getLength(); i++) {
            Autor autor = new Autor(listaAutores.item(i).getTextContent());
            //System.out.println("Leo autor "+autor.toString());
            listaAutoresArray.add(autor);
        }
        return listaAutoresArray;
    }

    public void escribirAutores(Document doc, Element eAutores, Book b) {
        Constantes cons = new Constantes();

        for (int i = 0; i < b.getAutores().size(); i++) {
            Element autor = doc.createElement(cons.ET_AUTOR);
            autor.appendChild(doc.createTextNode(b.getAutores().get(i).getNombre()));
            eAutores.appendChild(autor);
        }
    }

}

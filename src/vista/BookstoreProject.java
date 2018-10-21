/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.Constantes;
import controlador.ControlBookstore;
import controlador.ControlDom;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import modelo.Bookstore;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Carlos
 */
public class BookstoreProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        Scanner teclado = new Scanner(System.in);
        ControlDom ctrlDoc = new ControlDom();
        Constantes cons = new Constantes();
        ControlBookstore cb = new ControlBookstore();
        Document doc = null;
        Document docNuevo = null;
        Bookstore bs = null;
        Bookstore bs2 = null;
        int opcion = 999;
        String ruta = "", rutaAlmacenarXml = null;
        while (opcion != 0) {
            mostrarMenu();
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1: //Seleccionar fichero XML
                    System.out.print("Introduce la ruta del bookstore a leer (En blanco para bookstore por defecto): ");
                    ruta = teclado.nextLine();
                    if (ruta != "") {
                        doc = ctrlDoc.deXMLaDOM( new File("Bookstores/bookstore.xml"));
                    } else {
                        doc = ctrlDoc.deXMLaDOM( new File(ruta));
                    }
                    break;
                case 2: // Crear bookstore y leer del document
                    bs = new Bookstore();
                    bs = cb.leerBookstore(doc);
                    break;
                case 3: // Mostrar el bookstore
                    bs.mostrarBookstore();
                    break;
                case 4: // Crear un nuevo bookstore y añadir libros
                    bs2 = new Bookstore();
                    bs2 = cons.altaBookStore(bs2);
                    bs2.mostrarBookstore();
                    break;
                case 5: // Escribir el objeto bookstore en un nuevo documento
                    docNuevo = ctrlDoc.instanciarDocumento();
                    Element raiz = docNuevo.createElement("bookstore");
                    docNuevo.appendChild(raiz);
                    System.out.println("Mi raiz es  " + docNuevo.getDocumentElement().getTagName());
                    cb.escribirBookstore(docNuevo, bs2, raiz);
                    System.out.println("DOM GENERADO CON EXITO");
                    break;
                case 6: //Seleccionar el nombre del fichero donde almacenarlo
                    rutaAlmacenarXml = teclado.nextLine();
                    break;
                case 7: //Guardar el documento a xml
                    ctrlDoc.deDOMaXML(docNuevo, new File(rutaAlmacenarXml));
                    break;
            }
        }

    }

    public static void mostrarMenu() { //TODO PARA CAMBIAR TEXTOS
        System.out.println("1. Seleccionar fichero xml para nuevo documento");
        System.out.println("2. Leer documento y crear objeto Bookstore a partir de el");
        System.out.println("3.-Mostrar el BookStore");
        System.out.println("4.-Crear nuevo Bookstore, y añadir libros en el");
        System.out.println("5.-Escribir el bookstore a documento");
        System.out.println("6.-Introducir ruta destino del archivo xml (Solo nombre sin extension)");
        System.out.println("7.-Guardar el documento a XML");
        System.out.println("0. Salir");
    }
}

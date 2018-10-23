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
import modelo.Autor;
import modelo.Book;
import modelo.Bookstore;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Carlos
 */
public class BookstoreMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        limpiar();
        Scanner tc = new Scanner(System.in);
        ControlDom ctrlDoc = new ControlDom();
        Constantes cons = new Constantes();
        ControlBookstore cb = new ControlBookstore();
        Document doc = null;
        Document docNuevo = null;
        Bookstore bStore = null;
        Bookstore bStore2 = null;
        String opcion = "";
        String ruta = "", rutaAlmacenarXml = "";
        boolean docCreado=false;

        do {
            mostrarMenu();
            opcion = tc.nextLine();
            switch (opcion) {
                case "1": //Seleccionar fichero XML
                    System.out.print("Introduce la ruta del bookstore a leer (En blanco para bookstore por defecto): ");
                    ruta = tc.nextLine();
                    if (ruta.equals("")) {
                        doc = ctrlDoc.deXMLaDOM(new File("Bookstores/bookstore.xml"));
                    } else {
                        if(ruta.endsWith(".xml")){
                           doc = ctrlDoc.deXMLaDOM(new File("Bookstores/"+ruta)); 
                        }else{
                            doc = ctrlDoc.deXMLaDOM(new File("Bookstores/"+ruta+".xml"));
                        }
                        
                    }
                    docCreado=true;
                    break;
                case "2": // Crear bookstore y leer del document
                    if(docCreado){
                    bStore = new Bookstore();
                    bStore = cb.leerBookstore(doc);
                    }else{
                        System.out.println("El documento aun no se ha seleccionado");
                    }
                    break;
                case "3": // Mostrar el bookstore
                    if(docCreado){
                        bStore.mostrarBookstore();
                    }else{
                        System.out.println("El documento aun no se ha seleccionado");
                    }
                    
                    break;
                case "4": // Crear un nuevo bookstore y añadir libros
                    bStore2 = new Bookstore();
                    String masBooks="";

                    System.out.print("¿Desea crear libros por su cuenta? (s/n para usar los libros por defecto dejar en blanco o 'n'): ");
                    String lectura = tc.nextLine();

                    if (lectura.equals("s") || lectura.equals("S")) {
                        do {
                            Book book = new Book();
                            System.out.println("NUEVO LIBRO");
                            System.out.println("--------------------------------------------------------------------------");
                            System.out.print("Introduce la categoria: ");
                            String cat = tc.nextLine();
                            System.out.print("Introduce tipo de portada (En blanco si no tiene o se desconoce): ");
                            String cover = tc.nextLine();
                            if (cover.equals("")) {
                                cover = "cover";
                            }
                            System.out.print("Introduce el titulo del libro: ");
                            String title = tc.nextLine();
                            System.out.print("Introduce el año de publicacion: ");
                            String year = tc.nextLine();
                            System.out.print("Introduce el precio del libro: ");
                            String precio = tc.nextLine();
                            String masAutores = "";
                            book = new Book(cat, cover, title, year, precio);
                            do {
                                System.out.print("Introduce el nombre del autor: ");
                                String nombreAutor = tc.nextLine();
                                Autor autor = new Autor(nombreAutor);
                                book.getAutores().add(autor);
                                bStore2.add(book);
                                System.out.print("El libro posee mas autores (s/n): ");
                                masAutores = tc.nextLine();
                            } while (masAutores.equals("s") || masAutores.equals("S"));
                            
                            System.out.print("¿Desea crear mas libros para este bookstore?(s/n) : ");
                            masBooks = tc.nextLine();
                        } while (masBooks.equals("s")||masBooks.equals("S"));
                    } else {
                        cons.altaLibrosDefault(bStore2); //Añadimos algunos libros por defecto
                        bStore2.mostrarBookstore();
                    }

                    break;
                case "5": // Escribir el objeto bookstore en un nuevo documento
                    docNuevo = ctrlDoc.instanciarDocumento();
                    Element raiz = docNuevo.createElement("bookstore");
                    docNuevo.appendChild(raiz);
                    System.out.println("Se ha añadido de elemento raiz al documento: " + docNuevo.getDocumentElement().getTagName());
                    cb.escribirBookstore(docNuevo, bStore2, raiz);
                    System.out.println("Se ha generado el dom satisfactoriamente");
                    break;
                case "6": //Seleccionar el nombre del fichero donde almacenarlo
                    System.out.print("Nombre del fichero (En blanco para defaultBookstore.xml): ");
                    rutaAlmacenarXml = tc.nextLine();
                    break;
                case "7": //Guardar el documento a xml
                    if (!rutaAlmacenarXml.equals("")) {
                        ctrlDoc.deDOMaXML(docNuevo, new File("Bookstores/" + rutaAlmacenarXml + ".xml"));
                    } else {
                        ctrlDoc.deDOMaXML(docNuevo, new File("Bookstores/defaultBookstore.xml"));
                    }

                    break;
                case "0":
                    System.out.println("Finalizando programa...");
                    break;

                default:
                    System.out.println("Respuesta Incorrecta");
                    break;
            }
        } while (!opcion.equals("0"));

    }

    public static void mostrarMenu() { //Menu
        System.out.println("1. Seleccionar fichero xml para nuevo documento");
        System.out.println("2. Leer documento y crear objeto Bookstore a partir de el");
        System.out.println("3.-Mostrar el BookStore");
        System.out.println("4.-Crear nuevo Bookstore, y añadir libros en el");
        System.out.println("5.-Escribir el bookstore a documento");
        System.out.println("6.-Introducir ruta destino del archivo xml (Solo nombre sin extension)");
        System.out.println("7.-Guardar el documento a XML");
        System.out.println("0. Salir");
    }
    
    public static void limpiar(){
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }
}

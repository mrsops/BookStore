/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Autor;
import modelo.Book;
import modelo.Bookstore;

/**
 *
 * @author Carlos
 */
public class Constantes {

    public final String ET_TITULO = "title";
    public final String ET_AUTOR = "author";
    public final String ET_AÑO = "year";
    public final String ET_PRECIO = "price";
    public final String ET_AUTORES="autores";

    public Constantes() {
    }

    public void altaLibrosDefault(Bookstore bs) {
        Book book = new Book("Fiction YA", "hardcover", "The power of Six", "2011", "28.69");
        book.getAutores().add(new Autor("James Frey"));
        book.getAutores().add(new Autor("Jobie Hughes"));
        
        Book book2 = new Book("Childrens", "paperback", "Hunger Games: Catching Fire", "2013", "17.10");
        book2.getAutores().add(new Autor("Suzanne Collins"));
        
        Book book3 = new Book("Fiction YA", "hardcover", "Eldest", "2005", "16.15");
        book3.getAutores().add(new Autor("Cristopher Paolini"));

        
        bs.add(book);
        bs.add(book2);
        bs.add(book3);
    }

}

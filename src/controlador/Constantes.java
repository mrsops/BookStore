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

    public Bookstore altaBookStore(Bookstore bs) {
        Autor aut=new Autor("Samuel L. Jackson");
        Book libro1 = new Book("casero", "justinbiebier", "Justin Bieber y Michael Jackson unidos por el amor", "1999", "599");
        libro1.getAutores().add(aut);
        
        Book libro2 = new Book("web", "cover1", "Michael Jackson jugando con los niños ", "2999", "199");
        libro2.getAutores().add(new Autor("BurguerCangreBurger"));
        libro2.getAutores().add(new Autor("CurguerBangreCurger"));
        libro2.getAutores().add(aut);
        Book libro3 = new Book("educacional", "cover2", "Como hackear el algoritmo de Google Adsense y no morir en el intento", "999", "59");
        libro3.getAutores().add(new Autor("Yea boi"));
        bs.add(libro1);
        bs.add(libro2);
        bs.add(libro3);
        return bs;
    }

}

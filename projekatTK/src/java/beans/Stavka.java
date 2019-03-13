/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import util.Sesija;

/**
 *
 * @author tijana
 */
@ManagedBean
@SessionScoped
public class Stavka {

    private String naziv;
    private String opis;
    private static String[] selektovane;
  
    
    

    public String[] getSelektovane() {
        return selektovane;
    }

    public void setSelektovane(String[] selektovane) {
        this.selektovane = selektovane;
    }
    
    

    public Stavka() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    private Session sesija;
    private ArrayList<Stavka> stavke = new ArrayList<>();

    public ArrayList<Stavka> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<Stavka> stavke) {
        this.stavke = stavke;
    }
    
    

    public void izlistaj() {
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Stavka");
        List<Stavka> list = query.list();
        stavke = (ArrayList<Stavka>) list;

        sesija.getTransaction().commit();
        sesija.close();

        
        
    }
    
    public static String[]  ispisi(){
     return  selektovane;
    }
    
    
    public ArrayList<Stavka> dohvati(int id){
        
        ArrayList<Stavka> vrati=new ArrayList<>();
        
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Stavka");
        List<Stavka> list = query.list();
        stavke = (ArrayList<Stavka>) list;

        sesija.getTransaction().commit();
        sesija.close();
        
        for(Stavka s: stavke){
            Ima i=new Ima(s.naziv, id);
            int n=i.proveri();
            if (n==1) {
                vrati.add(s);
            }
        }
        
        return vrati;
    }
    
    
}

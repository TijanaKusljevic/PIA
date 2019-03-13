/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.hibernate.Query;
import org.hibernate.Session;
import util.Sesija;

/**
 *
 * @author tijana
 */
@ManagedBean
@SessionScoped
public class Paket {

    private int id;
    private String naziv;
    private int vrednost;
    private int trajanje;
    private int max;
    private ArrayList<Stavka> stavke=new ArrayList<>();
    private String opis;

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
    

    public ArrayList<Stavka> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<Stavka> stavke) {
        this.stavke = stavke;
    }
    
    

    public Paket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getVrednost() {
        return vrednost;
    }

    public void setVrednost(int vrednost) {
        this.vrednost = vrednost;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    private Session sesija;

    public String napravi() {

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();
        sesija.save(this);
        sesija.getTransaction().commit();
        sesija.close();

        String[] stavke = Stavka.ispisi();

        for (String i : stavke) {
            Ima ima = new Ima(i, this.id);
            ima.upisi();
        }
        return "admin";

    }

    private ArrayList<Paket> svi = new ArrayList<>();

    public ArrayList<Paket> getSvi() {
        return svi;
    }

    public void setSvi(ArrayList<Paket> svi) {
        this.svi = svi;
    }
    
    
    

    public void izlistaj() {
        
        StringBuilder sb=new StringBuilder();
        
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Paket");
        List<Paket> list = query.list();
        svi = (ArrayList<Paket>) list;

        sesija.getTransaction().commit();
        sesija.close();
        
        
        
        for(Paket p: svi){
            sb=new StringBuilder();
            Stavka s=new Stavka();
            p.stavke=s.dohvati(p.id);
            
            for(Stavka st: p.stavke){
                sb.append(st.getOpis());
                sb.append(";\n\n");
            }
           p.opis=sb.toString();
        }
        
        
    }
    
    
    List<SelectItem> items;
    public void procitaj(){
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Paket");
        List<Paket> list = query.list();
        svi = (ArrayList<Paket>) list;

        sesija.getTransaction().commit();
        sesija.close();
        
        
        items=new ArrayList<>();
        for(Paket p: svi){
            SelectItem s=new SelectItem(p.id, p.naziv);
            items.add(s);
        }
    }

    public List<SelectItem> getItems() {
        return items;
    }

    public void setItems(List<SelectItem> items) {
        this.items = items;
    }
    
    

}

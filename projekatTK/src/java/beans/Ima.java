/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.Sesija;

/**
 *
 * @author tijana
 */
public class Ima {
    private int id;
    private String stavka;
    private int paket;

    public Ima() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStavka() {
        return stavka;
    }

    public void setStavka(String stavka) {
        this.stavka = stavka;
    }

    public int getPaket() {
        return paket;
    }

    public void setPaket(int paket) {
        this.paket = paket;
    }

    public Ima(String stavka, int paket) {
        this.stavka = stavka;
        this.paket = paket;
    }
    
    private Session sesija;
    public void upisi(){
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();
        sesija.save(this);
        sesija.getTransaction().commit();
        sesija.close();
    }
    
    public int proveri(){
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();
        Query query = sesija.createQuery("from Ima");
            List<Ima> list = query.list();
           ArrayList<Ima> imanja = (ArrayList<Ima>) list;
            
            sesija.getTransaction().commit();
            sesija.close();
            
            for(Ima k: imanja){
                if(k.stavka.equals(stavka)){
                    if(k.paket==paket){
                        return 1;
                    }
                }
            }
        return 0;
    
    }
}

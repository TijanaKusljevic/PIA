/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import util.Sesija;

/**
 *
 * @author tijana
 */
@ManagedBean
@SessionScoped
public class Donatorski extends Ugovor{
    private String opis;
    private int kolicina;
    private Date isporuka;

    public Date getIsporuka() {
        return isporuka;
    }

    public void setIsporuka(Date isporuka) {
        this.isporuka = isporuka;
    }
    
    

    public Donatorski() {
        super();
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
    
    
    private Session sesija;
     
     
    
    public String napravi(){
        
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Paket p=(Paket) sesija.get(Paket.class, super.getPak());

        sesija.close();
        
        int d=p.getTrajanje();
        Calendar cal=Calendar.getInstance();
        cal.setTime(this.getDatum());
        cal.add(Calendar.YEAR, d);
        super.setIstice(cal.getTime());
        
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        sesija.save(this);

        sesija.getTransaction().commit();
        sesija.close();
        
        return "menadzer";
    }
    
}

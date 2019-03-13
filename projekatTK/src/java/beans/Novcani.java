/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class Novcani extends Ugovor{
    
    private int poslato;
    private int uplaceno;
    private Date datumUplate;
    
     private Session sesija;
     
     
    
    public String napravi(){
        
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Paket p=(Paket) sesija.get(Paket.class, super.getPak());
        this.setVrednost(p.getVrednost());

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
    

    public int getPoslato() {
        return poslato;
    }

    public void setPoslato(int poslato) {
        this.poslato = poslato;
    }

    public int getUplaceno() {
        return uplaceno;
    }

    public void setUplaceno(int uplaceno) {
        this.uplaceno = uplaceno;
    }

    public Date getDatumUplate() {
        return datumUplate;
    }

    public void setDatumUplate(Date datumUplate) {
        this.datumUplate = datumUplate;
    }
    
    
    
    

    public Novcani() {
        super();
    }
    
    
}

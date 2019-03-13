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
import org.hibernate.Query;
import org.hibernate.Session;
import util.Sesija;

/**
 *
 * @author tijana
 */
@ManagedBean
@SessionScoped
public class Odgovoran {

    private String username;
    private int komp;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Odgovoran() {
    }

    public Odgovoran(String username, int komp) {
        this.username = username;
        this.komp = komp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getKomp() {
        return komp;
    }

    public void setKomp(int komp) {
        this.komp = komp;
    }

    private Session sesija;
    private ArrayList<Odgovoran> odgovorni = new ArrayList<>();

    public void upisi() {
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Odgovoran");
        List<Odgovoran> list = query.list();
        odgovorni = (ArrayList<Odgovoran>) list;

        sesija.getTransaction().commit();
        sesija.close();

        for (Odgovoran k : odgovorni) {
            if (k.username.equals(username)) {
                if (k.komp == komp) {
                    return;
                }
            }
        }

        try {
            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.save(this);
            sesija.getTransaction().commit();

        } finally {
            sesija.close();
        }

    }
    
    private ArrayList<Par> parovi=new ArrayList<>();

    public ArrayList<Par> getParovi() {
        return parovi;
    }

    public void setParovi(ArrayList<Par> parovi) {
        this.parovi = parovi;
    }
    
    

    public String pokazi() {
        parovi=new ArrayList<>();
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Odgovoran");
        List<Odgovoran> lista = query.list();

        sesija.getTransaction().commit();
        sesija.close();

        for (Odgovoran o : lista) {
             sesija = Sesija.getSessionFactory().openSession();
             sesija.beginTransaction();
             
             Kompanija k= (Kompanija) sesija.get(Kompanija.class, o.getKomp());
             
             sesija.close();
             
             sesija = Sesija.getSessionFactory().openSession();
             sesija.beginTransaction();
             
             Registracija reg= (Registracija) sesija.get(Registracija.class, o.getUsername());
             
             sesija.close();
             
             StringBuilder sb=new StringBuilder();
             sb.append(reg.getIme());
             sb.append(" ");
             sb.append(reg.getPrezime());
             
             Par p=new Par(sb.toString(), k.getNaziv());
             parovi.add(p);
        }

        return "odgovorni";
    }

}

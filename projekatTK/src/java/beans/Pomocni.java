/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import util.Sesija;

/**
 *
 * @author tijana
 */
@ManagedBean
@SessionScoped
public class Pomocni {

    private String nazivPaketa;
    private String nazivKompanije;
    private boolean samoAktivne;
    private boolean gotovo = false;
    private boolean gotovo1=false;

    public boolean isGotovo1() {
        return gotovo1;
    }

    public void setGotovo1(boolean gotovo1) {
        this.gotovo1 = gotovo1;
    }
    
    
    

    public boolean isSamoAktivne() {
        return samoAktivne;
    }

    public void setSamoAktivne(boolean samoAktivne) {
        this.samoAktivne = samoAktivne;
    }

    public String getNazivPaketa() {
        return nazivPaketa;
    }

    public void setNazivPaketa(String nazivPaketa) {
        this.nazivPaketa = nazivPaketa;
    }

    public String getNazivKompanije() {
        return nazivKompanije;
    }

    public void setNazivKompanije(String nazivKompanije) {
        this.nazivKompanije = nazivKompanije;
    }

    private Session sesija;
    private ArrayList<Kompanija> kompanije = new ArrayList<>();
    private ArrayList<Kompanija> kompanije1 = new ArrayList<>();
    private ArrayList<Novcani> novcani = new ArrayList();
    private ArrayList<Donatorski> donatorski = new ArrayList();
    private ArrayList<Ugovor> ugovori = new ArrayList<>();
    private ArrayList<Kompanija> odgovarajuce = new ArrayList<>();
    private ArrayList<Paket> paketi = new ArrayList<>();
    private ArrayList<Kompanija> listaKompanija = new ArrayList<>();

    public ArrayList<Kompanija> getKompanije1() {
        return kompanije1;
    }

    public void setKompanije1(ArrayList<Kompanija> kompanije1) {
        this.kompanije1 = kompanije1;
    }
    
    

    public ArrayList<Kompanija> getKompanije() {
        return kompanije;
    }

    public void setKompanije(ArrayList<Kompanija> kompanije) {
        this.kompanije = kompanije;
    }

    private boolean aktivni(int id) {
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Novcani");
        List<Novcani> lista = query.list();
        novcani = (ArrayList<Novcani>) lista;

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query1 = sesija.createQuery("from Donatorski");
        List<Donatorski> lista1 = query1.list();
        donatorski = (ArrayList<Donatorski>) lista1;

        sesija.getTransaction().commit();
        sesija.close();

        Date date = new Date();

        for (Novcani n : novcani) {
            if (n.getKomp() == id && n.getIstice().after(date)) {
                ugovori.add(n);
            }
        }

        for (Donatorski n : donatorski) {
            if (n.getKomp() == id && n.getIstice().after(date)) {
                ugovori.add(n);
            }
        }

        if (ugovori.size() > 0) {
            return true;
        }

        return false;
    }

    public String gostTrazi() {
        
        kompanije1=new ArrayList<>();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query1 = sesija.createQuery("from Kompanija k where k.naziv like '%" + nazivKompanije + "%'");
        List<Kompanija> list = query1.list();
        kompanije1 = (ArrayList<Kompanija>) list;

        sesija.getTransaction().commit();
        sesija.close();
        
        gotovo1=true;

        return "index";
    }

    public String pretrazi() {

        kompanije = new ArrayList<>();

        if (nazivKompanije.equals("") && nazivPaketa.equals("")) {
            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();

            Query query1 = sesija.createQuery("from Kompanija");
            List<Kompanija> list = query1.list();
            kompanije = (ArrayList<Kompanija>) list;

            sesija.getTransaction().commit();
            sesija.close();

            ArrayList<Kompanija> pom = new ArrayList<>();

            if (samoAktivne) {
                for (Kompanija k : kompanije) {
                    if (this.aktivni(k.getId())) {
                        pom.add(k);
                    }
                }
                kompanije = pom;
            }

        }

        if (!nazivKompanije.equals("") && nazivPaketa.equals("")) {
            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();

            Query query1 = sesija.createQuery("from Kompanija k where k.naziv like '%" + nazivKompanije + "%'");
            List<Kompanija> list = query1.list();
            kompanije = (ArrayList<Kompanija>) list;

            sesija.getTransaction().commit();
            sesija.close();

            ArrayList<Kompanija> pom = new ArrayList<>();

            if (samoAktivne) {
                for (Kompanija k : kompanije) {
                    if (this.aktivni(k.getId())) {
                        pom.add(k);
                    }
                }
                kompanije = pom;
            }

        }

        if (nazivKompanije.equals("") && !nazivPaketa.equals("")) {
            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();

            Query query1 = sesija.createQuery("from Paket k where k.naziv like '%" + nazivPaketa + "%'");
            List<Paket> list = query1.list();
            paketi = (ArrayList<Paket>) list;

            sesija.getTransaction().commit();
            sesija.close();

            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();

            Query query = sesija.createQuery("from Novcani");
            List<Novcani> lista = query.list();
            novcani = (ArrayList<Novcani>) lista;

            sesija.getTransaction().commit();
            sesija.close();

            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();

            Query query2 = sesija.createQuery("from Donatorski");
            List<Donatorski> lista1 = query2.list();
            donatorski = (ArrayList<Donatorski>) lista1;

            sesija.getTransaction().commit();
            sesija.close();

            ArrayList<Ugovor> u = new ArrayList<>();

            for (Paket p : paketi) {
                for (Novcani n : novcani) {
                    if (n.getPak() == p.getId()) {
                        u.add(n);
                    }
                }
                for (Donatorski n : donatorski) {
                    if (n.getPak() == p.getId()) {
                        u.add(n);
                    }
                }
            }

            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();

            Query query3 = sesija.createQuery("from Kompanija");
            List<Kompanija> lista2 = query3.list();
            listaKompanija = (ArrayList<Kompanija>) lista2;

            sesija.getTransaction().commit();
            sesija.close();

            for (Kompanija k : listaKompanija) {
                for (Ugovor uu : u) {
                    if (k.getId() == uu.getKomp() && !kompanije.contains(k)) {
                        kompanije.add(k);
                    }
                }
            }

            ArrayList<Kompanija> pom = new ArrayList<>();

            if (samoAktivne) {
                for (Kompanija k : kompanije) {
                    if (this.aktivni(k.getId())) {
                        pom.add(k);
                    }
                }
                kompanije = pom;
            }

        }

        if (!nazivKompanije.equals("") && !nazivPaketa.equals("")) {
            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();

            Query query1 = sesija.createQuery("from Paket k where k.naziv like '%" + nazivPaketa + "%'");
            List<Paket> list = query1.list();
            paketi = (ArrayList<Paket>) list;

            sesija.getTransaction().commit();
            sesija.close();

            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();

            Query query = sesija.createQuery("from Novcani");
            List<Novcani> lista = query.list();
            novcani = (ArrayList<Novcani>) lista;

            sesija.getTransaction().commit();
            sesija.close();

            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();

            Query query2 = sesija.createQuery("from Donatorski");
            List<Donatorski> lista1 = query2.list();
            donatorski = (ArrayList<Donatorski>) lista1;

            sesija.getTransaction().commit();
            sesija.close();

            ArrayList<Ugovor> u = new ArrayList<>();

            for (Paket p : paketi) {
                for (Novcani n : novcani) {
                    if (n.getPak() == p.getId()) {
                        u.add(n);
                    }
                }
                for (Donatorski n : donatorski) {
                    if (n.getPak() == p.getId()) {
                        u.add(n);
                    }
                }
            }

            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();

            Query query3 = sesija.createQuery("from Kompanija k where k.naziv like '%" + nazivKompanije + "%'");
            List<Kompanija> lista2 = query3.list();
            listaKompanija = (ArrayList<Kompanija>) lista2;

            sesija.getTransaction().commit();
            sesija.close();

            for (Kompanija k : listaKompanija) {
                for (Ugovor uu : u) {
                    if (k.getId() == uu.getKomp() && !kompanije.contains(k)) {
                        kompanije.add(k);
                    }
                }
            }

            ArrayList<Kompanija> pom = new ArrayList<>();

            if (samoAktivne) {
                for (Kompanija k : kompanije) {
                    if (this.aktivni(k.getId())) {
                        pom.add(k);
                    }
                }
                kompanije = pom;
            }

        }
        gotovo = true;

        return "pretraga";
    }

    public boolean isGotovo() {
        return gotovo;
    }

    public void setGotovo(boolean gotovo) {
        this.gotovo = gotovo;
    }

    ArrayList<Paket> pre = new ArrayList<>();

    public ArrayList<Paket> getPre() {
        return pre;
    }

    public void setPre(ArrayList<Paket> pre) {
        this.pre = pre;
    }

    private int max;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    HashMap<String, ArrayList<Kompanija>> hmm = new HashMap<>();
    HashMap<String, Integer> hm = new HashMap<>();

    public HashMap<String, ArrayList<Kompanija>> getHmm() {
        return hmm;
    }

    public void setHmm(HashMap<String, ArrayList<Kompanija>> hmm) {
        this.hmm = hmm;
    }

    public void ucitaj() {
        
        
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Paket");
        List<Paket> lista = query.list();
        pre = (ArrayList<Paket>) lista;

        max = pre.size();

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query1 = sesija.createQuery("from Kompanija");
        List<Kompanija> listaa = query1.list();
        ArrayList<Kompanija> pom = (ArrayList<Kompanija>) listaa;

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query11 = sesija.createQuery("from Novcani");
        List<Novcani> lista2 = query11.list();
        novcani = (ArrayList<Novcani>) lista2;

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query2 = sesija.createQuery("from Donatorski");
        List<Donatorski> lista1 = query2.list();
        donatorski = (ArrayList<Donatorski>) lista1;

        sesija.getTransaction().commit();
        sesija.close();

        ArrayList<Ugovor> u = new ArrayList<>();
        Date danas = new Date();

        for (Novcani n : novcani) {
            if (n.getIstice().after(danas)) {
                u.add(n);
            }
        }

        for (Donatorski n : donatorski) {
            if (n.getIstice().after(danas)) {
                u.add(n);
            }
        }

        for (Paket p : pre) {
            ArrayList<Kompanija> moje = new ArrayList<>();
            for (Ugovor uu : u) {
                for (Kompanija k : pom) {
                    if (k.getId() == uu.getKomp() && uu.getPak() == p.getId()) {
                        moje.add(k);
                    }
                }
            }
            hmm.put(p.getNaziv(), moje);
            hm.put(p.getNaziv(), moje.size());
        }
    }

    public HashMap<String, Integer> getHm() {
        return hm;
    }

    public void setHm(HashMap<String, Integer> hm) {
        this.hm = hm;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import util.Sesija;

/**
 *
 * @author tijana
 */
@ManagedBean
@SessionScoped
public class Oglas {

    private int id;
    private String naslov;
    private String opis;
    private int[] vrsta;
    private int vr;
    private Date datum;
    private String prikaz = "kiki";
    private int komp;
    private UploadedFile  fajl;
    private String stringZaBazu;
    private StreamedContent file;
    private boolean ima=false;

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public boolean isIma() {
        return ima;
    }

    public void setIma(boolean ima) {
        this.ima = ima;
    }
    
    

    public String getStringZaBazu() {
        return stringZaBazu;
    }

    public void setStringZaBazu(String stringZaBazu) {
        this.stringZaBazu = stringZaBazu;
         if(stringZaBazu==null || stringZaBazu.equals("")){ return;}
        
        ima=true;
        String putanja=Kompanija.dohvati()+"\\"+stringZaBazu;
        File f=new File(putanja);
        InputStream stream=null;
        try {
            stream = new FileInputStream(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Predavanje.class.getName()).log(Level.SEVERE, null, ex);
        }
        file = new DefaultStreamedContent(stream, null, stringZaBazu);
    }
    
    

    public UploadedFile getFajl() {
        return fajl;
    }

    public void setFajl(UploadedFile fajl) {
        this.fajl = fajl;
    }
    
    

    private ArrayList<Kompanija> vrati = new ArrayList<>();

    public ArrayList<Kompanija> getVrati() {
        return vrati;
    }

    public void setVrati(ArrayList<Kompanija> kompanije) {
        this.vrati = kompanije;
    }

    public int getKomp() {
        return komp;
    }

    public void setKomp(int komp) {
        this.komp = komp;
    }

    public String getPrikaz() {
        return prikaz;
    }

    public void setPrikaz(String prikaz) {
        this.prikaz = prikaz;
    }

    public int getVr() {
        return vr;
    }

    public void setVr(int vr) {
        this.vr = vr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Oglas() {
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int[] getVrsta() {
        return vrsta;
    }

    public void setVrsta(int[] vrsta) {
        this.vrsta = vrsta;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    private Session sesija;

    public void postavi() {
        
        vr = 0;

        if (vrsta.length == 2) {
            vr = 3;
        } else {
            vr = vrsta[0];
        }

        datum = new Date();
        
         byte[] bajtovi = fajl.getContents();
        if (bajtovi != null) {
            if (bajtovi.length > 100) {
                try {
                    String putanja = Kompanija.dohvati();
                    stringZaBazu = "oglas" + this.naslov + fajl.getFileName();
                    putanja = putanja + "\\" + stringZaBazu;
                    File fajl1 = new File(putanja);
                    FileOutputStream output = new FileOutputStream(fajl1);
                    output.write(bajtovi);
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
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

    private ArrayList<Oglas> svi = new ArrayList<>();

    public ArrayList<Oglas> getSvi() {
        return svi;
    }

    public void setSvi(ArrayList<Oglas> svi) {
        this.svi = svi;
    }

    public void izlistaj() {
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Oglas");
        List<Oglas> list = query.list();
        svi = (ArrayList<Oglas>) list;

        sesija.getTransaction().commit();
        sesija.close();

        for (Oglas o : svi) {
            if (o.vr == 1) {
                o.prikaz = "praksa";
            }
            if (o.vr == 2) {
                o.prikaz = "posao";
            }
            if (o.vr == 3) {
                o.prikaz = "posao i praksa";
            }
        }
    }
    
     List<SelectItem> items;

    public List<SelectItem> getItems() {
        return items;
    }

    public void setItems(List<SelectItem> items) {
        this.items = items;
    }
     
     

    public void ucitaj() {
        items=new ArrayList<>();
        ELContext el = FacesContext.getCurrentInstance().getELContext();
        Registracija reg = (Registracija) el.getELResolver().getValue(el, null, "registracija");
        String username = reg.getUsername();
        
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query1 = sesija.createQuery("from Kompanija");
        List<Kompanija> list = query1.list();
        ArrayList<Kompanija> kompanije = (ArrayList<Kompanija>) list;

        sesija.getTransaction().commit();
        sesija.close();

        for (Kompanija k : kompanije) {

            ArrayList<Novcani> novcani = new ArrayList();
            ArrayList<Donatorski> donatorski = new ArrayList();
            ArrayList<Ugovor> ugovori = new ArrayList<>();

            int idd = k.getId();

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

            Date date = new Date();

            for (Novcani n : novcani) {
                if (n.getKomp() == idd && n.getIstice().after(date)) {
                    ugovori.add(n);
                }
            }

            for (Donatorski n : donatorski) {
                if (n.getKomp() == idd && n.getIstice().after(date)) {
                    ugovori.add(n);
                }
            }

            if (ugovori.size() > 0) {
                sesija = Sesija.getSessionFactory().openSession();
                sesija.beginTransaction();

                Query query3 = sesija.createQuery("from Odgovoran");
                List<Odgovoran> lista3 = query3.list();
                ArrayList<Odgovoran> odgovorni = (ArrayList<Odgovoran>) lista3;

                sesija.getTransaction().commit();
                sesija.close();
                
                for(Odgovoran o: odgovorni){
                    if(o.getUsername().equals(username) && o.getKomp()==idd){
                        vrati.add(k);
                        SelectItem s=new SelectItem(k.getId(), k.getNaziv());
                        items.add(s);
                        break;
                    }
                }

            }

        }
    }
    
    public void ucitajStreamOpet() {
       
            if (!(stringZaBazu == null || stringZaBazu.equals(""))) {
                String putanja = Kompanija.dohvati() + "\\" + stringZaBazu;
                File f = new File(putanja);
                InputStream stream = null;
                try {
                    stream = new FileInputStream(f);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Predavanje.class.getName()).log(Level.SEVERE, null, ex);
                }
                file = new DefaultStreamedContent(stream, null, stringZaBazu);
            }
        
    }

}

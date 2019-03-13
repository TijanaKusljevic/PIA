/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import antlr.debug.Event;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import util.Sesija;
import org.hibernate.Query;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author tijana
 */
@ManagedBean
@SessionScoped
public class Kompanija {

    private int id;
    private String ime;
    private String prezime;
    private String naziv;
    private String adresa;
    private String grad;
    private String zemlja;
    private String ziro;
    private int postanski;
    private String valuta;
    private String pib;
    private String telefon1;
    private String telefon2;
    private String telefon3;
    private String telefon4;
    private String telefon5;
    private String email1;
    private String email2;
    private String email3;
    private String email4;
    private String email5;
    private String kemail;
    private String ktelefon;
    private String username;
    private String poruka;
    private int trazeni;
    public Kompanija dosije;
    public static Kompanija staticka;
    private boolean azuriranje;
    private boolean moja = false;
    private UploadedFile logo;
    private String stringZaBazu;
    private String stringZaTabelu;
    private String opis="";

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
    

    public String getStringZaTabelu() {
        return stringZaTabelu;
    }
    
    

    public String getStringZaBazu() {
       
        return stringZaBazu;
    }

    public void setStringZaBazu(String stringZaBazu) {
        stringZaTabelu="resources/"+stringZaBazu;
        this.stringZaBazu = stringZaBazu;
    }

    public UploadedFile getLogo() {
        return logo;
    }

    public void setLogo(UploadedFile logo) {
        this.logo = logo;
    }

    public boolean isMoja() {
        return moja;
    }

    public void setMoja(boolean moja) {
        this.moja = moja;
    }

    public boolean isAzuriranje() {
        return azuriranje;
    }

    public void setAzuriranje(boolean azuriranje) {
        this.azuriranje = azuriranje;
    }

    public Kompanija getStaticka() {
        return staticka;
    }

    public void setStaticka(Kompanija staticka) {
        Kompanija.staticka = staticka;
    }

    public Kompanija() {
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getTelefon2() {
        return telefon2;
    }

    public void setTelefon2(String telefon2) {
        this.telefon2 = telefon2;
    }

    public String getTelefon3() {
        return telefon3;
    }

    public void setTelefon3(String telefon3) {
        this.telefon3 = telefon3;
    }

    public String getTelefon4() {
        return telefon4;
    }

    public void setTelefon4(String telefon4) {
        this.telefon4 = telefon4;
    }

    public String getTelefon5() {
        return telefon5;
    }

    public void setTelefon5(String telefon5) {
        this.telefon5 = telefon5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getZemlja() {
        return zemlja;
    }

    public void setZemlja(String zemlja) {
        this.zemlja = zemlja;
    }

    public String getZiro() {
        return ziro;
    }

    public void setZiro(String ziro) {
        this.ziro = ziro;
    }

    public int getPostanski() {
        return postanski;
    }

    public void setPostanski(int postanski) {
        this.postanski = postanski;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getTelefon1() {
        return telefon1;
    }

    public void setTelefon1(String telefon) {
        this.telefon1 = telefon;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }

    public String getEmail4() {
        return email4;
    }

    public void setEmail4(String email4) {
        this.email4 = email4;
    }

    public String getEmail5() {
        return email5;
    }

    public void setEmail5(String email5) {
        this.email5 = email5;
    }

    public String getKemail() {
        return kemail;
    }

    public void setKemail(String kemail) {
        this.kemail = kemail;
    }

    public String getKtelefon() {
        return ktelefon;
    }

    public void setKtelefon(String ktelefon) {
        this.ktelefon = ktelefon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private Session sesija;
    private int zadnji;
    Integer myId;
    private ArrayList<Kompanija> kompanije = new ArrayList<>();

    public ArrayList<Kompanija> getKompanije() {
        return kompanije;
    }

    public void setKompanije(ArrayList<Kompanija> kompanije) {
        this.kompanije = kompanije;
    }

    public String upisi() {

        System.out.println(logo.getFileName());

        ELContext el = FacesContext.getCurrentInstance().getELContext();
        Kompanija kompanija = (Kompanija) el.getELResolver().getValue(el, null, "kompanija");

        //username = kompanija.getUsername();
        Registracija reg = (Registracija) el.getELResolver().getValue(el, null, "registracija");
        username = reg.getUsername();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Kompanija");
        List<Kompanija> list = query.list();
        kompanije = (ArrayList<Kompanija>) list;

        sesija.getTransaction().commit();
        sesija.close();

        for (Kompanija k : kompanije) {
            if (k.naziv.equals(naziv)) {
                Odgovoran odg = new Odgovoran(username, k.id);
                odg.upisi();
                poruka = "vec je ima";
                return "clan";
            }
        }

        byte[] bajtovi = logo.getContents();
        if (bajtovi != null) {
            if (bajtovi.length > 100) {
                try {
                    String putanja = Kompanija.dohvati();
                    stringZaBazu = "logo" + this.naziv + logo.getFileName();
                    putanja = putanja + "\\" + stringZaBazu;
                    File fajl = new File(putanja);
                    FileOutputStream output = new FileOutputStream(fajl);
                    output.write(bajtovi);
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();

            myId = (Integer) sesija.save(this);
            sesija.getTransaction().commit();
            poruka = "";

            sesija.close();

            Odgovoran odg = new Odgovoran(username, myId);
            odg.upisi();
            return "clan";
        }

        ArrayList<Kompanija> sve = new ArrayList<>();
        List<SelectItem> items;

    

    public void procitaj() {
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Kompanija");
        List<Kompanija> list = query.list();
        sve = (ArrayList<Kompanija>) list;

        sesija.getTransaction().commit();
        sesija.close();

        items = new ArrayList<>();
        for (Kompanija k : sve) {
            SelectItem s = new SelectItem(k.id, k.naziv);
            items.add(s);
        }

    }

    public List<SelectItem> getItems() {
        return items;
    }

    public void setItems(List<SelectItem> items) {
        this.items = items;
    }

    public ArrayList<Kompanija> getSve() {
        return sve;
    }

    public void setSve(ArrayList<Kompanija> sve) {
        this.sve = sve;
    }

    public void sestNarednih() {
        kompanije = new ArrayList<>();
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Kompanija");
        List<Kompanija> list = query.list();
        sve = (ArrayList<Kompanija>) list;

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query1 = sesija.createQuery("from Novcani");
        List<Novcani> lista = query1.list();
        ArrayList<Novcani> novcani = (ArrayList<Novcani>) lista;

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query2 = sesija.createQuery("from Donatorski");
        List<Donatorski> listaa = query2.list();
        ArrayList<Donatorski> donatorski = (ArrayList<Donatorski>) listaa;

        sesija.getTransaction().commit();
        sesija.close();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6);
        Date sledeci = cal.getTime();
        Date danas = new Date();

        //ArrayList<Ugovor> ugovori=new ArrayList<>();
        for (Kompanija k : sve) {
            for (Novcani n : novcani) {
                if (n.getKomp() == k.getId()) {
                    if (n.getIstice().before(sledeci) && n.getIstice().after(danas)) {
                        //ugovori.add(n);
                        if (!kompanije.contains(k)) {
                            kompanije.add(k);
                        }
                    }
                }
            }
            for (Donatorski n : donatorski) {
                if (n.getKomp() == k.getId()) {
                    if (n.getIstice().before(sledeci) && n.getIstice().after(danas)) {
                        //ugovori.add(n);
                        if (!kompanije.contains(k)) {
                            kompanije.add(k);
                        }

                    }
                }
            }
        }

        int vel = kompanije.size();
        while (vel > 20) {
            kompanije.remove(vel - 1);
            vel--;
        }

        // for(int i=20; i<kompanije.size(); )
    }

    ArrayList<Kompanija> opet = new ArrayList<>();
    ArrayList<Kompanija> kiki = new ArrayList<>();

    public ArrayList<Kompanija> getOpet() {
        return opet;
    }

    public void setOpet(ArrayList<Kompanija> opet) {
        this.opet = opet;
    }

    public void sestProslih() {
        opet = new ArrayList();
        ugovori = new ArrayList<>();
        opet = new ArrayList<>();
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Kompanija");
        List<Kompanija> list = query.list();
        kiki = (ArrayList<Kompanija>) list;

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query1 = sesija.createQuery("from Novcani");
        List<Novcani> lista = query1.list();
        ArrayList<Novcani> novcani = (ArrayList<Novcani>) lista;

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query2 = sesija.createQuery("from Donatorski");
        List<Donatorski> listaa = query2.list();
        ArrayList<Donatorski> donatorski = (ArrayList<Donatorski>) listaa;

        sesija.getTransaction().commit();
        sesija.close();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        Date prosli = cal.getTime();
        Date danas = new Date();

        //ArrayList<Ugovor> ugovori=new ArrayList<>();
        for (Kompanija k : sve) {
            for (Novcani n : novcani) {
                if (n.getKomp() == k.getId()) {
                    if (n.getIstice().after(prosli) && n.getIstice().before(danas)) {
                        //ugovori.add(n);
                        if (!opet.contains(k)) {
                            opet.add(k);
                        }
                    }
                }
            }
            for (Donatorski n : donatorski) {
                if (n.getKomp() == k.getId()) {
                    if (n.getIstice().after(prosli) && n.getIstice().before(danas)) {
                        //ugovori.add(n);
                        if (!opet.contains(k)) {
                            opet.add(k);
                        }

                    }
                }
            }
        }

        ArrayList<Kompanija> pom = new ArrayList<>();
        pom = (ArrayList<Kompanija>) opet.clone();

        for (Kompanija k : opet) {
            for (Novcani n : novcani) {
                if (k.getId() == n.getKomp() && n.getIstice().after(danas)) {
                    pom.remove(k);
                }
            }

            for (Donatorski n : donatorski) {
                if (k.getId() == n.getKomp() && n.getIstice().after(danas)) {
                    pom.remove(k);
                }
            }
        }
        opet = pom;
        int vel = opet.size();
        while (vel > 20) {
            opet.remove(vel - 1);
            vel--;
        }
    }

    public int getTrazeni() {
        return trazeni;
    }

    public void setTrazeni(int trazeni) {
        this.trazeni = trazeni;
    }

    private ArrayList<Ugovor> ugovori = new ArrayList<>();

    public ArrayList<Ugovor> getUgovori() {
        return ugovori;
    }

    public void setUgovori(ArrayList<Ugovor> ugovori) {
        this.ugovori = ugovori;
    }

    public void onRowSelect(SelectEvent e) {
        ugovori = new ArrayList<>();
        Kompanija komp = (Kompanija) e.getObject();
        naziv = komp.getNaziv();
        adresa = komp.getAdresa();
        grad = komp.getGrad();
        postanski = komp.getPostanski();
        zemlja = komp.getZemlja();
        ziro = komp.getZiro();
        valuta = komp.getValuta();
        pib = komp.getPib();
        ime = komp.getIme();
        prezime = komp.getPrezime();
        kemail = komp.getKemail();
        ktelefon = komp.getKtelefon();
        email1 = komp.getEmail1();
        email2 = komp.getEmail2();
        email3 = komp.getEmail3();
        email4 = komp.getEmail4();
        email5 = komp.getEmail5();
        telefon1 = komp.getTelefon1();
        telefon2 = komp.getTelefon2();
        telefon3 = komp.getTelefon3();
        telefon4 = komp.getTelefon4();
        telefon5 = komp.getTelefon5();
        id = komp.getId();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query1 = sesija.createQuery("from Novcani");
        List<Novcani> lista = query1.list();
        ArrayList<Novcani> novcani = (ArrayList<Novcani>) lista;

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query2 = sesija.createQuery("from Donatorski");
        List<Donatorski> listaa = query2.list();
        ArrayList<Donatorski> donatorski = (ArrayList<Donatorski>) listaa;

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query3 = sesija.createQuery("from Paket");
        List<Paket> listaaa = query3.list();
        ArrayList<Paket> paketi = (ArrayList<Paket>) listaaa;

        sesija.getTransaction().commit();
        sesija.close();

        Date danas = new Date();

        for (Novcani n : novcani) {
            n.setVrsta("novčani");
            if (n.getKomp() == id) {
                if (n.getIstice().before(danas)) {
                    n.setIstekao(true);
                }
                ugovori.add(n);
                for (Paket p : paketi) {
                    if (p.getId() == n.getPak()) {
                        n.setPaket(p.getNaziv());
                    }
                }
            }
        }

        for (Donatorski n : donatorski) {
            n.setVrsta("donatorski");
            if (n.getKomp() == id) {
                if (n.getIstice().before(danas)) {
                    n.setIstekao(true);
                }
                ugovori.add(n);
                for (Paket p : paketi) {
                    if (p.getId() == n.getPak()) {
                        n.setPaket(p.getNaziv());
                    }
                }
            }
        }

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(contextPath + "/faces/dosije.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public Kompanija getDosije() {
        return dosije;
    }

    public void setDosije(Kompanija dosije) {
        this.dosije = dosije;
    }

    public String azuriraj() {
        azuriranje = true;
        return "dosije";
    }

    public void sacuvaj() {
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();
        Kompanija k = (Kompanija) sesija.get(Kompanija.class, id);
        k.setAdresa(adresa);
        k.setGrad(grad);
        k.setIme(ime);
        k.setPrezime(prezime);
        k.setKemail(kemail);
        k.setKtelefon(ktelefon);
        k.setZemlja(zemlja);
        k.setZiro(ziro);
        k.setValuta(valuta);
        k.setPib(pib);
        k.setPostanski(postanski);
        sesija.getTransaction().commit();
        sesija.close();

    }

    private ArrayList<Kompanija> prvih5 = new ArrayList<>();

    public ArrayList<Kompanija> getPrvih5() {
        return prvih5;
    }

    public void setPrvih5(ArrayList<Kompanija> prvih5) {
        this.prvih5 = prvih5;
    }

    private ArrayList<Kompanija> poslednjih5 = new ArrayList<>();

    public ArrayList<Kompanija> getPoslednjih5() {
        return poslednjih5;
    }

    public void setPoslednjih5(ArrayList<Kompanija> poslednjih5) {
        this.poslednjih5 = poslednjih5;
    }

    public void petNovijih() {

        prvih5 = new ArrayList<>();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query1 = sesija.createQuery("from Novcani");
        List<Novcani> lista = query1.list();
        ArrayList<Novcani> novcani = (ArrayList<Novcani>) lista;

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query2 = sesija.createQuery("from Donatorski");
        List<Donatorski> listaa = query2.list();
        ArrayList<Donatorski> donatorski = (ArrayList<Donatorski>) listaa;

        sesija.getTransaction().commit();
        sesija.close();

        /* sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();*/
        ArrayList<Ugovor> ugov = new ArrayList<>();
        ugov.addAll(novcani);
        ugov.addAll(donatorski);

        ugov.sort(new Comparator<Ugovor>() {
            @Override
            public int compare(Ugovor t, Ugovor t1) {
                return t1.getDatum().compareTo(t.getDatum());
            }

        });

        int brojac = 0;

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Kompanija");
        List<Kompanija> list = query.list();
        ArrayList<Kompanija> pom = (ArrayList<Kompanija>) list;

        sesija.getTransaction().commit();
        sesija.close();

        for (Ugovor u : ugov) {
            for (Kompanija k1 : pom) {
                if (k1.getId() == u.getKomp()) {

                    if (!prvih5.contains(k1)) {
                        prvih5.add(k1);
                        brojac++;
                    }

                    if (brojac == 5) {

                        ELContext el = FacesContext.getCurrentInstance().getELContext();
                        Kompanija kompanija = (Kompanija) el.getELResolver().getValue(el, null, "kompanija");

                        //username = kompanija.getUsername();
                        Registracija reg = (Registracija) el.getELResolver().getValue(el, null, "registracija");
                        String username = reg.getUsername();

                        for (Kompanija k : prvih5) {

                            int idd = k.getId();

                            sesija = Sesija.getSessionFactory().openSession();
                            sesija.beginTransaction();

                            Query query7 = sesija.createQuery("from Odgovoran where username = '" + username + "' and komp=" + idd);
                            List<Odgovoran> lista7 = query7.list();
                            ArrayList<Odgovoran> odgovorni = (ArrayList<Odgovoran>) lista7;

                            sesija.getTransaction().commit();
                            sesija.close();

                            if (odgovorni.size() > 0) {
                                k.setMoja(true);
                            }

                        }
                        return;
                    }
                }
            }
        }

    }

    public void petStarijih() {

        poslednjih5 = new ArrayList<>();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query1 = sesija.createQuery("from Novcani");
        List<Novcani> lista = query1.list();
        ArrayList<Novcani> novcani = (ArrayList<Novcani>) lista;

        sesija.getTransaction().commit();
        sesija.close();

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query2 = sesija.createQuery("from Donatorski");
        List<Donatorski> listaa = query2.list();
        ArrayList<Donatorski> donatorski = (ArrayList<Donatorski>) listaa;

        sesija.getTransaction().commit();
        sesija.close();

        ArrayList<Ugovor> ugov = new ArrayList<>();
        ugov.addAll(novcani);
        ugov.addAll(donatorski);

        ugov.sort(new Comparator<Ugovor>() {
            @Override
            public int compare(Ugovor t, Ugovor t1) {
                return t.getDatum().compareTo(t1.getDatum());
            }

        });

        Date danas = new Date();

        ArrayList<Ugovor> ugov1 = new ArrayList<>();

        for (Ugovor u : ugov) {
            if (!u.getIstice().before(danas)) {
                ugov1.add(u);
            }
        }

        int brojac = 0;

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Kompanija");
        List<Kompanija> list = query.list();
        ArrayList<Kompanija> pom = (ArrayList<Kompanija>) list;

        sesija.getTransaction().commit();
        sesija.close();

        for (Ugovor u : ugov1) {
            for (Kompanija k1 : pom) {
                if (k1.getId() == u.getKomp()) {

                    if (!poslednjih5.contains(k1)) {
                        poslednjih5.add(k1);
                        brojac++;
                    }

                    if (brojac == 5) {
                        ELContext el = FacesContext.getCurrentInstance().getELContext();
                        Kompanija kompanija = (Kompanija) el.getELResolver().getValue(el, null, "kompanija");

                        //username = kompanija.getUsername();
                        Registracija reg = (Registracija) el.getELResolver().getValue(el, null, "registracija");
                        String username = reg.getUsername();

                        for (Kompanija k : poslednjih5) {

                            int idd = k.getId();

                            sesija = Sesija.getSessionFactory().openSession();
                            sesija.beginTransaction();

                            Query query7 = sesija.createQuery("from Odgovoran where username = '" + username + "' and komp=" + idd);
                            List<Odgovoran> lista7 = query7.list();
                            ArrayList<Odgovoran> odgovorni = (ArrayList<Odgovoran>) lista7;

                            sesija.getTransaction().commit();
                            sesija.close();

                            if (odgovorni.size() > 0) {
                                k.setMoja(true);
                            }

                        }
                        return;
                    }
                }
            }

        }
    }

    public static String dohvati() {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String pathString = ctx.getRealPath("/");
            Path path = Paths.get(pathString);
            Path path2 = path.getParent().getParent();
            pathString = path2.toString();
            pathString = pathString + "\\web\\resources";
            return pathString;
        } catch (Exception ex) {
            Logger.getLogger(Kompanija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.UploadedFile;
import util.SHA;
import util.Sesija;

/**
 *
 * @author tijana
 */
@ManagedBean
@SessionScoped
public class Registracija {

    private String ime;
    private String prezime;
    private String username;
    private String poruka = "";
    private String mail;
    private String institucija;
    private Date datum;
    private int pol; //0 musko 1 zensko
    private String pol1;
    private String password;
    private String password1;
    private String inst;
    private String lozinka;
    private String profil;
    private boolean menadzer=false;
    private boolean admin=false;
    private UploadedFile  slika;
    private String stringZaBazu;

    public String getStringZaBazu() {
        return stringZaBazu;
    }

    public void setStringZaBazu(String stringZaBazu) {
        this.stringZaBazu = stringZaBazu;
    }
    
    

    public String getPol1() {
        return pol1;
    }

    public void setPol1(String pol1) {
        this.pol1 = pol1;
    }
     
     

    public UploadedFile getSlika() {
        return slika;
    }

    public void setSlika(UploadedFile slika) {
        this.slika = slika;
    }
     
     

    public boolean isMenadzer() {
        return menadzer;
    }

    public void setMenadzer(boolean menadzer) {
        this.menadzer = menadzer;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
    
    
   
    private int tip = 0;
    private int odobren = 0;
    private ArrayList<Registracija> traze;

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getOdobren() {
        return odobren;
    }

    public void setOdobren(int odobren) {
        this.odobren = odobren;
    }

    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getPol() {
        return pol;
    }

    public void setPol(int pol) {
        this.pol = pol;
        if(pol==0) pol1="muski";
        else {pol1="zenski";}
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Registracija() {
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    

    private Session sesija;

    public String registrujse() {
        poruka="";
        Registracija reg = null;

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();
        reg = (Registracija) sesija.get(Registracija.class, username);
        // sesija.getTransaction().commit();

        if (reg != null) {
            poruka = "Molimo odaberite drugo korisničko ime.";
            sesija.close();
            return "registrovanje";
        }

        if (!password.equals(password1)) {
            poruka = "Niste dobro potvrdili lozinku, pokušajte ponovo";
            sesija.close();
            return "registrovanje";
        }

        Pattern p = Pattern.compile(".{8,12}");
        Matcher m = p.matcher(password);
        boolean b = m.matches();

        if (!b) {
            poruka = "lozinka mora biti dužine od 8 do 12 karaktera";
            sesija.close();
            return "registrovanje";
        }

        p = Pattern.compile(".*[A-Z]{1,}.*");
        if (!p.matcher(password).matches()) {
            poruka = "lozinka mora da sadrži barem jedno veliko slovo";
            sesija.close();
            return "registrovanje";
        }

        p = Pattern.compile(".*[a-z].*[a-z].*[a-z].*");
        if (!p.matcher(password).matches()) {
            poruka = "lozinka mora da sadrži barem tri mala slova";
            sesija.close();
            return "registrovanje";
        }

        p = Pattern.compile(".*[1-9].*");
        if (!p.matcher(password).matches()) {
            poruka = "lozinka mora da sadrži barem jednu cifru";
            sesija.close();
            return "registrovanje";
        }

        p = Pattern.compile(".*[^a-z0-9 ].*");
        if (!p.matcher(password).matches()) {
            poruka = "lozinka mora da sadrži barem jedan specijalan karakter";
            sesija.close();
            return "registrovanje";
        }

        p = Pattern.compile("^[A-Za-z].*");
        if (!p.matcher(password).matches()) {
            poruka = "lozinka mora da počinje malim ili velikim slovom";
            sesija.close();
            return "registrovanje";
        }
        
        p = Pattern.compile(".*?(.)\\1{2,}.*?");
        if (p.matcher(password).matches()) {
            poruka = "lozinka ne sme imati tri ista karaktera u nizu";
            sesija.close();
            return "registrovanje";
        }
        
        p=Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        if (!p.matcher(mail).matches()) {
            poruka = "Neispravna email adresa";
            sesija.close();
            return "registrovanje";
        }
        
        byte[] bajtovi=slika.getContents();
        if (bajtovi != null) {
            if (bajtovi.length > 100) {
                try {
                    String putanja = Kompanija.dohvati();
                    stringZaBazu = "logo" + this.username + slika.getFileName();
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
        

        try {
            
            lozinka = SHA.generateHashNoSalt(password);
            //sesija = Sesija.getSessionFactory().openSession();
            //sesija.beginTransaction();

            sesija.save(this);
            sesija.getTransaction().commit();
            poruka = "";
            sesija.close();
            return "registrovanje";
        }catch(HibernateException e){
            e.printStackTrace();
        }
        sesija.close();
        return "index";
    }

    public String login() {
        poruka="";
        sesija = Sesija.getSessionFactory().openSession();

        sesija.beginTransaction();

        Registracija reg = (Registracija) sesija.get(Registracija.class, username);

        sesija.close();

        if (reg == null) {
            poruka = "Uneli ste nepostojeće korisničko ime.";
            return "index";
        }

        if (!reg.getLozinka().equals(SHA.generateHashNoSalt(password))) {
            poruka = "Niste uneli dobru lozinku.";
            return "index";
        }

        if (reg.odobren == 1) {
            if (reg.tip == 0) {
                return "clan";
            }
        }

        if (reg.tip == 2) {
            this.izlistaj();
            admin=true;
            return "admin";
        }

        if (reg.tip == 1) {
            menadzer=true;
            return "menadzer";
        }
        
        
        poruka = "Još vam nije odobren zahtev";
        return "index";
    }

    public ArrayList<Registracija> getTraze() {
        return traze;
    }

    public void setTraze(ArrayList<Registracija> traze) {
        this.traze = traze;
    }

    public void izlistaj() {
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Registracija where odobren=0 and tip=0");
        List<Registracija> list = query.list();
        traze = (ArrayList<Registracija>) list;

        sesija.getTransaction().commit();
        sesija.close();
        
        for(Registracija r: traze){
            if(r.getPol()==0) {r.pol1="muski";}
            else {r.pol1="zenski";}
        }

       

    }

    public String promeni() {
        poruka="";
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();
        Registracija reg = (Registracija) sesija.get(Registracija.class, username);
        //sesija.getTransaction().commit();
        if (reg == null) {
            poruka = "Uneli ste nepostojeće korisničko ime.";
            sesija.close();
            return "menjanje";
        }

        if (!reg.getLozinka().equals(SHA.generateHashNoSalt(password))) {
            poruka = "Niste uneli dobru lozinku.";
            sesija.close();
            return "menjanje";
        }

        password = password1;

        Pattern p = Pattern.compile(".{8,12}");
        Matcher m = p.matcher(password);
        boolean b = m.matches();

        if (!b) {
            poruka = "lozinka mora biti dužine od 8 do 12 karaktera";
            sesija.close();
            return "menjanje";
        }

        p = Pattern.compile(".*[A-Z]{1,}.*");
        if (!p.matcher(password).matches()) {
            poruka = "lozinka mora da sadrži barem jedno veliko slovo";
            sesija.close();
            return "menjanje";
        }

        p = Pattern.compile(".*[a-z].*[a-z].*[a-z].*");
        if (!p.matcher(password).matches()) {
            poruka = "lozinka mora da sadrži barem tri mala slova";
            sesija.close();
            return "menjanje";
        }

        p = Pattern.compile(".*[1-9].*");
        if (!p.matcher(password).matches()) {
            poruka = "lozinka mora da sadrži barem jednu cifru";
            sesija.close();
            return "menjanje";
        }

        p = Pattern.compile(".*[^a-z0-9 ].*");
        if (!p.matcher(password).matches()) {
            poruka = "lozinka mora da sadrži barem jedan specijalan karakter";
            sesija.close();
            return "menjanje";
        }

        p = Pattern.compile("^[A-Za-z].*");
        if (!p.matcher(password).matches()) {
            poruka = "lozinka mora da počinje malim ili velikim slovom";
            sesija.close();
            return "menjanje";
        }
        
       
                
        p = Pattern.compile( ".*?(.)\\1{2,}.*?");
        if (p.matcher(password).matches()) {
            poruka = "lozinka ne sme imati tri ista karaktera u nizu";
            sesija.close();
            return "menjanje";
        }

        lozinka = SHA.generateHashNoSalt(password);

        //sesija = Sesija.getSessionFactory().openSession();
        //sesija.beginTransaction();
        reg.lozinka = lozinka;
        sesija.update(reg);
        sesija.getTransaction().commit();
        poruka = "Uspešno ste promenili lozinku.";
        sesija.close();
        return "index";
    }

    public String odobri(Registracija r) {
        try {

            username = r.username;
            sesija = Sesija.getSessionFactory().openSession();
            sesija.beginTransaction();
            Registracija reg = (Registracija) sesija.get(Registracija.class, username);
            reg.setOdobren(1);
            sesija.getTransaction().commit();

        } finally {
            sesija.close();
        }
        this.izlistaj();
        return "admin";
    }

    public String odbaci(Registracija r) {

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();
        sesija.delete(r);
        sesija.getTransaction().commit();
        sesija.close();
        this.izlistaj();
        return "admin";
    }
    
    public String odjaviSe(){
        FacesContext fc=FacesContext.getCurrentInstance();
        fc.getExternalContext().invalidateSession();
        return "index";
    }

}

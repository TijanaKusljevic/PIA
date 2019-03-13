/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import util.Sesija;

/**
 *
 * @author tijana
 */
@ManagedBean
@SessionScoped
public class Ugovor {

    private LineChartModel dateModel;
    private int id;
    private double vrednost;
    private String paket;
    private Date datum;
    private int status;
    private Date istice;
    private String komentar;
    private String kompanja;
    private int komp;
    private int pak;
    private boolean istekao=false;
    
    private String vrsta="";

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }
    
    

    public boolean isIstekao() {
        return istekao;
    }

    public void setIstekao(boolean istekao) {
        this.istekao = istekao;
    }
    
    
           

    private Date danas;
    private Date sledeci;

    public Date getDanas() {
        return danas;
    }

    public void setDanas(Date danas) {
        this.danas = danas;
    }

    public Date getSledeci() {
        return sledeci;
    }

    public void setSledeci(Date sledeci) {
        this.sledeci = sledeci;
    }

    public LineChartModel getDateModel() {
        return dateModel;
    }

    public void setDateModel(LineChartModel dateModel) {
        this.dateModel = dateModel;
    }

    public Ugovor() {
    }

    private Session sesija;
    private ArrayList<Novcani> svi = new ArrayList<>();
    private ArrayList<Donatorski> svid = new ArrayList<>();
    private ArrayList<Ugovor> ugovori=new ArrayList<>();
    
    
    public void iscrtaj() {
        ugovori=new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Novcani");
        List<Novcani> list = query.list();
        svi = (ArrayList<Novcani>) list;
         

        sesija.getTransaction().commit();
        sesija.close();

        for (Novcani n : svi) {
             String ss=dateFormat.format(n.getIstice());
            series1.set(ss, n.getVrednost());
        }
        
      //  ugovori.addAll(svi);
        
        
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query1 = sesija.createQuery("from Donatorski");
        List<Donatorski> lista = query1.list();
        svid = (ArrayList<Donatorski>) lista;

        sesija.getTransaction().commit();
        sesija.close();

        for (Donatorski n : svid) {
            String ss=dateFormat.format(n.getIstice());
            series1.set(ss, n.getVrednost());
        }
        
      

        danas = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6);
        sledeci = cal.getTime();


        dateModel.addSeries(series1);
        dateModel.setTitle("Grafik o isteku ugovora");
        dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("Vrednost paketa");
        dateModel.getAxis(AxisType.Y).setMin(0);
        DateAxis axis = new DateAxis("Datumi");
        axis.setTickAngle(-50);

        
        String d1 = null;

        d1 = dateFormat.format(sledeci);
        String d2=dateFormat.format(danas);
        
        axis.setMax(d1);

        axis.setMin(d2);
        axis.setTickFormat("%b %#d, %y");

        dateModel.getAxes().put(AxisType.X, axis);
        
        

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getIstice() {
        return istice;
    }

    public void setIstice(Date istice) {
        this.istice = istice;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getKompanja() {
        return kompanja;
    }

    public void setKompanja(String kompanja) {
        this.kompanja = kompanja;
    }

    public int getKomp() {
        return komp;
    }

    public void setKomp(int komp) {
        this.komp = komp;
    }

    public int getPak() {
        return pak;
    }

    public void setPak(int pak) {
        this.pak = pak;
    }


    
    
}

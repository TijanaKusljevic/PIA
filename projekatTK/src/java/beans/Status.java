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

public class Status {
    private int id;
    private String opis;

    public Status() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
     List<SelectItem> items;
     private Session sesija;
     private ArrayList<Status> svi=new ArrayList<>();
     
     public void procitaj(){
        sesija = Sesija.getSessionFactory().openSession();
        sesija.beginTransaction();

        Query query = sesija.createQuery("from Status");
        List<Status> list = query.list();
        svi = (ArrayList<Status>) list;

        sesija.getTransaction().commit();
        sesija.close();
        
        
        items=new ArrayList<>();
        for(Status p: svi){
            SelectItem s=new SelectItem(p.id, p.opis);
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

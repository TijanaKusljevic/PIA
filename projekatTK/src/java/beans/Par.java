/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author tijana
 */
@ManagedBean
@SessionScoped
public class Par {
    private String ime;
    private String komp;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getKomp() {
        return komp;
    }

    public void setKomp(String komp) {
        this.komp = komp;
    }

    public Par(String ime, String komp) {
        this.ime = ime;
        this.komp = komp;
    }
    
    
}

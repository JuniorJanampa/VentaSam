/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author Jhunior
 */
public class Movimiento {
    private String cod_caja;
    private Date fec_caja;
    private String cod_vc;
    private Date fec_vc;
    private String cod_pro;
    private String nom_pro;
    private int total;
    
    public Movimiento(){
        cod_caja = "";
        fec_caja = null;
        cod_vc = "";
        fec_vc = null;
        cod_pro = "";
        nom_pro = "";
        total = 0;
    }

    public String getCod_caja() {
        return cod_caja;
    }

    public void setCod_caja(String cod_caja) {
        this.cod_caja = cod_caja;
    }

    public Date getFec_caja() {
        return fec_caja;
    }

    public void setFec_caja(Date fec_caja) {
        this.fec_caja = fec_caja;
    }

    public String getCod_vc() {
        return cod_vc;
    }

    public void setCod_vc(String cod_vc) {
        this.cod_vc = cod_vc;
    }

    public Date getFec_vc() {
        return fec_vc;
    }

    public void setFec_vc(Date fec_vc) {
        this.fec_vc = fec_vc;
    }

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public String getNom_pro() {
        return nom_pro;
    }

    public void setNom_pro(String nom_pro) {
        this.nom_pro = nom_pro;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}

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
public class Compras {
    private String codigo;
    private Date fecha;
    private double impt;
    private String dni_e;
    private String cod_prov;
    private String cod_caja;
    
    public Compras(){
        codigo = "";
        fecha = null;
        impt = 0.0;
        dni_e = "";
        cod_prov = "";
        cod_caja = "";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImpt() {
        return impt;
    }

    public void setImpt(double impt) {
        this.impt = impt;
    }

    public String getDni_e() {
        return dni_e;
    }

    public void setDni_e(String dni_e) {
        this.dni_e = dni_e;
    }

    public String getCod_prov() {
        return cod_prov;
    }

    public void setCod_prov(String cod_prov) {
        this.cod_prov = cod_prov;
    }

    public String getCod_caja() {
        return cod_caja;
    }

    public void setCod_caja(String cod_caja) {
        this.cod_caja = cod_caja;
    }
    
    
}

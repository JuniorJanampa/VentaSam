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
public class Caja {
    private String codigo;
    private Date fecha;
    private double D_ini;
    private double E_din;
    private double S_dni;
    
    public Caja(){
        codigo = "";
        fecha = null;
        D_ini = 0.0;
        E_din = 0.0;
        S_dni = 0.0;
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

    public double getD_ini() {
        return D_ini;
    }

    public void setD_ini(double D_ini) {
        this.D_ini = D_ini;
    }

    public double getE_din() {
        return E_din;
    }

    public void setE_din(double E_din) {
        this.E_din = E_din;
    }

    public double getS_dni() {
        return S_dni;
    }

    public void setS_dni(double S_dni) {
        this.S_dni = S_dni;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Jhunior
 */
public class Detalleventa {
    private String codigo;
    private String cod_ven;
    private String cod_prod;
    private double cantidad;
    private String um;
    private int und_med;
    private double p_base;
    private double p_cant;
    
    public Detalleventa(){
        codigo = "";
        cod_ven = "";
        cod_prod = "";
        cantidad = 0.0;
        und_med = 0;
        p_base = 0.0;
        p_cant = 0.0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCod_ven() {
        return cod_ven;
    }

    public void setCod_ven(String cod_ven) {
        this.cod_ven = cod_ven;
    }

    public String getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(String cod_prod) {
        this.cod_prod = cod_prod;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }
    
    

    public int getUnd_med() {
        return und_med;
    }

    public void setUnd_med(int und_med) {
        this.und_med = und_med;
    }

    public double getP_base() {
        return p_base;
    }

    public void setP_base(double p_base) {
        this.p_base = p_base;
    }

    public double getP_cant() {
        return p_cant;
    }

    public void setP_cant(double p_cant) {
        this.p_cant = p_cant;
    }
    
}

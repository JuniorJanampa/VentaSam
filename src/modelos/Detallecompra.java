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
public class Detallecompra {
    private String codigo;
    private String cod_com;
    private String cod_prod;
    private int cant;
    private int unm;
    private String un_m;
    private double p_com;
    private double imp;
    
    public Detallecompra(){
        codigo="";
        cod_com="";
        cod_prod = "";
        cant = 0;
        unm = 0;
        un_m = "";
    }

    public double getP_com() {
        return p_com;
    }

    public void setP_com(double p_com) {
        this.p_com = p_com;
    }

    public double getImp() {
        return imp;
    }

    public void setImp(double imp) {
        this.imp = imp;
    }
    
    
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCod_com() {
        return cod_com;
    }

    public void setCod_com(String cod_com) {
        this.cod_com = cod_com;
    }

    public String getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(String cod_prod) {
        this.cod_prod = cod_prod;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getUnm() {
        return unm;
    }

    public void setUnm(int unm) {
        this.unm = unm;
    }

    public String getUn_m() {
        return un_m;
    }

    public void setUn_m(String un_m) {
        this.un_m = un_m;
    }
    
    
}

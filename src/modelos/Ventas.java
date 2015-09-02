/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Date;

/**
 *
 * @author Jhunior
 */
public class Ventas {
    private String codigo;
    private Date fecha;
    private double imp_tot;
    private String empleado;
    private String cliente;
    private String caja;
    
    public Ventas(){
        codigo = "";
        fecha = null;
        imp_tot = 0.0;
        empleado ="";
        cliente="";
        caja="";
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
    

    public double getImp_tot() {
        return imp_tot;
    }

    public void setImp_tot(double imp_tot) {
        this.imp_tot = imp_tot;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }
    
    
}

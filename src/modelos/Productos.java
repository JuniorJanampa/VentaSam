/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author Jhunior
 */
public class Productos {
    private String codigo;
    private String nombre;
    private String categoria;
    private double precio_compra;
    private int stok;
    private String unidad_medida;
    private int unidad_med;
    private double precio_venta;
    private String cod_prov;
    private String razons;
    private FileInputStream imagen;
    private InputStream mimagen;
    
    public Productos(){
        codigo = "";
        nombre = "";
        categoria = "";
        precio_compra = 0.0;
        stok = 0;
        unidad_medida="";
        precio_venta = 0.0;
        cod_prov = "";
        razons = "";
        imagen = null;
        mimagen = null;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public String getCod_prov() {
        return cod_prov;
    }

    public void setCod_prov(String cod_prov) {
        this.cod_prov = cod_prov;
    }

    public String getRazons() {
        return razons;
    }

    public void setRazons(String razons) {
        this.razons = razons;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public FileInputStream getImagen() {
        return imagen;
    }

    public void setImagen(FileInputStream imagen) {
        this.imagen = imagen;
    }

    public int getUnidad_med() {
        return unidad_med;
    }

    public void setUnidad_med(int unidad_med) {
        this.unidad_med = unidad_med;
    }

    public InputStream getMimagen() {
        return mimagen;
    }

    public void setMimagen(InputStream mimagen) {
        this.mimagen = mimagen;
    }
    
    
}

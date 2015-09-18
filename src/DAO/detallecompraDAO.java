/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Detallecompra;

/**
 *
 * @author Jhunior
 */
public class detallecompraDAO {
    conexion conec;
    
    public detallecompraDAO(){
        conec = new conexion();
    }
    
    public String insertarDetalle(String codCo,String codProd,double cant,int unm){
        String rpta="";
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call detc_ins(?,?,?,?)}");
            cs.setString(1, codCo);
            cs.setString(2, codProd);
            cs.setDouble(3, cant);
            cs.setInt(4, unm);
            int filas = cs.executeUpdate();
            if(filas>0){
                rpta = "Registro Exitoso";
            }
        } catch (Exception e) {
        }
        return rpta;
    }
    
    public ArrayList<Detallecompra> listaDetCom(String cod_com){
        ArrayList listaDetCom = new ArrayList();
        Detallecompra modeloDCo;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call detc_lis(?)}");
            cs.setString(1, cod_com);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloDCo = new Detallecompra();
                modeloDCo.setCodigo(rs.getString(1));
                modeloDCo.setCod_com(rs.getString(2));
                modeloDCo.setCod_prod(rs.getString(3));
                modeloDCo.setCant(rs.getDouble(4));
                modeloDCo.setUn_m(rs.getString(5));
                listaDetCom.add(modeloDCo);
            }
        } catch (Exception e) {
        }
        return listaDetCom;
    }
}

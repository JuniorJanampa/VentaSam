/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import modelos.Productos;

/**
 *
 * @author Jhunior
 */
public class faltantesDAO {
    conexion conec;
    
    public faltantesDAO(){
        conec = new conexion();
    }
    
    public ArrayList<Productos> filtrarProv(String razon){
        ArrayList listaProd = new ArrayList();
        Productos modeloProd;
        try {
            Connection accesoBD = conec.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call fal_prov(?)}");
            cs.setString(1, razon);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                modeloProd = new Productos();
                modeloProd.setCodigo(rs.getString(1));
                modeloProd.setNombre(rs.getString(2));
                modeloProd.setCategoria(rs.getString(3));
                modeloProd.setPrecio_compra(rs.getDouble(4));
                modeloProd.setStok(rs.getInt(5));
                modeloProd.setUnidad_medida(rs.getString(6));
                modeloProd.setPrecio_venta(rs.getDouble(7));
                modeloProd.setCod_prov(rs.getString(8));
                modeloProd.setRazons(rs.getString(9));
                modeloProd.setMimagen(rs.getBinaryStream(10));
                listaProd.add(modeloProd);
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
        conec.desconectar();
        return listaProd;
    }
    
}

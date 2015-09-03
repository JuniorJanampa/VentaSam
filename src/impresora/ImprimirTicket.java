/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impresora;

import DAO.productoDAO;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelos.Detalleventa;
import modelos.Productos;

/**
 *
 * @author edson
 */
public class ImprimirTicket {
    Productos pro = new Productos();
    productoDAO prodao= new productoDAO();
    public void GenerarTickets(String nroventa,String personal,String fecha,ArrayList<Detalleventa> listaproductos,String total, String monedaPago, String monedavuelto ) throws FileNotFoundException, DocumentException{
        
       
        Ticket ticket = new Ticket();
        diasFestivos diaf= new diasFestivos();
        
        ticket.AddCabecera("Sistema de Ventas");
        
        ticket.AddCabecera(ticket.DarEspacio()); 
        ticket.AddCabecera(ticket.DibujarLinea(29)); 
        ticket.AddCabecera(ticket.DarEspacio()); 
        ticket.AddCabecera("SCAMVentas 1.1"); 
        ticket.AddCabecera(ticket.DarEspacio()); 
        ticket.AddSubCabecera("Venta Nro: "+nroventa); 
        ticket.AddSubCabecera(ticket.DarEspacio()); 
        ticket.AddSubCabecera("LE ATENDIO: "+personal); 
        ticket.AddSubCabecera(ticket.DarEspacio()); 
        ticket.AddSubCabecera(""+fecha); 
        ticket.AddSubCabecera(ticket.DarEspacio()); 
        ticket.AddSubCabecera(ticket.DibujarLinea(29)); 
        ticket.AddSubCabecera(ticket.DarEspacio());
        ticket.AddSubCabecera("Cant. - Prod. - Precio");
        ticket.AddSubCabecera(ticket.DarEspacio());
        for (int i = 0; i < listaproductos.size(); i++) {
            pro=prodao.buscarP(listaproductos.get(i).getCod_prod());
            String cunm= String.valueOf(listaproductos.get(i).getCantidad()) + pro.getUnidad_medida();
            ticket.AddItem(cunm,"   "+ pro.getNombre(),"   "+ String.valueOf(listaproductos.get(i).getP_cant()));
            ticket.AddItem("","",ticket.DarEspacio()); 
            JOptionPane.showMessageDialog(null, cunm);
        }
        
        ticket.AddItem("","",ticket.DarEspacio()); 
        ticket.AddTotal("",ticket.DibujarLinea(29)); 
        ticket.AddTotal("",ticket.DarEspacio()); 
        ticket.AddTotal("TOTAL:",total);
        ticket.AddTotal("",ticket.DarEspacio()); 
        ticket.AddTotal("RECIBIDO:",monedaPago); 
        ticket.AddTotal("",ticket.DarEspacio()); 
        ticket.AddTotal("VUELTO:",monedavuelto); 
        ticket.AddTotal("",ticket.DarEspacio()); 
        ticket.AddPieLinea(ticket.DibujarLinea(29)); 
        ticket.AddPieLinea(ticket.DarEspacio()); 
        ticket.AddPieLinea("Gracias por su visita"); 
        ticket.AddPieLinea(ticket.DarEspacio());
        ticket.AddPieLinea(diaf.VerificarDiaFestivo()); 
        ticket.AddPieLinea(ticket.DarEspacio());
        ticket.ImprimirDocumento();
        //ticket.GenerarDocumento();
        
    }
}
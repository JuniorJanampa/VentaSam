/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impresora;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author edson
 */
public class Ticket {
    public ArrayList<String> CabezaLineas=new ArrayList<String>();
    public ArrayList<String> subCabezaLineas= new ArrayList<String>();
    public ArrayList<String> items=new ArrayList<String>(); 
    public ArrayList<String> totales=new ArrayList<String>(); 
    public ArrayList<String> LineasPie=new ArrayList<String>();
    
   
    
    
    public void AddCabecera(String line){
        CabezaLineas.add(line);
    }
    public  void AddSubCabecera(String line){
        subCabezaLineas.add(line);
    }
    public void AddItem(String cantidad,String item,String price){
        OrderItem newItem = new OrderItem(' ');
        items.add(newItem.GeneraItem(cantidad,item, price)); 
    }
    public void AddTotal(String name,String price){ 
        OrderTotal newTotal = new OrderTotal(' '); 
        totales.add(newTotal.GeneraTotal(name, price)); 
    }
    
    public void AddPieLinea(String line){
        LineasPie.add(line);
    } 
    public  String DibujarLinea(int valor){ 
        String raya="";for(int x=0;x<valor;x++){raya+="=";}return raya; 
    } 
    public static String DarEspacio(){
        return "\n";
    } 
    public void ImprimirDocumento() { 
        String cadena=""; 
        for(int cabecera=0;cabecera<CabezaLineas.size();cabecera++){
            cadena+=CabezaLineas.get(cabecera);
        }
        for(int subcabecera=0;subcabecera<subCabezaLineas.size();subcabecera++){
            cadena+=subCabezaLineas.get(subcabecera);
        }
        for(int ITEM=0;ITEM<items.size();ITEM++){
            cadena+=items.get (ITEM);
        } 
        for(int total=0;total<totales.size();total++){
            cadena+=totales.get(total);
        } 
        for(int pie=0;pie<LineasPie.size();pie++){
            cadena+=LineasPie.get(pie);
        } 
        
        System.out.println(cadena);
     
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE; 
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob pj = service.createPrintJob(); 
        byte[]bytes =cadena.getBytes(); 
        Doc doc = new SimpleDoc(bytes, flavor,null);
        
        try{
            pj.print(doc,null); 
        }catch(Exception e){ } /**/
    } 
    public void GenerarDocumento() throws FileNotFoundException, DocumentException{ 
        String cadena="";
        if (JOptionPane.showConfirmDialog(null,"¿Quisiera generar un archivo de texto de la venta?","SCAMVentas - Venta",JOptionPane.OK_CANCEL_OPTION)==0){
                   
        try{
            FileOutputStream archivo = new FileOutputStream(EscogerRuta()+".pdf");
            Document documento = new Document();
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            for(int cabecera=0;cabecera<CabezaLineas.size();cabecera++){
                cadena+=CabezaLineas.get(cabecera);
                //documento.add(new Paragraph(cadena));
            }
            for(int subcabecera=0;subcabecera<subCabezaLineas.size();subcabecera++){
                cadena+=subCabezaLineas.get(subcabecera);
                //documento.add(new Paragraph(cadena));
            }
            for(int ITEM=0;ITEM<items.size();ITEM++){
                cadena+=items.get (ITEM);
                //documento.add(new Paragraph(cadena));
            } 
            for(int total=0;total<totales.size();total++){
                cadena+=totales.get(total);
                //documento.add(new Paragraph(cadena));
            } 
            for(int pie=0;pie<LineasPie.size();pie++){
                cadena+=LineasPie.get(pie);
                //documento.add(new Paragraph(cadena));
            }
            documento.add(new Paragraph(cadena));
            documento.close();
         
        }catch(Exception e){ System.out.println(e); } /**/
        
        }
    } 
    
    public String EscogerRuta(){
        JFileChooser jfile = new JFileChooser();
        String ruta="";
        int op=jfile.showSaveDialog(null);;
        
        if (op==JFileChooser.APPROVE_OPTION) {
          File guardar  = jfile.getSelectedFile();
         ruta = guardar.toString();
        }
        return ruta;
    }    
} 


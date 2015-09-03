/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impresora;

import DAO.empleadoDAO;
import DAO.productoDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modelos.Detalleventa;
import modelos.Empleado;
import modelos.Productos;


/**
 *
 * @author edson
 */
public class ImpTicket{
    
    Productos pro = new Productos();
    productoDAO prodao= new productoDAO();
    Empleado modeloEm;
    empleadoDAO daoem = new empleadoDAO();
    private String contentTicket = " {{nombreEmpresa}}\n"
            + " {{direccion}}\n"
            + " ============================\n"
            + " Venta # {{nroventa}}\n"
            + " LE ATENDIO: {{empleado}}\n"
            + " {{fecha}}\n"
            + " Cant.  Descripcion   Importe\n"
            + " ============================\n";
            
         

    public ImpTicket(String nroventa, String empleado, String fecha, ArrayList<Detalleventa> items, String total, String monpag, String monvuel) {
       modeloEm= new Empleado();
       modeloEm=daoem.VerEmpresa();
        this.contentTicket = this.contentTicket.replace("{{nombreEmpresa}}", modeloEm.getNombres());
        this.contentTicket = this.contentTicket.replace("{{direccion}}", modeloEm.getDireccion());
        this.contentTicket = this.contentTicket.replace("{{nroventa}}", nroventa);
        this.contentTicket = this.contentTicket.replace("{{empleado}}", empleado);
        this.contentTicket = this.contentTicket.replace("{{fecha}}", fecha);
        
        for (int i = 0; i < items.size(); i++) {
            this.contentTicket = this.contentTicket+"\n{{items}}";
            pro=prodao.buscarP(items.get(i).getCod_prod());
            String cunm= String.valueOf(items.get(i).getCantidad()) + pro.getUnidad_medida();
            String nombre= pro.getNombre();
            String precio=String.valueOf(items.get(i).getP_cant());
            this.contentTicket = this.contentTicket.replace("{{items}}", cunm +" - "+nombre +" - "+precio);
        }
        this.contentTicket=this.contentTicket
            + " \n============================\n"
            + " TOTAL: {{total}}              \n" 
            + " RECIBIDO: {{monrec}}\n"
            + " CAMBIO: {{moncam}}\n"
            + " ============================\n"
            + " Gracias por su compra,\n"
            + " vuelva pronto.\n"
            + " ******::::::::*******"
            + "\n           "
            + "\n           ";

        this.contentTicket = this.contentTicket.replace("{{total}}", total);
        this.contentTicket = this.contentTicket.replace("{{monrec}}", monpag);
        this.contentTicket = this.contentTicket.replace("{{moncam}}", monvuel);
        
    
    }
    
    public void Imprimir() throws FileNotFoundException, DocumentException{
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        if (printService.length>0){
            PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);
            byte[] bytes;


            bytes = this.contentTicket.getBytes();

            //Creamos un documento a imprimir, a el se le appendeara
            //el arreglo de bytes
            Doc doc = new SimpleDoc(bytes, flavor, null);

            //Creamos un trabajo de impresiÃ³n
            DocPrintJob job = service.createPrintJob();

            //Imprimimos dentro de un try de a huevo
            try {
                //El metodo print imprime

                job.print(doc, null);
                JOptionPane.showMessageDialog(null, "se está imprimiendo...");

            } catch (PrintException | HeadlessException er) {
                JOptionPane.showMessageDialog(null, "Error al imprimir: " + er.getMessage());
            }
          } else {
            GenerarDocumento();
          }
    }
    public void GenerarDocumento() throws FileNotFoundException, DocumentException{ 
        if (JOptionPane.showConfirmDialog(null,"¿Quisiera generar un archivo de texto de la venta?","SCAMVentas - Venta",JOptionPane.OK_CANCEL_OPTION)==0){
                   
        try{
            FileOutputStream archivo = new FileOutputStream(EscogerRuta()+".pdf");
            Document documento = new Document();
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            documento.add(new Paragraph(contentTicket));
            documento.close();
         
        }catch(FileNotFoundException | DocumentException e){ System.out.println(e); } /**/
        
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

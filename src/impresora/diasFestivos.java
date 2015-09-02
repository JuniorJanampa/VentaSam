/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impresora;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author edson
 */
public class diasFestivos {
    String fecha;
    public String VerificarDiaFestivo(){
        String saludo="";
        Date ahora = new java.sql.Date(System.currentTimeMillis());
        SimpleDateFormat forma = new SimpleDateFormat("dd-MM");
        String fecha = String.valueOf(forma.format(ahora));
           
        switch (fecha){
            case "01-01":
                saludo="¡Feliz Año Nuevo!...";
                break;
            case "14-02":
                saludo="¡Feliz día de San Valentin!...";
                break;
            case "01-05":
                saludo="Día del Trabajo";
                break;
            case "25-05":
                saludo="[Fs]MoeBiuS";
                break;
            case "28-06":
                saludo="Dia de San Pedro y San Pablo";
                break;
            case "29-06":
                saludo="Dia de San Pedro y San Pablo";
                break;
            case "28-07":
                saludo="¡Felices Fiestas Patrias!...";
                break;
            case "29-07":
                saludo="¡Felices Fiestas Patrias!...";
                break;
            case "17-10":
                saludo="Jhuni";
                break;
            case "30-08":
                saludo="Dia de Santa Rosa de Lima";
                break;
            case "08-10":
                saludo="Dia de San Pedro y San Pablo";
                break;
            case "31-10":
                saludo="¡Feliz Halloween, Día de la canción Criolla!...";
                break;
            case "01-11":
                saludo="Dia de Todos los Santos";
                break;
            case "24-12":
                saludo="Que tenga una Noche Buena";
                break;
            case "25-12":
                saludo="¡Feliz Navidad!...";
                break;
            case "30-12":
                saludo="Wrd14n1";
                break;
            case "31-12":
                saludo="Hoy es Fin de Año";
                break;
                    
        }
        return saludo;
    }
}

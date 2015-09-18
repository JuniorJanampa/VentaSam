/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

/**
 *
 * @author edson
 */
public class Conversion {

    public Conversion() {
    }
    
    public double convertir(double peso, String medida){
       double conv = 0.0;
       if (medida =="KG"){
           conv=peso/10;
       }
       if (medida=="GR"){
           conv=peso*10;
       }
       return conv;
    }
}

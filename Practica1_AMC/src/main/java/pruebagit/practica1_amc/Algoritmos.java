/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebagit.practica1_amc;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Algoritmos {
    
    public static Punto exhaustivo(ArrayList<Punto> puntos){
        double dis_min = 0;
        double dis = 0; 
        
        for (Punto p1 : puntos) {
            Punto primer_punto = p1;
            
            for(Punto p2 : puntos){
                dis_min = primer_punto.distancia(p2);
                if(dis < dis_min){
                    dis_min = dis;
                }
                
            }
        }
    }
}

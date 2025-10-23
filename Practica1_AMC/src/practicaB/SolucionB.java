/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaB;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class SolucionB {
    double distanciatotal;
    int[] ruta;
    ArrayList<Double> distancias = new ArrayList<>();
    
    public SolucionB(){
        
    }
    
     public SolucionB(double distanciatotal, int[] ruta, ArrayList<Double> distancias) {
        this.distanciatotal = distanciatotal;
        this.ruta = ruta;
        this.distancias = distancias;
    }

    public double getDistanciatotal() {
        return distanciatotal;
    }

    public int[] getRuta() {
        return ruta;
    }

    public ArrayList<Double> getDistancias() {
        return distancias;
    }

    public void setDistanciatotal(double distanciatotal) {
        this.distanciatotal = distanciatotal;
    }

    public void setRuta(int[] ruta) {
        this.ruta = ruta;
    }

    public void setDistancias(ArrayList<Double> distancias) {
        this.distancias = distancias;
    } 
    
}

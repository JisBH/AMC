package practicaA;

import Comun.Punto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
public class SolucionA {
    Punto p1;
    Punto p2;
    double dist;
    int calculadas;
    
    public SolucionA(){
        p1=null;
        p2=null;
        dist=0;
    }

    public SolucionA(Punto p1, Punto p2) {
        this.p1 = p1;
        this.p2 = p2;
        dist = distancia(p1, p2);
    }
    
    public Punto getP1() {
        return p1;
    }

    public Punto getP2() {
        return p2;
    }

    public double getDist() {
        return dist;
    }
    
    public int getCalculadas(){
        return calculadas;
    }

    public void setP1(Punto p1) {
        this.p1 = p1;
    }

    public void setP2(Punto p2) {
        this.p2 = p2;
    }
    
    public void setPuntos(Punto p1, Punto p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }
    
    public void setCalculadas(int i){
        calculadas = i;
    }
    
    public void calcularDist(){
        dist = distancia(p1, p2);
    }
    
    public double distancia(Punto a, Punto b) {
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        
    }
    
    @Override
    public String toString(){
        return ("La distancia minima es: "+dist+" entre el punto "+p1.toString()+" y el punto "+p2.toString());
    }
    
}
package Comun;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
public class Punto {
    
    private double x;
    private double y;
    private int i;
    
    public Punto(){
        x=0;    y=0;
    }   
            
    public Punto(double x, double y, int i){
        this.x=x;   this.y=y;   this.i=i;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public int getIndice(){
        return i;
    }
    
    public void setX(double x){
        this.x=x;
    }
    
    public void setY(double y){
        this.y=y;
    }
    
    public void setId(int i){
        this.i=i;
    }
    
    @Override
    public String toString(){
        return (i+" ("+x+", "+y+")");
    }
}

package practica2;

import Comun.Punto;
import java.util.ArrayList;

public class SolucionTSP {
    private ArrayList<Punto> ruta;
    private double distanciaTotal;
    private int calculadas;
    private double tiempoEjecucion; // En milisegundos

    public SolucionTSP() {
        this.ruta = new ArrayList<>();
        this.distanciaTotal = 0;
        this.calculadas = 0;
    }

    public void agregarPunto(Punto p) {
        this.ruta.add(p);
    }

    public ArrayList<Punto> getRuta() {
        return ruta;
    }

    public void setRuta(ArrayList<Punto> ruta) {
        this.ruta = ruta;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }
    
    public void sumarDistancia(double d) {
        this.distanciaTotal += d;
    }

    public int getCalculadas() {
        return calculadas;
    }

    public void setCalculadas(int calculadas) {
        this.calculadas = calculadas;
    }
    
    public void sumarCalculadas(int n) {
        this.calculadas += n;
    }

    public double getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(double tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    @Override
    public String toString() {
        return String.format("Coste: %.4f | Calculadas: %d | Tiempo: %.4f ms", distanciaTotal, calculadas, tiempoEjecucion);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaB;

/**
 *
 * @author USER
 */
public class SolucionCiudad {

    int ciudad;
    double distancia;

    public SolucionCiudad() {

    }

    public SolucionCiudad(int destino, double distancia) {
        this.ciudad = destino;
        this.distancia = distancia;
    }

    public int getCiudad() {
        return ciudad;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setCiudad(int ciudad) {
        this.ciudad = ciudad;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

}

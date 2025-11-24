package Modelo;

public interface Proceso {
    public abstract boolean esFinal(String estado); 
    public abstract boolean reconocer(String cadena);

    @Override
    public abstract String toString();
}
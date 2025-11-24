package Modelo;

import java.util.Set;

public class Transicion {

    private String estadoOrigen;
    private char simbolo;
    private String estadoDestino; 
    private Set<String> estadosDestinoSet; 

    public Transicion(String estadoIni, char cambio, String estadoFin) {
        this.estadoOrigen = estadoIni;
        this.simbolo = cambio;
        this.estadoDestino = estadoFin;
    }

    public Transicion(String estadoIni, char cambio, Set<String> estadosFin) {
        this.estadoOrigen = estadoIni;
        this.simbolo = cambio;
        this.estadosDestinoSet = estadosFin;
    }

    public Transicion(String estadoIni, Set<String> estadosFin) {
        this.estadoOrigen = estadoIni;
        this.simbolo = 'λ';
        this.estadosDestinoSet = estadosFin;
    }

    public String getEstadoOrigen() {
        return estadoOrigen;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public Set<String> getEstadosDestinoSet() {
        return estadosDestinoSet;
    }
}
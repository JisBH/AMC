package Modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AFD implements Proceso {

    private Set<String> estados; // Usamos Set para evitar duplicados
    private String estadoInicial;
    private Set<String> estadosFinales;
    
    // La JOYA DE LA CORONA: Búsqueda O(1)
    // Mapa: EstadoOrigen -> (Carácter -> EstadoDestino)
    private Map<String, Map<Character, String>> tablaTransiciones;

    private String traza; // Para guardar el historial paso a paso

    public AFD() {
        estados = new HashSet<>();
        estadosFinales = new HashSet<>();
        tablaTransiciones = new HashMap<>();
        traza = "";
    }

    // Configuración del autómata
    public void agregarEstado(String e) {
        estados.add(e);
    }

    public void setEstadoInicial(String e) {
        this.estadoInicial = e;
        if (!estados.contains(e)) estados.add(e);
    }

    public void agregarEstadoFinal(String e) {
        estadosFinales.add(e);
        if (!estados.contains(e)) estados.add(e);
    }

    public void agregarTransicion(String origen, char simbolo, String destino) {
        if (!estados.contains(origen)) estados.add(origen);
        if (!estados.contains(destino)) estados.add(destino);

        // Si no existe la entrada para el origen, la creamos
        tablaTransiciones.putIfAbsent(origen, new HashMap<>());
        // Guardamos la transición directamente en el mapa
        tablaTransiciones.get(origen).put(simbolo, destino);
    }

    // Lógica Core
    public String getTransicion(String estado, char simbolo) {
        if (tablaTransiciones.containsKey(estado)) {
            Map<Character, String> transicionesDelEstado = tablaTransiciones.get(estado);
            if (transicionesDelEstado.containsKey(simbolo)) {
                return transicionesDelEstado.get(simbolo);
            }
        }
        return null; // O manejar estado muerto/error
    }

    @Override
    public boolean esFinal(String estado) {
        return estadosFinales.contains(estado);
    }

    @Override
    public boolean reconocer(String cadena) {
        char[] simbolos = cadena.toCharArray();
        String estadoActual = estadoInicial;
        traza = "Inicio en: " + estadoActual + "\n";

        for (char s : simbolos) {
            String siguiente = getTransicion(estadoActual, s);
            traza += "Estado: " + estadoActual + " + '" + s + "' -> ";
            
            if (siguiente == null) {
                traza += "MUERTO (Rechazada)\n";
                return false; // No hay transición definida, cadena rechazada
            }
            
            estadoActual = siguiente;
            traza += estadoActual + "\n";
        }

        boolean aceptada = esFinal(estadoActual);
        traza += "Fin en: " + estadoActual + ". Resultado: " + (aceptada ? "ACEPTADA" : "RECHAZADA");
        return aceptada;
    }
    
    public String getTraza() { return traza; }

    @Override
    public String toString() {
        // Reconstruimos el String para mostrarlo (aunque internamente usemos Map)
        StringBuilder sb = new StringBuilder();
        sb.append("TIPO: AFD\nESTADOS:");
        for (String e : estados) sb.append(" ").append(e);
        sb.append("\nINICIAL: ").append(estadoInicial);
        sb.append("\nFINALES:");
        for (String e : estadosFinales) sb.append(" ").append(e);
        sb.append("\nTRANSICIONES:\n");
        
        for (String origen : tablaTransiciones.keySet()) {
            Map<Character, String> destinos = tablaTransiciones.get(origen);
            for (Map.Entry<Character, String> entry : destinos.entrySet()) {
                sb.append(origen).append(" '").append(entry.getKey()).append("' ").append(entry.getValue()).append("\n");
            }
        }
        sb.append("FIN");
        return sb.toString();
    }
    
    // Getters necesarios para la Vista
    public Set<String> getEstados() { return estados; }
    public String getEstadoInicial() { return estadoInicial; }
    public Set<String> getEstadosFinales() { return estadosFinales; }
}
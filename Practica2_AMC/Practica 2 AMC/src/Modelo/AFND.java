package Modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class AFND implements Proceso {

    private Set<String> estados;
    private String estadoInicial;
    private Set<String> estadosFinales;

    // Mapa para transiciones normales: Origen -> (Simbolo -> ConjuntoDestinos)
    private Map<String, Map<Character, Set<String>>> transiciones;
    // Mapa separado para Lambdas: Origen -> ConjuntoDestinos
    private Map<String, Set<String>> transicionesLambda;
    
    private String traza;

    public AFND() {
        estados = new HashSet<>();
        estadosFinales = new HashSet<>();
        transiciones = new HashMap<>();
        transicionesLambda = new HashMap<>();
        traza = "";
    }

    // --- Configuración ---
    public void setEstadoInicial(String e) {
        this.estadoInicial = e;
        if (!estados.contains(e)) estados.add(e);
    }
    
    public void agregarEstadoFinal(String e) {
        estadosFinales.add(e);
        if (!estados.contains(e)) estados.add(e);
    }

    public void agregarTransicion(String origen, char simbolo, Set<String> destinos) {
        estados.add(origen);
        estados.addAll(destinos);
        
        transiciones.putIfAbsent(origen, new HashMap<>());
        Map<Character, Set<String>> tOrigen = transiciones.get(origen);
        tOrigen.putIfAbsent(simbolo, new HashSet<>());
        tOrigen.get(simbolo).addAll(destinos);
    }

    public void agregarTransicionLambda(String origen, Set<String> destinos) {
        estados.add(origen);
        estados.addAll(destinos);
        
        transicionesLambda.putIfAbsent(origen, new HashSet<>());
        transicionesLambda.get(origen).addAll(destinos);
    }

    // --- LÓGICA CRÍTICA: Clausura Lambda ---
    // Calcula todos los estados alcanzables desde un conjunto "estados" usando solo λ
    private Set<String> lambdaClausura(Set<String> macroEstado) {
        Set<String> clausura = new HashSet<>(macroEstado);
        Stack<String> pila = new Stack<>();
        pila.addAll(macroEstado);

        while (!pila.isEmpty()) {
            String e = pila.pop();
            // Si el estado tiene transiciones lambda
            if (transicionesLambda.containsKey(e)) {
                for (String alcanzable : transicionesLambda.get(e)) {
                    if (!clausura.contains(alcanzable)) {
                        clausura.add(alcanzable);
                        pila.push(alcanzable);
                    }
                }
            }
        }
        return clausura;
    }

    // Transición para un conjunto de estados con un símbolo
    private Set<String> mover(Set<String> macroEstado, char simbolo) {
        Set<String> alcanzables = new HashSet<>();
        for (String estado : macroEstado) {
            if (transiciones.containsKey(estado) && transiciones.get(estado).containsKey(simbolo)) {
                alcanzables.addAll(transiciones.get(estado).get(simbolo));
            }
        }
        return alcanzables;
    }

    @Override
    public boolean esFinal(String estado) {
        // Este método es para un estado individual, pero en AFND trabajamos con conjuntos.
        // Lo dejamos por compatibilidad con la interfaz.
        return estadosFinales.contains(estado);
    }
    
    // Comprueba si en el macroestado hay ALGÚN estado final
    public boolean esFinal(Set<String> macroEstado) {
        for (String e : macroEstado) {
            if (estadosFinales.contains(e)) return true;
        }
        return false;
    }

    @Override
    public boolean reconocer(String cadena) {
        traza = "";
        // 1. Macroestado inicial = LambdaClausura({estadoInicial})
        Set<String> macroEstadoActual = new HashSet<>();
        macroEstadoActual.add(estadoInicial);
        macroEstadoActual = lambdaClausura(macroEstadoActual);
        
        traza += "Inicio (Clausura): " + macroEstadoActual + "\n";

        char[] simbolos = cadena.toCharArray();
        for (char s : simbolos) {
            // 2. Mover con el símbolo
            Set<String> siguientes = mover(macroEstadoActual, s);
            // 3. Calcular Clausura Lambda de los destinos
            macroEstadoActual = lambdaClausura(siguientes);
            
            traza += "Simbolo '" + s + "' -> Estados: " + macroEstadoActual + "\n";
            
            if (macroEstadoActual.isEmpty()) {
                traza += "CAMINO MUERTO (Rechazada)\n";
                return false;
            }
        }

        boolean aceptada = esFinal(macroEstadoActual);
        traza += "Resultado Final: " + (aceptada ? "ACEPTADA" : "RECHAZADA");
        return aceptada;
    }
    
    public String getTraza() { return traza; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TIPO: AFND\nESTADOS:");
        for (String e : estados) sb.append(" ").append(e);
        sb.append("\nINICIAL: ").append(estadoInicial);
        sb.append("\nFINALES:");
        for (String e : estadosFinales) sb.append(" ").append(e);
        
        sb.append("\nTRANSICIONES:\n");
        for (String origen : transiciones.keySet()) {
            for (Map.Entry<Character, Set<String>> entry : transiciones.get(origen).entrySet()) {
                sb.append(origen).append(" '").append(entry.getKey()).append("'");
                for (String dest : entry.getValue()) sb.append(" ").append(dest);
                sb.append("\n");
            }
        }
        
        sb.append("TRANSICIONES LAMBDA:\n");
        for (String origen : transicionesLambda.keySet()) {
            sb.append(origen);
            for (String dest : transicionesLambda.get(origen)) sb.append(" ").append(dest);
            sb.append("\n");
        }
        
        sb.append("FIN");
        return sb.toString();
    }
    
    public Set<String> getEstados() { return estados; }
    public String getEstadoInicial() { return estadoInicial; }
    public Set<String> getEstadosFinales() { return estadosFinales; }
}
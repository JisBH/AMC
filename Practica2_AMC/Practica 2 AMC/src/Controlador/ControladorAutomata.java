/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.AFD;
import Modelo.AFND;
import Vista.VistaAutomata;
import Vista.VistaMensajes;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.function.Function;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class ControladorAutomata implements ActionListener {

    private VistaAutomata vAutomata;
    private VistaMensajes vMensaje;

    private boolean esAFD;
    private AFD automataD;
    private AFND automataND;
    private DirectedSparseMultigraph<String, String> grafo; // Grafo de Strings

    public ControladorAutomata(AFD automataD) {
        this.automataD = automataD;
        this.esAFD = true;
        init();
    }

    public ControladorAutomata(AFND automataND) {
        this.automataND = automataND;
        this.esAFD = false;
        init();
    }

    private void init() {
        vMensaje = new VistaMensajes();
        vAutomata = new VistaAutomata();
        addListeners();
        vAutomata.setVisible(true);
        // Inicializar grafo JUNG aqui si es necesario o al pulsar pintar
        grafo = new DirectedSparseMultigraph<>();
    }

    private void addListeners() {
        vAutomata.buttonAgregarCadena.addActionListener(this);
        vAutomata.buttonEjecutar.addActionListener(this);
        // vAutomata.buttonPasoAPaso.addActionListener(this); // Si implementas paso a paso
        vAutomata.buttonPintarGrafo.addActionListener(this);
        vAutomata.buttonSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Ejecutar":
                String cadena = vAutomata.textCadena.getText();
                boolean resultado;
                String traza;
                
                if (esAFD) {
                    resultado = automataD.reconocer(cadena);
                    traza = automataD.getTraza(); // Asumiendo que implementaste getTraza en AFD
                } else {
                    resultado = automataND.reconocer(cadena);
                    traza = automataND.getTraza(); // Asumiendo que implementaste getTraza en AFND
                }
                
                vAutomata.textAutomata.setText(traza);
                if(resultado) vMensaje.mensajeJOptionPaneINFORMATION("Cadena ACEPTADA", "Resultado", vAutomata);
                else vMensaje.mensajeJOptionPaneERROR("Cadena RECHAZADA", "Resultado", vAutomata);
                
                break;

            case "Pintar Grafo":
                pintarGrafo();
                break;

            case "Salir":
                vAutomata.dispose();
                break;
        }
    }

    private void pintarGrafo() {
        grafo = new DirectedSparseMultigraph<>();
        Set<String> estados;
        String inicial;
        Set<String> finales;

        if (esAFD) {
            estados = automataD.getEstados();
            inicial = automataD.getEstadoInicial();
            finales = automataD.getEstadosFinales();
            // Reconstruir grafo desde el String toString() o métodos getters
            // Para simplificar, usamos el toString que ya tienes formateado o accedes al Map
            // Aquí es ideal añadir un método getTablaTransiciones() al modelo, pero usaremos toString por ahora si no quieres cambiar más modelo
            // O mejor, itera sobre el modelo si hiciste los getters públicos.
            
            // EJEMPLO GENERIO AGREGANDO NODOS:
            for(String estado : estados) grafo.addVertex(estado);
            
            // Nota: Para las aristas, deberías exponer el Mapa de transiciones en el Modelo
            // O parsear el toString() del automata. 
            // Como no tengo acceso a tu metodo de obtener transiciones aqui mismo:
            // Asegurate de añadir grafo.addEdge("etiqueta", origen, destino);
            
        } else {
            estados = automataND.getEstados();
            inicial = automataND.getEstadoInicial();
            finales = automataND.getEstadosFinales();
            for(String estado : estados) grafo.addVertex(estado);
        }

        VisualizationViewer<String, String> vv = new VisualizationViewer<>(new CircleLayout(grafo));
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());

        // PINTOR DE VÉRTICES CORREGIDO
        Function<String, Paint> vertexPaint = vertex -> {
            // vertex es el String del estado (ej: "q0", "A", "Error")
            
            // 1. Estado Inicial
            if (vertex.equals(inicial)) {
                return Color.GREEN;
            }
            
            // 2. Estado Final
            if (finales.contains(vertex)) {
                return Color.RED;
            }

            // 3. Normal
            return Color.YELLOW;
        };

        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint::apply);

        JDialog frame = new JDialog();
        frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.getContentPane().add(vv, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
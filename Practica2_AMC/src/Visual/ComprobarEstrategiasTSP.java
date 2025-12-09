package Visual;

import Comun.Punto;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import practica2.Algoritmos;
import practica2.SolucionTSP;

public class ComprobarEstrategiasTSP extends javax.swing.JPanel {

    private ArrayList<Punto> dataset;

    public ComprobarEstrategiasTSP(ArrayList<Punto> dataset) {
        this.dataset = dataset;
        initComponents();
        ejecutarAlgoritmos();
    }

    private void ejecutarAlgoritmos() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        // Ejecutar los 4 algoritmos TSP
        SolucionTSP s1 = Algoritmos.tspVorazUnidireccional(new ArrayList<>(dataset));
        model.addRow(new Object[]{"Unidireccional Exhaustivo", s1.getDistanciaTotal(), s1.getCalculadas(), s1.getTiempoEjecucion()});

        SolucionTSP s2 = Algoritmos.tspVorazBidireccional(new ArrayList<>(dataset));
        model.addRow(new Object[]{"Bidireccional Exhaustivo", s2.getDistanciaTotal(), s2.getCalculadas(), s2.getTiempoEjecucion()});

        SolucionTSP s3 = Algoritmos.tspVorazUnidireccionalPoda(new ArrayList<>(dataset));
        model.addRow(new Object[]{"Unidireccional Poda", s3.getDistanciaTotal(), s3.getCalculadas(), s3.getTiempoEjecucion()});

        SolucionTSP s4 = Algoritmos.tspVorazBidireccionalPoda(new ArrayList<>(dataset));
        model.addRow(new Object[]{"Bidireccional Poda", s4.getDistanciaTotal(), s4.getCalculadas(), s4.getTiempoEjecucion()});
        
        // Graficar la solución de la poda bidireccional automáticamente
        new Graficar(dataset, s4); 
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setLayout(new java.awt.BorderLayout());
        
        javax.swing.JLabel lbl = new javax.swing.JLabel("Comprobar Estrategias TSP (Resultados)");
        lbl.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(lbl, java.awt.BorderLayout.NORTH);

        jTable1 = new javax.swing.JTable();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Estrategia", "Solucion", "Calculadas", "Tiempo (ms)" }
        ));
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(jTable1);
        add(scroll, java.awt.BorderLayout.CENTER);
    }
    private javax.swing.JTable jTable1;
}
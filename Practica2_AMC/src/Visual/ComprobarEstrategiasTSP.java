package Visual;

import Comun.Punto;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import practica2.Algoritmos;
import practica2.SolucionTSP;

public class ComprobarEstrategiasTSP extends JPanel {

    private ArrayList<Punto> dataset;
    private MainVisual main;
    private PracticaMenu menu;
    private JTable jTable1;

    // CONSTRUCTOR ACTUALIZADO: Recibe MainVisual y PracticaMenu para poder volver
    public ComprobarEstrategiasTSP(MainVisual main, PracticaMenu menu, ArrayList<Punto> dataset) {
        this.main = main;
        this.menu = menu;
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
        
        // Graficar la solución (automáticamente o puedes poner un botón si prefieres)
        // new Graficar(dataset, s4); // Descomenta si quieres que salte sola
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        // --- Título ---
        JLabel lbl = new JLabel("Resultados Estrategias TSP");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbl, BorderLayout.NORTH);

        // --- Tabla ---
        jTable1 = new JTable();
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] { "Estrategia", "Solucion", "Calculadas", "Tiempo (ms)" }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        JScrollPane scroll = new JScrollPane(jTable1);
        add(scroll, BorderLayout.CENTER);
        
        // --- Panel Inferior con Botones ---
        JPanel panelSur = new JPanel(new FlowLayout());
        
        JButton btnGrafica = new JButton("Ver Gráfica (Mejor Solución)");
        btnGrafica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 // Recalculamos la mejor para mostrarla (o podrías guardarla en una variable de clase)
                 SolucionTSP s = Algoritmos.tspVorazBidireccionalPoda(new ArrayList<>(dataset));
                 new Graficar(dataset, s);
            }
        });
        panelSur.add(btnGrafica);
        
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // AQUÍ ESTÁ LA CLAVE: Volver a cargar el menú en el Main
                main.cambiarPanel(menu);
            }
        });
        panelSur.add(btnVolver);
        
        add(panelSur, BorderLayout.SOUTH);
    }
}
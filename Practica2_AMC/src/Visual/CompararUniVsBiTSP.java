package Visual;

import Comun.Punto;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import practica2.Algoritmos;
import practica2.SolucionTSP;
import org.math.plot.Plot2DPanel;
import java.awt.Color;
import java.awt.BorderLayout;

public class CompararUniVsBiTSP extends javax.swing.JFrame {
    
    public CompararUniVsBiTSP() {
        initComponents();
        setTitle("Comparativa: Uni vs Bidireccional");
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void ejecutar() {
        try {
            int ini = Integer.parseInt(txInicio.getText());
            int fin = Integer.parseInt(txFin.getText());
            int sal = Integer.parseInt(txSalto.getText());
            
            String[] columnas = {"Talla", "Gana Uni", "Gana Bi", "T.Medio Uni (ms)", "T.Medio Bi (ms)"};
            DefaultTableModel model = new DefaultTableModel(columnas, 0);
            jTable1.setModel(model);
            
            ArrayList<Double> xTallas = new ArrayList<>();
            ArrayList<Double> yGanaU = new ArrayList<>();
            ArrayList<Double> yGanaB = new ArrayList<>();
            ArrayList<Double> yTiempoU = new ArrayList<>();
            ArrayList<Double> yTiempoB = new ArrayList<>();

            for(int n = ini; n <= fin; n += sal) {
                int victoriasU = 0;
                int victoriasB = 0;
                double sumaTiempoU = 0;
                double sumaTiempoB = 0;
                int repeticiones = 20;
                
                for(int r = 0; r < repeticiones; r++) {
                    ArrayList<Punto> dataset = genDataset(n);
                    
                    SolucionTSP solU = Algoritmos.tspVorazUnidireccionalPoda(new ArrayList<>(dataset));
                    SolucionTSP solB = Algoritmos.tspVorazBidireccionalPoda(new ArrayList<>(dataset));
                    
                    if (solU.getDistanciaTotal() < solB.getDistanciaTotal()) {
                        victoriasU++;
                    } else if (solB.getDistanciaTotal() < solU.getDistanciaTotal()) {
                        victoriasB++;
                    }
                    
                    sumaTiempoU += solU.getTiempoEjecucion();
                    sumaTiempoB += solB.getTiempoEjecucion();
                }
                
                double mediaTiempoU = sumaTiempoU / repeticiones;
                double mediaTiempoB = sumaTiempoB / repeticiones;
                
                model.addRow(new Object[]{
                    n, 
                    victoriasU, 
                    victoriasB, 
                    String.format("%.4f", mediaTiempoU), 
                    String.format("%.4f", mediaTiempoB)
                });
                
                xTallas.add((double)n);
                yGanaU.add((double)victoriasU);
                yGanaB.add((double)victoriasB);
                yTiempoU.add(mediaTiempoU);
                yTiempoB.add(mediaTiempoB);
            }
            
            mostrarGraficaBarras(xTallas, yGanaU, yGanaB);
            
            mostrarGraficaLineas(xTallas, yTiempoU, yTiempoB);
            
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, introduce números válidos.");
        }
    }
    
    private void mostrarGraficaBarras(ArrayList<Double> x, ArrayList<Double> yU, ArrayList<Double> yB) {
        Plot2DPanel plot = new Plot2DPanel();
        plot.addBarPlot("Victorias Unidireccional", Color.GREEN, listToArray(x), listToArray(yU));
        plot.addBarPlot("Victorias Bidireccional", Color.ORANGE, listToArray(x), listToArray(yB));
        plot.addLegend("SOUTH");
        plot.setAxisLabel(0, "Talla del problema (n)");
        plot.setAxisLabel(1, "Número de Victorias");
        
        JFrame frame = new JFrame("Gráfica: Comparación de Victorias");
        frame.setContentPane(plot);
        frame.setSize(600, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void mostrarGraficaLineas(ArrayList<Double> x, ArrayList<Double> yU, ArrayList<Double> yB) {
        Plot2DPanel plot = new Plot2DPanel();
        plot.addLinePlot("Tiempo Unidireccional", Color.BLUE, listToArray(x), listToArray(yU));
        plot.addLinePlot("Tiempo Bidireccional", Color.RED, listToArray(x), listToArray(yB));
        plot.addLegend("SOUTH");
        plot.setAxisLabel(0, "Talla del problema (n)");
        plot.setAxisLabel(1, "Tiempo Medio (ms)");
        
        JFrame frame = new JFrame("Gráfica: Comparación de Tiempos");
        frame.setContentPane(plot);
        frame.setSize(600, 500);
        frame.setLocation(620, 0);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private double[] listToArray(ArrayList<Double> list) {
        return list.stream().mapToDouble(d -> d).toArray();
    }

    private ArrayList<Punto> genDataset(int n) {
        ArrayList<Punto> p = new ArrayList<>();
        Random r = new Random();
        for(int i=0; i<n; i++) {
            p.add(new Punto(r.nextDouble()*1000, r.nextDouble()*1000, i));
        }
        return p;
    }

    private void initComponents() {
        javax.swing.JPanel pControl = new javax.swing.JPanel();
        txInicio = new javax.swing.JTextField("100", 5);
        txFin = new javax.swing.JTextField("1000", 5);
        txSalto = new javax.swing.JTextField("100", 5);
        javax.swing.JButton btn = new javax.swing.JButton("Comparar");
        btn.addActionListener(e -> ejecutar());
        
        pControl.add(new javax.swing.JLabel("Inicio:")); pControl.add(txInicio);
        pControl.add(new javax.swing.JLabel("Fin:")); pControl.add(txFin);
        pControl.add(new javax.swing.JLabel("Salto:")); pControl.add(txSalto);
        pControl.add(btn);
        
        jTable1 = new javax.swing.JTable();
        
        add(pControl, BorderLayout.NORTH);
        add(new javax.swing.JScrollPane(jTable1), BorderLayout.CENTER);
    }
    
    private javax.swing.JTextField txInicio, txFin, txSalto;
    private javax.swing.JTable jTable1;
}
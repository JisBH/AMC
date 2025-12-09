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

public class CompararUniVsBiTSP extends javax.swing.JFrame {
    public CompararUniVsBiTSP() {
        initComponents();
        setTitle("Uni vs Bi Victorias");
        setSize(600, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void ejecutar() {
        int ini = Integer.parseInt(txInicio.getText());
        int fin = Integer.parseInt(txFin.getText());
        int sal = Integer.parseInt(txSalto.getText());
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        ArrayList<Double> x=new ArrayList<>(), yU=new ArrayList<>(), yB=new ArrayList<>();

        for(int n=ini; n<=fin; n+=sal) {
            int ganaU=0, ganaB=0;
            for(int r=0; r<20; r++) {
                ArrayList<Punto> d = genDataset(n);
                // Usamos versiones con poda que son más rápidas
                double d1 = Algoritmos.tspVorazUnidireccionalPoda(new ArrayList<>(d)).getDistanciaTotal();
                double d2 = Algoritmos.tspVorazBidireccionalPoda(new ArrayList<>(d)).getDistanciaTotal();
                if(d1 < d2) ganaU++; else if(d2 < d1) ganaB++;
            }
            model.addRow(new Object[]{n, ganaU, ganaB});
            x.add((double)n); yU.add((double)ganaU); yB.add((double)ganaB);
        }
        
        Plot2DPanel plot = new Plot2DPanel();
        double[] X = x.stream().mapToDouble(d->d).toArray();
        plot.addBarPlot("Uni", Color.GREEN, X, yU.stream().mapToDouble(d->d).toArray());
        plot.addBarPlot("Bi", Color.ORANGE, X, yB.stream().mapToDouble(d->d).toArray());
        plot.addLegend("SOUTH");
        JFrame f = new JFrame("Gráfica"); f.setContentPane(plot); f.setSize(500,500); f.setVisible(true);
    }

    private ArrayList<Punto> genDataset(int n) {
        ArrayList<Punto> p = new ArrayList<>();
        Random r = new Random();
        for(int i=0; i<n; i++) p.add(new Punto(r.nextDouble()*1000, r.nextDouble()*1000, i));
        return p;
    }

    private void initComponents() {
        javax.swing.JPanel p = new javax.swing.JPanel();
        txInicio = new javax.swing.JTextField("100", 5);
        txFin = new javax.swing.JTextField("1000", 5);
        txSalto = new javax.swing.JTextField("100", 5);
        javax.swing.JButton btn = new javax.swing.JButton("Comparar");
        btn.addActionListener(e -> ejecutar());
        
        p.add(new javax.swing.JLabel("I:")); p.add(txInicio);
        p.add(new javax.swing.JLabel("F:")); p.add(txFin);
        p.add(new javax.swing.JLabel("S:")); p.add(txSalto);
        p.add(btn);
        
        jTable1 = new javax.swing.JTable(new DefaultTableModel(new Object[]{"Talla","Gana Uni","Gana Bi"}, 0));
        add(p, java.awt.BorderLayout.NORTH);
        add(new javax.swing.JScrollPane(jTable1), java.awt.BorderLayout.CENTER);
    }
    private javax.swing.JTextField txInicio, txFin, txSalto;
    private javax.swing.JTable jTable1;
}
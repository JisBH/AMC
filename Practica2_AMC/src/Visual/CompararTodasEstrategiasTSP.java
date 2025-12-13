package Visual;

import Comun.Punto;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import practica2.Algoritmos;
import org.math.plot.Plot2DPanel;
import java.awt.Color;

public class CompararTodasEstrategiasTSP extends javax.swing.JFrame {

    public CompararTodasEstrategiasTSP() {
        initComponents();
        setTitle("Comparar Todas TSP");
        setSize(800, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void ejecutar() {
        int ini = Integer.parseInt(txInicio.getText());
        int fin = Integer.parseInt(txFin.getText());
        int sal = Integer.parseInt(txSalto.getText());
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y1 = new ArrayList<>(), y2 = new ArrayList<>(), y3 = new ArrayList<>(), y4 = new ArrayList<>();

        for(int n=ini; n<=fin; n+=sal) {
            double t1=0, t2=0, t3=0, t4=0;
            for(int r=0; r<10; r++) {
                ArrayList<Punto> d = genDataset(n);
                t1 += Algoritmos.tspVorazUnidireccional(new ArrayList<>(d)).getTiempoEjecucion();
                t2 += Algoritmos.tspVorazBidireccional(new ArrayList<>(d)).getTiempoEjecucion();
                t3 += Algoritmos.tspVorazUnidireccionalPoda(new ArrayList<>(d)).getTiempoEjecucion();
                t4 += Algoritmos.tspVorazBidireccionalPoda(new ArrayList<>(d)).getTiempoEjecucion();
            }
            model.addRow(new Object[]{n, t1/10, t2/10, t3/10, t4/10});
            x.add((double)n); y1.add(t1/10); y2.add(t2/10); y3.add(t3/10); y4.add(t4/10);
        }
        mostrarGrafica(x, y1, y2, y3, y4);
    }
    
    private void mostrarGrafica(ArrayList<Double> x, ArrayList<Double> y1, ArrayList<Double> y2, ArrayList<Double> y3, ArrayList<Double> y4) {
        Plot2DPanel plot = new Plot2DPanel();
        double[] X = x.stream().mapToDouble(d->d).toArray();
        plot.addLinePlot("Uni", Color.RED, X, y1.stream().mapToDouble(d->d).toArray());
        plot.addLinePlot("Bi", Color.BLUE, X, y2.stream().mapToDouble(d->d).toArray());
        plot.addLinePlot("Uni Poda", Color.GREEN, X, y3.stream().mapToDouble(d->d).toArray());
        plot.addLinePlot("Bi Poda", Color.MAGENTA, X, y4.stream().mapToDouble(d->d).toArray());
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
        javax.swing.JPanel pTop = new javax.swing.JPanel();
        txInicio = new javax.swing.JTextField("100", 5);
        txFin = new javax.swing.JTextField("1000", 5);
        txSalto = new javax.swing.JTextField("100", 5);
        javax.swing.JButton btn = new javax.swing.JButton("Ejecutar");
        btn.addActionListener(e -> ejecutar());
        
        pTop.add(new javax.swing.JLabel("Inicio:")); pTop.add(txInicio);
        pTop.add(new javax.swing.JLabel("Fin:")); pTop.add(txFin);
        pTop.add(new javax.swing.JLabel("Salto:")); pTop.add(txSalto);
        pTop.add(btn);
        
        jTable1 = new javax.swing.JTable(new DefaultTableModel(new Object[]{"Talla","Uni","Bi","Uni Poda","Bi Poda"}, 0));
        
        add(pTop, java.awt.BorderLayout.NORTH);
        add(new javax.swing.JScrollPane(jTable1), java.awt.BorderLayout.CENTER);
    }
    private javax.swing.JTextField txInicio, txFin, txSalto;
    private javax.swing.JTable jTable1;
}
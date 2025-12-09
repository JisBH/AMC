package Visual;

import Comun.Punto;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import practica2.Algoritmos;
import practica2.SolucionTSP;

public class CompararDosEstrategiasTSP extends javax.swing.JFrame {
    public CompararDosEstrategiasTSP() {
        initComponents();
        setTitle("Comparar 2 TSP");
        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void ejecutar() {
        int ini = Integer.parseInt(txInicio.getText());
        int fin = Integer.parseInt(txFin.getText());
        int sal = Integer.parseInt(txSalto.getText());
        String e1 = (String) cb1.getSelectedItem();
        String e2 = (String) cb2.getSelectedItem();
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"Talla", e1, e2, "Calc "+e1, "Calc "+e2});

        for(int n=ini; n<=fin; n+=sal) {
            double t1=0, t2=0; long c1=0, c2=0;
            for(int r=0; r<5; r++) {
                ArrayList<Punto> d = genDataset(n);
                SolucionTSP s1 = run(e1, new ArrayList<>(d));
                SolucionTSP s2 = run(e2, new ArrayList<>(d));
                t1+=s1.getTiempoEjecucion(); t2+=s2.getTiempoEjecucion();
                c1+=s1.getCalculadas(); c2+=s2.getCalculadas();
            }
            model.addRow(new Object[]{n, t1/5, t2/5, c1/5, c2/5});
        }
    }
    
    private SolucionTSP run(String alg, ArrayList<Punto> p) {
        if(alg.contains("Unidireccional Poda")) return Algoritmos.tspVorazUnidireccionalPoda(p);
        if(alg.contains("Bidireccional Poda")) return Algoritmos.tspVorazBidireccionalPoda(p);
        if(alg.contains("Unidireccional")) return Algoritmos.tspVorazUnidireccional(p);
        return Algoritmos.tspVorazBidireccional(p);
    }
    
    private ArrayList<Punto> genDataset(int n) {
        ArrayList<Punto> p = new ArrayList<>();
        Random r = new Random();
        for(int i=0; i<n; i++) p.add(new Punto(r.nextDouble()*1000, r.nextDouble()*1000, i));
        return p;
    }

    private void initComponents() {
        javax.swing.JPanel p = new javax.swing.JPanel();
        txInicio = new javax.swing.JTextField("100", 4);
        txFin = new javax.swing.JTextField("1000", 4);
        txSalto = new javax.swing.JTextField("100", 4);
        cb1 = new javax.swing.JComboBox<>(new String[]{"Unidireccional", "Bidireccional", "Unidireccional Poda", "Bidireccional Poda"});
        cb2 = new javax.swing.JComboBox<>(new String[]{"Unidireccional", "Bidireccional", "Unidireccional Poda", "Bidireccional Poda"});
        javax.swing.JButton btn = new javax.swing.JButton("Run");
        btn.addActionListener(e -> ejecutar());
        
        p.add(new javax.swing.JLabel("I:")); p.add(txInicio);
        p.add(new javax.swing.JLabel("F:")); p.add(txFin);
        p.add(new javax.swing.JLabel("S:")); p.add(txSalto);
        p.add(cb1); p.add(cb2); p.add(btn);
        
        jTable1 = new javax.swing.JTable(new DefaultTableModel());
        add(p, java.awt.BorderLayout.NORTH);
        add(new javax.swing.JScrollPane(jTable1), java.awt.BorderLayout.CENTER);
    }
    private javax.swing.JTextField txInicio, txFin, txSalto;
    private javax.swing.JComboBox<String> cb1, cb2;
    private javax.swing.JTable jTable1;
}
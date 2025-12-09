package Visual;

import Comun.Punto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import practica2.Solucion;
import practica2.SolucionTSP;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.math.plot.Plot2DPanel;

public class Graficar {

    private ArrayList<Punto> puntos;
    private Solucion solP1;
    private SolucionTSP solTSP;

    private JFrame frame;
    private JButton btsalir;
    private Plot2DPanel plot = new Plot2DPanel();

    // Constructor para Práctica 1 (Closest Pair)
    public Graficar(ArrayList<Punto> puntos, Solucion sol) {
        this.puntos = puntos;
        this.solP1 = sol;
        this.solTSP = null;
        init();
    }
    
    // Constructor para Práctica 2 (TSP)
    public Graficar(ArrayList<Punto> puntos, SolucionTSP sol) {
        this.puntos = puntos;
        this.solTSP = sol;
        this.solP1 = null;
        init();
    }

    private void init() {
        graficar();
        frame = new JFrame("GRAFICAS");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLocation(250, 20);
        frame.add(construirpanelPrincipal());
        frame.setVisible(true);
    }

    private void graficar() {
        double[] x = new double[puntos.size()];
        double[] y = new double[puntos.size()];

        for (int i = 0; i < puntos.size(); i++) {
            x[i] = puntos.get(i).getX();
            y[i] = puntos.get(i).getY();
        }

        plot.addScatterPlot("Datos", Color.GREEN, x, y);

        // Caso Práctica 1: Par de puntos más cercanos
        if (solP1 != null) {
            double[] p1 = {solP1.getP1().getX(), solP1.getP1().getY()};
            double[] p2 = {solP1.getP2().getX(), solP1.getP2().getY()};
            plot.addLinePlot("Solucion P1", Color.RED, p1, p2);
        }
        
        // Caso Práctica 2: Ruta TSP
        if (solTSP != null) {
            ArrayList<Punto> ruta = solTSP.getRuta();
            if (ruta != null && ruta.size() > 1) {
                double[] rx = new double[ruta.size()];
                double[] ry = new double[ruta.size()];
                
                for(int i=0; i<ruta.size(); i++) {
                    rx[i] = ruta.get(i).getX();
                    ry[i] = ruta.get(i).getY();
                }
                // Dibuja la ruta en azul
                plot.addLinePlot("Ruta TSP", Color.BLUE, rx, ry);
            }
        }
    }

    private JPanel construirpanelPrincipal() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(plot, BorderLayout.CENTER);
        panel.add(construirPanelSur(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel construirPanelSur() {
        JPanel panel = new JPanel();
        btsalir = new JButton("Cerrar Gráfica");
        btsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        panel.add(btsalir);
        return panel;
    }
}
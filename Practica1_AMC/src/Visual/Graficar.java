/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Comun.Punto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import practicaA.SolucionA;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.math.plot.Plot2DPanel;
import practicaB.SolucionB;

/**
 *
 * @author USER
 */
public class Graficar {

    private ArrayList<Punto> puntos;
    private SolucionA solA;
    private SolucionB solB;

    private JFrame frame;
    private JButton btsalir;
    private Plot2DPanel plot = new Plot2DPanel();

    public Graficar(ArrayList<Punto> puntos, SolucionA solA, SolucionB solB) {
        this.puntos = puntos;
        this.solA = solA;
        this.solB = solB;

        graficar();

        frame = new JFrame("GRAFICAS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        if (solA != null) {
            double[] p1 = {solA.getP1().getX(), solA.getP1().getY()};
            double[] p2 = {solA.getP2().getX(), solA.getP2().getY()};
            plot.addLinePlot("Solucion", Color.BLACK, p1, p2);
        }
        
        if(solB != null){
            int [] ruta = solB.getRuta();
            int i;
            
            for (i = 0; i < ruta.length-1; i++) {
                double[] p1 = {puntos.get(ruta[i]).getX(), puntos.get(ruta[i]).getY()};
                double[] p2 = {puntos.get(ruta[i+1]).getX(), puntos.get(ruta[i+1]).getY()};
                plot.addLinePlot("Solucion", Color.RED, p1, p2);
                if(i==0)
                    plot.addLinePlot("Solucion", Color.BLACK, p1, p2);
                    
            }
            
            double[] p1 = {puntos.get(ruta[0]).getX(), puntos.get(ruta[0]).getY()};
            plot.addScatterPlot("Solucion", Color.BLACK, p1, p1);
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
        
        btsalir = new JButton("Volver al Menu");
        btsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        
        panel.add(btsalir);
        return panel;
    }
}

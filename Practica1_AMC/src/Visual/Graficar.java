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
import practica1.Solucion;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author USER
 */
public class Graficar {

    private ArrayList<Punto> puntos;
    private Solucion sol;

    private JFrame frame;
    private JButton btsalir;
    private Plot2DPanel plot = new Plot2DPanel();

    public Graficar(ArrayList<Punto> puntos, Solucion sol) {
        this.puntos = puntos;
        this.sol = sol;

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

        if (sol != null) {
            double[] p1 = {sol.getP1().getX(), sol.getP1().getY()};
            double[] p2 = {sol.getP2().getX(), sol.getP2().getY()};
            plot.addLinePlot("Solucion", Color.BLACK, p1, p2);
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

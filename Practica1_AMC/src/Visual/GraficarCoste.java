/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import Comun.Punto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.pow;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.math.plot.Plot2DPanel;
import practica1.Solucion;

/**
 *
 * @author USER
 */
public class GraficarCoste {

    private JFrame frame;
    private JButton btsalir;
    private Plot2DPanel plot = new Plot2DPanel();
    
    private String titulo;

    public GraficarCoste(String algoritmo) {
        
        this.titulo = algoritmo;
        
        graficar(algoritmo);

        frame = new JFrame("GRAFICA DE COSTE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        frame.add(construirpanelPrincipal());
        frame.setVisible(true);

    }

    private void graficar(String algoritmo) {
        double[] casomejor = new double[10];
        double[] casomedio = new double[10];
        double[] casopeor = new double[10];
        double[] y = new double[10];
        double n;
        
        switch(algoritmo){
            case "Exhaustivo":
                for (int i = 0; i < 10; i++) {
                    n = (i+1) * 100;
                    y[i] = n;
                    casomejor[i] = 6*pow(n, 2) - 9*n + 12;
                    casomedio[i] = ((17*pow(n,2) - 17*n)/2) + 5*n + 12;
                    casopeor[i] = 11*pow(n,2) - 6*n + 12;
                }
                break;
            
            case "ExhaustivoPoda":
                for (int i = 0; i < 10; i++) {
                    n = (i+1) * 100;
                    y[i] = n;
                    casomejor[i] = 57*n + 9;
                    casomedio[i] = 15*pow(n,2) + 21*n + 9;
                    casopeor[i] = 18*pow(n,2) + 3*n + 9;
                    
                }
                break;

            case "Divide y Venceras":
                for (int i = 0; i < 10; i++) {
                    n = (i+1) * 100;
                    y[i] = n;
                    casomejor[i] = 14 + 
                            (((17*pow(3,2) - 17*3)/2) + 5*3 + 12); //Exhaustivo con 3 puntos (CasoMedio)
                    
                    casomedio[i] = 2*casomedio[i/2] + (29*n/2) + 77 +
                            (((17*pow(n/2,2) - 17*n/2)/2) + 5*n/2 + 12); //Exhaustivo con n/2 puntos (CasoMedio)
                    
                    casopeor[i] = 2*casopeor[i/2] + 29*n + 77 +
                            (((17*pow(n,2) - 17*n)/2) + 5*n + 12); //Exhaustivo con n punto (CasoMedio)
                    
                }
                break;
        
            case "Divide y VencerasPoda":
                for (int i = 0; i < 10; i++) {
                    n = (i+1) * 100;
                    y[i] = n;
                    casomejor[i] = 16 + 
                            (((17*pow(3,2) - 17*3)/2) + 5*3 + 12); //Exhaustivo con 3 puntos (CasoMedio)
                    
                    casomedio[i] = 2*casomedio[i/2] + 29*n/2 + 7*n/2 + 92 +
                            (130*n/2 + 9);   //Exhaustivo11 (CasoMedio)
                    
                    casopeor[i] = 2*casopeor[i/2] + 29*n + 7*n + 92 +
                            (130*n + 9);   //Exhaustivo11 (CasoMedio)
                }
                break;
        }
        
        plot.addScatterPlot("Algortimos", Color.GREEN, y, casomejor);
        plot.addLinePlot("Linea", Color.GREEN, y, casomejor);
        plot.addScatterPlot("Algortimos", Color.YELLOW, y, casomedio);
        plot.addLinePlot("Linea", Color.YELLOW, y, casomedio);
        plot.addScatterPlot("Algortimos", Color.RED, y, casopeor);
        plot.addLinePlot("Linea", Color.RED, y, casopeor);

    }

    private JPanel construirpanelPrincipal() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(plot, BorderLayout.CENTER);
        panel.add(construirPanelSur(), BorderLayout.SOUTH);
        panel.add(construirPanelNorte(), BorderLayout.NORTH);
        return panel;
    }

    private JPanel construirPanelSur() {
        JPanel panel = new JPanel();
        
        btsalir = new JButton("Volver al Menu");
        btsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
        panel.add(btsalir);
        return panel;
    }
    
    public JPanel construirPanelNorte(){
        JPanel panel = new JPanel();
        
        JLabel jl = new JLabel();
        
        Font f = new Font("Arial", Font.BOLD, 20);
        jl.setFont(f);
        
        jl.setText(titulo);
                
        panel.add(jl);
        return panel;
    }
}

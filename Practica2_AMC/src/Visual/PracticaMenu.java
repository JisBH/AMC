package Visual;

import Comun.Punto;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class PracticaMenu extends JPanel {

    public static boolean peorcaso = false;
    public static ArrayList<Punto> dataSet = null;

    private MainVisual mainV;

    private JLabel lbdsmemoria;
    private JCheckBox ckpeorcaso;

    public PracticaMenu(MainVisual m) {
        this.mainV = m;
        initComponents(); 
        actualizarEstadoDataset(); 
    }

    public void setLbDataSet(String s) {
        lbdsmemoria.setText(s);
        actualizarEstadoDataset();
    }
    
    public String getLbDataSet() {
        return lbdsmemoria.getText();
    }
    
    public MainVisual getMainVisual() {
        return mainV;
    }

    private void actualizarEstadoDataset() {
        if (dataSet == null || dataSet.isEmpty()) {
            lbdsmemoria.setText("Ningún dataset cargado");
            lbdsmemoria.setForeground(Color.RED);
        } else {
            lbdsmemoria.setForeground(new Color(0, 100, 0)); // Verde
        }
    }

    private boolean validarDataset() {
        if (dataSet == null || dataSet.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Debes cargar o crear un dataset primero.", 
                "Dataset Vacío", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void initComponents() {
        setLayout(null); // Diseño manual

        JLabel lblDatos = new JLabel("Gestión de Datos");
        lblDatos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDatos.setBounds(20, 20, 150, 20);
        add(lblDatos);

        JButton btnCargar = new JButton("Cargar / Crear Dataset");
        btnCargar.setBounds(20, 50, 200, 30);
        btnCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new CargarDataSet(PracticaMenu.this).setVisible(true);
            }
        });
        add(btnCargar);

        ckpeorcaso = new JCheckBox("Peor Caso (P1)");
        ckpeorcaso.setBounds(240, 50, 120, 30);
        ckpeorcaso.addActionListener(e -> peorcaso = ckpeorcaso.isSelected());
        add(ckpeorcaso);

        JButton btnGrafico = new JButton("Ver Gráfico");
        btnGrafico.setBounds(380, 50, 100, 30);
        btnGrafico.addActionListener(e -> {
            if(validarDataset()) new Graficar(dataSet, (practica2.Solucion)null);
        });
        add(btnGrafico);

        JButton btnTabla = new JButton("Ver Tabla");
        btnTabla.setBounds(500, 50, 100, 30);
        btnTabla.addActionListener(e -> {
            if(validarDataset()) new MostrarDataSetTabla().setVisible(true);
        });
        add(btnTabla);

        lbdsmemoria = new JLabel("Ningún dataset cargado");
        lbdsmemoria.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lbdsmemoria.setBounds(20, 90, 400, 20);
        add(lbdsmemoria);

        JSeparator sep1 = new JSeparator();
        sep1.setBounds(10, 120, 760, 10);
        add(sep1);

        JLabel lblP1 = new JLabel("Práctica 1: Puntos más cercanos");
        lblP1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblP1.setBounds(20, 130, 300, 20);
        add(lblP1);

        JButton btnP1Comp = new JButton("Comprobar Estrategias");
        btnP1Comp.setBounds(20, 160, 200, 30);
        btnP1Comp.addActionListener(e -> {
            if (validarDataset()) mainV.cambiarPanel(new ComprobarEstrategia(mainV, PracticaMenu.this));
        });
        add(btnP1Comp);

        JButton btnP1All = new JButton("Comparar Todas");
        btnP1All.setBounds(230, 160, 150, 30);
        btnP1All.addActionListener(e -> {
            mainV.cambiarPanel(new CompararTodasEstrategias(mainV, PracticaMenu.this));
        });
        add(btnP1All);

        JButton btnP1Two = new JButton("Comparar 2");
        btnP1Two.setBounds(390, 160, 120, 30);
        btnP1Two.addActionListener(e -> {
            mainV.cambiarPanel(new CompararDosEstrategias(mainV, PracticaMenu.this));
        });
        add(btnP1Two);

        JButton btnP1Graf = new JButton("Gráficas Coste");
        btnP1Graf.setBounds(520, 160, 150, 30);
        btnP1Graf.addActionListener(e -> new MostrarGraficaCoste());
        add(btnP1Graf);

        JSeparator sep2 = new JSeparator();
        sep2.setBounds(10, 210, 760, 10);
        add(sep2);

        // --- PRÁCTICA 2  ---
        JLabel lblP2 = new JLabel("Práctica 2: Viajante de Comercio (TSP)");
        lblP2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblP2.setBounds(20, 230, 300, 20);
        add(lblP2);

        JButton btnP2Comp = new JButton("Comprobar Estrategias (TSP)");
        btnP2Comp.setBounds(20, 260, 200, 30);
        btnP2Comp.addActionListener(e -> {
            if (validarDataset()) {
                mainV.cambiarPanel(new ComprobarEstrategiasTSP(mainV, PracticaMenu.this, dataSet));
            }
        });
        add(btnP2Comp);

        JButton btnP2All = new JButton("Comparar Todas (TSP)");
        btnP2All.setBounds(230, 260, 180, 30);
        btnP2All.addActionListener(e -> new CompararTodasEstrategiasTSP());
        add(btnP2All);

        JButton btnP2Two = new JButton("Comparar 2 (TSP)");
        btnP2Two.setBounds(420, 260, 150, 30);
        btnP2Two.addActionListener(e -> new CompararDosEstrategiasTSP());
        add(btnP2Two);

        JButton btnP2UniBi = new JButton("Uni vs Bi Direccional");
        btnP2UniBi.setBounds(580, 260, 160, 30);
        btnP2UniBi.addActionListener(e -> new CompararUniVsBiTSP());
        add(btnP2UniBi);
    }
}
package Visual;

import Comun.Lectura;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CargarDataSet extends javax.swing.JFrame {

    private PracticaMenu jp1;

    public CargarDataSet(PracticaMenu jp) {
        initComponents();
        this.jp1 = jp;
        this.setLocationRelativeTo(null);
        this.setTitle("CARGAR DATASET");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        btberlin52 = new javax.swing.JButton();
        btch130 = new javax.swing.JButton();
        btch150 = new javax.swing.JButton();
        btd493 = new javax.swing.JButton();
        btd657 = new javax.swing.JButton();
        btdsindice = new javax.swing.JButton();
        btCargarOtro = new javax.swing.JButton();
        btcerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setSize(450, 350);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); 
        jLabel1.setText("Cargar DataSet");
        jLabel1.setBounds(130, 20, 200, 30);
        add(jLabel1);

        btberlin52.setText("BERLIN52");
        btberlin52.setBounds(30, 70, 100, 40);
        btberlin52.addActionListener(evt -> cargarFichero("berlin52.tsp"));
        add(btberlin52);

        btch130.setText("CH130");
        btch130.setBounds(140, 70, 100, 40);
        btch130.addActionListener(evt -> cargarFichero("ch130.tsp"));
        add(btch130);

        btch150.setText("CH150");
        btch150.setBounds(250, 70, 100, 40);
        btch150.addActionListener(evt -> cargarFichero("ch150.tsp"));
        add(btch150);

        btd493.setText("D493");
        btd493.setBounds(30, 120, 100, 40);
        btd493.addActionListener(evt -> cargarFichero("d493.tsp"));
        add(btd493);

        btd657.setText("D657");
        btd657.setBounds(140, 120, 100, 40);
        btd657.addActionListener(evt -> cargarFichero("d657.tsp"));
        add(btd657);

        btdsindice.setText("Generar Aleatorio");
        btdsindice.setBounds(250, 120, 150, 40);
        btdsindice.addActionListener(evt -> generarAleatorio());
        add(btdsindice);
        
        btCargarOtro.setText("Cargar Otro Fichero...");
        btCargarOtro.setBounds(30, 180, 370, 40);
        btCargarOtro.addActionListener(evt -> abrirFileChooser());
        add(btCargarOtro);

        btcerrar.setText("Cancelar");
        btcerrar.setBounds(160, 250, 100, 30);
        btcerrar.addActionListener(evt -> this.dispose());
        add(btcerrar);
    }

    private void cargarFichero(String nombre) {
        PracticaMenu.dataSet = Lectura.leerFichero(nombre);
        if (PracticaMenu.dataSet != null && !PracticaMenu.dataSet.isEmpty()) {
            jp1.setLbDataSet(nombre);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al leer " + nombre);
        }
    }

    private void generarAleatorio() {
        String s = JOptionPane.showInputDialog(this, "Introduce la talla (n):");
        if (s != null && !s.isEmpty()) {
            try {
                int i = Integer.parseInt(s);
                if (i > 0) {
                    Lectura.generarDataSet(i, PracticaMenu.peorcaso);
                    String nombreFichero = "DataSet" + i + ".tsp";
                    cargarFichero(nombreFichero);
                } else {
                    JOptionPane.showMessageDialog(this, "El número debe ser mayor que 0");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Introduce un número válido");
            }
        }
    }
    
    private void abrirFileChooser() {
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos TSP", "tsp"));
        int seleccion = fileChooser.showOpenDialog(this);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            PracticaMenu.dataSet = Lectura.leerFichero(fichero.getAbsolutePath());
            jp1.setLbDataSet(fichero.getName());
            this.dispose();
        }
    }

    // Componentes
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton btberlin52;
    private javax.swing.JButton btch130;
    private javax.swing.JButton btch150;
    private javax.swing.JButton btd493;
    private javax.swing.JButton btd657;
    private javax.swing.JButton btdsindice;
    private javax.swing.JButton btCargarOtro;
    private javax.swing.JButton btcerrar;
}
package Visual;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import practica1.Algoritmos;
import Comun.Lectura;
import Comun.Punto;
import practica1.Solucion;

public class CompararDosEstrategias extends javax.swing.JPanel {

    private MainVisual mainV;
    private PracticaMenu p1;

    private int num;

    public CompararDosEstrategias(MainVisual m, PracticaMenu p) {
        initComponents();

        num = 0;
        mainV = m;
        p1 = p;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbexhapoda = new javax.swing.JCheckBox();
        cbexha = new javax.swing.JCheckBox();
        cbdyv = new javax.swing.JCheckBox();
        cbdyvm = new javax.swing.JCheckBox();
        btvolvermenu = new javax.swing.JButton();
        btejecutar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtpane = new javax.swing.JTextArea();

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Comparar dos Estrategias");

        cbexhapoda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbexhapoda.setText("Exhaustivo Poda");
        cbexhapoda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbExhaustivoPoda(evt);
            }
        });

        cbexha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbexha.setText("Exhaustivo");
        cbexha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbExhaustivo(evt);
            }
        });

        cbdyv.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbdyv.setText("Divide y Veceras");
        cbdyv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDyV(evt);
            }
        });

        cbdyvm.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbdyvm.setText("Divide y Veceras Mejorado");
        cbdyvm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDivideyVencerasMejorado(evt);
            }
        });

        btvolvermenu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btvolvermenu.setText("Volver al Menu");
        btvolvermenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVolverMenu(evt);
            }
        });

        btejecutar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btejecutar.setText("Ejecutar");
        btejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEjecutar(evt);
            }
        });

        txtpane.setColumns(20);
        txtpane.setRows(5);
        jScrollPane1.setViewportView(txtpane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbexha)
                                .addComponent(cbdyvm)
                                .addComponent(cbexhapoda)
                                .addComponent(cbdyv))
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addComponent(btejecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btvolvermenu)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(jLabel1)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbexha)
                        .addGap(18, 18, 18)
                        .addComponent(cbexhapoda)
                        .addGap(18, 18, 18)
                        .addComponent(cbdyv, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbdyvm)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(btvolvermenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btejecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btVolverMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVolverMenu
        mainV.cambiarPanel(p1);
        this.setVisible(false);
    }//GEN-LAST:event_btVolverMenu

    private void cbExhaustivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbExhaustivo
        if (cbexha.isSelected()) {
            if (num == 2) {
                JOptionPane.showMessageDialog(this,
                        "Solo puede estar seleccionado 2 Algoritmos",
                        "INFO", JOptionPane.WARNING_MESSAGE);
                cbexha.setSelected(false);
            } else {
                num++;
                System.out.println(num);
            }
        } else {
            num--;
            System.out.println(num);
        }
    }//GEN-LAST:event_cbExhaustivo

    private void cbExhaustivoPoda(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbExhaustivoPoda
        if (cbexhapoda.isSelected()) {
            if (num == 2) {
                JOptionPane.showMessageDialog(this,
                        "Solo puede estar seleccionado 2 Algoritmos",
                        "INFO", JOptionPane.WARNING_MESSAGE);
                cbexhapoda.setSelected(false);
            } else {
                num++;
                System.out.println(num);
            }
        } else {
            num--;
            System.out.println(num);
        }
    }//GEN-LAST:event_cbExhaustivoPoda

    private void cbDyV(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDyV
        if (cbdyv.isSelected()) {
            if (num == 2) {
                JOptionPane.showMessageDialog(this,
                        "Solo puede estar seleccionado 2 Algoritmos",
                        "INFO", JOptionPane.WARNING_MESSAGE);
                cbdyv.setSelected(false);
            } else {
                num++;
                System.out.println(num);
            }
        } else {
            num--;
            System.out.println(num);
        }
    }//GEN-LAST:event_cbDyV

    private void cbDivideyVencerasMejorado(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDivideyVencerasMejorado
        if (cbdyvm.isSelected()) {
            if (num == 2) {
                JOptionPane.showMessageDialog(this,
                        "Solo puede estar seleccionado 2 Algoritmos",
                        "INFO", JOptionPane.WARNING_MESSAGE);
                cbdyvm.setSelected(false);
            } else {
                num++;
                System.out.println(num);
            }
        } else {
            num--;
            System.out.println(num);
        }
    }//GEN-LAST:event_cbDivideyVencerasMejorado

    private void btEjecutar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEjecutar
        if (num != 2) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar 2 Algoritmos",
                    "INFO", JOptionPane.WARNING_MESSAGE);
        } else {
            String cadena = "";
            Solucion sol = new Solucion();
            long t1, t2;
            double aux;

            if (cbexha.isSelected()) {
                cadena += "\tExhaustivo\tExhaustivo";
            }

            if (cbexhapoda.isSelected()) {
                cadena += "\tExhaustivoP\tExhaustivoP";
            }

            if (cbdyv.isSelected()) {
                cadena += "\tDyV\tDyV";
            }

            if (cbdyvm.isSelected()) {
                cadena += "\tDyV M\tDyV M";
            }

            cadena += "\nTalla\tTiempo(mseg)\tDistancias\tTiempo(mseg)\tDistancias";

            for (int i = 1000; i <= 5000; i += 1000) {

                Lectura.generarDataSet(i, PracticaMenu.peorcaso);

                ArrayList<Punto> p = Lectura.leerFichero("DataSet" + i + ".tsp");

                cadena += "\n" + i;

                if (cbexha.isSelected()) {
                    t1 = System.nanoTime();
                    sol = Algoritmos.exhaustivoRecursivo((ArrayList<Punto>) p.clone());
                    t2 = System.nanoTime();
                    aux = (double) (t2 - t1) / 1000000;
                    cadena = cadena + "\t" + aux + "\t" + sol.getCalculadas();
                }

                if (cbexhapoda.isSelected()) {
                    t1 = System.nanoTime();
                    sol = Algoritmos.exhaustivoPodaRecursivo((ArrayList<Punto>) p.clone());
                    t2 = System.nanoTime();
                    aux = (double) (t2 - t1) / 1000000;
                    cadena = cadena + "\t" + aux + "\t" + sol.getCalculadas();
                }

                if (cbdyv.isSelected()) {
                    t1 = System.nanoTime();
                    sol = Algoritmos.DyVRecursivo((ArrayList<Punto>) p.clone());
                    t2 = System.nanoTime();
                    aux = (double) (t2 - t1) / 1000000;
                    cadena = cadena + "\t" + aux + "\t" + sol.getCalculadas();
                }

                if (cbdyvm.isSelected()) {
                    t1 = System.nanoTime();
                    sol = Algoritmos.DyVconPodaRecursivo((ArrayList<Punto>) p.clone());
                    t2 = System.nanoTime();
                    aux = (double) (t2 - t1) / 1000000;
                    cadena = cadena + "\t" + aux + "\t" + sol.getCalculadas();
                }

            }

            txtpane.setText(cadena);
        }
    }//GEN-LAST:event_btEjecutar


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btejecutar;
    private javax.swing.JButton btvolvermenu;
    private javax.swing.JCheckBox cbdyv;
    private javax.swing.JCheckBox cbdyvm;
    private javax.swing.JCheckBox cbexha;
    private javax.swing.JCheckBox cbexhapoda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtpane;
    // End of variables declaration//GEN-END:variables
}

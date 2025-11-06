
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
    private PracticaMenu pA;

    private int num;

    public CompararDosEstrategias(MainVisual m, PracticaMenu p) {
        initComponents();

        num = 0;
        mainV = m;
        pA = p;
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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btvolvermenu)
                .addGap(169, 169, 169))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btejecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbexha)
                                        .addGap(35, 35, 35)
                                        .addComponent(cbexhapoda)))
                                .addGap(44, 44, 44)
                                .addComponent(cbdyv)
                                .addGap(38, 38, 38)))
                        .addGap(8, 8, 8)
                        .addComponent(cbdyvm))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbexha)
                    .addComponent(cbexhapoda)
                    .addComponent(cbdyv)
                    .addComponent(cbdyvm))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btvolvermenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btejecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btVolverMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVolverMenu
        mainV.cambiarPanel(pA);
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

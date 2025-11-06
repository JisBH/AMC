package Visual;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Comun.Lectura;

public class CargarDataSet extends javax.swing.JFrame {

    private PracticaMenu jp1;
    boolean p1Seleccionada;

    public CargarDataSet(PracticaMenu jp) {
        initComponents();

        this.jp1 = jp;
        p1Seleccionada = true;

        this.setLocation(400, 100);
        this.setTitle("CARGAR DATASET");
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btberlin52 = new javax.swing.JButton();
        btch130 = new javax.swing.JButton();
        btch150 = new javax.swing.JButton();
        btd493 = new javax.swing.JButton();
        btd657 = new javax.swing.JButton();
        btdsindice = new javax.swing.JButton();
        btcerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cargar DataSet");

        btberlin52.setText("berlin52");
        btberlin52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBerlin52(evt);
            }
        });

        btch130.setText("ch130");
        btch130.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCh130(evt);
            }
        });

        btch150.setText("ch150");
        btch150.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCh150(evt);
            }
        });

        btd493.setText("d493");
        btd493.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btD493(evt);
            }
        });

        btd657.setText("d657");
        btd657.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btD657(evt);
            }
        });

        btdsindice.setText("DataSet con indice");
        btdsindice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDataSetIndice(evt);
            }
        });

        btcerrar.setText("Volver al Menu");
        btcerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVolverMenu(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btch130, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btberlin52, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btch150, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btd493, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btd657, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(btdsindice))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btcerrar)
                            .addComponent(jLabel1))))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btberlin52, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btd493, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btch130, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btd657, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btch150, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btdsindice, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(btcerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btBerlin52(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBerlin52

        PracticaMenu.dataSet = null;
        PracticaMenu.dataSet = Lectura.leerFichero("berlin52.tsp");
        jp1.setLbDataSet("berlin52");

        this.setVisible(false);

    }//GEN-LAST:event_btBerlin52

    private void btCh130(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCh130

        PracticaMenu.dataSet = null;
        PracticaMenu.dataSet = Lectura.leerFichero("ch130.tsp");
        jp1.setLbDataSet("ch130");

        this.setVisible(false);

    }//GEN-LAST:event_btCh130

    private void btCh150(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCh150

        PracticaMenu.dataSet = null;
        PracticaMenu.dataSet = Lectura.leerFichero("ch150.tsp");
        jp1.setLbDataSet("ch150");

        this.setVisible(false);

    }//GEN-LAST:event_btCh150

    private void btD493(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btD493

        PracticaMenu.dataSet = null;
        PracticaMenu.dataSet = Lectura.leerFichero("d493.tsp");
        jp1.setLbDataSet("d493");

        this.setVisible(false);

    }//GEN-LAST:event_btD493

    private void btD657(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btD657

        PracticaMenu.dataSet = null;
        PracticaMenu.dataSet = Lectura.leerFichero("d657.tsp");
        jp1.setLbDataSet("g657");

        this.setVisible(false);

    }//GEN-LAST:event_btD657

    private void btDataSetIndice(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDataSetIndice
        String s = JOptionPane.showInputDialog(this, "Dime la talla");

        if (!s.isEmpty()) {
            int i = Integer.valueOf(s);
            if (i > 0) {

                Lectura.generarDataSet(i, PracticaMenu.peorcaso);

                PracticaMenu.dataSet = null;
                PracticaMenu.dataSet = Lectura.leerFichero("DataSet" + i + ".tsp");
                jp1.setLbDataSet("DataSet" + i);

                this.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(this, "Debes poner un numero entero mayor que 0", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes poner un numero entero mayor que 0", "AVISO", JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_btDataSetIndice

    private void btVolverMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVolverMenu
        this.setVisible(false);
    }//GEN-LAST:event_btVolverMenu

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btberlin52;
    private javax.swing.JButton btcerrar;
    private javax.swing.JButton btch130;
    private javax.swing.JButton btch150;
    private javax.swing.JButton btd493;
    private javax.swing.JButton btd657;
    private javax.swing.JButton btdsindice;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

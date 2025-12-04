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

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Cargar DataSet");

        btberlin52.setText("BERLIN52");
        btberlin52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBerlin52(evt);
            }
        });

        btch130.setText("CH130");
        btch130.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCh130(evt);
            }
        });

        btch150.setText("CH150");
        btch150.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCh150(evt);
            }
        });

        btd493.setText("D493");
        btd493.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btD493(evt);
            }
        });

        btd657.setText("D657");
        btd657.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btD657(evt);
            }
        });

        btdsindice.setText("DataSet con indice (aleatorio)");
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
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btd657, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btberlin52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btch150, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btd493, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btch130, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btdsindice)))))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btcerrar)
                .addGap(178, 178, 178))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btberlin52, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btch150, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btd493, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btd657, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btch130, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btdsindice, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(btcerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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

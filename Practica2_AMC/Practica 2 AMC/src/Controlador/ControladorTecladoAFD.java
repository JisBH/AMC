/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.AFD;
import Vista.VistaTecladoAFD;
import Vista.VistaMensajes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class ControladorTecladoAFD implements ActionListener {

    private AFD automataD;
    private boolean noseguir = false;

    private VistaTecladoAFD vTecladoAFD;
    private VistaMensajes vMensaje;
    private DefaultTableModel dtm;

    public void addListeners() {
        vTecladoAFD.buttonAT.addActionListener(this);
        vTecladoAFD.buttonBT.addActionListener(this);
        vTecladoAFD.buttonCargar.addActionListener(this);
        vTecladoAFD.buttonSalir.addActionListener(this);
    }

    public ControladorTecladoAFD(AFD automataD) {
        vMensaje = new VistaMensajes();
        vTecladoAFD = new VistaTecladoAFD();
        this.automataD = automataD;

        addListeners();

        vTecladoAFD.setResizable(false);
        vTecladoAFD.setVisible(true);
        dtm = (DefaultTableModel) vTecladoAFD.tablaTransiciones.getModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Añadir":
                String[] datos = new String[3];
                datos[0] = vTecladoAFD.txtEstados.getText();
                datos[1] = vTecladoAFD.txtEstados.getText();
                datos[2] = vTecladoAFD.txtEstados.getText();
                dtm.addRow(datos);
                vTecladoAFD.txtEstados.setText("");
                break;

            case "Borrar":
                if (vTecladoAFD.tablaTransiciones.getSelectedRow() != -1) {
                    dtm.removeRow(vTecladoAFD.tablaTransiciones.getSelectedRow());
                } else {
                    vMensaje.mensajeJOptionPaneWARNING("Selecciona una fila para borrar", "Advertencia", vTecladoAFD);
                }
                break;

            case "Cargar":
                if (vTecladoAFD.txtEstIni.getText().isEmpty()) {
                    vMensaje.mensajeJOptionPaneWARNING("Introduce el estado inicial", "Advertencia", vTecladoAFD);
                    break;
                }

                // Configurar estados
                automataD.setEstadoInicial(vTecladoAFD.txtEstIni.getText());
                String[] finales = vTecladoAFD.txtEstFin.getText().split(" ");
                for (String f : finales) {
                    automataD.agregarEstadoFinal(f);
                }
                
                // Necesitamos recoger todos los estados posibles primero (opcional si el modelo lo maneja, 
                // pero tu vista valida contra "estados" que no veo dónde se llenan en el código original.
                // Asumiremos que el modelo agrega estados dinámicamente).
                Set<String> estadosValidos = automataD.getEstados(); 

                boolean fin = false;
                int filas = dtm.getRowCount();
                
                // Iteramos sin borrar las filas mientras leemos, es más seguro
                for (int i = 0; i < filas; i++) {
                    String c0 = (String) dtm.getValueAt(i, 0); // Origen
                    String c1 = (String) dtm.getValueAt(i, 1); // Simbolo
                    String c2 = (String) dtm.getValueAt(i, 2); // Destino

                    if (c0 == null || c0.isEmpty() || c2 == null || c2.isEmpty()) {
                         vMensaje.mensajeJOptionPaneERROR("Estados vacíos en la tabla", "Error", vTecladoAFD);
                         fin = true;
                         break;
                    }

                    // Simplemente pasamos los strings. Adiós Integer.parseInt
                    automataD.agregarTransicion(c0, c1.charAt(0), c2);
                }

                if (fin) {
                    break;
                }

                vTecladoAFD.dispose();
                break;

            case "Salir":
                vTecladoAFD.dispose();
                noseguir = true;
                break;
        }
    }

    public boolean noSeguir() {
        return noseguir;
    }
}
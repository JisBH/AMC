/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.AFND;
import Vista.VistaMensajes;
import Vista.VistaTecladoAFND;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class ControladorTecladoAFND implements ActionListener {

    private AFND automataND;
    private boolean noseguir = false;

    private VistaTecladoAFND vTecladoAFND;
    private VistaMensajes vMensaje;
    private DefaultTableModel dtmT, dtmTLambda;

    private void addListeners() {
        vTecladoAFND.buttonAT.addActionListener(this);
        vTecladoAFND.buttonBT.addActionListener(this);
        vTecladoAFND.buttonCargar.addActionListener(this);
        vTecladoAFND.buttonSalir.addActionListener(this);
        vTecladoAFND.buttonATLambda.addActionListener(this);
        vTecladoAFND.buttonBTLambda.addActionListener(this);
    }

    public ControladorTecladoAFND(AFND automataND) {
        this.automataND = automataND;
        vMensaje = new VistaMensajes();
        vTecladoAFND = new VistaTecladoAFND();

        addListeners();

        vTecladoAFND.setResizable(false);
        vTecladoAFND.setVisible(true);
        dtmT = (DefaultTableModel) vTecladoAFND.tablaTransiciones.getModel();
        dtmTLambda = (DefaultTableModel) vTecladoAFND.tablaTransicionesLAMBDA.getModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Añadir":
                String[] datos = new String[3];
                datos[0] = vTecladoAFND.txtEstados.getText(); // Reutilizando campo texto por simplicidad
                datos[1] = "";
                datos[2] = "";
                dtmT.addRow(datos);
                break;
            case "Borrar":
                if (vTecladoAFND.tablaTransiciones.getSelectedRow() != -1) {
                    dtmT.removeRow(vTecladoAFND.tablaTransiciones.getSelectedRow());
                }
                break;
            case "AñadirL":
                String[] datosL = new String[2];
                dtmTLambda.addRow(datosL);
                break;
            case "BorrarL":
                 if (vTecladoAFND.tablaTransicionesLAMBDA.getSelectedRow() != -1) {
                    dtmTLambda.removeRow(vTecladoAFND.tablaTransicionesLAMBDA.getSelectedRow());
                }
                break;

            case "Cargar":
                 if (vTecladoAFND.txtEstIni.getText().isEmpty()) {
                    vMensaje.mensajeJOptionPaneWARNING("Introduce el estado inicial", "Advertencia", vTecladoAFND);
                    break;
                }

                automataND.setEstadoInicial(vTecladoAFND.txtEstIni.getText());
                
                String[] finales = vTecladoAFND.txtEstFin.getText().split(" ");
                for(String f : finales) automataND.agregarEstadoFinal(f);

                // Transiciones Normales
                int filas = dtmT.getRowCount();
                for (int i = 0; i < filas; i++) {
                    String origen = (String) dtmT.getValueAt(i, 0);
                    String simbStr = (String) dtmT.getValueAt(i, 1);
                    String destinosStr = (String) dtmT.getValueAt(i, 2);

                    if (origen != null && simbStr != null && destinosStr != null && !simbStr.isEmpty()) {
                        Set<String> destinos = new HashSet<>();
                        String[] partes = destinosStr.split(" ");
                        for(String p : partes) destinos.add(p);
                        
                        automataND.agregarTransicion(origen, simbStr.charAt(0), destinos);
                    }
                }

                // Transiciones Lambda
                filas = dtmTLambda.getRowCount();
                for (int i = 0; i < filas; i++) {
                    String origen = (String) dtmTLambda.getValueAt(i, 0);
                    String destinosStr = (String) dtmTLambda.getValueAt(i, 1);

                    if (origen != null && destinosStr != null) {
                        Set<String> destinos = new HashSet<>();
                        String[] partes = destinosStr.split(" ");
                        for(String p : partes) destinos.add(p);
                        
                        automataND.agregarTransicionLambda(origen, destinos);
                    }
                }

                vTecladoAFND.dispose();
                break;

            case "Salir":
                vTecladoAFND.dispose();
                noseguir = true;
                break;
        }
    }

    public boolean noSeguir() {
        return noseguir;
    }
}
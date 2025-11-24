/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.AFD;
import Modelo.AFND;
import Vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author USER
 */
public class ControladorPrincipal implements ActionListener {

    private final VistaPrincipal vPrincipal;
    private final VistaMensajes vMensaje;

    private AFD automataD;
    private AFND automataND;

    private void addListeners() {
        vPrincipal.buttonFichero.addActionListener(this);
        vPrincipal.buttonTeclado.addActionListener(this);
        vPrincipal.buttonSalir.addActionListener(this);
    }

    public ControladorPrincipal() {
        vMensaje = new VistaMensajes();
        vPrincipal = new VistaPrincipal();

        addListeners();
        vPrincipal.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Fichero": {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Texto", "txt");
                fc.setFileFilter(filtro);
                int result = fc.showOpenDialog(vPrincipal);

                if (result != JFileChooser.CANCEL_OPTION) {
                    File nombreFichero = fc.getSelectedFile();
                    leerFichero(nombreFichero);
                }
                break;
            }

            case "Teclado": {
                String tipo = (String) vPrincipal.jComboBox.getSelectedItem();
                if (tipo.equals("AFD")) {
                    automataD = new AFD();
                    ControladorTecladoAFD c = new ControladorTecladoAFD(automataD);
                    if (!c.noSeguir()) {
                        ControladorAutomata ca = new ControladorAutomata(automataD);
                    }
                } else {
                    automataND = new AFND();
                    ControladorTecladoAFND c = new ControladorTecladoAFND(automataND);
                    if (!c.noSeguir()) {
                        ControladorAutomata ca = new ControladorAutomata(automataND);
                    }
                }
                break;
            }

            case "Salir": {
                System.exit(0);
                break;
            }
        }
    }

    public void leerFichero(File fichero) {
        try {
            Scanner scan = new Scanner(fichero);
            String linea = scan.nextLine();
            String[] partes = linea.split(" ");
            String tipo = partes[1];

            if (tipo.equals("AFD")) {
                automataD = new AFD();
                while (scan.hasNextLine()) {
                    linea = scan.nextLine();
                    partes = linea.split(" ");
                    switch (partes[0]) {
                        case "ESTADOS:":
                            for (int i = 1; i < partes.length; i++) {
                                automataD.agregarEstado(partes[i]);
                            }
                            break;
                        case "INICIAL:":
                            automataD.setEstadoInicial(partes[1]);
                            break;
                        case "FINALES:":
                            for (int i = 1; i < partes.length; i++) {
                                automataD.agregarEstadoFinal(partes[i]);
                            }
                            break;
                        case "TRANSICIONES:":
                            linea = scan.nextLine();
                            while (!linea.equals("FIN")) {
                                partes = linea.split(" ");
                                // Ya no hacemos split("q") ni parseInt. Pasamos el String directo.
                                // Formato: Origen 'Simbolo' Destino
                                String origen = partes[0];
                                char simbolo = partes[1].charAt(1); // Asume formato 'a'
                                String destino = partes[2];
                                
                                automataD.agregarTransicion(origen, simbolo, destino);
                                linea = scan.nextLine();
                            }
                            break;
                    }
                }
                ControladorAutomata c = new ControladorAutomata(automataD);

            } else { // AFND
                automataND = new AFND();
                while (scan.hasNextLine()) {
                    linea = scan.nextLine();
                    partes = linea.split(" ");
                    switch (partes[0]) {
                        case "ESTADOS:":
                            for (int i = 1; i < partes.length; i++) {
                                // No es necesario guardar estados explícitamente en AFND si se añaden con transiciones,
                                // pero lo mantenemos por consistencia si el método existe.
                            }
                            break;
                        case "INICIAL:":
                            automataND.setEstadoInicial(partes[1]);
                            break;
                        case "FINALES:":
                            for (int i = 1; i < partes.length; i++) {
                                automataND.agregarEstadoFinal(partes[i]);
                            }
                            break;
                        case "TRANSICIONES:":
                            linea = scan.nextLine();
                            while (!linea.equals("TRANSICIONES LAMBDA:") && !linea.equals("FIN")) {
                                partes = linea.split(" ");
                                String origen = partes[0];
                                // partes[1] es el simbolo 'x'
                                char simbolo = partes[1].charAt(1); 
                                
                                Set<String> destinos = new HashSet<>();
                                for (int j = 2; j < partes.length; j++) {
                                    destinos.add(partes[j]);
                                }

                                automataND.agregarTransicion(origen, simbolo, destinos);
                                linea = scan.nextLine();
                            }
                            
                            if (linea.equals("TRANSICIONES LAMBDA:")) {
                                linea = scan.nextLine();
                                while (!linea.equals("FIN")) {
                                    partes = linea.split(" ");
                                    String origen = partes[0];
                                    Set<String> destinos = new HashSet<>();
                                    for (int j = 1; j < partes.length; j++) {
                                        destinos.add(partes[j]);
                                    }
                                    automataND.agregarTransicionLambda(origen, destinos);
                                    linea = scan.nextLine();
                                }
                            }
                            break;
                    }
                }
                ControladorAutomata c = new ControladorAutomata(automataND);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado");
        } catch (Exception ex) {
            System.out.println("Error al leer el fichero: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
package Comun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author USER
 */
public class Lectura {

    public static void generarDataSet(int numele, boolean peor) {
        try {
            FileWriter fichero = null;
            String d = "DataSet" + numele + ".tsp";
            fichero = new FileWriter(d);
            fichero.write("NAME: DataSet" + numele + " David Rico");
            fichero.write("\nTYPE: TSP GENERATE");
            fichero.write("\nCOMMENT: " + numele + " locations");
            fichero.write("\nDIMENSION: " + numele + "");
            fichero.write("\nEDGE_WEIGHT_TYPE: EUC_2D");
            fichero.write("\nNODE_COORD_SECTION");
            int num, den;
            double x, y, aux;
            Random r = new Random(System.currentTimeMillis());
            if (peor) {
                for (int i = 0; i < numele; i++) {
                    aux = r.nextDouble(1000) + 7;
                    y = aux / ((double) i + 1 + i * 0.100);
                    num = r.nextInt(3);
                    y += ((i % 500) - num * (r.nextDouble() % 100));
                    x = 1;
                    fichero.write("\n" + (i + 1) + " " + x + " " + y + "");
                }
            } else {
                for (int i = 0; i < numele; i++) {
                    num = r.nextInt(4000) + 1;
                    den = r.nextInt(11) + 7;
                    x = num / ((double) den + 0.37);
                    y = (r.nextDouble(4000) + 1) / ((double) (r.nextDouble(11) + 7) + 0.37);
                    fichero.write("\n" + (i + 1) + " " + x + " " + y + "");
                }
            }
            fichero.write("\nEOF");
            fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Punto> leerFichero(String direccion) {

        try {
            ArrayList<Punto> puntos = new ArrayList<>();

            Scanner scan = new Scanner(new File(direccion));
            int i = 0;

            while (scan.hasNext()) {
                String mensaje = scan.nextLine();
                String[] partes = mensaje.split(" ");

                if (i > 5 && !partes[0].equals("EOF")) {
                    int ite = Integer.parseInt(partes[0]);
                    double x = Double.parseDouble(partes[1]);
                    double y = Double.parseDouble(partes[2]);
                    puntos.add(new Punto(x, y, ite));
                }

                i++;
            }

            System.out.println("Array de puntos lleno con dimension de " + puntos.size() + " puntos\n");

            return puntos;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public static ArrayList<Punto> generaryLlenarArray(int numele, boolean peor) {
        ArrayList<Punto> puntos = new ArrayList<>();

        int num, den;
        double x, y, aux;
        Random r = new Random(System.currentTimeMillis());

        if (peor) {
            for (int i = 0; i < numele; i++) {
                aux = r.nextDouble(1000) + 7;
                y = aux / ((double) i + 1 + i * 0.100);
                num = r.nextInt(3);
                y += ((i % 500) - num * (r.nextDouble() % 100));
                x = 1;
                puntos.add(new Punto(x, y, i + 1));
            }
        } else {
            for (int i = 0; i < numele; i++) {
                num = r.nextInt(4000) + 1;
                den = r.nextInt(11) + 7;
                x = num / ((double) den + 0.37);
                y = (r.nextDouble(4000) + 1) / ((double) (r.nextDouble(11) + 7) + 0.37);
                puntos.add(new Punto(x, y, i + 1));
            }
        }

        return puntos;
    }

    public static void mostrarArray(ArrayList<Punto> puntos) {
        if (puntos == null) {
            System.out.println("Array apuntando a null");
            return;
        }

        for (int i = 0; i < puntos.size(); i++) {
            System.out.println(puntos.get(i).toString());
        }
    }

    public static void generarSolucionCE(String nombre, String p1, String p2, double dist, double tiempo) {
        FileWriter file = null;
        try {
            file = new FileWriter(nombre + ".tsp");
            file.write("Estrategia: " + nombre);
            file.write("\n\tPunto 1: " + p1);
            file.write("\n\tPunto 2: " + p2);
            file.write("\n\tDistancia: " + dist);
            file.write("\n\tTiempo: " + tiempo);
            file.close();

        } catch (IOException ex) {
            System.out.println("ERROR al escribir fichero " + nombre);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                System.out.println("ERROR en el close");
            }
        }
    }
}

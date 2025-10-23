/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaB;

import java.util.ArrayList;
import practicaA.Algoritmos;
import Comun.Punto;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class AlgoritmosVoraces {

    public static SolucionB unidireccional(ArrayList<Punto> puntos) {
        int numciudades = puntos.size();

        int[] ruta = new int[numciudades + 1];
        boolean[] visitados = new boolean[numciudades];
        double dist = 0;
        ArrayList<Double> distancias = new ArrayList<>();

        for (int i = 0; i < numciudades; i++) {
            visitados[i] = false;
        }

        SolucionCiudad solC = new SolucionCiudad();
        
        //int ciudad = 0;
        int ciudad = new java.util.Random(System.currentTimeMillis()).nextInt(puntos.size());
        ruta[0] = ciudad;

        int i = 1;
        while (i < numciudades) {
            visitados[ciudad] = true;
            solC = ciudadMasCercana(puntos, visitados, ciudad);
            ruta[i] = solC.getCiudad();
            ciudad = solC.getCiudad();
            distancias.add(solC.getDistancia());
            dist = dist + solC.getDistancia();

            i++;
        }

        ruta[numciudades] = ruta[0];
        double d = Algoritmos.distancia(puntos.get(ruta[numciudades - 1]), puntos.get(ruta[0]));
        dist = dist + d;
        distancias.add(d);

        return new SolucionB(dist, ruta, distancias);

    }

    public static SolucionB bidireccional(ArrayList<Punto> puntos) {
        int numciudades = puntos.size();

        int[] ruta = new int[(numciudades + 1) * 2];
        boolean[] visitados = new boolean[numciudades];
        double dist = 0;
        ArrayList<Double> distanciaDer = new ArrayList<>();
        ArrayList<Double> distanciaIzq = new ArrayList<>();

        for (int i = 0; i < numciudades; i++) {
            visitados[i] = false;
        }

        SolucionCiudad solDer = new SolucionCiudad();
        SolucionCiudad solIzq = new SolucionCiudad();

        //int ciudad = 0;
        int ciudad = new java.util.Random(System.currentTimeMillis()).nextInt(puntos.size());
        ruta[numciudades + 1] = ciudad;
        visitados[ciudad] = true;
        int der = numciudades + 1, izq = numciudades + 1;

        int i = 1;
        while (i < numciudades) {
            solDer = ciudadMasCercana(puntos, visitados, ruta[der]);
            solIzq = ciudadMasCercana(puntos, visitados, ruta[izq]);

            if (solDer.getDistancia() <= solIzq.getDistancia()) {
                der++;
                ruta[der] = solDer.getCiudad();
                ciudad = solDer.getCiudad();
                distanciaDer.add(solDer.getDistancia());
                dist = dist + solDer.getDistancia();
            } else {
                izq--;
                ruta[izq] = solIzq.getCiudad();
                ciudad = solIzq.getCiudad();
                distanciaIzq.add(solIzq.getDistancia());
                dist = dist + solIzq.getDistancia();
            }

            visitados[ciudad] = true;
            i++;
        }

        der++;
        ruta[der] = ruta[izq];

        int[] rutafinal = new int[numciudades + 1];
        int k = 0;
        for (int j = (numciudades + 1); j <= der; j++, k++) {
            rutafinal[k] = ruta[j];
        }
        for (int j = izq + 1; j <= numciudades + 1; j++, k++) {
            rutafinal[k] = ruta[j];
        }

        ArrayList<Double> distancias = new ArrayList<>();
        for (int j = 0; j < distanciaDer.size(); j++) {
            distancias.add(distanciaDer.get(j));
        }
        for (int j = 0; j < distanciaIzq.size(); j++) {
            distancias.add(distanciaIzq.get(j));
        }

        double d = Algoritmos.distancia(puntos.get(rutafinal[0]),
                    puntos.get(rutafinal[rutafinal.length - 1]));
        distancias.add(d);
        dist += d;

        return new SolucionB(dist, rutafinal, distancias);
    }

    public static SolucionCiudad ciudadMasCercana(ArrayList<Punto> puntos, boolean[] visitados, int ciudadactual) {
        double dmin = Double.MAX_VALUE;
        int destino = -1;

        for (int i = 0; i < puntos.size(); i++) {

            if (!visitados[i]) {

                double aux = AlgoritmosVoraces.distancia(puntos.get(i), puntos.get(ciudadactual));

                if (aux < dmin) {
                    dmin = aux;
                    destino = i;
                }
            }

        }

        SolucionCiudad sol = new SolucionCiudad(destino, dmin);

        return sol;
    }

    public static double distancia(Punto a, Punto b) {
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

    }

    public static void generarFichero(SolucionB sol, String s) {
        FileWriter fichero = null;

        String nombre = "FicheroSolucion " + s + ".txt";
        try {
            fichero = new FileWriter(nombre);

            int[] ruta = sol.getRuta();

            fichero.write("NAME: huelva4.opt.tour");
            fichero.write("\nTYPE: TOUR");
            fichero.write("\nDIMENSION: " + ruta.length + "");
            fichero.write("\nSOLUTION: " + sol.distanciatotal + "");
            fichero.write("\nTOUR_SECTION\n");

            for (int i = 0; i < ruta.length; i++) {
                if (i != ruta.length - 1) {
                    fichero.write(ruta[i] + ",");
                } else {
                    fichero.write(ruta[i] + "");
                }
            }

            ArrayList<Double> distancias = sol.getDistancias();
            for (int i = 0; i < ruta.length - 1; i++) {
                fichero.write("\n" + distancias.get(i) + " - " + ruta[i] + " " + ruta[i + 1]);
            }

            fichero.write("\nEOF");
            
            System.out.println("Fichero solucion " + s + " generado");
            
        } catch (IOException ex) {
            Logger.getLogger(AlgoritmosVoraces.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

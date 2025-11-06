package practica1;

import Comun.Punto;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author USER
 */
public class Algoritmos {

    /**
     * Estrategia de búsqueda exhaustiva O(n^2)
     */
    public static Solucion exhaustivoRecursivo(ArrayList<Punto> puntos) {
        Solucion sol = exhaustivo(puntos, 0, puntos.size() - 1);

        return sol;
    }

    public static Solucion exhaustivo(ArrayList<Punto> puntos, int izq, int dcha) {
        Solucion sol = new Solucion(puntos.get(0), puntos.get(1));
        int calculadas = 0;

        double d = sol.getDist();

        for (int i = izq; i <= dcha; i++) {
            for (int j = i + 1; j <= dcha; j++) {
                d = distancia(puntos.get(i),
                        puntos.get(j));
                calculadas++;
                if (d < sol.getDist()) {
                    sol.setDist(d);
                    sol.setP1(puntos.get(i));
                    sol.setP2(puntos.get(j));
                }
            }
        }

        sol.setCalculadas(calculadas);
        return sol;
    }

    /**
     * Estrategia de búsqueda con poda O(n*log n) + ...
     */
    public static Solucion exhaustivoPodaRecursivo(ArrayList<Punto> puntos) {
        Solucion sol = null;
        ArrayList<Punto> p = quicksortX(puntos, 0, puntos.size() - 1);
        sol = exhaustivoPoda(p);

        return sol;
    }

    public static Solucion exhaustivoPoda(ArrayList<Punto> puntos) {
        Solucion sol = new Solucion(puntos.get(0), puntos.get(1));
        int j, calculadas = 0;

        for (int i = 0; i < puntos.size(); i++) {
            j = i + 1;
            while ((j < puntos.size())
                    && ((puntos.get(j).getX()
                    - puntos.get(i).getX())
                    < sol.getDist())) {

                Solucion aux = new Solucion(puntos.get(i), puntos.get(j));

                calculadas++;

                if (aux.getDist() < sol.getDist()) {
                    sol.setDist(aux.getDist());
                    sol.setPuntos(aux.getP1(), aux.getP2());
                }
                j++;
            }
        }

        sol.setCalculadas(calculadas);
        return sol;
    }

    /**
     * Estrategia de búsqueda con técnica Divide y Vencerás Hay que pasarle el
     * array ordenado por quicksortX
     */
    public static Solucion DyVRecursivo(ArrayList<Punto> puntos) {
        Solucion sol = null;
        ArrayList<Punto> p = quicksortX(puntos, 0, puntos.size() - 1);
        sol = DyV(p, 0, puntos.size() - 1);

        return sol;
    }

    public static Solucion DyV(ArrayList<Punto> puntos, int i, int d) {

        Solucion sol = null;
        int opc = d - i + 1;
        int calculadas = 0;

        if (opc > 3) {
            int m = (i + d) / 2;
            Solucion di = DyV(puntos, i, m);
            calculadas += di.getCalculadas();
            Solucion dd = DyV(puntos, m + 1, d);
            calculadas += dd.getCalculadas();

            Solucion dmin = new Solucion();
            dmin.setDist(Double.min(di.getDist(), dd.getDist()));

            if (dmin.getDist() == di.getDist()) {
                dmin = di;
            } else {
                dmin = dd;
            }

            int a = m;
            while ((a >= i)
                    && ((puntos.get(m + 1).getX()
                    - puntos.get(a).getX())
                    < dmin.getDist())) {
                a--;
            }

            int b = m + 1;
            while ((b <= d)
                    && ((puntos.get(b).getX()
                    - puntos.get(m).getX())
                    < dmin.getDist())) {
                b++;
            }

            sol = exhaustivo(puntos, a + 1, b - 1);
            calculadas += sol.getCalculadas();

            if (sol.getDist() < dmin.getDist()) {
                dmin = sol;
            }

            dmin.setCalculadas(calculadas);
            return dmin;

        } else {
            sol = exhaustivo(puntos, i, d);
            calculadas += sol.getCalculadas();

            sol.setCalculadas(calculadas);
            return sol;
        }
    }

    /**
     * Estrategia de búsqueda con técnica Divide y Vencerás con mejora en la
     * búsqueda en la zona intermedia
     */
    public static Solucion DyVconPodaRecursivo(ArrayList<Punto> puntos) {
        Solucion sol = null;
        ArrayList<Punto> p = quicksortX(puntos, 0, puntos.size() - 1);
        sol = DyVconPoda(p, 0, puntos.size() - 1);

        return sol;
    }

    public static Solucion DyVconPoda(ArrayList<Punto> puntos, int i, int d) {

        Solucion sol = null;
        int opc = d - i + 1;
        int calculadas = 0;

        if (opc > 3) {
            int m = (i + d) / 2;
            Solucion di = DyV(puntos, i, m);
            calculadas += di.getCalculadas();

            Solucion dd = DyV(puntos, m + 1, d);
            calculadas += dd.getCalculadas();

            Solucion dmin = new Solucion();

            dmin.setDist(Double.min(di.getDist(), dd.getDist()));

            if (dmin.getDist() == di.getDist()) {
                dmin = di;
            } else {
                dmin = dd;
            }

            int a = m;
            while ((a >= i)
                    && ((puntos.get(m + 1).getX()
                    - puntos.get(a).getX())
                    < dmin.getDist())) {
                a--;
            }

            int b = m + 1;
            while ((b <= d)
                    && ((puntos.get(b).getX()
                    - puntos.get(m).getX())
                    < dmin.getDist())) {
                b++;
            }

            ArrayList<Punto> aux = new ArrayList<>();

            if ((a + 1) < (b - 1)) {

                for (int j = a + 1; j <= b - 1; j++) {
                    aux.add(puntos.get(j));
                }

                aux = quicksortY(aux, 0, aux.size() - 1);

                sol = exhaustivo11(aux, 0, aux.size() - 1);
                calculadas += sol.getCalculadas();

                if (sol.getDist() < dmin.getDist()) {
                    sol.setCalculadas(calculadas);
                    return sol;
                }

            }

            dmin.setCalculadas(calculadas);
            return dmin;

        } else {

            sol = new Solucion();
            sol = exhaustivo(puntos, i, d);
            calculadas += sol.getCalculadas();

            sol.setCalculadas(calculadas);
            return sol;
        }
    }

    private static Solucion exhaustivo11(ArrayList<Punto> puntos, int izq, int dcha) {
        Solucion sol = new Solucion(puntos.get(izq), puntos.get(dcha));
        int calculadas = 0;
        double d;

        for (int i = izq; i <= dcha; i++) {
            for (int k = i + 1; (k <= dcha && k <= i + 11); k++) {
                d = distancia(puntos.get(i), puntos.get(k));
                calculadas++;
                if (d < sol.getDist()) {
                    sol.setDist(d);
                    sol.setP1(puntos.get(i));
                    sol.setP2(puntos.get(k));
                }
            }
        }

        sol.setCalculadas(calculadas);
        return sol;
    }

    public static double distancia(Punto a, Punto b) {
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

    }

    public static ArrayList<Punto> quicksortX(ArrayList<Punto> p, int izq, int der) {
        Punto pivote = p.get(izq);
        int i = izq;
        int d = der;

        while (i < d) {
            while (p.get(i).getX() <= pivote.getX() && i < d) {
                i++;
            }
            while (p.get(d).getX() > pivote.getX()) {
                d--;
            }

            if (i < d) {
                Punto aux = p.get(i);
                p.set(i, p.get(d));
                p.set(d, aux);
            }
        }

        p.set(izq, p.get(d));
        p.set(d, pivote);

        if (izq < d - 1) {
            quicksortX(p, izq, d - 1);
        }

        if (d + 1 < der) {
            quicksortX(p, d + 1, der);
        }

        return p;
    }

    public static ArrayList<Punto> quicksortY(ArrayList<Punto> p, int izq, int der) {
        Punto pivote = p.get(izq);
        int i = izq;
        int d = der;

        while (i < d) {
            while (p.get(i).getY() <= pivote.getY() && i < d) {
                i++;
            }
            while (p.get(d).getY() > pivote.getY()) {
                d--;
            }

            if (i < d) {
                Punto aux = p.get(i);
                p.set(i, p.get(d));
                p.set(d, aux);
            }
        }

        p.set(izq, p.get(d));
        p.set(d, pivote);

        if (izq < d - 1) {
            quicksortY(p, izq, d - 1);
        }

        if (d + 1 < der) {
            quicksortY(p, d + 1, der);
        }

        return p;
    }

}

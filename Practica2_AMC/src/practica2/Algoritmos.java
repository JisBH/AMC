package practica2;

import Comun.Punto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import practica2.SolucionTSP;


public class Algoritmos {

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
                d = distancia(puntos.get(i), puntos.get(j));
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
            
            Solucion di = DyVconPoda(puntos, i, m);
            calculadas += di.getCalculadas();

            Solucion dd = DyVconPoda(puntos, m + 1, d);
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

    public static SolucionTSP tspVorazUnidireccional(ArrayList<Punto> puntos) {
        long inicio = System.nanoTime();
        SolucionTSP sol = new SolucionTSP();
        
        int n = puntos.size();
        if (n == 0) return sol;

        boolean[] visitados = new boolean[n];
        
        Punto actual = puntos.get(0);
        sol.agregarPunto(actual);
        visitados[0] = true; 
        
        double distanciaTotal = 0;
        int calculadas = 0;

        for (int i = 0; i < n - 1; i++) {
            double minDist = Double.MAX_VALUE;
            int mejorIndice = -1;

            for (int j = 0; j < n; j++) {
                if (!visitados[j]) {
                    double d = distancia(actual, puntos.get(j));
                    calculadas++;
                    if (d < minDist) {
                        minDist = d;
                        mejorIndice = j;
                    }
                }
            }

            if (mejorIndice != -1) {
                visitados[mejorIndice] = true;
                Punto siguiente = puntos.get(mejorIndice);
                sol.agregarPunto(siguiente);
                distanciaTotal += minDist;
                actual = siguiente;
            }
        }

        distanciaTotal += distancia(actual, sol.getRuta().get(0));
        calculadas++;
        sol.agregarPunto(sol.getRuta().get(0));

        sol.setDistanciaTotal(distanciaTotal);
        sol.setCalculadas(calculadas);
        sol.setTiempoEjecucion((System.nanoTime() - inicio) / 1_000_000.0);
        return sol;
    }

    public static SolucionTSP tspVorazBidireccional(ArrayList<Punto> puntos) {
        long inicio = System.nanoTime();
        SolucionTSP sol = new SolucionTSP();
        
        int n = puntos.size();
        if (n == 0) return sol;

        boolean[] visitados = new boolean[n];
        
        Punto extremoIzq = puntos.get(0);
        visitados[0] = true;
        
        double minDistInicio = Double.MAX_VALUE;
        int idxSegundo = -1;
        int calculadas = 0;
        
        for(int i=1; i<n; i++) {
            double d = distancia(extremoIzq, puntos.get(i));
            calculadas++;
            if(d < minDistInicio) {
                minDistInicio = d;
                idxSegundo = i;
            }
        }
        
        ArrayList<Punto> dequeRuta = new ArrayList<>();
        dequeRuta.add(extremoIzq);
        
        Punto extremoDer = extremoIzq;
        
        if (idxSegundo != -1) {
            visitados[idxSegundo] = true;
            extremoDer = puntos.get(idxSegundo);
            dequeRuta.add(extremoDer);
        }
        
        double distanciaTotal = minDistInicio;

        for (int i = 0; i < n - 2; i++) {
            double minDist = Double.MAX_VALUE;
            int mejorIndice = -1;
            boolean insertarEnIzq = false; 

            for (int j = 0; j < n; j++) {
                if (!visitados[j]) {
                    double distI = distancia(extremoIzq, puntos.get(j));
                    calculadas++;
                    
                    double distD = distancia(extremoDer, puntos.get(j));
                    calculadas++;

                    if (distI < minDist) {
                        minDist = distI;
                        mejorIndice = j;
                        insertarEnIzq = true;
                    }
                    if (distD < minDist) {
                        minDist = distD;
                        mejorIndice = j;
                        insertarEnIzq = false;
                    }
                }
            }

            if (mejorIndice != -1) {
                visitados[mejorIndice] = true;
                distanciaTotal += minDist;
                Punto p = puntos.get(mejorIndice);
                
                if (insertarEnIzq) {
                    dequeRuta.add(0, p);
                    extremoIzq = p;
                } else {
                    dequeRuta.add(p);
                    extremoDer = p;
                }
            }
        }

        distanciaTotal += distancia(extremoIzq, extremoDer);
        calculadas++;
        dequeRuta.add(dequeRuta.get(0));

        sol.setRuta(dequeRuta);
        sol.setDistanciaTotal(distanciaTotal);
        sol.setCalculadas(calculadas);
        sol.setTiempoEjecucion((System.nanoTime() - inicio) / 1_000_000.0);
        return sol;
    }

    public static SolucionTSP tspVorazUnidireccionalPoda(ArrayList<Punto> puntosOriginal) {
        long inicio = System.nanoTime();
        SolucionTSP sol = new SolucionTSP();
        
        ArrayList<Punto> puntosOrdenados = new ArrayList<>(puntosOriginal);
        Collections.sort(puntosOrdenados, Comparator.comparingDouble(Punto::getX));
        
        int n = puntosOrdenados.size();
        boolean[] visitadosOrdenados = new boolean[n];

        Punto puntoInicial = puntosOriginal.get(0);
        int indiceEnOrdenado = -1;
        for(int k=0; k<n; k++){
            if(puntosOrdenados.get(k) == puntoInicial) {
                indiceEnOrdenado = k; break;
            }
        }
        
        Punto actual = puntoInicial;
        visitadosOrdenados[indiceEnOrdenado] = true;
        sol.agregarPunto(actual);
        
        double distanciaTotal = 0;
        int calculadas = 0;

        for (int step = 0; step < n - 1; step++) {
            double minDist = Double.MAX_VALUE;
            int mejorIndice = -1;
            
            for (int k = indiceEnOrdenado + 1; k < n; k++) {
                if (visitadosOrdenados[k]) continue;
                if ((puntosOrdenados.get(k).getX() - actual.getX()) >= minDist) break;
                
                double d = distancia(actual, puntosOrdenados.get(k));
                calculadas++;
                if (d < minDist) {
                    minDist = d;
                    mejorIndice = k;
                }
            }
            
            for (int k = indiceEnOrdenado - 1; k >= 0; k--) {
                if (visitadosOrdenados[k]) continue;
                if ((actual.getX() - puntosOrdenados.get(k).getX()) >= minDist) break;
                
                double d = distancia(actual, puntosOrdenados.get(k));
                calculadas++;
                if (d < minDist) {
                    minDist = d;
                    mejorIndice = k;
                }
            }
            
            if (mejorIndice != -1) {
                visitadosOrdenados[mejorIndice] = true;
                Punto siguiente = puntosOrdenados.get(mejorIndice);
                distanciaTotal += minDist;
                sol.agregarPunto(siguiente);
                actual = siguiente;
                indiceEnOrdenado = mejorIndice;
            }
        }

        distanciaTotal += distancia(actual, sol.getRuta().get(0));
        calculadas++;
        sol.agregarPunto(sol.getRuta().get(0));

        sol.setDistanciaTotal(distanciaTotal);
        sol.setCalculadas(calculadas);
        sol.setTiempoEjecucion((System.nanoTime() - inicio) / 1_000_000.0);
        return sol;
    }

    public static SolucionTSP tspVorazBidireccionalPoda(ArrayList<Punto> puntosOriginal) {
        long inicio = System.nanoTime();
        SolucionTSP sol = new SolucionTSP();
        
        ArrayList<Punto> puntosOrdenados = new ArrayList<>(puntosOriginal);
        Collections.sort(puntosOrdenados, Comparator.comparingDouble(Punto::getX));
        
        int n = puntosOrdenados.size();
        boolean[] visitadosOrdenados = new boolean[n];

        Punto pInicial = puntosOriginal.get(0);
        int idxIzq = -1; 
        for(int k=0; k<n; k++){
            if(puntosOrdenados.get(k) == pInicial) { idxIzq = k; break; }
        }
        visitadosOrdenados[idxIzq] = true;
        Punto extIzq = pInicial;
        
        double minDist = Double.MAX_VALUE;
        int idxSegundo = -1;
        int calculadas = 0;
        
        for(int k=idxIzq+1; k<n; k++){
            if ((puntosOrdenados.get(k).getX() - extIzq.getX()) >= minDist) break;
            double d = distancia(extIzq, puntosOrdenados.get(k));
            calculadas++;
            if(d < minDist) { minDist = d; idxSegundo = k; }
        }
        
        for(int k=idxIzq-1; k>=0; k--){
            if ((extIzq.getX() - puntosOrdenados.get(k).getX()) >= minDist) break;
            double d = distancia(extIzq, puntosOrdenados.get(k));
            calculadas++;
            if(d < minDist) { minDist = d; idxSegundo = k; }
        }
        
        visitadosOrdenados[idxSegundo] = true;
        Punto extDer = puntosOrdenados.get(idxSegundo);
        int idxDer = idxSegundo;
        
        ArrayList<Punto> dequeRuta = new ArrayList<>();
        dequeRuta.add(extIzq);
        dequeRuta.add(extDer);
        
        double distanciaTotal = minDist;

        for (int i = 0; i < n - 2; i++) {
            minDist = Double.MAX_VALUE;
            int mejorIndice = -1;
            boolean esParaIzq = false;

            for(int k=idxIzq+1; k<n; k++){
                if(visitadosOrdenados[k]) continue;
                if((puntosOrdenados.get(k).getX() - extIzq.getX()) >= minDist) break;
                double d = distancia(extIzq, puntosOrdenados.get(k));
                calculadas++;
                if(d < minDist) { minDist = d; mejorIndice = k; esParaIzq = true; }
            }
            
            for(int k=idxIzq-1; k>=0; k--){
                if(visitadosOrdenados[k]) continue;
                if((extIzq.getX() - puntosOrdenados.get(k).getX()) >= minDist) break;
                double d = distancia(extIzq, puntosOrdenados.get(k));
                calculadas++;
                if(d < minDist) { minDist = d; mejorIndice = k; esParaIzq = true; }
            }

            for(int k=idxDer+1; k<n; k++){
                if(visitadosOrdenados[k]) continue;
                if((puntosOrdenados.get(k).getX() - extDer.getX()) >= minDist) break;
                double d = distancia(extDer, puntosOrdenados.get(k));
                calculadas++;
                if(d < minDist) { minDist = d; mejorIndice = k; esParaIzq = false; }
            }

            for(int k=idxDer-1; k>=0; k--){
                if(visitadosOrdenados[k]) continue;
                if((extDer.getX() - puntosOrdenados.get(k).getX()) >= minDist) break;
                double d = distancia(extDer, puntosOrdenados.get(k));
                calculadas++;
                if(d < minDist) { minDist = d; mejorIndice = k; esParaIzq = false; }
            }

            if(mejorIndice != -1) {
                visitadosOrdenados[mejorIndice] = true;
                distanciaTotal += minDist;
                Punto p = puntosOrdenados.get(mejorIndice);
                
                if(esParaIzq) {
                    dequeRuta.add(0, p);
                    extIzq = p;
                    idxIzq = mejorIndice;
                } else {
                    dequeRuta.add(p);
                    extDer = p;
                    idxDer = mejorIndice;
                }
            }
        }
        
        distanciaTotal += distancia(extIzq, extDer);
        calculadas++;
        dequeRuta.add(dequeRuta.get(0));

        sol.setRuta(dequeRuta);
        sol.setDistanciaTotal(distanciaTotal);
        sol.setCalculadas(calculadas);
        sol.setTiempoEjecucion((System.nanoTime() - inicio) / 1_000_000.0);
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
            while (p.get(i).getX() <= pivote.getX() && i < d) i++;
            while (p.get(d).getX() > pivote.getX()) d--;
            if (i < d) {
                Punto aux = p.get(i);
                p.set(i, p.get(d));
                p.set(d, aux);
            }
        }
        p.set(izq, p.get(d));
        p.set(d, pivote);
        if (izq < d - 1) quicksortX(p, izq, d - 1);
        if (d + 1 < der) quicksortX(p, d + 1, der);
        return p;
    }

    public static ArrayList<Punto> quicksortY(ArrayList<Punto> p, int izq, int der) {
        Punto pivote = p.get(izq);
        int i = izq;
        int d = der;

        while (i < d) {
            while (p.get(i).getY() <= pivote.getY() && i < d) i++;
            while (p.get(d).getY() > pivote.getY()) d--;
            if (i < d) {
                Punto aux = p.get(i);
                p.set(i, p.get(d));
                p.set(d, aux);
            }
        }
        p.set(izq, p.get(d));
        p.set(d, pivote);
        if (izq < d - 1) quicksortY(p, izq, d - 1);
        if (d + 1 < der) quicksortY(p, d + 1, der);
        return p;
    }
}
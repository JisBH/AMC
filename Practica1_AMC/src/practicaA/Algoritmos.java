package practicaA;



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
     *  Estrategia de búsqueda exhaustiva O(n^2)
     */
    public static SolucionA exhaustivoRecursivo(ArrayList<Punto> puntos){
        SolucionA sol = exhaustivo(puntos, 0, puntos.size()-1);
        
        return sol;
    }
    
    public static SolucionA exhaustivo(ArrayList<Punto> puntos, int izq, int dcha) {
                                                          //Operaciones elementales
        SolucionA sol = new SolucionA                       
            (puntos.get(0), puntos.get(1));     //5
        int calculadas = 0;                                     //1
        
        double d = sol.getDist();                               //3

        for (int i = izq; i <= dcha; i++) {                     //1 + 1 + 2
            for (int j = i + 1; j <= dcha; j++) {               //2 + 1 + 2
                d = distancia(puntos.get(i),             
                        puntos.get(j));                  //7
                calculadas++;                                   //2
                if (d < sol.getDist()) {                        //3
                    sol.setDist(d);                         //1
                    sol.setP1(puntos.get(i));           //3
                    sol.setP2(puntos.get(j));           //3
                }
            }
        }
        
        sol.setCalculadas(calculadas);                        //1
        return sol;                                             //1
    }

    /**
     *  Estrategia de búsqueda con poda O(n*log n) + ... 
     *  Nos decantamos por una coordenada (x o y) para ordenar
     * 
     */
    public static SolucionA exhaustivoPodaRecursivo(ArrayList<Punto> puntos){
        SolucionA sol = null;
        ArrayList<Punto> p = quicksortX(puntos, 0, puntos.size()-1);
        sol = exhaustivoPoda(p);
        
        return sol;
    }
    
    public static SolucionA exhaustivoPoda(ArrayList<Punto> puntos) {
                                                                //Operaciones elementales
        SolucionA sol = new SolucionA
            (puntos.get(0), puntos.get(1));            //5
        int j, calculadas=0;                                           //1
        
        for (int i = 0; i < puntos.size(); i++) {                      //1 + 3 + 2     
            j = i+1;                                                   //2
            while((j < puntos.size()) &&                                
                    ((puntos.get(j).getX() - 
                    puntos.get(i).getX()) 
                    < sol.getDist())){                                 //3 +1+ 4 + 4 + 2
                
                SolucionA aux = new SolucionA
                    (puntos.get(i), puntos.get(j));    //5
                
                calculadas++;                                          //2
                
                if(aux.getDist() < sol.getDist()){                     //5
                    sol.setDist(aux.getDist());                    //3
                    sol.setPuntos(aux.getP1(), aux.getP2());     //5
                }
                j++;                                                   //2
            }
        }
        
        sol.setCalculadas(calculadas);                               //1
        return sol;                                                    //1
    }

    /**
     *  Estrategia de búsqueda con técnica Divide y Vencerás
     *  Hay que pasarle el array ordenado por quicksortX
     */
    public static SolucionA DyVRecursivo(ArrayList<Punto> puntos){
        SolucionA sol = null;
        ArrayList<Punto> p = quicksortX(puntos, 0, puntos.size()-1);
        sol = DyV(p, 0, puntos.size()-1);
                
        return sol;
    }
    
    public static SolucionA DyV(ArrayList<Punto> puntos, int i, int d) {
                                                            //Operaciones elementales
        SolucionA sol = null;                                       //1
        int opc = d-i+1;                                            //3
        int calculadas=0;                                           //1
        
        if(opc>3){                                                  //1
            int m = (i+d)/2;                                        //3
            SolucionA di = DyV(puntos, i, m);                     //1 + 1 + T(n/2)
            calculadas += di.getCalculadas();                       //4
            SolucionA dd = DyV(puntos, m+1, d);                     //1 + 1 + T(n/2) + 1
            calculadas += dd.getCalculadas();                       //4
            
            SolucionA dmin = new SolucionA();                       //1
            dmin.setDist
                (Double.min(di.getDist(), dd.getDist()));   //7
            
            if(dmin.getDist() == di.getDist())                      //5
                dmin = di;                                          //1
            else
                dmin = dd;                                          //1
            
            //IZQ T[i, m]
            int a = m;                                              //1
            while ((a >= i) && 
                    ((puntos.get(m+1).getX() - 
                    puntos.get(a).getX()) <
                    dmin.getDist())){                               //1 +1+ 5 + 4 + 2
                a--;                                                //2
            }
            //FRANJA IZQ T[a+1, m]
            
            //DCHA T[m+1, d]
            int b = m+1;                                            //2
            while ((b <= d) && 
                    ((puntos.get(b).getX() - 
                    puntos.get(m).getX()) < 
                    dmin.getDist())){                               //1 +1+ 4 + 4 + 2
                b++;                                                //2
            }
            //FRANJA DCHA T[m+1, b-1]
            
            sol = exhaustivo(puntos, a+1, b-1);                     //5
            calculadas += sol.getCalculadas();                      //4
            
            if (sol.getDist() < dmin.getDist())                     //5
                    dmin = sol;                                     //1
            
            dmin.setCalculadas(calculadas);                       //1
            return dmin;                                            //1

        }else{
            sol=exhaustivo(puntos, i, d);                    //2
            calculadas += sol.getCalculadas();                      //4
            
            sol.setCalculadas(calculadas);                        //1
            return sol;                                             //1
        }
    }

    /**
     * Estrategia de búsqueda con técnica Divide y Vencerás con mejora en la
     * búsqueda en la zona intermedia
     */
    public static SolucionA DyVconPodaRecursivo(ArrayList<Punto> puntos){
        SolucionA sol = null;
        ArrayList<Punto> p = quicksortX(puntos, 0, puntos.size()-1);
        sol = DyVconPoda(p, 0, puntos.size()-1);
        
        return sol;
    }
    
    public static SolucionA DyVconPoda(ArrayList<Punto> puntos, int i, int d) {
                                                                 //Operaciones elementales
        SolucionA sol = null;                                           //1
        int opc = d-i+1;                                                //3
        int calculadas = 0;                                             //1
        
        if(opc>3){                                                      //1
            int m = (i+d)/2;                                            //3
            SolucionA di = DyV(puntos, i, m);                         //1 + 1 + T(n/2)
            calculadas += di.getCalculadas();                           //4
            
            SolucionA dd = DyV(puntos, m+1, d);                         //1 + 1 + T(n/2)
            calculadas += dd.getCalculadas();                           //4
            
            SolucionA dmin = new SolucionA();                           //1
            
            dmin.setDist    
                (Double.min(di.getDist(), dd.getDist()));       //7
            
            if(dmin.getDist() == di.getDist())                          //5
                dmin = di;                                              //1
            else
                dmin = dd;                                              //1
            
            //IZQ T[i, m]
            int a = m;                                                  //1
            while ((a >= i) &&                                              
                    ((puntos.get(m+1).getX() -
                    puntos.get(a).getX()) <
                    dmin.getDist())){                                   //1 +1+ 5 + 4 + 2
                a--;                                                    //2
            }
            //FRANJA IZQ T[a+1, m]
            
            //DCHA T[m+1, d]
            int b = m+1;                                                //2
            while ((b <= d) &&          
                    ((puntos.get(b).getX() -
                    puntos.get(m).getX()) <
                    dmin.getDist())){                                   //1 +1+ 4 + 4 + 2
                b++;                                                    //2
            }
            //FRANJA DCHA T[m+1, b-1]
            
            ArrayList<Punto> aux = new ArrayList<>();                   //1
            
            if((a+1)<(b-1)){                                            //3
            
                for (int j = a+1; j <= b-1; j++) {                      //2 + 2 + 2
                    aux.add(puntos.get(j));                      //3
                }
            
                aux = quicksortY(aux, 0, aux.size()-1);            //5
            
                sol = exhaustivo11(aux, 0, aux.size()-1);      //5
                calculadas += sol.getCalculadas();                      //4
            
            
                if (sol.getDist() < dmin.getDist()){                    //5
                        sol.setCalculadas(calculadas);                //1
                        return sol;                                     //1
                }
            
            }

            dmin.setCalculadas(calculadas);                           //1
            return dmin;                                                //1
            
        }else{
            
            sol = new SolucionA();                                      //1
            sol = exhaustivo(puntos, i, d);                      //3
            calculadas += sol.getCalculadas();                          //4
            
            sol.setCalculadas(calculadas);                            //1
            return sol;                                                 //1
        }
    }
    
    
    
    private static SolucionA exhaustivo11(ArrayList<Punto> puntos, int izq, int dcha) {
                                                                         //Operaciones elementales
        SolucionA sol = new SolucionA           
            (puntos.get(izq), puntos.get(dcha));               //5
        int calculadas = 0;                                                    //1
        double d;

        for (int i = izq; i <= dcha; i++) {                                    //1 + 1 + 2
            for (int k = i + 1; (k <= dcha && k<=dcha+11); k++) {              //2 + 4 + 2
                d = distancia(puntos.get(i), puntos.get(k));     //7
                calculadas++;                                                  //2
                if (d < sol.getDist()) {                                       //3
                    sol.setDist(d);                                        //1
                    sol.setP1(puntos.get(i));                          //3
                    sol.setP2(puntos.get(k));                          //3
                }
            }
        }
        
        sol.setCalculadas(calculadas);                                       //1
        return sol;                                                            //1
    }
    
    public static double distancia(Punto a, Punto b) {
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        
    }
    
    public static ArrayList<Punto> quicksortX(ArrayList<Punto> p, int izq, int der){
        Punto pivote = p.get(izq);
        int i = izq;
        int d = der;
        
        while(i < d){
            while (p.get(i).getX() <= pivote.getX() && i < d)
                i++;
            while (p.get(d).getX() > pivote.getX())
                d--;
            
            if(i < d){
                Punto aux = p.get(i);
                p.set(i, p.get(d));
                p.set(d, aux);
            }
        }
        
        p.set(izq, p.get(d));
        p.set(d, pivote);
        
        if (izq < d-1)
            quicksortX(p, izq, d-1);
        
        if (d+1 < der)
            quicksortX(p, d+1, der);
        
        return p;
    }
    
    public static ArrayList<Punto> quicksortY(ArrayList<Punto> p, int izq, int der){
        Punto pivote = p.get(izq);
        int i = izq;
        int d = der;
        
        while(i < d){
            while (p.get(i).getY() <= pivote.getY() && i < d)
                i++;
            while (p.get(d).getY() > pivote.getY())
                d--;
            
            if(i < d){
                Punto aux = p.get(i);
                p.set(i, p.get(d));
                p.set(d, aux);
            }
        }
        
        p.set(izq, p.get(d));
        p.set(d, pivote);
        
        if (izq < d-1)
            quicksortX(p, izq, d-1);
        
        if (d+1 < der)
            quicksortX(p, d+1, der);
        
        return p;
    }
    
}

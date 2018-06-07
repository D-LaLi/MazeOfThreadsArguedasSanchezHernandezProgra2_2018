package util;

import java.text.DecimalFormat;
import java.util.Random;

public class Utilidades {

    final static String s = "###,###,###.##";
    final static DecimalFormat formato = new DecimalFormat(s);

    //Retorna un valor de la forma ###,###,###.##
    public static String getFormat(double value) {
        return formato.format(value);
    }//End method

    //Retorna un entero en forma aleatoria de 0 a delimitador
    public static int getRandom(int delimitador) {

        Random r = new Random();
        return r.nextInt(delimitador);

    }//End method getRandom

    public String instanciaDe(Object a, Object b) {
        
        if (a instanceof Integer && b instanceof Integer) {
            return "entero";
        }
        if (a instanceof String && b instanceof String) {
            return "string";
        }
        if (a== null && b== null) {
            return "nulos";
        }


        return "desconocido";
    }
    public boolean igualQ(Object a, Object b) {
        System.out.println(instanciaDe(a, b));
        switch (instanciaDe(a, b)) {
            case "entero":{
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x == y;
        }
            case "string":{
                String p = (String) a;
                String q = (String) b;
                return p.equals(q);
            }
        }
        return false;
    }


    public boolean esMayorQ(Object a, Object b) {
        switch (instanciaDe(a, b)) {
            case "entero":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x > y;
            case "string":
                String p = (String) a;
                String q = (String) b;
                return p.compareToIgnoreCase(q) > 0;
        }
        return false;
    }

    public boolean esMenorQ(Object a, Object b) {
        switch (instanciaDe(a, b)) {
            case "entero":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x < y;
            case "string":
                String p = (String) a;
                String q = (String) b;
                return p.compareToIgnoreCase(q) < 0;
        }
        return false;
    }

    

    public String formato(double n) {
        String numero = "";
        DecimalFormat df = new DecimalFormat("###,###,###.00");
        numero = df.format(n);

        return numero;
    }

    public int RandomNumeros() {
        return (int) Math.floor(Math.random() * 99);// si se desea cambiar el maximo del numero se cambia el numero despues del *
    }//Random 

}

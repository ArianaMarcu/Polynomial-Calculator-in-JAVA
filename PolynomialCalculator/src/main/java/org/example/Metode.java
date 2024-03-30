package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Metode {
        public static Boolean areTermenLiber(String[] monoame, ArrayList<Monomial> all_monoms){
            boolean areTermenLiber = false;
            for (String monom : monoame) {
                if (!monom.contains("x")) {
                    areTermenLiber = true;
                    break;
                }
            }
            if (!areTermenLiber) {
                all_monoms.add(new Monomial(0, 0D));
            }
            return areTermenLiber;
        }
        static void isMonomNegativ(String[] monoame, int index, ArrayList<Monomial> all_monoms){
            String[] monoame2 = monoame[index].split("-");
            Boolean negativ = false;
            if(monoame2[0].equals("")) {
                negativ = true;
            }
            for (int j = 0; j < monoame2.length; j++) {
                String var = monoame2[j];
                if(negativ) {
                    if (!var.equals("")) {
                        all_monoms.add(parse_map_to_monomial(var, true));
                    }
                }
                else {
                    if (j == 0) {
                        all_monoms.add(parse_map_to_monomial(var, false));
                    }
                    else {
                        all_monoms.add(parse_map_to_monomial(var, true));
                    }
                }
            }
        }
        static Polynomial readPolynomial(String polinom) {
        polinom = polinom.replace(" ", "");
        String[] monoame1 = polinom.split("\\+");//caracter special
        ArrayList<Monomial> all_monoms = new ArrayList<>();
        Polynomial polynomial_out = new Polynomial();
        areTermenLiber(monoame1, all_monoms);
        for (int i = 0; i < monoame1.length; i++) {
            if (monoame1[i].contains("-")) {
                isMonomNegativ(monoame1, i, all_monoms);
            }
            else{
                all_monoms.add(parse_map_to_monomial(monoame1[i], false));
            }
        }
        Collections.sort(all_monoms, new Comparator<Monomial>() {
            public int compare(Monomial o1, Monomial o2) {
                return o2.getP()-o1.getP(); //daca o1 are puterea mai mare
            }
        });
        polynomial_out.setGrad(all_monoms.get(0).getP());
        for(Monomial m : all_monoms) {
            if(m.getCoef() != 0)
                polynomial_out.addMonom(m);
        }
        return polynomial_out;
    }
    private static Monomial parse_map_to_monomial(String s, Boolean is_negativ) {
        if (s.contains("x") && s.contains("^"))
        {
            s = s.replace("x", "");
            String[] parts = s.split("\\^");
            if (parts[0].equals("")) {
                parts[0] = "1";
            }
            Double coeficient = is_negativ ? -Double.parseDouble(parts[0]) : Double.parseDouble(parts[0]);
            //daca e negativ monomul, ii pune minus, altfel e pozitiv
            return new Monomial(Integer.parseInt(parts[1]), coeficient);
        }
        if (s.contains("x") && !s.contains("^"))
        {
            s = s.replace("x", "");
            if (s.equals("")) {
                s = "1";
            }
            Double coeficient = is_negativ ? -Double.parseDouble(s) : Double.parseDouble(s);
            //Integer coeficient = is_negativ ? -Integer.parseInt(s) : Integer.parseInt(s);
            return new Monomial(1, coeficient);
        }
        //Integer coeficient = is_negativ ? -Integer.parseInt(s) : Integer.parseInt(s);
        Double coeficient = is_negativ ? -Double.parseDouble(s) : Double.parseDouble(s);
        return new Monomial(0, coeficient);
    }
}

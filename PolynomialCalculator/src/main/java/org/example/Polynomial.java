package org.example;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

public class Polynomial {
    private TreeMap<Integer,Double> polinom = new TreeMap<Integer, Double>(Collections.reverseOrder());
    //private TreeMap<Integer,Integer> polinom = new TreeMap<Integer, Integer>(Collections.reverseOrder());
    private Integer grad;

    public void setGrad(Integer grad) {
        this.grad = grad;
    }
    public Polynomial(){
        this.polinom = new TreeMap<Integer, Double>();
    };
    public Polynomial (TreeMap <Integer, Double> map, Integer gradul){
        this.polinom = map;
        this.grad = gradul;
    }
    public TreeMap<Integer, Double> getP() {
        return polinom;
    }

    public void addMonom(Monomial monom)
    {
        polinom.put(monom.getP(), monom.getCoef());
    }

    public Polynomial adunare(Polynomial p2)
    {
        TreeMap<Integer, Double> rezultat = new TreeMap<>();
        int gradMaxim = Math.max(p2.grad, this.grad);
        for(int i = gradMaxim; i >= 0; i--)
        {
            Double coef1 = getCoef(i, p2.polinom);
            Double coef2 = getCoef(i, this.polinom);
            rezultat.put(i, coef1 + coef2);
        }
        return new Polynomial(rezultat, gradMaxim);
    }

    private Double getCoef(Integer grad, TreeMap<Integer, Double> polinom){
        AtomicReference<Double> returnValue = new AtomicReference<>(0D);
        if(polinom.size() == 0)
            return 0D;
        //if(grad > polinom.firstKey())
           // return 0;
        //returnValue.set(0);
        polinom.forEach((g, c)->{
            if(g.equals(grad)){
                returnValue.set(c);
            }
        });
        return returnValue.get();
    }
    public Polynomial scadere(Polynomial p2)
    {
        TreeMap<Integer, Double> rezultat = new TreeMap<>();
        int gradMaxim = Math.max(p2.grad, this.grad);
        Integer g = gradMaxim;
        for(int i = gradMaxim; i >= 0; i--)
        {
            Double coef1 = getCoef(i, p2.polinom);
            Double coef2 = getCoef(i, this.polinom);
            if(coef2 - coef1 == 0){
                g--;
            }
            rezultat.put(i, coef2 - coef1);
        }
        return new Polynomial(rezultat, g+1);
    }

    public Polynomial inmultire(Polynomial p) {
        TreeMap<Integer, Double> rezultat = new TreeMap<>();
        polinom.forEach((grad1, coef1)->{
            p.getP().forEach((grad2, coef2)->{
                Double coeficient = getCoef(grad1+grad2, rezultat); ///trebuie sa caut in cele pe care le am deja
                if(coeficient != 0){
                    rezultat.replace(grad1+grad2, coeficient+coef1*coef2);//daca exista, doar il updatam
                }
                else
                    rezultat.put(grad1+grad2, coef1*coef2); //daca nu mai exista deja un x, il adaugam
            });
        });
        return new Polynomial(rezultat, p.grad+this.grad);
    }

    public Polynomial derivare(){
        TreeMap<Integer, Double> rezultat = new TreeMap<>();
        polinom.forEach((grad, coef)->{
            rezultat.put(grad-1, coef*grad);
        });
        return new Polynomial(rezultat, grad-1);
    }

    public Polynomial integrare(){
        TreeMap<Integer, Double> rezultat = new TreeMap<>();
        polinom.forEach((grad, coef)->{
            rezultat.put(grad+1, coef/(grad+1));
        });
        return new Polynomial(rezultat, grad+1);
    }

    public RezultatImpartire impartire(Polynomial p)
    {
        Polynomial catul = new Polynomial();
        Polynomial restul = new Polynomial();
        Polynomial deimpartit = this;
        Polynomial impartitor = p;
        if(p.grad == 0 && p.getP().firstEntry().getValue().equals(0)){
            return null;
        }
        if(p.grad > this.grad ){
            return null;
        }
        if(p.grad == this.grad){
            catul = this;
            restul = null;
        }
        while(deimpartit.grad >= impartitor.grad){
            Polynomial copieCat = new Polynomial();
            Double coeficient = deimpartit.polinom.firstEntry().getValue()/impartitor.polinom.firstEntry().getValue();
            //trebuie Double
            Integer putere = deimpartit.grad - impartitor.grad;
            Monomial m = new Monomial(putere, coeficient);
            catul.addMonom(m);
            copieCat.addMonom(m);
            copieCat.setGrad(copieCat.polinom.firstKey());
            Polynomial intermediar = copieCat.inmultire(impartitor);
            deimpartit = deimpartit.scadere(intermediar);
            copieCat.polinom.clear();
        }
        return new RezultatImpartire(catul, deimpartit);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        ArrayList<Integer> DescDupaPuteri = new ArrayList<>(polinom.keySet()); //returneaza un tablou cu cheile din map
        Collections.sort(DescDupaPuteri, Collections.reverseOrder());
        DecimalFormat df = new DecimalFormat("0.0");
        for (int grad : DescDupaPuteri) {
            Double coef = polinom.get(grad);
            if (coef != 0) {
                if (str.length() > 0) {
                    str.append(coef > 0 ? " + " : " - ");
                } else if (coef < 0) {
                    str.append("-");
                }
                if (Math.abs(coef) != 1 || grad == 0) {
                    str.append(df.format(Math.abs(coef)));
                    //str.append(Math.abs(coef));
                }
                if (grad > 0) {
                    str.append("x");
                    if (grad > 1) {
                        str.append("^").append(grad);
                    }
                }
            }
        }
        return str.length() == 0 ? "0" : str.toString();
    }
    //Nu puteam sa mai folosesc forEach ul din Map pentru ca itereaza elementele in ordinea crescatoare a cheilor
    //Noua ne trebuie descrescator
}

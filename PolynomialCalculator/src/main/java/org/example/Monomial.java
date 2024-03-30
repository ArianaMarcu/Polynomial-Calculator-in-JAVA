package org.example;

public class Monomial {
    private Integer putere;
    private Double coef;

    public int getP() {
        return putere;
    }
    public void setP(int p) {
        this.putere = putere;
    }

    public Double getCoef() {
        return coef;
    }

    public void setCoef(Double coef) {
        this.coef = coef;
    }
    public Monomial(int putere, Double coef) {
        this.putere = putere;
        this.coef = coef;
    }
}

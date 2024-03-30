package org.example;

public class RezultatImpartire {
    private Polynomial cat;
    private Polynomial rest;

    public Polynomial getCat() {
        return cat;
    }

    public void setCat(Polynomial cat) {
        this.cat = cat;
    }

    public RezultatImpartire(Polynomial cat, Polynomial rest) {
        this.cat = cat;
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "cat=" + cat +
                ", rest=" + rest +
                '}';
    }

    public Polynomial getRest() {
        return rest;
    }

    public void setRest(Polynomial rest) {
        this.rest = rest;
    }
}

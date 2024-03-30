package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {
    @Test
    void adunare() {
        Polynomial p1 = Metode.readPolynomial("x^3+7x^2-9x-100");
        Polynomial p2 = Metode.readPolynomial("x^2+5x-7x^7");
        Polynomial p3 = p1.adunare(p2);
        assertEquals(p3.toString(), "-7,0x^7 + x^3 + 8,0x^2 - 4,0x - 100,0");

        Polynomial p4 = Metode.readPolynomial("x^3");
        Polynomial p5 = Metode.readPolynomial("x^2+1");
        Polynomial p6 = p4.adunare(p5);
        assertEquals(p6.toString(), "x^3 - 1");

    }
    @Test
    void scadere() {
        Polynomial p1 = Metode.readPolynomial("x^3+7x^2-9x-100");
        Polynomial p2 = Metode.readPolynomial("x^2+5x-7x^7");
        Polynomial p4 = p1.scadere(p2);
        assertEquals(p4.toString(), "7,0x^7 + x^3 + 6,0x^2 - 14,0x - 100,0");
    }

    @Test
    void inmultire() {
        Polynomial p1 = Metode.readPolynomial("x^3+7x^2-9x-100");
        Polynomial p2 = Metode.readPolynomial("x^2+5x-7x^7");
        Polynomial p4 = p1.inmultire(p2);
        assertEquals(p4.toString(), "-7,0x^10 - 49,0x^9 + 63,0x^8 + 700,0x^7 + x^5 + 12,0x^4 + 26,0x^3 - 145,0x^2 - 500,0x");
    }

    @Test
    void derivare() {
        Polynomial p1 = Metode.readPolynomial("x^3+7x^2-9x-100");
        Polynomial p2 = p1.derivare();
        assertEquals(p2.toString(), "3,0x^2 + 14,0x - 9,0");

        Polynomial p3 = Metode.readPolynomial("15x+1");
        Polynomial p4 = p3.derivare();
        assertEquals(p4.toString(), "15x");
    }

    @Test
    void integrare() {
        Polynomial p3 = Metode.readPolynomial("3x^2+6x-9");
        Polynomial p4 = p3.integrare();
        assertEquals(p4.toString(), "x^3 + 3,0x^2 - 9,0x");
    }
}
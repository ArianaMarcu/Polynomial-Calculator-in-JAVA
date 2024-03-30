package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    GUI () {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(550, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        JLabel label1 = new JLabel("Polinomul 1: ");
        label1.setBounds(10, 20, 80, 25);
        panel.add(label1);

        JTextField Polinom1 = new JTextField();
        Polinom1.setBounds(100, 20, 400, 25);
        panel.add(Polinom1);

        JLabel label2 = new JLabel("Polinomul 2: ");
        label2.setBounds(10, 50, 80, 25);
        panel.add(label2);

        JTextField Polinom2 = new JTextField();
        Polinom2.setBounds(100, 50, 400, 25);
        panel.add(Polinom2);

        JButton buton1 = new JButton("ADD");
        buton1.setBounds(10, 80, 60, 60);
        panel.add(buton1);

        JButton buton2 = new JButton("SUB");
        buton2.setBounds(100, 80, 60, 60);
        panel.add(buton2);

        JButton buton3 = new JButton("MUL");
        buton3.setBounds(190, 80, 60, 60);
        panel.add(buton3);

        JButton buton4 = new JButton("DIV");
        buton4.setBounds(280, 80, 60, 60);
        panel.add(buton4);

        JButton buton5 = new JButton("DER");
        buton5.setBounds(370, 80, 60, 60);
        panel.add(buton5);

        JButton buton6 = new JButton("INT");
        buton6.setBounds(460, 80, 60, 60);
        panel.add(buton6);

        JLabel label3 = new JLabel("Rezultat: ");
        label3.setBounds(10, 160, 80, 25);
        panel.add(label3);

        JTextField Polinom3 = new JTextField();
        Polinom3.setBounds(100, 155, 400, 25);
        panel.add(Polinom3);

        frame.setVisible(true);

        buton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String p1String = Polinom1.getText();
                String p2String = Polinom2.getText();
                Polynomial p1 = Metode.readPolynomial(p1String);
                Polynomial p2 = Metode.readPolynomial(p2String);
                Polynomial p3 = p1.adunare(p2);
                Polinom3.setText(p3.toString());
            }
        });

        buton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String p1String = Polinom1.getText();
                String p2String = Polinom2.getText();
                Polynomial p1 = Metode.readPolynomial(p1String);
                Polynomial p2 = Metode.readPolynomial(p2String);
                Polynomial p4 = p1.scadere(p2);
                Polinom3.setText(p4.toString());
            }
        });

        buton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String p1String = Polinom1.getText();
                String p2String = Polinom2.getText();
                Polynomial p1 = Metode.readPolynomial(p1String);
                Polynomial p2 = Metode.readPolynomial(p2String);
                Polynomial p3 = p1.inmultire(p2);
                Polinom3.setText(p3.toString());
            }
        });

        /*buton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String p1String = Polinom1.getText();
                String p2String = Polinom2.getText();
                Polynomial p1 = Metode.readPolynomial(p1String);
                Polynomial p2 = Metode.readPolynomial(p2String);
                RezultatImpartire p3 = p1.impartire(p2);
                Polinom3.setText(p3.toString());
            }
        });*/

        buton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String p1String = Polinom1.getText();
                String p2String = Polinom2.getText();
                Polynomial p1 = Metode.readPolynomial(p1String);
                Polynomial p2 = Metode.readPolynomial(p2String);
                Polynomial p3 = p1.derivare();
                Polinom3.setText(p3.toString());
            }
        });

        buton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String p1String = Polinom1.getText();
                String p2String = Polinom2.getText();
                Polynomial p1 = Metode.readPolynomial(p1String);
                Polynomial p2 = Metode.readPolynomial(p2String);
                Polynomial p3 = p1.integrare();
                Polinom3.setText(p3.toString());
            }
        });

        Color c1 = new Color(102, 255, 102);
        Color c2 = new Color(0, 153, 0);
        panel.setBackground(c1);

        buton1.setForeground(Color.WHITE);
        buton2.setForeground(Color.WHITE);
        buton3.setForeground(Color.WHITE);
        buton4.setForeground(Color.WHITE);
        buton5.setForeground(Color.WHITE);
        buton6.setForeground(Color.WHITE);

        buton1.setBackground(c2);
        buton2.setBackground(c2);
        buton3.setBackground(c2);
        buton4.setBackground(c2);
        buton5.setBackground(c2);
        buton6.setBackground(c2);

        Border empty = BorderFactory.createEmptyBorder();
        buton1.setBorder(empty);
        buton2.setBorder(empty);
        buton3.setBorder(empty);
        buton4.setBorder(empty);
        buton5.setBorder(empty);
        buton6.setBorder(empty);
    };
}

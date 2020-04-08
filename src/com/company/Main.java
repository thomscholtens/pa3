package com.company;


import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        new GUI();

        Rsa rsa = new Rsa(323);
        rsa.calculatePandQ();
        rsa.calculatePhi();
        rsa.generateE();
        rsa.calculateD();
        String Text = "jaja we doen het gewoon";

        rsa.encryptMessage(Text);

        rsa.decryptMessage(rsa.encryptMessage);

        System.out.println("n = "+ rsa.n);
        System.out.println("p = "+ rsa.p);
        System.out.println("q = "+ rsa.q);
        System.out.println("time = " + rsa.time + "ms");
        System.out.println("phi = "+ rsa.phi);
        System.out.println("e = "+ rsa.e);
        System.out.println("message = " + Text);
        System.out.println("encrypted message = " + rsa.encryptMessage);
        System.out.println("d " + rsa.d);
        System.out.println("decrypt " + rsa.decryptMessage );

    }

}

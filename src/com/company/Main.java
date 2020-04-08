package com.company;


import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
//        new GUI();

        Rsa rsa = new Rsa(323);
        rsa.calculatePandQ();
        rsa.calculatePhi();
        rsa.generateE();
        String Text = "jaja we doen het gewoon";

        ArrayList<Integer> message = new ArrayList();
        for (int i = 0; i < Text.length(); i++) {
            message.add( (int) Text.charAt(i));

        }
        rsa.encryptMessage(message);

        System.out.println("n = "+ rsa.n);
        System.out.println("p = "+ rsa.p);
        System.out.println("q = "+ rsa.q);
        System.out.println("time = " + rsa.time + "ms");
        System.out.println("phi = "+ rsa.phi);
        System.out.println("e = "+ rsa.e);
        System.out.println("message = " + message);
        System.out.println("encrypted message = " + rsa.encryptMessage(message));

    }

}

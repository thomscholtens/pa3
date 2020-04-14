package com.company;


import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;


public class Rsa {

    private static final BigInteger ONE = BigInteger.ONE;
    public long time;
    public int p;
    public int q;
    public int phi;
    public int e;
    public int n;
    public int d;
    public String decryptMessage;
    public ArrayList<Integer> encryptMessage;

    Rsa(int n) {
        this.n = n;
    }

    public static int calculateNextPrime(int input) {
        int number = input + 1;
        while (!isPrime(number)) {
            number++;
        }

        return number;
    }

    private static boolean isPrime(int n) {
        if (n % 2 == 0) return false;
        //if not, then just check the odds
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public void calculatePandQ() {
        long startTime = System.currentTimeMillis();
        int p = 2;
        while (p <= this.n) {
            int q = 2;
            while (q <= n) {
                if (n / q == p && n % q == 0) {
                    this.p = p;
                    this.q = q;
                    break;
                }
                if (n / q < p) {
                    break;
                }

                q = Rsa.calculateNextPrime(q);
            }
            if (n / p == q) {
                break;
            } else {
                p = Rsa.calculateNextPrime(p);
            }
        }
        this.time = System.currentTimeMillis() - startTime;
    }

    public void calculatePhi() {
        this.phi = (this.p - 1) * (this.q - 1);

    }

    public void generateE() {
        BigInteger e;
        Random random = new Random();
        do {
            e = BigInteger.valueOf(random.nextInt(1000000));
        }
        while (e.intValue() == 1 || e.intValue() > this.phi || !isPrime(e.intValue()) || e.gcd(BigInteger.valueOf(this.phi)).compareTo(ONE) != 0);

        this.e = e.intValue();
    }

    public void encryptMessage(String Text) {
        ArrayList<Integer> message = new ArrayList();

        for (int i = 0; i < Text.length(); i++) {
            message.add((int) Text.charAt(i));

        }
        BigInteger c;
        ArrayList<Integer> encrypted = new ArrayList<>();
        for (int i = 0; i < message.size(); i++) {
            c = BigInteger.valueOf(message.get(i));
            c = c.pow(this.e).mod(BigInteger.valueOf(this.n));
            encrypted.add(c.intValue());
        }

        this.encryptMessage = encrypted;
    }

    public void decryptMessage(ArrayList<Integer> message) {
        BigInteger c;

        ArrayList<Integer> decrypted = new ArrayList<>();
        for (int i = 0; i < message.size(); i++) {
            c = BigInteger.valueOf(message.get(i));
            c = c.pow(this.d).mod(BigInteger.valueOf(this.n));
            decrypted.add(c.intValue());
        }
        StringBuilder decryptmessage = new StringBuilder();
        for (int i = 0; i < decrypted.size(); i++) {
            String s = Character.toString((char) (int) decrypted.get(i));
            decryptmessage.append(s);
        }
        this.decryptMessage = decryptmessage.toString();
    }

    public void calculateD() {
        BigInteger e = BigInteger.valueOf(this.e);
        this.d = e.modInverse(BigInteger.valueOf(this.phi)).intValue();
    }

}

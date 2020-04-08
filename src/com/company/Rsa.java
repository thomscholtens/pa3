package com.company;


import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;


public class Rsa {

    private static final BigInteger ONE = BigInteger.ONE;
    public boolean isValid = false;
    public long time;
    public int p;
    public int q;
    public int phi;
    public int e;
    public int n;
    public int d;
    public ArrayList<Integer> decryptMessage;
    public ArrayList<Integer> encryptMessage;
    public ArrayList<Integer> c;

    Rsa(int n) {
        this.n  = n;
    }

    public static int calculateNextPrime(int input){
        int number = input + 1;
        while(!isPrime(number)){
            number++;
        }

        return number;
    }

    private static boolean isPrime(int number) {
        boolean flag = false;
        for(int i = 2; i <= number/2; ++i) {
            if(number % i == 0) {
                flag = true;
                break;
            }
        }
        return !flag;
    }

    public void calculatePandQ() {
        long startTime = System.currentTimeMillis();
        int p = 1;

        // while p <= n else INVALID
        while(p <= this.n){
            int q = 1;
            // while q <= n check if p * q = n else nextprime(q)
            while(q <= n) {
                if (p * q == n){
                    this.p = p;
                    this.q = q;
                    this.isValid = true;
                    break;
                }
                q = Rsa.calculateNextPrime(q);
            }

            if(this.isValid) break;
            else p = Rsa.calculateNextPrime(p);
        }

        long endTime = System.currentTimeMillis();
        this.time = endTime - startTime;
    }

    public void calculatePhi() {
        this.phi = (this.p - 1) * (this.q - 1);

    }

    public void generateE() {
        BigInteger e = BigInteger.valueOf(0);
        Random random = new Random();
        do {
            e = BigInteger.valueOf(random.nextInt(1000000));
        } while (e.intValue() == 1 || e.intValue() > this.phi || !isPrime(e.intValue()) || e.gcd(BigInteger.valueOf(this.phi)).compareTo(ONE) != 0);

        this.e = e.intValue();
    }

    public void encryptMessage(ArrayList<Integer> message) {
        BigInteger c;
        ArrayList<Integer> encrypted = new ArrayList<>();
        for(int i = 0; i < message.size(); i++) {
            c = BigInteger.valueOf(message.get(i));
            c = c.pow(this.e).mod(BigInteger.valueOf(this.n));
            encrypted.add(c.intValue());
        }

       this.encryptMessage = encrypted;
    }
 public void decryptMessage(ArrayList<Integer> message) {
        BigInteger c;

        ArrayList<Integer> decrypted = new ArrayList<>();
        for(int i = 0; i < message.size(); i++) {
            c = BigInteger.valueOf(message.get(i));
            c = c.pow(this.d).mod(BigInteger.valueOf(this.n));
            decrypted.add(c.intValue());
        }

        this.decryptMessage=  decrypted;
    }

    public void calculateD(){
        BigInteger e = BigInteger.valueOf(this.e);
        this.d =   e.modInverse(BigInteger.valueOf(this.phi)).intValue();
    }

    @Override
    public String toString() {
        return "Rsa{" +
                "isValid=" + isValid +
                ", time=" + time +
                ", p=" + p +
                ", q=" + q +
                ", phi=" + phi +
                ", e=" + e +
                ", n=" + n +
                ", c=" + c +
                '}';
    }
}

package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.repository.MilRabRepository;
import ru.itpark.noteswithsecurity.service.MilRabService;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private BigInteger n, d, e, fi, p, q;
    private int bitlen;
    private MilRabRepository milRabRepository;

    public RSA(BigInteger n, BigInteger e, BigInteger d, int bitlen) {
        this.n = n;
        this.e = e;
        this.d = d;
        this.bitlen = bitlen;
    }


    public RSA(int bits, MilRabRepository milRabRepository) {
        bitlen = bits;
        this.milRabRepository = milRabRepository;
        SecureRandom r = new SecureRandom();
        p = new BigInteger(bitlen / 2, 100, r);
        MilRabService milRabService = new MilRabService(milRabRepository);
        System.out.println(milRabService.MilRab(p));
        q = new BigInteger(bitlen / 2, 100, r);
        System.out.println(milRabService.MilRab(p));
        n = p.multiply(q);
        fi = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while (fi.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        d = e.modInverse(fi);
    }


    public String encrypt(String message) {
        return (new BigInteger(message.getBytes())).modPow(e, n).toString();
    }


    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }


    public String decrypt(String message) {
        return new String((new BigInteger(message)).modPow(d, n).toByteArray());
    }


    public BigInteger decrypt(BigInteger message) {
        return message.modPow(d, n);
    }


    public BigInteger getN() {
        return n;
    }


    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getFI() {
        return fi;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }
}
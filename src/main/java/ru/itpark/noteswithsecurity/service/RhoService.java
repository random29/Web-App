package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.DiffiHellEntity;
import ru.itpark.noteswithsecurity.entity.RhoEntity;
import ru.itpark.noteswithsecurity.repository.DiffHellRepository;
import ru.itpark.noteswithsecurity.repository.RhoRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.math.BigInteger.*;

@Service
public class RhoService {
    private RhoRepository repository ;

    private List list = new ArrayList();

    private final Random random = new Random();

    private BigInteger N;

    public RhoService(RhoRepository repository) {
        this.repository = repository;
    }

    public RhoEntity findById() {
        return repository.findById(3)
                .orElseThrow(); // TODO: throw specific exception
    }


    public  void gen(int razm) {
        Random random = new Random();
        RhoEntity rhoEntity = new RhoEntity(3, list, new BigInteger(razm, random));
        repository.save(rhoEntity);
    }

    public  void gogo(BigInteger w) {
        N = w;
        factor(w);
        RhoEntity rhoEntity = new RhoEntity(3, list, N);
        repository.save(rhoEntity);
        reset();
    }

    public  BigInteger rho_new(BigInteger N) { // Вариация Флойда
        BigInteger divisor;
        BigInteger y = new BigInteger(N.bitLength(), random);
        BigInteger x = y;
        if (N.mod(TWO).compareTo(ZERO) == 0) return TWO;
        do {
            y = y.multiply(y).mod(N).add(ONE).mod(N);
            x = x.multiply(x).mod(N).add(ONE).mod(N);
            x = x.multiply(x).mod(N).add(ONE).mod(N);
            divisor = y.subtract(x).gcd(N);
//            System.out.println("---" + divisor);
        } while ((divisor.compareTo(ONE)) == 0);
        return divisor;
    }

    public  BigInteger rho_old(BigInteger N) {
        BigInteger x = new BigInteger(N.bitLength(), random);
        BigInteger y = ONE;
        BigInteger i = BigInteger.ZERO;
        BigInteger stage = BigInteger.TWO;
        while ((x.subtract(y).gcd(N).compareTo(ONE) == 0)) {
            if (i == stage) {
                y = x;
                stage = stage.multiply(BigInteger.TWO);
            }
            x = (x.multiply(x).add(ONE).mod(N));
            i = i.add(ONE);
        }
        return x.subtract(y).gcd(N);
    }

    public  void save() {
        RhoEntity rhoEntity = new RhoEntity(3, list, findById().getX());
        repository.save(rhoEntity);
    }

    public  void reset() {
        list = new ArrayList();
    }

    public  void factor(BigInteger N) {
        if (N.compareTo(ONE) == 0) return;
        if (N.isProbablePrime(20)) {
            System.out.println(N);
            list.add(N.toString());
//            cou(N);
            return;
        }
        BigInteger divisor = rho_new(N);
        factor(divisor);
        factor(N.divide(divisor));
    }


}

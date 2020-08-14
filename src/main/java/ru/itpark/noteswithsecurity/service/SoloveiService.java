package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.SoloveiEntity;
import ru.itpark.noteswithsecurity.repository.SoloveiRepository;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

@Service

public class SoloveiService {
    private final SoloveiRepository repository;

    public SoloveiService(SoloveiRepository repository) {
        this.repository = repository;
    }

    public SoloveiEntity findById() {
        return repository.findById(3)
                .orElseThrow(); // TODO: throw specific exception
    }


    public static BigInteger gen(int razm) {
        SecureRandom r = new SecureRandom();
        Random random = new Random();
        BigInteger randomNumber;
        do {
            randomNumber = new BigInteger(razm, r);
//                    randomNumber = randomNumber.add(BigInteger.TWO);
        } while (randomNumber.compareTo(BigInteger.ONE.shiftLeft(razm).subtract(BigInteger.ONE)) <= 0 &&
                randomNumber.compareTo(BigInteger.ONE.shiftLeft(razm).subtract(BigInteger.valueOf(9))) >= 0 && !randomNumber.isProbablePrime(50));

        BigInteger wow = BigInteger.probablePrime(razm, random);
        String lol = randomNumber.toString();
        //System.out.println(lol.length());
        System.out.println(randomNumber);
        return wow;
    }

    public Boolean solovei(BigInteger n) {
        if (n.mod(BigInteger.TWO) == BigInteger.ZERO) {
            return false;
        } else {
            BigInteger reserveN = n;
            Random random = new Random();
//            int num = 1 + random.nextInt(100);
            double num1 = Math.log(n.doubleValue());
            int i = 0;
            while (i < num1) {
                n = reserveN;
                BigInteger upperLimit = n.subtract(BigInteger.valueOf(2));
                BigInteger randomNumber;
                do {
                    randomNumber = new BigInteger(upperLimit.bitLength(), random);
//                    randomNumber = randomNumber.add(BigInteger.TWO);
                } while (randomNumber.compareTo(upperLimit) >= 0 || randomNumber.compareTo(BigInteger.valueOf(2)) < 0);
                System.out.println(randomNumber);
                BigInteger d = Rae.gcd(randomNumber, n);
                if (d.compareTo(BigInteger.ONE) == 1) {
                    return false;
                } else {
                    BigInteger reserveA = randomNumber;
                    boolean r;
                    if (!Rae.gcd(randomNumber, n).equals(BigInteger.ONE)) {
                        return false;
                    } else {
                        r = true; //Символ Якоби
                        do {
                            BigInteger t = BigInteger.ZERO; //избавление от чётности
                            while (randomNumber.mod(BigInteger.TWO).equals(0)) {
                                t = t.add(BigInteger.ONE);
                                randomNumber = randomNumber.divide(BigInteger.TWO);
                            }
                            if (!t.mod(BigInteger.TWO).equals(BigInteger.ZERO) && (n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(3)) || n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(5))))
                                r = !r;
                            if (randomNumber.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)) && n.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)))  //квадратичный закон взаимности
                                r = !r;
                            BigInteger c = randomNumber;
                            randomNumber = n.mod(c);
                            n = c;
                        } while (!randomNumber.equals(BigInteger.ZERO));
                        BigInteger lol = reserveN.subtract(BigInteger.ONE).divide(BigInteger.TWO);
//                        System.out.println(lol);
//                        System.out.println(randomNumber);
                        BigInteger s = reserveA.modPow(lol, reserveN);
//                        System.out.println(s);
                        BigInteger r1;
                        if (r == true) {
                            r1 = BigInteger.ONE;
                        } else {
                            r1 = BigInteger.valueOf(-1);
                        }
                        if (s.compareTo(r1) == -1) {
//                            System.out.println(1);
                            return false;
                        }
                    }
                }
                i++;
            }
            return true;
        }
    }

    public void save(BigInteger n, String resu, int razm) {
        SoloveiEntity soloveiEntity = new SoloveiEntity(3, n, 0, resu, 128, true);

        repository.save(soloveiEntity);
    }
}

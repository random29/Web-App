package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.InPowEntity;
import ru.itpark.noteswithsecurity.entity.MilRabEntity;
import ru.itpark.noteswithsecurity.entity.RaeEntity;
import ru.itpark.noteswithsecurity.repository.InPowRepository;
import ru.itpark.noteswithsecurity.repository.MilRabRepository;
import ru.itpark.noteswithsecurity.repository.RaeRepository;

import java.math.BigInteger;
import java.util.Random;

@Service

public class MilRabService {
    private final MilRabRepository repository;

    public MilRabService(MilRabRepository repository) {
        this.repository = repository;
    }

    public MilRabEntity findById() {
        return repository.findById(3)
                .orElseThrow(); // TODO: throw specific exception
    }

    public boolean MilRab(BigInteger n) {
        if (n.equals(2))
            return true;
        int k = n.bitLength();
        if (n.compareTo(BigInteger.valueOf(2)) < 0)
            return false;
        BigInteger nminus = n.subtract(BigInteger.ONE);
        int s = 0;
        while (nminus.mod(BigInteger.valueOf(2)).equals(0)) {
            nminus = nminus.divide(BigInteger.valueOf(2));
            s += 1;
        }
        for (int i = 0; i < k; i++) {
            Random randomSource = new Random();
            BigInteger upperLimit = n.subtract(BigInteger.valueOf(2));
            BigInteger randomNumber;
            do {
                randomNumber = new BigInteger(upperLimit.bitLength(), randomSource);
            } while (randomNumber.compareTo(upperLimit) >= 0 || randomNumber.compareTo(BigInteger.valueOf(2)) < 0);
            BigInteger x = randomNumber.modPow(nminus, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE)))
                continue;
            for (int j = 1; j < s; j++) {
                x = x.modPow(BigInteger.valueOf(2), n);
                if (x.equals(BigInteger.ONE))
                    return false;
                if (x.equals(n.subtract(BigInteger.ONE)))
                    break;
            }
            if (!x.equals(n.subtract(BigInteger.ONE)))
                return false;
        }
        return true;
    }
}
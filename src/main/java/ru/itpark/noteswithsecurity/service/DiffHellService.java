package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.DiffiHellEntity;
import ru.itpark.noteswithsecurity.entity.RhoEntity;
import ru.itpark.noteswithsecurity.repository.DiffHellRepository;
import ru.itpark.noteswithsecurity.repository.RhoRepository;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class DiffHellService {
    private final DiffHellRepository repository;
    private final RhoService service;

    public DiffHellService(DiffHellRepository repository, RhoService service) {
        this.repository = repository;
        this.service = service;
    }

    public DiffiHellEntity findById() {
        return repository.findById(3)
                .orElseThrow(); // TODO: throw specific exception
    }

    public void gen(int razm) {
//        BigInteger key1 = SoloveiService.gen(razm);
//        BigInteger key2 = SoloveiService.gen(razm);
        SecureRandom random = new SecureRandom();
        BigInteger key1 = new BigInteger(razm, random);
        BigInteger key2 = new BigInteger(razm, random);
        DiffiHellEntity diffiHellEntity = new DiffiHellEntity(3, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO, key1, key2, BigInteger.ZERO, razm);
        repository.save(diffiHellEntity);
    }

    public void genP() {
        DiffiHellEntity diffiHellEntity = findById();
        BigInteger key1 = SoloveiService.gen(diffiHellEntity.getRazm1());
//        service.factor(key1);
//        RhoEntity rhoEntity = service.findById();
        BigInteger key2 = SoloveiService.gen(diffiHellEntity.getRazm1());
        DiffiHellEntity diffiHellEntity1 = new DiffiHellEntity(3, key1, key2, BigInteger.ZERO, BigInteger.ZERO, diffiHellEntity.getKey1(), diffiHellEntity.getKey2(), BigInteger.ZERO, diffiHellEntity.getRazm1());
        repository.save(diffiHellEntity1);
    }

    public void genA() {
        DiffiHellEntity diffiHellEntity = findById();
        BigInteger a = diffiHellEntity.getG().modPow(diffiHellEntity.getKey1(), diffiHellEntity.getP());
        BigInteger b = diffiHellEntity.getG().modPow(diffiHellEntity.getKey2(), diffiHellEntity.getP());
        diffiHellEntity.setA(a);
        diffiHellEntity.setB(b);
        repository.save(diffiHellEntity);
    }

    public void genK() {
        DiffiHellEntity diffiHellEntity = findById();
        BigInteger k = diffiHellEntity.getB().modPow(diffiHellEntity.getKey1(), diffiHellEntity.getP());
        BigInteger k2 = diffiHellEntity.getA().modPow(diffiHellEntity.getKey2(), diffiHellEntity.getP());
        if (k.equals(k2)) {
            diffiHellEntity.setK(k);
        }
        else diffiHellEntity.setK(BigInteger.ZERO);
        repository.save(diffiHellEntity);
    }


}

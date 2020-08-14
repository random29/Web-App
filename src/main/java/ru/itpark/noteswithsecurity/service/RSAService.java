package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.MilRabEntity;
import ru.itpark.noteswithsecurity.entity.RSAEntity;
import ru.itpark.noteswithsecurity.repository.MilRabRepository;
import ru.itpark.noteswithsecurity.repository.RSARepository;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.ExecutionException;

@Service
public class RSAService {
    private final RSARepository repository;
    private final MilRabRepository milRabRepository;
    public RSAService(RSARepository repository, MilRabRepository milRabRepository) {
        this.repository = repository;
        this.milRabRepository = milRabRepository;
    }

    public RSAEntity findById() {
        return repository.findById(3)
                .orElseThrow(); // TODO: throw specific exception
    }

    public String solve(String dec, int bitlen){
        RSA rsa = new RSA(bitlen, milRabRepository);
        System.out.println(dec);
        String result = rsa.encrypt(dec);
        RSAEntity rsaEntity = new RSAEntity();
        rsaEntity.setDecryptedText(dec);
        rsaEntity.setCipherText(result);
        rsaEntity.setD(rsa.getD());
        rsaEntity.setId(3);
        rsaEntity.setE(rsa.getE());
        rsaEntity.setFi(rsa.getFI());
        System.out.println(rsa.getD());
        rsaEntity.setN(rsa.getN());
        rsaEntity.setP(rsa.getP());
        rsaEntity.setBitlen(bitlen);
        rsaEntity.setQ(rsa.getQ());
        repository.save(rsaEntity);
        return result;
    }
    public String solveDec(String enc, int bitlen, BigInteger n , BigInteger e, BigInteger d){
        RSA rsa = new RSA(n, e, d, bitlen);
        String result = rsa.decrypt(enc);
        RSAEntity rsaEntity = new RSAEntity();
        rsaEntity.setDecryptedText(result);
        rsaEntity.setCipherText(enc);
        rsaEntity.setD(repository.getOne(3).getD());
        rsaEntity.setId(3);
        rsaEntity.setBitlen(bitlen);
        rsaEntity.setE(repository.getOne(3).getE());
        rsaEntity.setFi(repository.getOne(3).getFi());
        rsaEntity.setN(repository.getOne(3).getN());
        rsaEntity.setP(repository.getOne(3).getP());
        rsaEntity.setQ(repository.getOne(3).getQ());
        repository.save(rsaEntity);
        return result;
    }

}

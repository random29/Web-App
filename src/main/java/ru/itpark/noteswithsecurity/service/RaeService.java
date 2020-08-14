package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.InPowEntity;
import ru.itpark.noteswithsecurity.entity.RaeEntity;
import ru.itpark.noteswithsecurity.repository.InPowRepository;
import ru.itpark.noteswithsecurity.repository.RaeRepository;

import java.math.BigInteger;

@Service

public class RaeService {
    private final RaeRepository repository;
    private final InPowRepository inPowRepository;

    public RaeService(RaeRepository repository, InPowRepository inPowRepository) {
        this.repository = repository;
        this.inPowRepository = inPowRepository;
    }

    public RaeEntity findById() {
        return repository.findById(3)
                .orElseThrow(); // TODO: throw specific exception
    }

    public InPowEntity findByIdInPow() {
        return inPowRepository.findById(3)
                .orElseThrow(); // TODO: throw specific exception
    }

    public void solve(BigInteger a, BigInteger b) {
        Rae rae = new Rae();
        rae = rae.doRae(a, b);
        RaeEntity raeEntity = new RaeEntity();
        raeEntity.setId(3);
        raeEntity.setA(a);
        raeEntity.setB(b);
        raeEntity.setD("Остаток от деления = " + rae.d);
        raeEntity.setX("x компонента = " + rae.x);
        raeEntity.setY("y компонента = " + rae.y);
        repository.save(raeEntity);
    }

    public void powder(BigInteger a, BigInteger b, BigInteger n) {
        Rae rae = new Rae();
    BigInteger result = rae.inPow(a, b, n);
    InPowEntity inPowEntity = new InPowEntity();
        inPowEntity.setId(3);
                inPowEntity.setA(a);
                inPowEntity.setB(b);
                inPowEntity.setN(n);
                inPowEntity.setResult(result);
                inPowRepository.save(inPowEntity);
                }
                }

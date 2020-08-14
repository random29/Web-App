package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.AccountInfoEntity;
import ru.itpark.noteswithsecurity.repository.AccountInfoRepository;

import java.util.List;

@Service
public class AccountInfoService {
    private final AccountInfoRepository repository;

    public AccountInfoService(AccountInfoRepository repository) {
        this.repository = repository;
    }


    public List<AccountInfoEntity> findAll() {
        return repository.findAll();
    }

    public AccountInfoEntity findById(int id) {
        return repository.findById(id)
                .orElseThrow(); // TODO: throw specific exception
    }
}

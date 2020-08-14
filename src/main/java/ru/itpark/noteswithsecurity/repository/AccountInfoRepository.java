package ru.itpark.noteswithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.noteswithsecurity.entity.AccountInfoEntity;

import java.util.Optional;

public interface AccountInfoRepository extends JpaRepository<AccountInfoEntity, Integer> {

}

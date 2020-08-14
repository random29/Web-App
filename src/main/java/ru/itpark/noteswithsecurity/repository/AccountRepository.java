package ru.itpark.noteswithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.noteswithsecurity.entity.AccountEntity;

import java.util.Optional;

public interface AccountRepository
        extends JpaRepository<AccountEntity, Integer> {
    Optional<AccountEntity> findByUsername(String username); // Derived queries
//    AccountEntity findByEmail(String email);
//    AccountEntity findByConfirmationToken(String confirmationToken);
}

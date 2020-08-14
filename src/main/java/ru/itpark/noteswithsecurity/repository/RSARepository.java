package ru.itpark.noteswithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.noteswithsecurity.entity.RSAEntity;
import ru.itpark.noteswithsecurity.entity.RaeEntity;

public interface RSARepository extends JpaRepository<RSAEntity, Integer> {
}

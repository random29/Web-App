package ru.itpark.noteswithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.noteswithsecurity.entity.MilRabEntity;
import ru.itpark.noteswithsecurity.entity.SoloveiEntity;

public interface SoloveiRepository extends JpaRepository<SoloveiEntity, Integer> {
}

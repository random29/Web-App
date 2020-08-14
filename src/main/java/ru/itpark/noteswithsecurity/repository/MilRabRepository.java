package ru.itpark.noteswithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.noteswithsecurity.entity.MilRabEntity;
import ru.itpark.noteswithsecurity.entity.RaeEntity;

public interface MilRabRepository extends JpaRepository<MilRabEntity, Integer> {
}

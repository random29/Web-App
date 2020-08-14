package ru.itpark.noteswithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.noteswithsecurity.entity.GammingEntity;
import ru.itpark.noteswithsecurity.entity.RaeEntity;

public interface RaeRepository extends JpaRepository<RaeEntity, Integer> {
}

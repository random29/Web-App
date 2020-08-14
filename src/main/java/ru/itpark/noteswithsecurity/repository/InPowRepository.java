package ru.itpark.noteswithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.noteswithsecurity.entity.InPowEntity;
import ru.itpark.noteswithsecurity.entity.RaeEntity;

public interface InPowRepository extends JpaRepository<InPowEntity, Integer> {
}

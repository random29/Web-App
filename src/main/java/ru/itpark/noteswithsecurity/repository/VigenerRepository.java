package ru.itpark.noteswithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.noteswithsecurity.entity.VigenerEntity;

public interface VigenerRepository extends JpaRepository<VigenerEntity, Integer> {
}

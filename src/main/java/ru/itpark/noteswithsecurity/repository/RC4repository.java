package ru.itpark.noteswithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.noteswithsecurity.entity.RC4Entity;
import ru.itpark.noteswithsecurity.entity.RaeEntity;

public interface RC4repository extends JpaRepository<RC4Entity, Integer> {
}

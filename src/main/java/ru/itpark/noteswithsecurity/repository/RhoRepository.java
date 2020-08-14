package ru.itpark.noteswithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.noteswithsecurity.entity.DiffiHellEntity;
import ru.itpark.noteswithsecurity.entity.RhoEntity;

public interface RhoRepository extends JpaRepository<RhoEntity, Integer> {
}

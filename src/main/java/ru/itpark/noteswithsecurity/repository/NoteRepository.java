package ru.itpark.noteswithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.noteswithsecurity.entity.NoteEntity;

import java.util.List;

public interface NoteRepository
        extends JpaRepository<NoteEntity, Integer> { // Integer - то, над чем написано @Id
    // boolean existsByOwnerIdAndId(int ownerId, int id);
    List<NoteEntity> findAllByNameContainsIgnoreCaseOrderByName(String name);
    List<NoteEntity> findAllById(int id);
    void deleteById(int id);
    List<NoteEntity> findAllById(List<Integer> integers);
}

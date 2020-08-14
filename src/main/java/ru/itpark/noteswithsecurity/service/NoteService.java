package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.NoteEntity;
import ru.itpark.noteswithsecurity.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<NoteEntity> findAll() {
        return repository.findAll();
    }

    public NoteEntity findById(int id) {
        return repository.findById(id)
                .orElseThrow(); // TODO: throw specific exception
    }

    public List<NoteEntity> findAllById(List<Integer> integers){
//        List<NoteEntity> list = new ArrayList<>();
//        for (int i = 0; i < integers.size(); i++) {
//            NoteEntity entity = repository.findById(integers.get(i));
//             list.add(repository.findById(integers.get(i)));
//
//        }
//        return list
        return repository.findAllById(integers);
    }

    public List<NoteEntity> findByName(String name) {
        return repository.findAllByNameContainsIgnoreCaseOrderByName(name);
    }

    public NoteEntity getByIdOrEmpty(int id) {
//        if (id == 0) {
//            return repository.getById(id)
//                    .orElse(new ProductEntity());
//        }
//
//        return getById(id);
        return repository.findById(id)
                .orElse(new NoteEntity());
    }
    public void removeById(int id) {
        System.out.println("qweqweqweqwe");
        repository.deleteById(id);
    }

    public void add(NoteEntity entity) {
        repository.save(entity); // TODO: add edit functionality
    }

    public void edit(NoteEntity entity) {
//        Optional<NoteEntity> noteEntity = repository.findById(entity.getId());
//        noteEntity
        repository.save(entity);
    }
}

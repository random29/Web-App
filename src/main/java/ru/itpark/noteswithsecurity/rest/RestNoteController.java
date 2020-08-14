package ru.itpark.noteswithsecurity.rest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.noteswithsecurity.entity.NoteEntity;
import ru.itpark.noteswithsecurity.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class RestNoteController {
    private final NoteService service;

    public RestNoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<NoteEntity> getAll() {
        return service.findAll();
    }

    @PostMapping
    public void add(@RequestBody NoteEntity entity) {

    }
}

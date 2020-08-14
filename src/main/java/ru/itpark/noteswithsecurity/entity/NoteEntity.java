package ru.itpark.noteswithsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntity {
    @Id
    @GeneratedValue
    private int id;
    // private int ownerId; -> id'шник пользователя, который добавил, надо записывать руками
    private String name;
    @Lob
    @Column(name="CONTENT", length=10245)
    private String content;
    @ElementCollection
    @Column(name="COMMENTS", length=10245)
    private List<String> comments;
    private int price;
}

package ru.itpark.noteswithsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CaesarEntity {
    @Id
    private int id;
    @Lob
    @Column(name = "CONTENT", length = 10245)
    private String content;
    private String plus;
    private int key;
    @Lob
    @Column(name = "RESULT", length = 10245)
    private String result;
}

package ru.itpark.noteswithsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GammingEntity {
    @Id
    private int id;
    @Lob
    @Column(name = "CONTENT", length = 10245)
    private String content;
    private String key;
    @Lob
    @Column(name = "RESULT", length = 10245)
    private String result;
}

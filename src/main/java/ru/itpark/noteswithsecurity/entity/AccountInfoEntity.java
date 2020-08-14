package ru.itpark.noteswithsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoEntity {
    @Id
    @GeneratedValue
    private int id;
    private int money;
    private String name;
    private String surname;
    private String groupID;
}

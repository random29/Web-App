package ru.itpark.noteswithsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MilRabEntity {
    @Id
    private int id;
    private BigInteger n;
    private int k;
    private String result;
}

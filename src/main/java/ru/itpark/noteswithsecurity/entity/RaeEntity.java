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
public class RaeEntity {
    @Id
    private int id;
    private BigInteger a;
    private BigInteger b;
    private String d;
    private String x;
    private String y;
}
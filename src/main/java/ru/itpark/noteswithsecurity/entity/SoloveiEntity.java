package ru.itpark.noteswithsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.math.BigInteger;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SoloveiEntity {
    @Id
    private int id;
    @Lob
    @Column(name = "N", length =  100000)
    private BigInteger n;
    private int k;
    private String result;
    private int razm;
    private Boolean res;
}

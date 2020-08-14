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
public class RC4Entity {
    @Id
    private int id;
    @Lob
    @Column(name = "KEY", length =  1000000)
    private BigInteger key;
    @Lob
    @Column(name = "CIP", length =  1000000)
    private String cip;
}

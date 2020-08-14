package ru.itpark.noteswithsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RhoEntity {
    @Id
    private int id;
    @ElementCollection
    @Column(name="NUMBERS", length=1000000)
    private List<String> numbers;
    @Lob
    @Column(name = "X", length =  1000000)
    private BigInteger x;
}

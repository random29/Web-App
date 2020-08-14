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
public class DiffiHellEntity {
    @Id
    private int id;
    @Lob
    @Column(name = "P", length =  100000)
    private BigInteger p;
    @Lob
    @Column(name = "G", length =  100000)
    private BigInteger g;
    @Lob
    @Column(name = "A", length =  100000)
    private BigInteger A;
    @Lob
    @Column(name = "B", length =  100000)
    private BigInteger B;
    @Lob
    @Column(name = "KEY1", length =  100000)
    private BigInteger key1;
    @Lob
    @Column(name = "KEY2", length =  100000)
    private BigInteger key2;
    @Lob
    @Column(name = "K", length =  100000)
    private BigInteger K;
    private int razm1;
}

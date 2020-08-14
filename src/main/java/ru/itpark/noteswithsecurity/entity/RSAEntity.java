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
public class RSAEntity {
    @Id
    private int id;
    private int bitlen;
    @Lob
    @Column(name = "N", length =  100000)
    private BigInteger n;
    @Lob
    @Column(name = "E", length =  100000)
    private BigInteger e;
    @Lob
    @Column(name = "D", length =  100000)
    private BigInteger d;
    @Lob
    @Column(name = "FI", length =  100000)
    private BigInteger fi;
    @Lob
    @Column(name = "P", length =  100000)
    private BigInteger p;
    @Lob
    @Column(name = "Q", length =  100000)
    private BigInteger q;
    @Lob
    @Column(name = "DECRYPTEDTEXT", length = 102450)
    private String decryptedText;
    @Lob
    @Column(name = "CIPHERTEXT", length = 102450)
    private String cipherText;
}

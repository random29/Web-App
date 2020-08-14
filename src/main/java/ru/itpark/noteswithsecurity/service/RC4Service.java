package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.GammingEntity;
import ru.itpark.noteswithsecurity.entity.RC4Entity;
import ru.itpark.noteswithsecurity.entity.RhoEntity;
import ru.itpark.noteswithsecurity.repository.RC4repository;

@Service
public class RC4Service {
    private final RC4repository repository;

    public RC4Service(RC4repository repository) {
        this.repository = repository;
    }

    public RC4Entity findById() {
        return repository.findById(3)
                .orElseThrow(); // TODO: throw specific exception
    }

    private final byte[] S = new byte[256];
    private final byte[] T = new byte[256];
    private int keylen;

    public void RC4(final byte[] key) throws IllegalAccessException {
        if (key.length < 1 || key.length > 256) {
            throw new IllegalAccessException("Только 256");
        } else {
            keylen = key.length;
            for (int i = 0; i < 256; i++) {
                S[i] = (byte) i;
                T[i] = key[i % keylen];
            }
            int j = 0;
            byte tmp;
            for (int i = 0; i < 256; i++) {
                j = (j + S[i] + T[i]) & 0xFF;
                tmp = S[j];
                S[j] = S[i];
                S[i] = tmp;
            }
        }
    }

    public byte[] encrypt(final byte[] plaintext) {
        final byte[] ciphertext = new byte[plaintext.length];
        int i = 0, j = 0, k, t;
        byte tmp;
        for (int counter = 0; counter < plaintext.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            tmp = S[j];
            S[j] = S[i];
            S[i] = tmp;
            t = (S[i] + S[j]) & 0xFF;
            k = S[t];
            ciphertext[counter] = (byte) (plaintext[counter] ^ k);
        }
        return ciphertext;
    }

    public byte[] decrypt(final byte[] ciphertext) {
        return encrypt(ciphertext);
    }
    public void save(RC4Entity gammingEntity) {
        repository.save(gammingEntity);
    }


}

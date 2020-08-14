package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.VigenerEntity;
import ru.itpark.noteswithsecurity.repository.VigenerRepository;

@Service

public class VigenerService {
    private final VigenerRepository repository;

    public VigenerService(VigenerRepository repository) {
        this.repository = repository;
    }

    public VigenerEntity findById() {
        return repository.findById(3)
                .orElseThrow(); // TODO: throw specific exception
    }

    public void encrypt(String text, String key) {
        char rus = 'а';
        char ruse = 'я';
        char rusUp = 'А';
        char ruseUp = 'Я';
        char eng = 'a';
        char engUp = 'A';
        char enge = 'z';
        char engeUp = 'Z';
        char dig = '0';
        char dige = '9';
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if ((text.toCharArray()[0] >= rus) && (text.toCharArray()[0] <= ruse)) {
                int alphabetSmeshenie = 1072;
                int alphabet = 33;
                result += (char) (((text.charAt(i) + key.charAt(i % key.length()) - 2 * alphabetSmeshenie) % alphabet) + alphabetSmeshenie);
            }
            if ((text.toCharArray()[0] >= rusUp) && (text.toCharArray()[0] <= ruseUp)) {
                int alphabetSmeshenie = 1040;
                int alphabet = 33;
                result += (char) (((text.charAt(i) + key.charAt(i % key.length()) - 2 * alphabetSmeshenie) % alphabet) + alphabetSmeshenie);
            }
            if ((text.toCharArray()[0] >= eng) && (text.toCharArray()[0] <= enge)) {
                int alphabetSmeshenie = 97;
                int alphabet = 26;
                result += (char) (((text.charAt(i) + key.charAt(i % key.length()) - 2 * alphabetSmeshenie) % alphabet) + alphabetSmeshenie);
            }
            if ((text.toCharArray()[0] >= engUp) && (text.toCharArray()[0] <= engeUp)) {
                int alphabetSmeshenie = 65;
                int alphabet = 26;
                result += (char) (((text.charAt(i) + key.charAt(i % key.length()) - 2 * alphabetSmeshenie) % alphabet) + alphabetSmeshenie);
            }
            if ((text.toCharArray()[0] >= dig) && (text.toCharArray()[0] <= dige)) {
                int alphabetSmeshenie = 48;
                int alphabet = 10;
                result += (char) (((text.charAt(i) + key.charAt(i % key.length()) - 2 * alphabetSmeshenie) % alphabet) + alphabetSmeshenie);
            }
        }
        System.out.println(result);
        VigenerEntity entity = new VigenerEntity();
        entity.setId(3);
        entity.setContent(text);
        entity.setKey(key);
        entity.setResult(result);
        repository.save(entity);
    }
    public void save(VigenerEntity vigenerEntity){
        repository.save(vigenerEntity);
    }
    public void decrypt(String text, String key) {
        char rus = 'а';
        char ruse = 'я';
        char rusUp = 'А';
        char ruseUp = 'Я';
        char eng = 'a';
        char engUp = 'A';
        char enge = 'z';
        char engeUp = 'Z';
        char dig = '0';
        char dige = '9';
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if ((text.toCharArray()[0] >= rus) && (text.toCharArray()[0] <= ruse)) {
                int alphabetSmeshenie = 1072;
                int alphabet = 33;
                result += (char) (((text.charAt(i) - key.charAt(i % key.length()) + alphabet) % alphabet) + alphabetSmeshenie);
            }
            if ((text.toCharArray()[0] >= rusUp) && (text.toCharArray()[0] <= ruseUp)) {
                int alphabetSmeshenie = 1040;
                int alphabet = 33;
                result += (char) (((text.charAt(i) - key.charAt(i % key.length()) + alphabet) % alphabet) + alphabetSmeshenie);
            }
            if ((text.toCharArray()[0] >= eng) && (text.toCharArray()[0] <= enge)) {
                int alphabetSmeshenie = 97;
                int alphabet = 26;
                result += (char) (((text.charAt(i) - key.charAt(i % key.length()) + alphabet) % alphabet) + alphabetSmeshenie);
            }
            if ((text.toCharArray()[0] >= engUp) && (text.toCharArray()[0] <= engeUp)) {
                int alphabetSmeshenie = 65;
                int alphabet = 26;
                result += (char) (((text.charAt(i) - key.charAt(i % key.length()) + alphabet) % alphabet) + alphabetSmeshenie);
            }
            if ((text.toCharArray()[0] >= dig) && (text.toCharArray()[0] <= dige)) {
                int alphabetSmeshenie = 48;
                int alphabet = 10;
                result += (char) (((text.charAt(i) - key.charAt(i % key.length()) + alphabet) % alphabet) + alphabetSmeshenie);
            }
        }
        System.out.println(result);
        VigenerEntity entity = new VigenerEntity();
        entity.setId(3);
        entity.setContent(text);
        entity.setKey(key);
        entity.setResult(result);
        repository.save(entity);
    }
}

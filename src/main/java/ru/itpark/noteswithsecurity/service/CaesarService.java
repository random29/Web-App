package ru.itpark.noteswithsecurity.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.CaesarEntity;
import ru.itpark.noteswithsecurity.repository.CaesarRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class CaesarService {
    private final CaesarRepository repository;

    public CaesarService(CaesarRepository repository) {
        this.repository = repository;
    }

    public CaesarEntity findById() {
        return repository.findById(3)
                .orElseThrow(); // TODO: throw specific exception
    }

    public void solve(String text, int key, String test) {
        String input = text;
        List<Character> result = new ArrayList();
        boolean plus;
        if (test.contains("+")) {
            plus = true;
        } else
            plus = false;
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
        for (char i : input.toCharArray()) {
            int keyTemp = key;
            if ((i >= rus) && (i <= ruse)) {
                while (keyTemp != 0) {
                    if (plus == true) {
                        i++;
                        if (i > ruse)
                            i = rus;
                        keyTemp--;
                    } else {
                        i--;
                        if (i < rus)
                            i = ruse;
                        keyTemp--;
                    }
                }
                result.add(i);
            } else if ((i >= rusUp) && (i <= ruseUp)) {
                while (keyTemp != 0) {
                    if (plus == true) {
                        i++;
                        if (i > ruseUp)
                            i = rusUp;
                        keyTemp--;
                    } else {
                        i--;
                        if (i < rusUp)
                            i = ruseUp;
                        keyTemp--;
                    }
                }
                result.add(i);
            } else if ((i >= eng) && (i <= enge)) {
                while (keyTemp != 0) {
                    if (plus == true) {
                        i++;
                        if (i > enge)
                            i = eng;
                        keyTemp--;
                    } else {
                        i--;
                        if (i < eng)
                            i = enge;
                        keyTemp--;
                    }
                }
                result.add(i);
            } else if ((i >= engUp) && (i <= engeUp)) {
                while (keyTemp != 0) {
                    if (plus == true) {
                        i++;
                        if (i > engeUp)
                            i = engUp;
                        keyTemp--;
                    } else {
                        i--;
                        if (i < engUp)
                            i = engeUp;
                        keyTemp--;
                    }
                }
                result.add(i);
            } else if ((i >= dig) && (i <= dige)) {
                while (keyTemp != 0) {
                    if (plus == true) {
                        i++;
                        if (i > dige)
                            i = dig;
                        keyTemp--;
                    } else {
                        i--;
                        if (i < dig)
                            i = dige;
                        keyTemp--;
                    }
                }
                result.add(i);
            }
        }
        CaesarEntity entity = new CaesarEntity();
        entity.setId(3);
        entity.setContent(text);
        entity.setKey(key);
        entity.setPlus(test);
        StringBuilder builder = new StringBuilder(result.size());
        for (Character ch : result) {
            builder.append(ch);
        }
        entity.setResult(builder.toString());
        repository.save(entity);
    }
}

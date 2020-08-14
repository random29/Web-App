package ru.itpark.noteswithsecurity.service;

import org.springframework.stereotype.Service;
import ru.itpark.noteswithsecurity.entity.GammingEntity;
import ru.itpark.noteswithsecurity.entity.VigenerEntity;
import ru.itpark.noteswithsecurity.repository.GammingRepository;
import ru.itpark.noteswithsecurity.repository.VigenerRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class GammingService {
    private final GammingRepository repository;

    public GammingService(GammingRepository repository) {
        this.repository = repository;
    }

    public GammingEntity findById() {
        return repository.findById(3)
                .orElseThrow(); // TODO: throw specific exception
    }

    public void encryptRandom(String text) {
        List forKey = new ArrayList();
        List list = new ArrayList();
        String rofl = "";
        int r = 0;
        for (char c : text.toCharArray()) {
            String b = Integer.toBinaryString(c);
            String res = "";
            if (b.length() < 11) {
                int temp = 11 - b.length() - 1;
                String lol = "";
                while (temp >= 0) {
                    lol += '0';
                    temp--;
                }
                res = lol + b;
            } else res = b;
            System.out.println(res);
            list.add(res);
        }
//
//        for (char o : text.toCharArray()) {
//            rofl += o;
//            r++;
//            if (r == 11) {
//                r = 0;
//                list.add(rofl);
//                rofl = "";
//            }
//        }
//        if (r != 0) {
//            int h = rofl.length();
//            while (h < 11) {
//                rofl += '0';
//                h++;
//            }
//            list.add(rofl);
//        }
        int i = 0;
        int countOnes = 0;
        int countZeros = 0;
        while (i < 12) {
            int j = 0;
            String key = "";
            while (j < 11) {
                long temp = Math.round(Math.random());
                if (countOnes == 66) {
                    temp = 0;
                } else if (countZeros == 66) {
                    temp = 1;
                }
                if (temp == 1) {
                    countOnes++;
                } else if (temp == 0) {
                    countZeros++;
                }
                key += temp;
                j++;
            }
            forKey.add(key);
            i++;
        }
        List resFirst = new ArrayList();
        int count = 0;
        for (Object o : list) {
            String te = o.toString();
            String keyLen = forKey.get(count).toString();
            int j = 0;
            String result = "";
            while (j < te.length()) {
                String t = "";
                if ((te.toCharArray()[j] == '0' && keyLen.toCharArray()[j] == '0') || (te.toCharArray()[j] == '1' && keyLen.toCharArray()[j] == '1')) {
                    t += '0';
                } else if ((te.toCharArray()[j] == '1' && keyLen.toCharArray()[j] == '0') || (te.toCharArray()[j] == '0' && keyLen.toCharArray()[j] == '1')) {
                    t += '1';
                }
                result += t;
                j++;
            }
            resFirst.add(result);
            count++;
            if (count == forKey.size()) {
                count = 0;
            }
        }
        String vol = "";
        for (Object o : resFirst) {
            String t = o.toString();
            vol += (char) Integer.parseInt(t, 2);
        }
        count = 0;
        String key = "";
        for (Object o : forKey) {
            key += o.toString();
        }
        GammingEntity entity = new GammingEntity();
        entity.setId(3);
        entity.setContent(text);
        entity.setKey(key);
        entity.setResult(vol);
        repository.save(entity);
    }

    public void encryptRandomNoGen() {
        System.out.println("KEK");
        List forKey = new ArrayList();
        List list = new ArrayList();
        GammingEntity gammingEntity = repository.getOne(3);
        String key1 = gammingEntity.getKey();
        System.out.println(key1);
        String text = gammingEntity.getContent();
        for (char c : text.toCharArray()) {
            String b = Integer.toBinaryString(c);
            String res = "";
            if (b.length() < 11) {
                int temp = 11 - b.length() - 1;
                String lol = "";
                while (temp >= 0) {
                    lol += '0';
                    temp--;
                }
                res = lol + b;
            } else res = b;
            list.add(res);
        }
        int i = 0;
        int r = 0;
        System.out.println(list);
        String rofl = "";
        for (char d : key1.toCharArray()) {
            rofl += d;
            r++;
            if (r == 11) {
                r = 0;
                forKey.add(rofl);
                rofl = "";
            }
        }
        System.out.println(forKey);
        List resFirst = new ArrayList();
        int count = 0;
        for (Object o : list) {
            String te = o.toString();
            String keyLen = forKey.get(count).toString();
            int j = 0;
            String result = "";
            while (j < te.length()) {
                String t = "";
                if ((te.toCharArray()[j] == '0' && keyLen.toCharArray()[j] == '0') || (te.toCharArray()[j] == '1' && keyLen.toCharArray()[j] == '1')) {
                    t += '0';
                } else if ((te.toCharArray()[j] == '1' && keyLen.toCharArray()[j] == '0') || (te.toCharArray()[j] == '0' && keyLen.toCharArray()[j] == '1')) {
                    t += '1';
                }
                result += t;
                j++;
            }
            resFirst.add(result);
            count++;
            if (count == forKey.size()) {
                count = 0;
            }
        }
        String vol = "";
        for (Object o : resFirst) {
            String t = o.toString();
            vol += (char) Integer.parseInt(t, 2);
        }
        count = 0;
        GammingEntity entity = new GammingEntity();
        entity.setId(3);
        entity.setContent(text);
        entity.setKey(key1);
        entity.setResult(vol);
        repository.save(entity);
    }

    public void encrypt(String text, String key) {
        List list = new ArrayList();
        List forKey = new ArrayList();
        for (char c : text.toCharArray()) {
            String b = Integer.toBinaryString(c);
            String res = "";
            if (b.length() < 11) {
                int temp = 11 - b.length() - 1;
                String lol = "";
                while (temp >= 0) {
                    lol += '0';
                    temp--;
                }
                res = lol + b;
            } else res = b;
            System.out.println(res);
            list.add(res);
        }
//        System.out.println((char)Integer.parseInt((String)list.get(0), 2));
        for (char c : key.toCharArray()) {
            String b = Integer.toBinaryString(c);
            String res = "";
            if (b.length() < 11) {
                int temp = 11 - b.length() - 1;
                String lol = "";
                while (temp >= 0) {
                    lol += '0';
                    temp--;
                }
                res = lol + b;
            } else res = b;
            forKey.add(res);
        }
        List resFirst = new ArrayList();
        int count = 0;
        for (Object o : list) {
            String te = o.toString();
            String keyLen = forKey.get(count).toString();
            int i = 0;
            String result = "";
            while (i < te.length()) {
                String t = "";
                if ((te.toCharArray()[i] == '0' && keyLen.toCharArray()[i] == '0') || (te.toCharArray()[i] == '1' && keyLen.toCharArray()[i] == '1')) {
                    t += '0';
                } else if ((te.toCharArray()[i] == '1' && keyLen.toCharArray()[i] == '0') || (te.toCharArray()[i] == '0' && keyLen.toCharArray()[i] == '1')) {
                    t += '1';
                }
                result += t;
                i++;
            }
            resFirst.add(result);
            count++;
            if (count == forKey.size()) {
                count = 0;
            }
        }
        String vol = "";
        for (Object o : resFirst) {
            String t = o.toString();
            vol += (char) Integer.parseInt(t, 2);
        }
        count = 0;
        GammingEntity entity = new GammingEntity();
        entity.setId(3);
        entity.setContent(text);
        entity.setKey(key);
        entity.setResult(vol);
        repository.save(entity);
    }

    public void decrypt(String text, String key) {
        List list = new ArrayList();
        List forKey = new ArrayList();
        for (char c : text.toCharArray()) {
            String b = Integer.toBinaryString(c);
            String res = "";
            if (b.length() < 11) {
                int temp = 11 - b.length() - 1;
                String lol = "";
                while (temp >= 0) {
                    lol += '0';
                    temp--;
                }
                res = lol + b;
            } else res = b;
            list.add(res);
        }
//        System.out.println((char)Integer.parseInt((String)list.get(0), 2));
        for (char c : key.toCharArray()) {
            String b = Integer.toBinaryString(c);
            String res = "";
            if (b.length() < 11) {
                int temp = 11 - b.length() - 1;
                String lol = "";
                while (temp >= 0) {
                    lol += '0';
                    temp--;
                }
                res = lol + b;
            } else res = b;
            forKey.add(res);
        }
        List resFirst = new ArrayList();
        int count = 0;
        for (Object o : list) {
            String te = o.toString();
            String keyLen = forKey.get(count).toString();
            int i = 0;
            String result = "";
            while (i < te.length()) {
                String t = "";
                if ((te.toCharArray()[i] == '0' && keyLen.toCharArray()[i] == '0') || (te.toCharArray()[i] == '1' && keyLen.toCharArray()[i] == '1')) {
                    t += '0';
                } else if ((te.toCharArray()[i] == '1' && keyLen.toCharArray()[i] == '0') || (te.toCharArray()[i] == '0' && keyLen.toCharArray()[i] == '1')) {
                    t += '1';
                }
                result += t;
                i++;
            }
            resFirst.add(result);
            count++;
            if (count == forKey.size()) {
                count = 0;
            }
        }
        String vol = "";
        for (Object o : resFirst) {
            String t = o.toString();
            vol += (char) Integer.parseInt(t, 2);
        }
        count = 0;
        GammingEntity entity = new GammingEntity();
        entity.setId(3);
        entity.setContent(text);
        entity.setKey(key);
        entity.setResult(vol);
        repository.save(entity);
    }

    public void save(GammingEntity gammingEntity) {
        repository.save(gammingEntity);
    }

}

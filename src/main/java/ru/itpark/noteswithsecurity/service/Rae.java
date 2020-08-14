package ru.itpark.noteswithsecurity.service;

import java.math.BigInteger;
import java.util.List;

class Rae {
    Rae(BigInteger d, BigInteger x, BigInteger y) {
        this.d = d;
        this.x = x;
        this.y = y;
    }

    Rae() {
    }

    BigInteger d;
    BigInteger x;
    BigInteger y;

    Rae doRae(BigInteger a, BigInteger b) {
        if (a.compareTo(b) == -1) {
            BigInteger temp = b;
            b = a;
            a = temp;
        }
        Rae last = new Rae(a, BigInteger.valueOf(1), BigInteger.valueOf(0));
        Rae update;
        if (b == BigInteger.valueOf(0)) {
            return last;
        }
        update = doRae(b, a.mod(b));
        last = new Rae();
        last.d = update.d;
        last.x = update.y;
        last.y = update.x.subtract(a.divide(b).multiply(update.y));
        return last;
    }

    BigInteger inPow(BigInteger a, BigInteger b, BigInteger n) {
        String temp = Integer.toBinaryString(Integer.parseInt(b.toString()));
        BigInteger[] result = new BigInteger[temp.length()];
        result[0] = a;
        int i = 1;
        while (i < temp.length()) {
            if (temp.charAt(i) == '0') {
                result[i] = result[i - 1].multiply(result[i - 1]).mod(n);
            } else {
                result[i] = result[i - 1].multiply(result[i - 1]).multiply(result[0]).mod(n);
            }
            i++;
        }
        return result[temp.length() - 1];
    }
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return b.equals(BigInteger.ZERO) ? a : gcd(b, a.mod(b));
    }
}


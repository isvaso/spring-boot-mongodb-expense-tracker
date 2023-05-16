package com.isvaso.springbootmongodbexpensetracker.testutil;

import java.util.Random;

public class StringGenerator {

    public static String generate(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new Random();
        int charStart = 'A';
        int charEnd = 'Z';

        for (int i = 0; i < length; i++) {
            char tempChar = (char) random.nextInt(charStart, charEnd + 1);
            stringBuilder.append(tempChar);
        }

        return stringBuilder.toString();
    }
}

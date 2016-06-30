package com.raychen518.study.hibernate;

import java.util.Random;

public final class CommonsUtil {

    private CommonsUtil() {
    }

    public static Long generateRandomNumber() {
        Random random = new Random();
        return Math.abs(random.nextLong());
    }

    public static void main(String[] args) {
        System.out.println(generateRandomNumber());
        System.out.println(generateRandomNumber());
        System.out.println(generateRandomNumber());
        System.out.println(generateRandomNumber());
        System.out.println(generateRandomNumber());
    }

}

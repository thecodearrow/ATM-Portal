package com.pvbank.portal.utils;

import java.util.Random;
import java.util.UUID;

public class RandomGenerator {
    public static String getRandomNDigits(int n){
        return String.format("%0"+n+"d", new Random().nextInt((int) Math.pow(10,n)));
    }
    public static String getRandomString(){
        return UUID.randomUUID().toString();
    }
}

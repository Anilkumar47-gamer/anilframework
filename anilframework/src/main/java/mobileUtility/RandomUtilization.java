package mobileUtility;

import java.util.Random;

public class RandomUtilization {
public static String randomreturn() {
    Random num = new Random();
    int phnumber=num.nextInt(9000000) + 1000000; 
    return "867"+Integer.toString(phnumber);
}
}

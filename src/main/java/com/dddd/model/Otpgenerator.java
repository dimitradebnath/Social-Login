package com.dddd.model;
import java.util.*; 
//import java.util.Random;

public class Otpgenerator {
    public static String OTP()
    {
        System.out.println("Generating OTP using random() : ");
        System.out.print("You OTP is : ");
 
        // Using numeric values
        String numbers = "0123456789";
 
        // Using random method
        Random rndm_method = new Random();
 
        char[] otp = new char[6];
 
        for (int i = 0; i < 6; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] =
             numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        
        System.out.println(otp);
        return otp.toString();
    }
    /*public static void main(String[] args)
    {
        int length = 6;
        System.out.println(OTP(length));
    }*/
}

package service;

public class OTPChecker {
    public static boolean checker(String userInput, String realInput){
       return userInput.equals(realInput);
    }
}

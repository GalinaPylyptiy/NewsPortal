package com.epam.newsPortal.util;

public class PasswordHandler {

    private PasswordHandler() {
    }

    public static String reversePassword(String password){
        StringBuilder sb = new StringBuilder(password);
        return sb.reverse().toString();
    }

}

package com.example.blogpr.utility;

public class HeaderCheck {
    public static void main(String[] args) {

        String headerValue = "Bearer 654654654.6464654.9846551";
        String value = headerValue.substring(7);
        System.out.println(value);
    }
}

package com.example.blogpr.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PassEncryptor {
    public static void main(String[] args) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println("nikhil123: "+passwordEncoder.encode("nikhil123"));
        System.out.println("ramesh123: "+passwordEncoder.encode("ramesh123"));
        System.out.println("john123: "+passwordEncoder.encode("john123"));

    }
}

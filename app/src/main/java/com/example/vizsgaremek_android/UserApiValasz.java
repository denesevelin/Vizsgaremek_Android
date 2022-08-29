package com.example.vizsgaremek_android;

import java.util.List;

public class UserApiValasz extends ApiValasz<User>{
    public UserApiValasz(boolean error, String message, List<User> adatok) {
        super(error, message, adatok);
    }
}

package com.piratedropbox.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class Criptografia{
 
    public static String convertPasswordToMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
 
        BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
 
        return String.format("%32x", hash);
    }
    

}
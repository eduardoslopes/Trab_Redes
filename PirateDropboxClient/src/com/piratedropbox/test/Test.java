package com.piratedropbox.test;

import java.security.NoSuchAlgorithmException;

import com.piratedropbox.controller.Criptografia;

public class Test {

	public static void main(String[] args) {
		String senha1 = "Senha1";
        String senha2 = "Senha2";
 
        try {
            String senha1Criptografada = Criptografia.convertPasswordToMD5(senha1);
            String senha2Criptografada = Criptografia.convertPasswordToMD5(senha2);
 
            String senha1CriptografadaTeste = Criptografia.convertPasswordToMD5("Senha1");
 
            System.out.println("Senha 1 Criptografada: " + senha1Criptografada);
            System.out.println("Senha 2 Criptografada " + senha2Criptografada);
 
            System.out.println("senha1CriptografadaTeste: " + senha1Criptografada);
 
            if (senha1Criptografada.equals(senha1CriptografadaTeste)) {
                System.out.println("Compare OK");
            } else {
                System.out.println("Compare OK");
            }
 
            if (senha2Criptografada.equals(senha1Criptografada)) {
                System.out.println("Compare OK");
            } else {
                System.out.println("Compare OK");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
	}

}

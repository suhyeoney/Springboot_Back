package com.devops.demo.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;


public class EncDec {
	
	public EncDec() {} // Constructor
	
	public String encryptText(String text) { // Encrypting PlainText
		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		enc.setProvider(new BouncyCastleProvider());
		enc.setAlgorithm("PBEWithMD5AndDES");
		enc.setPassword("devops");
		String encryptedText = enc.encrypt(text);
		return encryptedText;
	}
	
	public String decryptText(String enc) { // Decrypting EncryptedText 
		// 복호화 method는 사용할 곳이 없는듯?합니다
		StandardPBEStringEncryptor dec = new StandardPBEStringEncryptor();
		dec.setProvider(new BouncyCastleProvider());
		dec.setAlgorithm("PBEWithMD5AndDES");
		dec.setPassword("devops");
		String decryptedText = dec.decrypt(enc);
		return decryptedText;
	}

	public static void main(String [] args) {
		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor(); 
		enc.setProvider(new BouncyCastleProvider());
		enc.setAlgorithm("PBEWithMD5AndDES");
		enc.setPassword("devops");
		
		String username = "master";
		String password = "12345678";
		
		System.out.println("encrypted username : " + enc.encrypt(username));
		System.out.println("encrypted password : " + enc.encrypt(password));
		
	}
	
//	StandardPBEStringEncryptor e = new StandardPBEStringEncryptor();
//	e.setPassword("devops1234");
//	e.setAlgorithm("PBEWithMD5AndDES");
//	e.setSaltGenerator(new StringFixedSaltGenerator("someFixedSalt"));
//	
//	String encryptedText = e.encrypt("master");
//	String plainText = e.decrypt(encryptedText);
//	
//	System.out.println("encryptedText : " + encryptedText);
//	System.out.println("plainText : " + plainText);
//	System.out.println(encryptedText.getClass().getName());
//	System.out.println(plainText.getClass().getName());
}

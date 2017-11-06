package br.com.sispet.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String criptografarSenha(String senha){
		
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
	        byte messageDigestSenhaAdmin[] = algorithm.digest(senha.getBytes("UTF-8"));
	         
	        StringBuilder hexStringSenhaAdmin = new StringBuilder();
	        for (byte b : messageDigestSenhaAdmin) {
	                 hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
	        }

	        return hexStringSenhaAdmin.toString();

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return senha;
		}
	}
	
	public static String getDataHoraCorrente() { 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = new Date(); 
		return dateFormat.format(date); 
	}

}
package com.yuanchuangyun.secrity;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;

//extends Encrypt implements IFileEncrypt
public class RSAEncrypt {

	 KeyPairGenerator keyPairGen;

	 KeyPair keyPair;

	 RSAPrivateKey privateKey;

	 RSAPublicKey publicKey;

	 public RSAEncrypt(String strPrivateKey) {
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(512);
			keyPair = keyPairGen.generateKeyPair();
			// Generate keys
			privateKey = (RSAPrivateKey) keyPair.getPrivate();
			publicKey = (RSAPublicKey) keyPair.getPublic();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void encryptFile(String filepath, String encryptfilepath) {
		try {
			File file = new File(filepath);
			File newFile = new File(encryptfilepath);

			InputStream is = new FileInputStream(file);
			OutputStream os = new FileOutputStream(newFile);

			byte[] bytes = new byte[53];
			while (is.read(bytes) > 0) {
				byte[] e = encrypt(bytes);
				bytes = new byte[53];
				os.write(e, 0, e.length);
			}
			os.close();
			is.close();
			System.out.println("write success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void decryptFile(String encryptfilepath, String decryptfilepath) {
		try {
			File file1 = new File(encryptfilepath);
			File newFile1 = new File(decryptfilepath);
			
			InputStream is = new FileInputStream(file1);
			OutputStream os = new FileOutputStream(newFile1);
			byte[] bytes1 = new byte[64];
			while (is.read(bytes1) > 0) {
				byte[] de = decrypt(bytes1);
				bytes1 = new byte[64];
				os.write(de, 0, de.length);
			}
			os.close();
			is.close();
			System.out.println("write success");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** */
	/**
	 * * Encrypt String. *
	 * 
	 * @return byte[]
	 */
	protected byte[] encrypt(byte[] obj) {
		if (publicKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				return cipher.doFinal(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	protected String encrypt(String obj) {
		if (publicKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				return cipher.doFinal(obj.getBytes()).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/** */
	/**
	 * * Basic decrypt method *
	 * 
	 * @return byte[]
	 */
	protected byte[] decrypt(byte[] obj) {
		if (privateKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				return cipher.doFinal(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	protected String decrypt(String obj) {
		if (privateKey != null) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				return cipher.doFinal(obj.getBytes()).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

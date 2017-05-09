package com.yuanchuangyun.secrity;

import java.io.File;
import java.util.ArrayList;

public class FileCryptographyHelper {
	public static void main(String args[]) throws Exception {
		String privatekey = "1234567";
		DESEncrypt2 encrypt = new DESEncrypt2(privatekey);
		//DESEncrypt encrypt = new DESEncrypt(privatekey);
		//RSAEncrypt encrypt = new RSAEncrypt(privatekey);

		ArrayList extionsionList = new ArrayList();
		extionsionList.add("txt");
		extionsionList.add("doc");
		extionsionList.add("wmv");
		extionsionList.add("rar");

		for (Object extionsion : extionsionList) {
			String filename = "test";
			String filepath = "D:\\test\\" + filename + "." + extionsion;
			String encryptfilepath = filepath + "_encrypt_"
					+ encrypt.getClass().getSimpleName() + "." + extionsion;
			String decryptfilepath = filepath + "_decrypt_"
					+ encrypt.getClass().getSimpleName() + "." + extionsion;
			// DES 加密文件
			encrypt.encryptFile(filepath, encryptfilepath);
			// DES 解密文件
			encrypt.decryptFile(encryptfilepath, decryptfilepath);
		}
		
		
		 String str1 = " 要加密的字符串 test";
		 // DES 加密字符串
		 String str2 = encrypt.encrypt(str1);
		 // DES 解密字符串
		 String deStr = encrypt.decrypt(str2);

		 System.out.println(" 加密前： " + str1);
		 System.out.println(" 加密后： " + str2);
		 System.out.println(" 解密后： " + deStr);
	}
}

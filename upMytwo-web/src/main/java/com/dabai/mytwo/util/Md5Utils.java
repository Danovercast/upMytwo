package com.dabai.mytwo.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**  
* @author dabai: 

* 类说明  md5加密 指定加密明文和 盐  加密次数
*/
public class Md5Utils {

	public static String toMd5(String password,String salt,int count){
		Md5Hash toMd5=new Md5Hash(password,salt,count);
		return toMd5.toString();
	}
}

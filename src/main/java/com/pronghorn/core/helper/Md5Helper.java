package com.pronghorn.core.helper;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author:GuangKai Wang
 * @Description:用于MD5加密解密
 * @Date: Create in 17:15 2019-06-03
 * @Modified By: 17:15 2019-06-03
 */
public class Md5Helper {
    public static final String md5_key = "ProngHorn";

    /**
     * @param str 要加密的字符
     * @Description MD5加密
     */
    public static String encryptionMd5(String str) throws NoSuchAlgorithmException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        StringBuilder builder = new StringBuilder(md5_key);
        builder.append(str);
        //加密后的字符串
        String newStr = new BASE64Encoder().encode(md5.digest(builder.toString().getBytes()));
        return newStr;
    }
}

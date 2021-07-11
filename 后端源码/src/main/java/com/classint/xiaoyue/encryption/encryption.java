package com.classint.xiaoyue.encryption;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/** MD5加密解密工具类 */
public class encryption {

  /**
   * 普通MD5加密 01
   *
   * <p>
   */
  public static String getStrMD5(String inStr) {
    // 获取MD5实例
    MessageDigest md5;
    try {
      md5 = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      System.out.println(e);
      return "";
    }
    // 将加密字符串转换为字符数组
    char[] charArray = inStr.toCharArray();
    byte[] byteArray = new byte[charArray.length];
    // 开始加密
    for (int i = 0; i < charArray.length; i++) byteArray[i] = (byte) charArray[i];
    byte[] digest = md5.digest(byteArray);
    StringBuilder sb = new StringBuilder();
    for (byte b : digest) {
      int var = b & 0xff;
      if (var < 16) sb.append("0");
      sb.append(Integer.toHexString(var));
    }
    return sb.toString();
  }
  /**
   * MD5双重解密
   *
   * <p>
   */
  public static String getconvertMD5(String inStr) {
    char[] charArray = inStr.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      charArray[i] = (char) (charArray[i] ^ 't');
    }
    return String.valueOf(charArray);
  }

  /**
   * 使用Apache的Hex类实现Hex(16进制字符串和)和字节数组的互转
   *
   * <p>
   */
  @SuppressWarnings("unused")
  private static String md5Hex(String str) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digest = md.digest(str.getBytes());
      return new String(new Hex().encode(digest));
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e);
      return "";
    }
  }

  /**
   * 生成盐
   *
   * <p>
   */
  public static String makeSalt(long id, Date date) {
    // 生成一个16位的随机数
    String saltone = String.valueOf(Math.abs(date.getTime() * id));
    String salttwo = saltone.substring(0, 16);
    return String.format("%016d", Long.parseLong(salttwo));
    // 生成最终的加密盐
  }

  /**
   * 加盐MD5加密
   *
   * <p>
   */
  public static String getSaltMD5(String password, String salt) {
    String Salt = salt;
    password = md5Hex(password + Salt);
    char[] cs = new char[48];
    for (int i = 0; i < 48; i += 3) {
      cs[i] = password.charAt(i / 3 * 2);
      char c = Salt.charAt(i / 3);
      cs[i + 1] = c;
      cs[i + 2] = password.charAt(i / 3 * 2 + 1);
    }
    return String.valueOf(cs);
  }

  //    encryption md = new encryption();
  //    String strMD5 = new String("12345");
  //
  //        System.out.println("原始：" + strMD5);
  //        System.out.println("MD5后：" + md.getStrMD5(strMD5));
  //        System.out.println("加密的：" + md.getconvertMD5(strMD5));
  //        System.out.println("解密的：" + md.getconvertMD5(md.getconvertMD5(strMD5)));
  //    // 原文
  //    String plaintext = "huazai";
  //    // plaintext = "123456";
  //        System.out.println("原始：" + plaintext);
  //        System.out.println("普通MD5后：" + encryption.getStrMD5(plaintext));
  //
  //    // 获取加盐后的MD5值
  //        String salt=makeSalt();
  //        System.out.println("加盐：" + salt);
  //        String ciphertext = encryption.getSaltMD5(plaintext,salt);
  //        System.out.println("加盐后MD5：" + ciphertext);
  //        System.out.println("是否是同一字符串:" + encryption.getSaltverifyMD5(plaintext, ciphertext));
}

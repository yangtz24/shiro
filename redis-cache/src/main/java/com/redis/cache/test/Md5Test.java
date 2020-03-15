package com.redis.cache.test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author
 * @description Md5Test
 * @date 2019/11/26 17:56
 **/
public class Md5Test {

    public static void main(String[] args) {
        String url = "https://juejin.im/timeline";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update((url).getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md5.digest();
            System.out.println(bytes + "    ----");
            int i;
            StringBuilder stringBuilder = new StringBuilder();
            for (int offset = 0; offset < bytes.length; offset++) {
                i = bytes[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(i));
            }

            url = stringBuilder.toString();
            System.out.println("url = " + url);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}

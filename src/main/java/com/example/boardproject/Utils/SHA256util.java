package com.example.boardproject.Utils;

import lombok.extern.log4j.Log4j2;

import java.security.MessageDigest;

@Log4j2
public class SHA256util {
    public static final String ENCRYPTION_KEY = "SHA-256";

    public static String encryptSHA256(String str){
        String sha = null;
        MessageDigest sh;
        try{
            sh = MessageDigest.getInstance(ENCRYPTION_KEY);
            sh.update(str.getBytes());
            byte[] byteData = sh.digest();
            StringBuffer sb = new StringBuffer();
            for(byte byteDatum : byteData){
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100,16).substring(1));
            }
            sha=sb.toString();
        }
        catch(Exception e){
            log.error("encrypSHA256 error : {}",e.getMessage());
            sha=null;
        }

        return sha;
    }
}

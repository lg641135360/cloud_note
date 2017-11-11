package com.lg.cloud_note.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Create by MIO on 2017/11/04 12:25
 */
public class NoteUtil {

    public static String md5(String src){
        try {
            //获取MD5对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            //加密处理
            byte[] output = md.digest(src.getBytes());
            //利用Base64转换成字符串结果
            String str = Base64.encodeBase64String(output);
            return str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("加密失败",e);
        }
    }

    /**
     * 生成UUID
     * java中的UUID为36位不重复字符串(32位+4个"-"号)
     * 数据库中也有UUID为362位不重复字符串
     * 一般用于主键
     */
    public static String createId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    //测试
    public static void main(String[] args) {
        System.out.println("密码123456加密后:"+md5("123456"));
        System.out.println(md5("123456").length());
        System.out.println("自动生成UUID主键:"+createId());
        System.out.println(createId().length());
    }
}

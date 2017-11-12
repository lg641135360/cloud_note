package com.lg.cloud_note.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by MIO on 2017/11/11 20:36
 */
@Component     //扫描到spring容器
@Aspect        //将该类作为切面组件
public class ExceptionBean {
    //e是目标组件抛出的异常对象
    @AfterThrowing(throwing = "e",pointcut = "within(com.lg.cloud_note.service..*)")
    public void excute(Exception e){
        //将异常信息输入文件
        try {
            FileWriter fw = new FileWriter("D:\\DevDoc\\Projects\\cloud_note\\note_error.log",true);
            PrintWriter pw = new PrintWriter(fw);
            //利用pw对象写入异常信息
            Date time = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeStr = sdf.format(time);
            pw.println("****************************************");
            pw.println("*异常类型: " + e);
            pw.println("*异常时间: " + timeStr);
            pw.println("*****************************************");
            pw.println("************异常详细信息*************");
            e.printStackTrace(pw);
            pw.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println("记录异常失败");
        }
    }
}

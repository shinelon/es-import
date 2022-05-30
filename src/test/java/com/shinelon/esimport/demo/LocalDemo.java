package com.shinelon.esimport.demo;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName LocalDemo
 * @Author shinelon
 * @Date 16:51 2022/5/30
 * @Version 1.0
 **/
public class LocalDemo {

    @Test
    public void test(){
        LocalDateTime parse = LocalDateTime.parse("2022/12/24 21:26:13", DateTimeFormatter.ofPattern("yyyy/M/d H:m:s"));
        System.out.println(parse);
    }
}

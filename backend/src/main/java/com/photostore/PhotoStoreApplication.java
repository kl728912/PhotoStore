package com.photostore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PhotoStore 應用程序的主入口類
 * 這是一個攝影作品銷售平台的 Spring Boot 應用程序
 * 
 * @author Trae
 * @version 1.0
 */
@SpringBootApplication
public class PhotoStoreApplication {
    /**
     * 應用程序的主入口方法
     * 
     * @param args 命令行參數
     */
    public static void main(String[] args) {
        SpringApplication.run(PhotoStoreApplication.class, args);
    }
}
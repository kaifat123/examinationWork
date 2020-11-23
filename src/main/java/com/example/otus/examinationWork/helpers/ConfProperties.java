package com.example.otus.examinationWork.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfProperties {
    public static Logger logger = LogManager.getLogger(ConfProperties.class);
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;

    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/config/conf.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
            logger.info("Файл с настройками успешно загружен");
        } catch (IOException e) {
            logger.error("Файл с настройками отсутствует или не может быть прочитан");
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}

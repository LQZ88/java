package com.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.BooleanUtils;

public class PropertiesUtil {

    private static Properties properties = new Properties();

    private static PropertiesUtil propertiesUtil;

    private PropertiesUtil() {
    }

    private static void loadFile(String filename) {
        try {
            properties.load(PropertiesUtil.class.getResourceAsStream("/"
                    + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized PropertiesUtil createPropertiesUtil(
            String filename) {
        if (propertiesUtil == null) {
            propertiesUtil = new PropertiesUtil();
        }
        loadFile(filename);
        return propertiesUtil;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
	public  Properties getProperties() {
		return properties;
	}

    public static void main(String[] args) {
        PropertiesUtil pu = PropertiesUtil
                .createPropertiesUtil("database.properties");
        String singleLogin = pu.getProperty("single_login");
        System.out.println(BooleanUtils.toBoolean(singleLogin));
    }
}

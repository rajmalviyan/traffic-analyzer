package com.rthym.trafficanalyzer.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyLoader {
    private final static Logger LOGGER= LoggerFactory.getLogger(PropertyLoader.class);
    private final static String FILE_NAME =".\\src\\main\\resources\\application.properties";
    public static String getProperty(String key){
        return getRawValue(key);
    }

    public static  boolean propertyExists(String key){ return getRawValue(key)!=null;}

    private static String getRawValue(String key) {
        return prop.getProperty(key);
    }

    public  static int getValueOrDefault(String property, int defaultValue){
        return  getProperty(property)!=null ? getIntegerProperty(property): defaultValue;
    }

    public  static String getValueOrDefault(String property, String defaultValue){
        return  getProperty(property)!=null ? getProperty(property): defaultValue;
    }
    public  static String getValueOrDefault(String[] property, String defaultValue){
        return  property.length>1 ? property[1]: defaultValue;
    }

    public static Integer getIntegerProperty(String key) {
        return Integer.valueOf(getRawValue(key));
    }

    public static Boolean getBooleanProperty(String key) {
        return Boolean.valueOf(getRawValue(key));
    }

    public static Long getLongProperty(String key) {
        return Long.valueOf(getRawValue(key));
    }
    public  static Properties prop;
    static {
        prop= new Properties();
        FileInputStream inputStream=null;
        try {
            inputStream= new FileInputStream(FILE_NAME);
            if(inputStream == null){
                LOGGER.error("Sorry, Unable to find "+FILE_NAME);
            }else {
                prop.load(inputStream);
            }

        }catch (Exception e){
            LOGGER.error("Property Loader Error"+ e.getMessage());
        }
    }
}

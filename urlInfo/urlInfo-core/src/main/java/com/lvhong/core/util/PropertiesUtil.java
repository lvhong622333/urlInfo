package com.lvhong.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 用于静态工具类加载配置文件内容(一般使用在没有spring对象的场景)
 * @author lvhong
 */
public class PropertiesUtil{
	
	    private static Properties props;
	    
	    //加载配置文件
	    static{
	    		//访问配置文件的私有静态方法
	        loadProps();
	    }
	    
	    //同步加载配置文件
	    synchronized private static void loadProps(){
	        props = new Properties();
	        InputStream in = null;
	        try {
	            in = PropertiesUtil.class.getClassLoader().getResourceAsStream("appconfig.properties");
	            props.load(in);
	        } catch (Exception e) {
	        } finally {
	            try {
	                if(null != in) {
	                    in.close();
	                }
	            } catch (IOException e) {
	            		
	            }
	        }
	    }

	    /**
	     * 
	     * @param key 配置文件中的主键字段
	     * @return
	     */
	    public static String getProperty(String key){
	        if(null == props) {
	            loadProps();
	        }
	        return props.getProperty(key);
	    }

	    /**
	     * 
	     * @param key  配置文件中的主键值
	     * @param defaultValue 若配置文件中不存在指定的配置，使用默认值
	     * @return
	     */
	    public static String getProperty(String key, String defaultValue) {
	        if(null == props) {
	            loadProps();
	        }
	        return props.getProperty(key, defaultValue);
	    }
}

package com.api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {
	
	private static Properties properties;
	
	public static String getProperty(String key) {
		if(properties==null) {
			properties = new Properties();
			try {
			    //load a properties file from given path, inside static method
				InputStream is= new FileInputStream("src/test/resources/configuration/config.properties");
			    properties.load(is);
			} 
			catch (IOException ex) {
			    ex.printStackTrace();
			}
		}
		return properties.getProperty(key);
			
	}

	public static String getBaseUrl() {
		return getProperty("base.url");
	}

	public static String getProgramPath() {
		return getProperty("program.save");
	}

	public static String getDeleteProgramPath() {
		return getProperty("program.delete");
	}

	public static String getProgramsPath() {
		return getProperty("programs.path");

	}
	public static String getUpdatebyIdPath() {
		return getProperty("program.updatebyid");

	}
	public static String getUpdatebyNamePath() {
		return getProperty("program.updatebyname");

	}
	public static String getBatchbyIdPath() {
		return getProperty("batch.getbatchid");

	}
	public static String getAllprogramsPath() {
		return getProperty("program.getallprograms");

	}
	public static String createBatchPath() {
		return getProperty("batch.createbatch");

	}

	public static String getBatchNameUrlPath() {
		return getProperty("batch.batchnameurlpath");
	}
	
	public static String getBatchByProgramIDUrlPath() {
		return getProperty("batch.programidurlpath");
	}
	
	public static String getDeleteBatchPath() {
		return getProperty("batch.delete");
	}
}

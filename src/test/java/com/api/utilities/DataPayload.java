package com.api.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataPayload {
	
	private static Properties prop;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	private static SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");

		static {
			
			 try (InputStream input = DataPayload.class.getClassLoader().getResourceAsStream("configuration/config.properties")) {

		            prop = new Properties();

		            if (input == null) {
		                System.out.println("Sorry, unable to find config.properties");
		            }

		            //load a properties file from class path, inside static method
		            prop.load(input);

		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		}
		
		
		
		public static String getSaveData() {
			
			Map<String, String> map = new HashMap();
			Date dt= new Date();
			String dateNow = format.format(dt);
			String date2 = ""+System.nanoTime();
			
			System.out.println("******************************** " + dateNow);
			System.out.println("**************************************" + date2);
			
			map.put("programName", prop.getProperty("programName")+date2);
			map.put("programDescription", prop.getProperty("programDescription"));
			map.put("programStatus", prop.getProperty("programStatus"));
			map.put("creationTime", dateNow);
			map.put("lastModTime", dateNow);
			
			ObjectMapper mapper = new ObjectMapper();
			String programBody = null;
			try {
				programBody = mapper.writeValueAsString(map);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return programBody;
			
//			Program program = new Program();
//			
//			program.setProgramName(prop.getProperty("programName"));
//			program.setProgramDescription( prop.getProperty("programDescription"));
//			program.setProgramStatus(prop.getProperty("programStatus"));
//			program.setCreationTime(dateNow);
//			program.setLastModTime(dateNow);
//			
//			return program;
		}
		
		public static void main(String[] args) {
			System.out.println(getSaveData());
		}

}

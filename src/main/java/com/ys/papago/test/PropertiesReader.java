package com.ys.papago.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	public static void main(String[] args) {
		String path = "test.properties";		
		try {
			InputStream fis = PropertiesReader.class.getClassLoader().getResourceAsStream(path);
			Properties prop = new Properties();
			prop.load(fis);
			System.out.println(prop.getProperty("client.id"));
			System.out.println(prop.getProperty("client.secret"));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
//		try {
//			FileInputStream fis = new FileInputStream(path);
//			InputStreamReader isr = new InputStreamReader(fis);
//			BufferedReader br = new BufferedReader(isr);
//			
//			String str1 = br.readLine();
//			String str2 = br.readLine();
//			
//			String rstKey1= str1.substring(0,str1.indexOf("="));
//			String rstValue1 = str1.substring(str1.indexOf("=")+1);
//			
//			String rstKey2= str2.substring(0,str2.indexOf("="));
//			String rstValue2 = str2.substring(str2.indexOf("=")+1);
//
//			
//			Map<String,String> rMap = new HashMap<String,String>();
//			rMap.put(rstKey1, rstValue1);
//			rMap.put(rstKey2, rstValue2);
//			
//			System.out.println(rMap);
			
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}

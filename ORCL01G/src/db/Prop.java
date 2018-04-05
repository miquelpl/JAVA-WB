package db;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class Prop {
	
	public final static String PROPERTY_PATH = "/properties/db.properties";
	
	private static Properties props;
	
	private Prop(){}
		
	static{
		props = new Properties();
		
		
		try {
			props.load(new BufferedInputStream  ( Prop.class.getResourceAsStream(PROPERTY_PATH))   );
		
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key){
		return props.getProperty(key);
	}

}

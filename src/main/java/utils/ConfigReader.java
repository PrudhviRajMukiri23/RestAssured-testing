package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	static Properties properties;
	
	public static Properties readConfigFile(String filename) throws IOException {
		
		properties = new Properties();
		
		InputStream ioFile = new FileInputStream(filename);
		
		properties.load(ioFile);
		
		return properties;
	}

}

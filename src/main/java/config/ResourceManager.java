package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceManager {
	
	public static String readProperty(String propertyName) throws IOException {
		try (InputStream input = ResourceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
			Properties properties = new Properties();
			properties.load(input);
			
			return properties.getProperty(propertyName);	
		}
		catch(Exception e) {
			throw new IOException("Could not read a property file");
		}
	}
}

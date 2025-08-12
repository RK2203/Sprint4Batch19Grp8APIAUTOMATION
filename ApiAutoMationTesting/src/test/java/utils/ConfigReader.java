package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ConfigReader {
	
	 public static String get(String key) {
	        Properties props = new Properties();
	        try (FileInputStream fis = new FileInputStream("src/test/java/resources/config.properties")) {
	            props.load(fis);
	        } catch (IOException e) {
	            throw new RuntimeException("Unable to read config.properties", e);
	        }
	        return props.getProperty(key);
	    }

}

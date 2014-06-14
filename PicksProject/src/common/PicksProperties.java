package common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PicksProperties {

	private static Properties prop;
	
	static
	{
		try 
		{
			prop = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();           
			InputStream stream = loader.getResourceAsStream("/picks.properties");
			prop.load(stream);
			System.out.println("LOADED PROPERTIES");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String get(String key)
	{
		return prop.getProperty(key);
	}
	
	
}

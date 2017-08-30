package mobile_odt.com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	static FileInputStream inputStream;

	public static String getProperty(String property) {
		try {
			return getPropertyValue(property);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private static synchronized String getPropertyValue(String property) throws IOException {
		String prop = null;
		try {
			Properties props = new Properties();
			String propFileName = "config.properties";

			inputStream = new FileInputStream(System.getProperty("user.dir") + "//" + propFileName);

			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			prop = props.getProperty(property);

			// get the property value and print it out
			// System.out.println("Property: " + property + " = " + prop);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}

		// if (prop.equalsIgnoreCase("true"))
		// return true;
		//
		// if (prop.equalsIgnoreCase("false"))
		// return false;

		return prop;

	}
}

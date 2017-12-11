package fileUtilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import driver.Global;

public class PropertiesLoader {
	private final String FRAMEOWRK_PROPERTY_FILE_PATH = "/PropertyFiles/FrameworkDefaultProperties.properties"; 
	private final String OBJECT_PROPERTY_FILE_PATH = "/PropertyFiles/WPR_OR.properties"; 
	private final String PROJECT_PROPERTY_FILE_PATH = "/SystemProperties.properties";
	private static Properties objectsProp = new Properties();
	
	public void loadProps() {
		InputStream fwkPropIn = this.getClass().getResourceAsStream(FRAMEOWRK_PROPERTY_FILE_PATH);
		InputStream objIn = this.getClass().getResourceAsStream(OBJECT_PROPERTY_FILE_PATH);
		InputStream projectPropIn = this.getClass().getResourceAsStream(PROJECT_PROPERTY_FILE_PATH);
		try {
			Global.testProps.load(fwkPropIn);
			Global.testProps.load(projectPropIn);
			objectsProp.load(objIn);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadObjects() {
		for (Entry<Object, Object> entry: objectsProp.entrySet()) {
		    String key = (String) entry.getKey();
		    String value = (String) entry.getValue();
		    String locType = StringUtils.split(value, "~")[0];
		    String locator = StringUtils.split(value, "~")[1];
		    Global.locatorProps.put(key, Global.elements.byLocator(locType, locator));
		}
	}
}

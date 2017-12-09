package fileUtilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import driver.Global;

public class PropertiesLoader {
	private final String DEFAULT_PROPERTY_FILE_PATH = "/PropertyFiles/FrameworkDefaultProperties.properties"; 
	private final String OBJECT_PROPERTY_FILE_PATH = "/PropertyFiles/WPR_OR.properties"; 
	private static Properties configProp = new Properties();
	private static Properties objectsProp = new Properties();
	
	public void loadProps() {
		InputStream in = this.getClass().getResourceAsStream(DEFAULT_PROPERTY_FILE_PATH);
		InputStream objIn = this.getClass().getResourceAsStream(OBJECT_PROPERTY_FILE_PATH);
		try {
			configProp.load(in);
			objectsProp.load(objIn);
			loadObjects();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadObjects() {
		for (Entry<Object, Object> entry: objectsProp.entrySet()) {
		    String key = (String) entry.getKey();
		    String value = (String) entry.getValue();
		    String locType = StringUtils.split(value, "~")[0];
		    String locator = StringUtils.split(value, "~")[1];
		    Global.locatorProps.put(key, Global.elements.byLocator(locType, locator));
		}
	}
	
	public Properties getConfigProp() {
		return configProp;
	}

	public void setConfigProp(Properties configProp) {
		this.configProp = configProp;
	}

}

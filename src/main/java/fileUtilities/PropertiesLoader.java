package fileUtilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class PropertiesLoader {
	private final String DEFAULT_PROPERTY_FILE_PATH = "/PropertyFiles/FrameworkDefaultProperties.properties"; 
	private static Properties configProp = new Properties();
	
	public void loadProps() {
		InputStream in = this.getClass().getResourceAsStream(DEFAULT_PROPERTY_FILE_PATH);
		try {
			configProp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(configProp.getProperty("browser"));
	}
	
	public Properties getConfigProp() {
		return configProp;
	}

	public void setConfigProp(Properties configProp) {
		this.configProp = configProp;
	}

	
	@Test
	public void main() {
		new PropertiesLoader().loadProps();
		System.out.println(getConfigProp().getProperty("browser"));
	}

}

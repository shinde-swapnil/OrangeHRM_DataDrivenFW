package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GenericUtility 
{
	static Properties p ;
	
	public static Properties loadProperties() throws IOException
	{
		// Property config
        FileInputStream fis = new FileInputStream("D:\\Workspace\\OrangeHRM_DDF1\\TestData\\hrmDetails.properties");
        p = new Properties();
        p.load(fis);
        return p;
	}

}

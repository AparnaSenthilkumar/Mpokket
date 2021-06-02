package com.restapi.propconfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class PropRepo {
	public static Properties readPropertiesFile() throws IOException {
		String fileName = "C:/Practice/Mpokket/src/resources/java/Input.properties";
        FileInputStream fis = null;
        Properties prop = null;
        try {
           fis = new FileInputStream(fileName);
           prop = new Properties();
           prop.load(fis);
        } catch(FileNotFoundException fnfe) {
           fnfe.printStackTrace();
        } catch(IOException ioe) {
           ioe.printStackTrace();
        } finally {
           fis.close();
        }
        return prop;
     }

}

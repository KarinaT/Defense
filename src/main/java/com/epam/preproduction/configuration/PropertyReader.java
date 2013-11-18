package com.epam.preproduction.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.testng.Reporter;

public class PropertyReader {

	private static final String sFileName = "configurations.properties";
	private static String sDirSeparator = System.getProperty("file.separator");
	private static Properties props = new Properties();

	static {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		File currentDir = new File(".");
		try {
			String sFilePath = currentDir.getCanonicalPath() + sDirSeparator + sFileName;

			FileInputStream ins = new FileInputStream(sFilePath);
			props.load(ins);

		} catch (FileNotFoundException e) {

		Reporter.log("File not found!");
			
			e.printStackTrace();

		} catch (IOException ex) {

			Reporter.log("IO Error!");

			ex.printStackTrace();

		}
	}

	public static String getInputExcelFileName() {
		return props.getProperty("fileInputExcel");
	}

	public static String getScreenShotsDestinationFolder() {
		return props.getProperty("fileOutputScreenshot");
	}

	public static String getScreenShotsFileExtension() {
		return props.getProperty("screenShotsFileExtension");
	}

	public static String getMainPageUrl() {
		return System.getProperty("home.page.url", "http://pn.com.ua");
	}

	public static String getOperaBinaryPath() {
		return props.getProperty("operaDriverFile");
	}

	public static String getChromeBinaryPath() {
		return props.getProperty("chromedriverFile");
	}

	public static String getInputXmlFileName() {
		return props.getProperty("fileInputXml");
	}
}

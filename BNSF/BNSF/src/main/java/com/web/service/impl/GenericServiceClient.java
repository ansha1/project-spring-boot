package com.web.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericServiceClient {

	protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

	protected String url;

	protected void loadProperties() {

		try {
			FileInputStream inFile = new FileInputStream(new File("connection.properties"));
			Properties properties = new Properties();
			properties.load(inFile);
			url = properties.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

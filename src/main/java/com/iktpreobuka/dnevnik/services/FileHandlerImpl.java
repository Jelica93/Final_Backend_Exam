package com.iktpreobuka.dnevnik.services;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class FileHandlerImpl implements FileHandler {

	public File getLogs() {
//		
		String path = "c:\\Users\\User\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\Elektronski_Dnevnik\\logs\\spring-boot-logging.log";
		File log = new File(path);

		return log;

	}
	
}

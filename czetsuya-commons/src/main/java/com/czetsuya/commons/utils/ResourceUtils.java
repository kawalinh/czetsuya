package com.czetsuya.commons.utils;

import java.io.File;
import java.net.URL;

/**
 * @author Edward P. Legaspi
 * @since Mar 27, 2013
 */
public class ResourceUtils {

	public final static String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public static File getFileFromClasspathResource(String resource) {
		return new File(findBasePathFromClasspathResource(resource), resource);
	}
	
	public static String findBasePathFromClasspathResource(String resource) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(resource);
		
		return findBasePath(convertUrlToFilename(url));
	}

	public static String findBasePath(String path) {
		if(path.indexOf(FILE_SEPARATOR)==-1)
			return path;
		
		return path.substring(0, path.lastIndexOf(FILE_SEPARATOR));
	}
	
	public static String convertUrlToFilename(URL url) {
		return convertUrlToFilename(url.getFile());
	}

	public static String convertUrlToFilename(String path) {
		if (path.indexOf("file:/")!=-1) {
			if (path.indexOf(":", path.indexOf("file:/") + 6) != -1)
				path = path.substring(path.indexOf("file:/")+6);
			else
				path = path.substring(path.indexOf("file:/")+5);
		}

		return path.replace('/', System.getProperty("file.separator").charAt(0));
	}
}

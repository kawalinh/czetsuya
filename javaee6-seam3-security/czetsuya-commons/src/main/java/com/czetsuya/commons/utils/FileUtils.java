package com.czetsuya.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil;

/**
 * @author Edward P. Legaspi
 * @since Nov 18, 2012
 **/
public class FileUtils {
	public static File getFile(String path, String filename) {
		new File(path).mkdirs();
		return new File(path, filename);
	}

	/**
	 * Recursively delete directory and it's contents
	 * 
	 * @param path
	 *            Directory to delete
	 * @return True if directory and it's contents were deleted
	 */
	public static boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	/**
	 * Construct a DOM document from XML file
	 * 
	 * @param file
	 *            A full path to XML file
	 * @return Document
	 * @throws Exception
	 */
	public static Document getDocumentByFile(String absoulutFileName) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(absoulutFileName));
		return document;
	}

	public static boolean saveXmlFile(String filePath, StringBuffer ret) throws IOException {
		File fl = new File(filePath);
		File createDir = fl.getParentFile();
		if (createDir != null) {
			createDir.mkdirs();
		}
		FileWriter fw = new FileWriter(fl);
		fw.write(ret.toString());
		fw.close();
		return true;
	}

	/**
	 * Find a first file in a given directory matching given file extension.
	 * 
	 * @param sourceDirectory
	 *            Directory to search inside.
	 * @param extensions
	 *            A list of acceptable file extensions
	 * @return File found
	 */
	public static File findAFirstFile(String sourceDirectory, final List<String> extensions) {
		List<File> files = findFiles(sourceDirectory, extensions);
		if (files != null && !files.isEmpty()) {
			return files.get(0);
		}

		return null;
	}

	/**
	 * Find all files in a given directory matching given file extension.
	 * 
	 * @param sourceDirectory
	 *            Directory to search inside.
	 * @param extensions
	 *            A list of acceptable file extensions
	 * @return File found
	 */
	public static List<File> findFiles(String sourceDirectory, final List<String> extensions) {
		File sourceDir = new File(sourceDirectory);
		if (!sourceDir.exists() || !sourceDir.isDirectory()) {
			Logger log = LoggerFactory.getLogger(FileUtils.class);
			log.info(String.format("Wrong source directory: %s", sourceDir.getAbsolutePath()));
			return null;
		}
		File[] files = sourceDir.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				if (extensions == null) {
					return true;
				}
				for (String extension : extensions) {
					if (name.endsWith(extension)) {
						return true;
					}
				}
				return false;
			}

		});
		if (files == null || files.length == 0) {
			return null;
		}

		Arrays.sort(files, new Comparator<File>() {
			// sort files by modification date asc.
			public int compare(File file1, File file2) {
				if (file1.lastModified() < file2.lastModified()) {
					return -1;
				} else if (file1.lastModified() > file2.lastModified()) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		List<File> foundFiles = new ArrayList<File>();
		for (File file : files) {
			if (file.isFile()) {
				foundFiles.add(file);
			}
		}
		return foundFiles;
	}

	public static byte[] readInputStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buf = new byte[4096];
		while (true) {
			int n = is.read(buf);
			if (n < 0)
				break;
			baos.write(buf, 0, n);
		}

		return baos.toByteArray();
	}

	public static void saveStreamToFile(InputStream is, String fileName) throws IOException {
		File _fileMarketing = new File(fileName);
		if (!_fileMarketing.exists()) {
			_fileMarketing.createNewFile();
		}

		byte[] data = FileUtils.readInputStream(is);

		FileOutputStream os = new FileOutputStream(_fileMarketing);
		os.write(data, 0, data.length);
		os.flush();

		os.close();
		is.close();
	}

	public static MimeType getMimeType(String file) {
		Collection<?> mimeTypes = MimeUtil.getMimeTypes(file);
		MimeType m = mimeTypes.toArray(new MimeType[mimeTypes.size()])[0];
		return m;
	}
}

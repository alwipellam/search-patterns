package com.references.coding.directory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.references.coding.domain.Reference;

public final class FileWriterUtil {

	private static final Logger LOG = LoggerFactory.getLogger(FileWriterUtil.class);

	private static final String OUTPUT_FOLDER = "C://readfile//output";

	private FileWriterUtil() {
	}

	public static void writeFilesReferences(List<Reference> references) throws IOException {

		createDirectory(OUTPUT_FOLDER);
		for (Reference reference : references) {
			String fileName = UUID.randomUUID().toString() + ".txt";
			writeContentToFile(OUTPUT_FOLDER, fileName, reference.toString());
			LOG.info("File wrote : " + reference.getUrl() + " --> " + fileName);
		}
	}

	public static void createDirectory(String directoryName) {
		File file = new File(directoryName);
		boolean isRemoved = deleteDirectory(file);

		LOG.info("Status directory : " + isRemoved);

		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	static boolean deleteDirectory(File directoryToBeDeleted) {
		File[] allContents = directoryToBeDeleted.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				deleteDirectory(file);
			}
		}
		return directoryToBeDeleted.delete();
	}

	public static void writeContentToFile(String directoryName, String fileName, String referenceContent)
			throws IOException {
		File file = new File(directoryName, fileName);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
			bufferedWriter.write(referenceContent);
		} catch (IOException e) {
			throw e;
		}
	}

}

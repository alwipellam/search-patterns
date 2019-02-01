package com.references.coding.directory;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.references.coding.domain.Reference;

public class ReadFileTest {

	private static final Logger LOG = LoggerFactory.getLogger(ReadFileTest.class);
	private static final String OUTPUT_FOLDER = "C://readfile//output";
	private static final String INPUT_FOLDER = "C://readfile//input";

	public static void main(String[] args) throws IOException {

		LOG.info("***************** Start reading urls *************************");
		String filePath = INPUT_FOLDER;
		List<String> result = FileReadUtil.readFileAndProcessing(filePath);
		LOG.info("****************** End reading urls****************************");
		result.forEach(System.out::println);

		LOG.info("***************** Start reading urls *************************");
		String outPath = OUTPUT_FOLDER;
		List<Reference> references = getReferences();
		references.forEach(System.out::println);
		LOG.info("************* Start Writing on a directory ****************************");
		FileWriterUtil.writeFilesReferences(references);
		LOG.info("***************End Writing on a directory ***************************");
	}

	private static List<Reference> getReferences() {
		// TODO Auto-generated method stub
		return null;
	}

}

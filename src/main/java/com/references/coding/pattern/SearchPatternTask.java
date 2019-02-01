package com.references.coding.pattern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.references.coding.directory.FileWriterUtil;
import com.references.coding.domain.Reference;
import com.references.coding.enums.ReferencePattern;
import com.references.coding.util.ReferenceUtil;

public class SearchPatternTask {

	private static final Logger LOG = LoggerFactory.getLogger(SearchPatternTask.class);

	public static void main(String[] args) throws IOException {

		// args[0] = "C://readfile/input.txt";
		// args[1] = "ReferencePattern.HASH_TAG,
		// ReferencePattern.TWITTER_ACCOUNT,ReferencePattern.PROPER_NAME";

		LOG.info("*******************Start search patterns ***********************");
		// args[0];
		String filePath = "C://readfile/input//urls.txt";
		// getReferencePatterns(args[1]);
		List<ReferencePattern> patterns = getReferencePatterns();

		/*****
		 * Here We Are Creating A 'Parallel Stream' & Displaying The Result
		 *****/
		getInputReferencesByParallelStream(filePath, patterns);

		/*****
		 * Here We Are Creating A 'Stream' & Displaying The Result
		 *****/
		getInputReferencesByStream(filePath, patterns);

		LOG.info("*******************End search patterns ***********************");

	}

	private static List<Reference> getInputReferencesByParallelStream(String filePath, List<ReferencePattern> patterns)
			throws IOException {

		long t1, t2;
		t1 = System.currentTimeMillis();
		System.out.println("::::::::::: Start search patterns by Parallel Stream ::: ");
		List<Reference> references = ReferenceUtil.getInputReferences(filePath, patterns);
		t2 = System.currentTimeMillis();
		System.out.println("::::::::::: End search patterns Parallel Stream Time Taken?= " + (t2 - t1));
		LOG.info("************* Start Writing on a directory ****************************");
		FileWriterUtil.writeFilesReferences(references);
		LOG.info("***************End Writing on a directory ***************************");
		// references.forEach(System.out::println);
		return references;
	}

	private static List<Reference> getInputReferencesByStream(String filePath, List<ReferencePattern> patterns)
			throws IOException {

		long t1, t2;
		t1 = System.currentTimeMillis();
		System.out.println(":::::::::::::::::::::: Start search patterns by Only Stream ::: ");
		List<Reference> references = ReferenceUtil.getInputReferencesByStream(filePath, patterns);
		t2 = System.currentTimeMillis();
		System.out.println(":::::::::::::::::::::: End search patterns Only Stream Time Taken?= " + (t2 - t1));
		LOG.info("************* Start Writing on a directory ****************************");
		FileWriterUtil.writeFilesReferences(references);
		LOG.info("***************End Writing on a directory ***************************");
		// references.forEach(System.out::println);
		return references;
	}

	private static List<ReferencePattern> getReferencePatterns() {
		List<ReferencePattern> patterns = new ArrayList<>();
		patterns = Arrays.asList(ReferencePattern.HASH_TAG, ReferencePattern.TWITTER_ACCOUNT,
				ReferencePattern.PROPER_NAME);
		return patterns;
	}

}

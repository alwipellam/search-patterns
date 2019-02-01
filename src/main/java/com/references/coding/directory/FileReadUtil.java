package com.references.coding.directory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

import com.references.coding.enums.FileErrorCode;
import com.references.coding.exception.SystemException;

public class FileReadUtil {

	public FileReadUtil() {
		// TODO Auto-generated constructor stub
	}

	public static List<String> readFileAndProcessing(String filePath) {

		if (StringUtils.isEmpty(filePath)) {
			throw new SystemException(FileErrorCode.FILE_PATH_INVALID);
		}

		if (!StringUtils.isEmpty(filePath.trim()) || filePath.trim() != null) {
			try (Stream<String> stream = Files.lines(Paths.get(filePath.trim()))) {
				List<String> result = new ArrayList<>();
				// 1. get all line 2. convert stream into a List
				result = stream.map(line -> line.trim().toString()).collect(Collectors.toList());
				// process result List
				//result.forEach(System.out::println);
				return result;

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// process result List
		return new ArrayList<>();
	}

}

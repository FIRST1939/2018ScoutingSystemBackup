package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.StringJoiner;

/**
 * The utilities for writing and reading files. 
 * @author Grayson Spidle
 *
 */
public class FileUtils {
	
	/**
	 * Writes the specified list to the specified file.
	 * @param file The specified file.
	 * @param lines The specified list of Strings to write.
	 * @param option The desired method of writing the given data
	 * @throws IOException 
	 */
	public static void write(File file, List<List<String>> lines, StandardOpenOption option) throws IOException {
		StringJoiner sj = new StringJoiner(",");
		for (List<String> ls : lines) {
			for (String s : ls) {
				sj.add(s);
			}
		}
		
		String output = sj.toString();
		if (file.exists()) Files.write(file.toPath(), (output + System.lineSeparator()).getBytes(), option);
		else Files.write(file.toPath(), (output + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE_NEW);
	}
	
	public static List<String> read(File file) throws IOException {
		List<String> lines = Files.readAllLines(file.toPath());
		return lines;
	}
	

}

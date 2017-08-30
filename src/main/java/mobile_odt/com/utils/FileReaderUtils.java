package mobile_odt.com.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class FileReaderUtils {

	static StringBuffer stringBufferOfData = new StringBuffer();
	static String filename = null;

	public static void replacement(StringBuffer stringBufferOfData, String lineToEdit, String replacementText) {

		int startIndex = stringBufferOfData.indexOf(lineToEdit);
		int endIndex = startIndex + lineToEdit.length();
		stringBufferOfData.replace(startIndex, endIndex, replacementText);
	}

	public static void writeToFile(String data, String filename) {
		try {
			stringBufferOfData = new StringBuffer(data);
			BufferedWriter bufwriter = new BufferedWriter(new FileWriter(filename));
			bufwriter.write(stringBufferOfData.toString());
			bufwriter.close();
		} catch (Exception e) {
			System.out.println("Error occured while attempting to write to file: " + e.getMessage());
		}
	}

	public static StringBuffer readFile(String filetoread) throws FileNotFoundException {

		filename = filetoread;
		Scanner fileToRead = null;

		try {
			fileToRead = new Scanner(new File(filename));
			for (String line; fileToRead.hasNextLine() && (line = fileToRead.nextLine()) != null;) {
				System.out.println(line);
				stringBufferOfData.append(line).append("\r\n");
			}
			fileToRead.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stringBufferOfData;

	}

}

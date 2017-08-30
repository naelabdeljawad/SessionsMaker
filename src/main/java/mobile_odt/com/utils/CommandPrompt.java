package mobile_odt.com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Command Prompt - this class contains method to run windows and mac commands
 * 
 * @author Nael Abd Aljawad
 */

public class CommandPrompt implements Runnable {

	private Process p;
	private ProcessBuilder builder;
	private InputStream istrm;
	private OutputStream ostrm;

	public CommandPrompt() {
		// Default Constructor
	}

	public CommandPrompt(InputStream istrm, OutputStream ostrm) {
		this.istrm = istrm;
		this.ostrm = ostrm;
	}

	/**
	 * This method run command on windows and mac
	 * 
	 * @param command
	 *            to run
	 */
	public String runCommand(String command) {
		String os = System.getProperty("os.name");
		// build cmd proccess according to os
		if (os.contains("Windows")) // if windows
		{
			builder = new ProcessBuilder("cmd.exe", "/c", command);
			builder.redirectErrorStream(true);
			try {
				p = builder.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			try {
				p = Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				e.printStackTrace();
			}

		// get std output
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";
		String allLine = "";

		try {
			while ((line = r.readLine()) != null) {
				allLine = allLine + "" + line + "\n";
				if (line.contains("Console LogLevel: debug"))
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.err.println(allLine);

		return allLine;
	}

	public void run() {
		try {
			final byte[] buffer = new byte[1024];
			for (int length = 0; (length = this.istrm.read(buffer)) != -1;) {
				this.ostrm.write(buffer, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

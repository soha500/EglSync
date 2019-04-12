package org.eclipse.epsilon.egl.sync;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SyncReader {

	FileInputStream fIn;
	public BufferedReader bRead;

	public SyncReader(String fileName) {
		try {

			fIn = new FileInputStream(fileName);
			bRead = new BufferedReader(new InputStreamReader(fIn));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void read() {
		try {
			fIn.getChannel().position(0);
			bRead = new BufferedReader(new InputStreamReader(fIn));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

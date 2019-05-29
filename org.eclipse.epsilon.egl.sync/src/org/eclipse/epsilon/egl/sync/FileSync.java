package org.eclipse.epsilon.egl.sync;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.epsilon.eol.models.IModel;

public class FileSync {

	// all fields
	FileInputStream fIn;
	public BufferedReader bRead;
	public IModel model;

	public FileSync(String fileName) {
		try {
			fIn = new FileInputStream(fileName);
			bRead = new BufferedReader(new InputStreamReader(fIn));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void reusefile() {
		try {
			fIn.getChannel().position(0);
			bRead = new BufferedReader(new InputStreamReader(fIn));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	// Find all the sync regions
	public ArrayList<Synchronization> getAllTheSyncRegionsOfTheFile() {
		ArrayList<Synchronization> allTheSyncRegionsInTheFile = new ArrayList<Synchronization>();

		this.reusefile();
		String line = null;

		while (true) {
			try {
				line = this.bRead.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			if (line == null) {
				break;
			}

			if (line.contains("//sync")) {

				String[] idAndProperty = null;
				//  "[^..]" // matches any single character not in brackets
				//  "(.:?)" // matches all 
				//  "\\s.\\w\\d\\w+.\\s\\w+"
				//Pattern p = Pattern.compile("[^..]");

				Pattern p = Pattern.compile("\\s.\\w\\d\\w+.\\s\\w+");
				Matcher m = p.matcher(line.trim());

				if (m.find())
					idAndProperty = (String[]) (m.group(0)).split(",");
				Synchronization sync = new Synchronization(idAndProperty[0].trim(), idAndProperty[1].trim());

				try {
					while (!(line = this.bRead.readLine()).contains("//endsync"))
						sync.addContent(line.trim());

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				allTheSyncRegionsInTheFile.add(sync);

			}
		}
		return allTheSyncRegionsInTheFile;
	}
}
//if (line.contains("/*sync")) {
//
//	String[] idAndProperty = null;
//	//  "[^..]" // matches any single character not in brackets 
//	//  "\\s.\\w\\d\\w+.\\s\\w+"
//
//	Pattern p = Pattern.compile("[^..]");
//	Matcher m = p.matcher(line);
//
//	if (m.find())
//		idAndProperty = (String[]) (m.group(0)).split(",");
//	Synchronization sync = new Synchronization(idAndProperty[0].trim(), idAndProperty[1].trim());
//
//	try {
//		while (!(line = this.bRead.readLine()).contains("/*endsync"))
//			sync.addContent(line);
//
//	} catch (IOException e1) {
//		e1.printStackTrace();
//	}
//	allTheSyncRegionsInTheFile.add(sync);
//
//}

//if (line.contains("//sync" || "/*sync")) {

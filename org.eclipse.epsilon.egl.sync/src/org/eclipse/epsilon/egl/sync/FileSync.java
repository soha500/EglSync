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

				String[] idAndAttribute1 = null;

				Pattern p = Pattern.compile("(\\s\\w+.\\s\\w+.)"); // Ok

				Matcher m = p.matcher(line.trim());

				if (m.find())
					idAndAttribute1 = (String[]) (m.group(0)).split(",");
				Synchronization sync = new Synchronization(idAndAttribute1[0].trim(), idAndAttribute1[1].trim());

				try {
					while (!(line = this.bRead.readLine()).contains("//endsync"))
						sync.addContent(line.trim());

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				allTheSyncRegionsInTheFile.add(sync);

			} else if (line.contains("/*sync")) {

				// /*sync with extend 4 groups
				if (line.contains("extends")) {

					Pattern p = Pattern.compile("\\s(.+\\s*,\\s*\\w+)\\s*\\*\\/\\s(\\w+)\\s*\\/\\*\\s*endsync\\s*\\*\\/\\s+\\w+\\s+\\/\\*\\s*sync\\s+(.+\\s*,\\s*\\w+)\\s*\\*\\/\\s*(\\w+)\\s*\\/\\*\\s*endSync\\s*\\*\\/"); // One group without sync and endsync

					Matcher m = p.matcher(line.trim());

					if (m.find()) {
						System.out.println(m.group(1));
						System.out.println(m.group(2));
						System.out.println(m.group(3));
						System.out.println(m.group(4));

						// matching before extend is saved in sync 1
						String[] idAndAttribute1 = (String[]) (m.group(1)).split(",");
						String content1 = m.group(2).trim();
						Synchronization sync1 = new Synchronization(idAndAttribute1[0].trim(),
								idAndAttribute1[1].trim(), content1);

						// matching after extend is saved in sync 2
						String[] idAndAttribute2 = (String[]) (m.group(3)).split(",");
						String content2 = m.group(4).trim();
						Synchronization sync2 = new Synchronization(idAndAttribute2[0].trim(),
								idAndAttribute2[1].trim(), content2);

						allTheSyncRegionsInTheFile.add(sync1);
						allTheSyncRegionsInTheFile.add(sync2);
					}

				}
				// /*sync without extend 3 groups
				else {

					final String regex = "\\/\\*\\s*sync\\s+(.+)\\s*,\\s*(\\w+)\\s*\\*\\/\\s*(\\w+)\\s*\\/\\*\\s*endsync\\s*\\*\\/";

					final Pattern pattern = Pattern.compile(regex);
					final Matcher matcher = pattern.matcher(line);

					if (matcher.find()) {

						String id = matcher.group(1).trim();
						String attribute = matcher.group(2).trim();
						String content = matcher.group(3).trim();

						Synchronization sync = new Synchronization(id, attribute, content);

						allTheSyncRegionsInTheFile.add(sync);
					}
				}
			}

		}
		return allTheSyncRegionsInTheFile;
	}
}

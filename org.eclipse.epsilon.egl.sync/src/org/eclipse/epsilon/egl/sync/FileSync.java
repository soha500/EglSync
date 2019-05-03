package org.eclipse.epsilon.egl.sync;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.epsilon.eol.models.IModel;

public class FileSync {

	// all fields
	FileInputStream fIn;
	public BufferedReader bRead;
	public IModel model;
	public List<Synchronization> syncLists;

	public FileSync(String fileName, IModel model, List<Synchronization> synclists) {
		try {
			fIn = new FileInputStream(fileName);
			bRead = new BufferedReader(new InputStreamReader(fIn));
			this.model = model;
			this.syncLists = synclists;

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
	/// Find all the sync regions
	public boolean getSyncs() {

		this.reusefile();
		String syncRegions = null;

		while (true) {
			try {
				syncRegions = this.bRead.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			if (syncRegions == null) {
				break;
			}

			if (syncRegions.contains("//sync")) {

				String[] idAndProperty = null;

				Pattern p = Pattern.compile("\\s.\\w\\d\\w+.\\s\\w+");
				Matcher m = p.matcher(syncRegions);

				if (m.find())
					idAndProperty = (String[]) (m.group(0)).split(",");
				Synchronization sync = new Synchronization(idAndProperty[0].trim(), idAndProperty[1].trim());

				try {
					while (!(syncRegions = this.bRead.readLine()).contains("//endsync"))
						sync.addContent(syncRegions);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// Check if the value of each property is different or similar
				for (Synchronization syncItem : syncLists) {
					syncItem.getId();
					syncItem.getAttribute();
					syncItem.getContent();

					if (sync.getId().equals(syncItem.getId()) && sync.getAttribute().equals(syncItem.getAttribute())
							&& !(sync.getContent().equals(syncItem.getContent()))) {

						return false;
					}
				}
				syncLists.add(sync);

			}
		}
		return true;
	}
}

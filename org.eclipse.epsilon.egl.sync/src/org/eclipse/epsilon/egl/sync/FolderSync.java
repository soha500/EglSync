package org.eclipse.epsilon.egl.sync;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.models.IModel;

public class FolderSync {

	public void sync(String folder, IModel model) {
		Path folderPath = Paths.get(folder);

		// create data structure for all files's names and contents in the folder
		Map<String, List<String>> namesAndContents = new TreeMap<String, List<String>>();

		List<Synchronization> syncLists = new ArrayList<Synchronization>();

		// call all list of the files in the folder
		List<String> fileNames = new ArrayList<>();

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
			for (Path path : directoryStream) {
				fileNames.add(path.toString());
			}
		} catch (IOException ex) {
			System.err.println("Error reading files");
			ex.printStackTrace();
		}

		// go through the list of files in folder..
		for (String file : fileNames) {
			System.out.println("the filename is :" + file);

			try {
				// put the generated file's name and its content into the data structure
				List<String> content = Files.readAllLines(folderPath.resolve(file));
				namesAndContents.put(file, content);

				FileSync tasks = new FileSync(file, model, syncLists);

				boolean result = tasks.getSyncs();
				if (result != true) {
					System.out.println(
							"Sorry..!! there is consistent value of the same property in some sync regions, so the model cannot be updated.");	

					
				} else {
					for (Synchronization sync : syncLists) {
						Object modelElement = model.getElementById(sync.getId());

						IPropertySetter propertySetter = model.getPropertySetter();
						propertySetter.setObject(modelElement);
						propertySetter.setProperty(sync.getAttribute());
						try {
							propertySetter.invoke(sync.getContent());
						} catch (EolRuntimeException e) {
							e.printStackTrace();
						}

						System.out.println(sync.getId());
						System.out.println(sync.getAttribute());
						System.out.println(sync.getContent());
					}
				}
				model.store();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}



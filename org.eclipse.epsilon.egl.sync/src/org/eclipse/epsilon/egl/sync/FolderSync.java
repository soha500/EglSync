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

import org.eclipse.epsilon.eol.models.IModel;

public class FolderSync {


	public void sync(String folder, IModel model) {
		Path folderPath = Paths.get(folder);

		// create data structure for all files's names and contents in the folder
		Map<String, List<String>> namesAndContents = new TreeMap<String, List<String>>();

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
				FileSync tasks = new FileSync(file, model);

				tasks.updateModel();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

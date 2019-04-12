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

import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

public class SyncAppFromModel {

	private static final String FOLDER_PATH = "/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/All-Generated-Files";

	public static void main(String[] args) throws EolModelLoadingException, IOException {

		List<Synchronization> listObjects = new ArrayList<Synchronization>();

		Path folderPath = Paths.get(FOLDER_PATH);

		// create data structure for a generated file's name and content
		Map<String, List<String>> linesOfFiles = new TreeMap<String, List<String>>();

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
			Tasks tasks = new Tasks(file);

			tasks.readAllFiles();
			tasks.printSyncLines();
			tasks.getSyncContent();
			tasks.updateModel(listObjects);

			try {
				// put the generated file's name and its content into the data structure
				List<String> lines = Files.readAllLines(folderPath.resolve(file));
				linesOfFiles.put(file, lines);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

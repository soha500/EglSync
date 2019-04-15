package org.eclipse.epsilon.egl.sync;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

public class SyncAppFromModel {

	private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/All-Generated-Files";

	public static void main(String[] args) throws EolModelLoadingException, IOException {

		//List<Synchronization> listObjects = new ArrayList<Synchronization>();
		EmfModel model = new EmfModel();
		model.setName("M");
		model.setMetamodelFile(new File("Statemachine.ecore").getAbsolutePath());
		model.setModelFile(new File("Statemachine.model").getAbsolutePath());
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(true);

		try {
			model.load();
		} catch (EolModelLoadingException e2) {
			e2.printStackTrace();
		}
		
		FolderSync syncReader = new FolderSync();
		syncReader.sync(FOLDER_PATH, model);

		// create new object of Tasks class to call all method that inside it.
//		Tasks tasks = new Tasks(FOLDER_PATH);
//
//		tasks.readAllFiles();
//		tasks.printSyncLines();
//		tasks.getSyncContent();
//		tasks.updateModel(listObjects);

	}
}

//
//List<Synchronization> listObjects = new ArrayList<Synchronization>();
//
//
//Path folderPath = Paths.get(FOLDER_PATH);
//
//// create data structure for all files's names and contents in the folder
//Map<String, List<String>> namesAndContents = new TreeMap<String, List<String>>();
//
//// call all list of the files in the folder
//List<String> fileNames = new ArrayList<>();
//
//try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
//	for (Path path : directoryStream) {
//		fileNames.add(path.toString());
//	}
//} catch (IOException ex) {
//	System.err.println("Error reading files");
//	ex.printStackTrace();
//}
//
//// go through the list of files in folder..
//for (String file : fileNames) {
//	System.out.println("the filename is :" + file);
//
//	try {
//		// put the generated file's name and its content into the data structure
//		List<String> content = Files.readAllLines(folderPath.resolve(file));
//		namesAndContents.put(file, content);
//
//	} catch (IOException e) {
//		e.printStackTrace();
//	Tasks tasks = new Tasks(file);
//
//	tasks.readAllFiles();
//	tasks.printSyncLines();
//	tasks.getSyncContent();
//	tasks.updateModel(listObjects);
//}

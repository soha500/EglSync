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
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.models.IModel;

public class FolderSync {

	public List<Synchronization> getAllTheSyncsRegionsOfTheFolder(String folder) {

		Path folderPath = Paths.get(folder);

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

		// create data structure for all files's names and contents in the folder
		Map<String, List<String>> namesAndContents = new TreeMap<String, List<String>>();

		List<Synchronization> allTheSyncRegionsInTheFolder = new ArrayList<Synchronization>();

		for (String file : fileNames) {
			System.out.println("the filename is :" + file);

			try {
				// put the generated file's name and its content into the data structure
				List<String> content = Files.readAllLines(folderPath.resolve(file));
				namesAndContents.put(file, content);

				FileSync fileSync = new FileSync(file);
				List<Synchronization> syncRegionsOfThisFile = fileSync.getAllTheSyncRegionsOfTheFile();
				allTheSyncRegionsInTheFolder.addAll(syncRegionsOfThisFile);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return allTheSyncRegionsInTheFolder;

	}

	public void checkSyncs(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {

		IPropertyGetter propertyGetter = model.getPropertyGetter();

		// IPropertySetter propertySetter = model.getPropertySetter();
		// int count = 0 ;

		for (Synchronization sync : allTheSyncsRegionOfTheFolder) {
			Object modelElement = model.getElementById(sync.getId());
			try {
				String modelAttribute = (String) propertyGetter.invoke(modelElement, sync.getAttribute());

				String modelContent = (String) sync.getContent();

				if (modelAttribute.equals(modelContent)) {
//				if ( modelElement.equals(sync.getId()) && modelAttribute.equals(sync.getAttribute())&& ! (modelContent.equals(sync.getContent()))) {

					// System.out.println("Sorry! there are two different values.");

				} else {
					Object modelElement1 = model.getElementById(sync.getId());

					IPropertySetter propertySetter1 = model.getPropertySetter();
					propertySetter1.setObject(modelElement1);
					propertySetter1.setProperty(sync.getAttribute());
					try {
						propertySetter1.invoke(sync.getContent());
					} catch (EolRuntimeException e) {
						e.printStackTrace();
					}

					// System.out.println(sync.getId());
					// System.out.println(sync.getAttribute());
					System.out.println(sync.getContent());

					model.store();

					System.out.println("the value of property have been updated");
					return;
				}

			} catch (EolRuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void updateTheModel(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {
		IPropertyGetter propertyGetter = model.getPropertyGetter();
		System.out.println("The value in the model now are:");

		for (Synchronization sync : allTheSyncsRegionOfTheFolder) {
			Object modelElement = model.getElementById(sync.getId());
			try {
				System.out.println(propertyGetter.invoke(modelElement, sync.getAttribute()));

			} catch (EolRuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		checkSyncs(model, allTheSyncsRegionOfTheFolder);

	}

	public void getSynchronization(String folder, IModel model) {

		List<Synchronization> allTheSyncRegionsInTheFolder = new ArrayList<Synchronization>();

		allTheSyncRegionsInTheFolder = getAllTheSyncsRegionsOfTheFolder(folder);

		for (Synchronization sync : allTheSyncRegionsInTheFolder) {
			System.out.println(sync.getId());
			System.out.println(sync.getAttribute());
			System.out.println(sync.getContent());
		}

		updateTheModel(model, allTheSyncRegionsInTheFolder);
	}

}

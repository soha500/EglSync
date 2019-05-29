package org.eclipse.epsilon.egl.sync;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

	public void checkSyncs(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {
		
		// create a data structure
		Map<String, Set<String>> valueInTheModel = new HashMap<String, Set<String>>();

		for (Synchronization sync : allTheSyncsRegionOfTheFolder) {

			IPropertyGetter propertyGetter = model.getPropertyGetter();
			Object modelElement = model.getElementById(sync.getId());
			String valueOfAttributeInTheModel;
			try {
				valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, sync.getAttribute());

				String valueOfAttributeInSyncRegion = (String) sync.getContent();
				
				String key = sync.getId() + "." + sync.getAttribute();
				// list of values
				Set<String> listOfAllValluesInSyncRegion = new HashSet<>();

				listOfAllValluesInSyncRegion.add(valueOfAttributeInSyncRegion);

				if (valueInTheModel.containsKey(key))
					valueInTheModel.get(key).add(valueOfAttributeInSyncRegion);
				else
					valueInTheModel.put(sync.getId() + "." + sync.getAttribute(), listOfAllValluesInSyncRegion);

				if (valueInTheModel.get(key).size() == 1) {

					if (valueOfAttributeInTheModel.equals(valueOfAttributeInSyncRegion)) {

						System.out.println("Do not do anything..");

					} else {
						System.out.println("Update the model");

						Object modelElement1 = model.getElementById(sync.getId());
						IPropertySetter propertySetter = model.getPropertySetter();
						propertySetter.setObject(modelElement1);
						propertySetter.setProperty(sync.getAttribute());
						try {
							propertySetter.invoke(sync.getContent());
						} catch (EolRuntimeException e) {
							e.printStackTrace();
						}
						model.store();
						return;
					}

				} else if (valueInTheModel.get(key).size() > 1) {

					if (valueOfAttributeInTheModel.equals(valueOfAttributeInSyncRegion)) {

						System.out.println("Sorry!! I connot do anything.. Don not know which to pick");

					} else {
						
						System.out.println("Find differnt one and Update the model");
						Object modelElement1 = model.getElementById(sync.getId());

						IPropertySetter propertySetter = model.getPropertySetter();
						propertySetter.setObject(modelElement1);
						propertySetter.setProperty(sync.getAttribute());
						try {
							propertySetter.invoke(sync.getContent());
						} catch (EolRuntimeException e) {
							e.printStackTrace();
						}
						model.store();
						//return;
					}

				} else {
					System.out.println(" ");
				}

			} catch (EolRuntimeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}


//public void checkSyncs(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {
//
//	IPropertyGetter propertyGetter = model.getPropertyGetter();
//
//
//	for (Synchronization sync : allTheSyncsRegionOfTheFolder) {
//		Object modelElement = model.getElementById(sync.getId());
//		
//	
//		try {
//			String valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, sync.getAttribute());
//
//			String contentOfSyncRegion = (String) sync.getContent();
//
//			if ((valueOfAttributeInTheModel.equals(contentOfSyncRegion))) {
//
//
//			}  else  {
//				Object modelElement1 = model.getElementById(sync.getId());
//
//				IPropertySetter propertySetter1 = model.getPropertySetter();
//				propertySetter1.setObject(modelElement1);
//				propertySetter1.setProperty(sync.getAttribute());
//				try {
//					propertySetter1.invoke(sync.getContent());
//				} catch (EolRuntimeException e) {
//					e.printStackTrace();
//				}
//
//				model.store();
//
//				System.out.println("the value of property has been updated");
//				return;
//			}
//
//		} catch (EolRuntimeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}


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

		//		for (Synchronization sync : allTheSyncsRegionOfTheFolder) {
		//			
		//			IPropertyGetter propertyGetter = model.getPropertyGetter();
		//			Object modelElement = model.getElementById(sync.getId());
		//			try {
		//				System.out.println(propertyGetter.invoke(modelElement, sync.getAttribute()));
		//				//System.out.println();
		//
		//			} catch (EolRuntimeException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}
		//		}

		checkSyncs(model, allTheSyncsRegionOfTheFolder);

	}
	/*
	 * //				Pattern p = Pattern.compile("(\\s+\\w+,\\s+\\w+)");  //Worked..
////			Pattern p = Pattern.compile("\\/\\*\\s*sync\\s+(.+)\\s*,\\s*(\\w+)\\s*\\*\\/\\s*(\\w+)\\s*\\/\\*\\s*endSync\\s*\\*\\/"); // The whole line with sync and endsync 
////			Pattern p = Pattern.compile("\\s+(.+)\\s*,\\s*(\\w+)\\s*\\*\\/\\s*(\\w+)\\s*\\/\\*\\s*endSync\\s*\\*\\/");  //three groups
//				Pattern p = Pattern.compile("\\/\\*sync\\s+(.+\\s*,\\s*\\w+)\\s*\\*\\/\\s*(\\w+)\\s*\\/\\*\\s*endSync\\s*\\*\\/"); // Two groups
////			Pattern p = Pattern.compile("\\/\\*\\s*sync\\s+(.+\\w+\\s*\\*\\/\\s*\\w+)\\s*\\/\\*\\s*endSync\\s*\\*\\/");  //One group with sync and endsync
////			Pattern p = Pattern.compile("\\s+(.+\\w+\\s*\\*\\/\\s*\\w+)\\s*");  //One group without sync and endsync
// 
	 */

	public void getSynchronization(String folder, IModel model) {

		List<Synchronization> allTheSyncRegionsInTheFolder = new ArrayList<Synchronization>();

		allTheSyncRegionsInTheFolder = getAllTheSyncsRegionsOfTheFolder(folder);

		for (Synchronization sync : allTheSyncRegionsInTheFolder) {
			//			System.out.println(sync.getId());
			//			System.out.println(sync.getAttribute());
			//			System.out.println(sync.getContent());
		}

		updateTheModel(model, allTheSyncRegionsInTheFolder);
	}

public void checkSyncs(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {

		// create a data structure
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		IPropertyGetter propertyGetter = model.getPropertyGetter();

		for (Synchronization sync : allTheSyncsRegionOfTheFolder) {

			try {

				Object modelElement = model.getElementById(sync.getId());

				String valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, sync.getAttribute());

				String valueOfAttributeInSyncRegion = (String) sync.getContent();

				// new array without duplicated values
				Set<String> valuesInSyncRegionWithoutDuplactie = new HashSet<>();

				// Concatenation Id and attribute in model to have one key
				String key = sync.getId() + "." + sync.getAttribute();

				valuesInSyncRegionWithoutDuplactie.add(valueOfAttributeInSyncRegion);

				if (map.containsKey(key))
					map.get(key).add(valueOfAttributeInSyncRegion);
				else
					map.put(key, valuesInSyncRegionWithoutDuplactie);

			} catch (EolRuntimeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			String[] id_attr = (String[]) key.split("\\.");
			String id = id_attr[0];
			String attribute = id_attr[1];
			ArrayList<String> values = new ArrayList(entry.getValue());
			Object modelElement = model.getElementById(id);

			String valueOfAttributeInTheModel;
			try {
				valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, attribute);

				if (values.size() == 1) {
					if (valueOfAttributeInTheModel.equals(values.get(0))) {

						System.out.println("size 1, same value in the model " + valueOfAttributeInTheModel);

					} else {
						System.out.println("size 1, but differnt value from the one that in the model "
								+ valueOfAttributeInTheModel);

						Object modelElement1 = model.getElementById(id);
						IPropertySetter propertySetter = model.getPropertySetter();
						propertySetter.setObject(modelElement1);
						propertySetter.setProperty(attribute);
						try {
							propertySetter.invoke(values.get(0));
						} catch (EolRuntimeException e) {
							e.printStackTrace();
						}
						model.store();
					}

				} else if (values.size() == 2) {

					if (valueOfAttributeInTheModel.equals(values.get(0))) {
						System.out.println("two different values but one of them same the one that in the model ");
						Object modelElement1 = model.getElementById(id);

						IPropertySetter propertySetter = model.getPropertySetter();
						propertySetter.setObject(modelElement1);
						propertySetter.setProperty(attribute);
						try {
							propertySetter.invoke(values.get(1));
						} catch (EolRuntimeException e) {
							e.printStackTrace();
						}
						model.store();

					} else if (valueOfAttributeInTheModel.equals(values.get(1))) {
						Object modelElement1 = model.getElementById(id);

						IPropertySetter propertySetter = model.getPropertySetter();
						propertySetter.setObject(modelElement1);
						propertySetter.setProperty(attribute);
						try {
							propertySetter.invoke(values.get(0));
						} catch (EolRuntimeException e) {
							e.printStackTrace();
						}
						model.store();

					} else {
						System.out.println("two different values ");

					}

				} else {
					System.out.println("more than two values");
				}
			} catch (EolRuntimeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}

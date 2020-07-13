package org.eclipse.epsilon.egl.sync;

import java.io.IOException;
import java.lang.reflect.Array;
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

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.models.IModel;

import com.sun.glass.ui.CommonDialogs.Type;

public class FolderSync {

	public List<Synchronization> getAllTheSyncsRegionsOfTheFolder(String folder) {
		Path folderPath = Paths.get(folder);
		List<String> fileNames = new ArrayList<>();
		
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
			for (Path path : directoryStream) {
				fileNames.add(path.toString());
			}
		} catch (IOException ex) {
			System.err.println("Error reading files");
		}

		// create data structure for all files's names and contents in the folder
		Map<String, List<String>> namesAndContents = new TreeMap<String, List<String>>();
		List<Synchronization> allTheSyncRegionsInTheFolder = new ArrayList<Synchronization>();

		for (String file : fileNames) {
			try {
				List<String> content = Files.readAllLines(Paths.get(file));
				namesAndContents.put(file, content);
				FileSync fileSync = new FileSync(file);
				List<Synchronization> syncRegionsOfThisFile = fileSync.getAllTheSyncRegionsOfTheFile();
				if (syncRegionsOfThisFile == null) {
					return null;
				}
				allTheSyncRegionsInTheFolder.addAll(syncRegionsOfThisFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return allTheSyncRegionsInTheFolder;
	}

	public void checkSyncs(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {
		// create a data structure
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		IPropertyGetter propertyGetter = model.getPropertyGetter();

		for (Synchronization sync : allTheSyncsRegionOfTheFolder) {

			if (model.getElementById(sync.getId()) == null) {
				System.err.println("Sorry! There's no respictive id in the model: " + sync.getId());
				System.exit(0);
			}
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
		}

		for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			String[] id_attr = (String[]) key.split("\\.");
			String id = id_attr[0];
			String attribute = id_attr[1];

			ArrayList<String> values = new ArrayList<String>(entry.getValue());

			int type = 0;

			Object modelElement = model.getElementById(id);
			Object valueOfAttributeInTheModel = null;
			try {
				Object property = propertyGetter.invoke(modelElement, attribute);
				String str = propertyGetter.invoke(modelElement, attribute).toString();
				if (property instanceof String) {
					valueOfAttributeInTheModel = str;
					type = 0;
				} else if (property instanceof Integer) {
					valueOfAttributeInTheModel = Integer.parseInt(str);
					type = 1;
				} else if (property instanceof Double) {
					valueOfAttributeInTheModel = Double.parseDouble(str);
					type = 2;
				} else if (property instanceof Float) {
					valueOfAttributeInTheModel = Float.parseFloat(str);
					type = 3;
				} else if (property instanceof Boolean) {
					valueOfAttributeInTheModel = Boolean.parseBoolean(str);
					type = 4;
				} else if (property instanceof Long) {
					valueOfAttributeInTheModel = Long.parseLong(str);
					type = 5;
				} else if (property instanceof Short) {
					valueOfAttributeInTheModel = Short.parseShort(str);
					type = 6;
				} else if (property instanceof Byte) {
					valueOfAttributeInTheModel = Byte.parseByte(str);
					type = 7;
				} else {
					System.err.println("Other wrong type");
				}

				// First condition
				if (values.size() == 1) {
					// Case 1-a:
					if ((valueOfAttributeInTheModel.toString()).equals(values.get(0))) {
//						System.out.println("size 1, same value in the model: " + valueOfAttributeInTheModel);
					// Case 1-a:
					} else {
//						System.out.println("size 1, but differnt value from the one that in the model: " + valueOfAttributeInTheModel);

						DataTypes.getModelValue(model, id, attribute, type, values, 0);

					}
					// Second condition
				} else if (values.size() == 2) {
					// Case 2-a:
					if ((valueOfAttributeInTheModel.toString()).equals(values.get(0)) && !(valueOfAttributeInTheModel.toString()).equals(values.get(1))) {
//						System.out.println("Size 2, two different values but one of them same the one that in the model: " + valueOfAttributeInTheModel);

						DataTypes.getModelValue(model, id, attribute, type, values, 1);

					// Case 2-b:
					} else if ((valueOfAttributeInTheModel.toString()).equals(values.get(1)) && !(valueOfAttributeInTheModel.toString()).equals(values.get(0))) {
//						System.out.println("Size 2, two different values but one of them same the one that in the model : " + valueOfAttributeInTheModel);

						DataTypes.getModelValue(model, id, attribute, type, values, 0);
					// Case 3:
					} else {
//						System.err.println("Size 2, two different values from the one in the model.");
						System.exit(0);
					}

				} else {
//					System.err.println("Sorry! two or more different values from the one in the model.");
					System.exit(0);
				}
			} catch (EolRuntimeException e1) {
				e1.printStackTrace();
			}
		}
	}

	// -------------------------------------- check model method
	public String checkModelAgainstEachSyncRegion(IModel model, List<Synchronization> allTheSyncsRegionsOfTheFolder) {

		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		DataTypes dt = new DataTypes();
		IPropertyGetter propertyGetter = model.getPropertyGetter();

		for (Synchronization sync : allTheSyncsRegionsOfTheFolder) {

			String valueOfAttributeInTheModel;
			// a.The respective element is found
			if ((model.getElementById(sync.id) != null)) {

//				System.out.println("The respective element is found");
				// b. The value of the region is checked against the type of the respective property
				try {
					valueOfAttributeInTheModel = propertyGetter
							.invoke(model.getElementById(sync.id), sync.getAttribute()).toString();
				} catch (EolRuntimeException e1) {
					System.err.println("Sorry! There's no respictive attribute in the model: " + sync.getAttribute());
					return "The respective attribute is not found";
				}
				// for all types
				if (dt.isCompatibale(sync.content, valueOfAttributeInTheModel)) {
//					System.out.println("type is compatible");
				} else {
					System.err.println(" Sorry! The value's types are not compatible ");
					return "Incompatible type";
				}
			} else {
				System.err.println("The respective element not found");
				return "The respictive element not found";
			}
		}
		return "finish";
	}

	public String updateTheModel(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {

		String stepCheck = "finish";
		stepCheck = checkModelAgainstEachSyncRegion(model, allTheSyncsRegionOfTheFolder);
		if (!stepCheck.equals("finish"))
			return stepCheck;

		checkSyncs(model, allTheSyncsRegionOfTheFolder);
			System.out.println("All sync regions are without conflicts or errors. Thus, model has been updated.");
		return stepCheck;
	}

	public String getSynchronization(String folder, IModel model) {

		List<Synchronization> allTheSyncRegionsInTheFolder = new ArrayList<Synchronization>();

		allTheSyncRegionsInTheFolder = getAllTheSyncsRegionsOfTheFolder(folder);

		if (allTheSyncRegionsInTheFolder == null)
			return "Misformated or incompleted";
		String result = updateTheModel(model, allTheSyncRegionsInTheFolder);
		return result;
	}

}
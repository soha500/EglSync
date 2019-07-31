package org.eclipse.epsilon.egl.sync;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.models.IModel;

public class SyncAppFromModel {

	//League
	//private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/GeneratedFileFromLeague";
	private static final String FOLDER_PATH ="./SimpleExample/GeneratedFileFromLeague";

	public static void main(String[] args) throws EolModelLoadingException, IOException {

		// League ecore and model
		EmfModel model = new EmfModel();
		model.setName("M");
		model.setMetamodelFile(new File("SimpleExample/ModelLeague/League.ecore").getAbsolutePath());
		model.setModelFile(new File("SimpleExample/ModelLeague/League.model").getAbsolutePath());
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(true);

		try {
			model.load();
		} catch (EolModelLoadingException e2) {
			e2.printStackTrace();
		}

		FolderSync syncReader = new FolderSync();
		syncReader.getSynchronization(FOLDER_PATH, model);

	}
}






























//old one 
//private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/All-Generated-Files";


//hospital
//private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync";



//model.setMetamodelFile(new File("Statemachine.ecore").getAbsolutePath());
//model.setModelFile(new File("SimpleExample/models/Statemachine.model").getAbsolutePath());

// hospital
//model.setMetamodelFile(new File("Hospital.ecore").getAbsolutePath());
//model.setModelFile(new File("Hospital.model").getAbsolutePath());		


//public void checkSyncs(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {
//	
//	// create a data structure
//	Map<String, Set<String>> valueInTheModel = new HashMap<String, Set<String>>();
//
//	for (Synchronization sync : allTheSyncsRegionOfTheFolder) {
//
//		IPropertyGetter propertyGetter = model.getPropertyGetter();
//		Object modelElement = model.getElementById(sync.getId());
//		String valueOfAttributeInTheModel;
//		try {
//			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, sync.getAttribute());
//
//			String valueOfAttributeInSyncRegion = (String) sync.getContent();
//			
//			String key = sync.getId() + "." + sync.getAttribute();
//			// list of values
//			Set<String> listOfAllValluesInSyncRegion = new HashSet<>();
//
//			//for (String synchronization : listOfAllValluesInSyncRegion) {	
//			
//			listOfAllValluesInSyncRegion.add(valueOfAttributeInSyncRegion);
//
//			if (valueInTheModel.containsKey(key))
//				valueInTheModel.get(key).add(valueOfAttributeInSyncRegion);
//			else
//				valueInTheModel.put(sync.getId() + "." + sync.getAttribute(), listOfAllValluesInSyncRegion);
//
//			if (valueInTheModel.get(key).size() == 1) {
//
//				if (valueOfAttributeInTheModel.equals(valueOfAttributeInSyncRegion)) {
//
//					System.out.println("size 1, same value in the model " + valueOfAttributeInSyncRegion);
//
//				} else {
//					System.out.println("size 1, but differnt value from the one that in the model " + valueOfAttributeInSyncRegion);
//
//					Object modelElement1 = model.getElementById(sync.getId());
//					IPropertySetter propertySetter = model.getPropertySetter();
//					propertySetter.setObject(modelElement1);
//					propertySetter.setProperty(sync.getAttribute());
//					try {
//						propertySetter.invoke(sync.getContent());
//					} catch (EolRuntimeException e) {
//						e.printStackTrace();
//					}
//					model.store();
//					return;
//				}
//
//			} else if (valueInTheModel.get(key).size() > 1) {
//
//				if (valueOfAttributeInTheModel.equals(valueOfAttributeInSyncRegion)) {
//
//					System.out.println("Sorry!! I connot do anything.. Don not know which to pick");
//
//				} else {
//					
//					System.out.println("Find differnt one and Update the model");
//					Object modelElement1 = model.getElementById(sync.getId());
//
//					IPropertySetter propertySetter = model.getPropertySetter();
//					propertySetter.setObject(modelElement1);
//					propertySetter.setProperty(sync.getAttribute());
//					try {
//						propertySetter.invoke(sync.getContent());
//					} catch (EolRuntimeException e) {
//						e.printStackTrace();
//					}
//					model.store();
//					
//					//return;
//				}
//
//			} else {
//				System.out.println(" ");
//			}
//		//}
//		} catch (EolRuntimeException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//	}
//
//}
//}




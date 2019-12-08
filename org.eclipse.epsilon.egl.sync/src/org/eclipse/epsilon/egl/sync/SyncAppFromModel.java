package org.eclipse.epsilon.egl.sync;

import static org.junit.Assert.assertEquals;

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
import org.junit.Test;

public class SyncAppFromModel {

	//League
	//private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/GeneratedFileFromLeague";
	//private static final String FOLDER_PATH ="./SimpleExample/GeneratedFileFromLeague";
	// this works with updating 
	private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/GeneratedFilesFromUniversity/Test1";
	
	//this works and automatically generates the files without need to all url but 
	// dose not work with updating after i added ./ it works with updating
	//private static final String FOLDER_PATH ="./SimpleExample/GeneratedFilesFromUniversity";
	
	public static void main(String[] args) throws EolModelLoadingException, IOException {

		// League ecore and model
		EmfModel model = new EmfModel();
		model.setName("M");
		// League
//		model.setMetamodelFile(new File("SimpleExample/ModelLeague/League.ecore").getAbsolutePath());
//		model.setModelFile(new File("SimpleExample/ModelLeague/League.model").getAbsolutePath());
//		
		// University
		model.setMetamodelFile(new File("SimpleExample/Model-University/University.ecore").getAbsolutePath());
		model.setModelFile(new File("SimpleExample/Model-University/University.model").getAbsolutePath());
		
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(true);

		try {
			model.load();
		} catch (EolModelLoadingException e2) {
			//e2.printStackTrace();

			System.err.println("The model element is not found in model: " + e2.getMessage());
		} 
//		catch (Exception e) {
//			System.err.println("The model element is not found in model: " + e.getMessage());
//		}
		

		FolderSync syncReader = new FolderSync();
		syncReader.getSynchronization(FOLDER_PATH, model);

	}
}

















// All test for the three scenarios 

//// add the new value to the generated file.
//lines.set(lineNumber++, "hello");
//
//while(!original.readLine().contains("//endSync")) {
//	if(!lines.contains("//endSync")) {
//		lines.remove(lineNumber);


/*
 * 1. Loop through the file until it runs out of lines.
 * 2. If a line does not contain //sync, add it to the List of new lines
 * 3. If a line contains //sync, special case:
 * 3a. add the //sync line to the List of new lines
 * 3b. add the new value to the List of new lines
 * 3c. Loop until //endSync
 */




//
////test first scenario when the value equals 1 
//@Test
//public void test1() throws IOException{ 
//	
//	
//	FolderSync folderSync = new FolderSync();
//	
//	String value = folderSync.getSynchronization(FOLDER_PATH + "TestScenario1", model);
//	
//	
//	assertEquals("test 1", "hi this is new text1", value);
//	
//	
//}
////test first scenario when the value equals 2 / 
//@Test
//public void test2() throws IOException{ 
//	
//	FolderSync folderSync = new FolderSync();
//	
//	String value = folderSync.getSynchronization(FOLDER_PATH + "TestScenario2", model);
//	
//	
//	assertEquals("test 2", "hi", value);
//	
//	
//	
//}
////test first scenario when there are two or more different values / here just return message 
//@Test
//public void test3() throws IOException{
//	
//	FolderSync folderSync = new FolderSync();
//	
//	String value = folderSync.getSynchronization(FOLDER_PATH +"TestScenario3", model);
//	
//	
//	assertEquals("test 3", "z", value);	
//	
//}




























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




//------------------------------------------------------------- Test 1------------------------------------------------------------------------------------		
	// Scenario 1, There is one sync regions which contains different  value  from the one in the model.	
	
//	@Test
//	public void test1() throws IOException {
//		String pathString = FOLDER_PATH + "/Premier League .html";
//
//		Path path = Paths.get(pathString);
//
//		BufferedReader original = new BufferedReader(new FileReader(pathString));
//
//		String line;
//		int lineNumber = 1;
//
//		while ((line = original.readLine()) != null && !line.contains("//sync _zQ-iAKRVEemMKZVsE_d7nA, refereeReport"))
//			lineNumber++;
//
////		if (!line.contains("//sync _zQ-iAKRVEemMKZVsE_d7nA, refereeReport")) {
////			
////		} else if (!line.contains("//endsync")){
////
////		}else {
////			System.out.println("---");
////		}
//		original.close();
//
//		List<String> lines = Files.readAllLines(path);
//
//		lines.set(lineNumber, "hello");
//
//		Files.write(path, lines);
//
//		FolderSync folderSync = new FolderSync();
//		folderSync.getSynchronization(FOLDER_PATH, model);
//
//		IPropertyGetter propertyGetter = model.getPropertyGetter();
//		Object modelElement = model.getElementById("_zQ-iAKRVEemMKZVsE_d7nA");
//		String valueOfAttributeInTheModel = null;
//		try {
//			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "refereeReport");
//			// System.out.println(valueOfAttributeInTheModel);
//		} catch (EolRuntimeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// check if the value in the model equals the actual value
//		assertEquals("test 1", "hello", valueOfAttributeInTheModel);
//
//	}






//------------------------------------------------------------- Test 4---------------------------------------------------------------------------------------------		
// There are two sync regions but I removed //endsync what will happen. close test 2 before run this..	

//@Test
//public void test4() throws IOException {
//	String pathString = FOLDER_PATH + "/Premier League .html";
//
//	Path path = Paths.get(pathString);
//
//	BufferedReader original = new BufferedReader(new FileReader(pathString));
//
//	String line;
//	int lineNumber = 1;
//	List<String> lines = null;
//	int count = 0;
//	
//	while ((line = original.readLine()) != null && !line.contains("//sync _zQ-iAKRVEemMKZVsE_d7nA, journalistReport")) 
//		lineNumber++;
//
//	original.close();
//
//	lines = Files.readAllLines(path);
//
//	lines.set(lineNumber, "hello");
//
//
//	Files.write(path, lines);
//
//	FolderSync folderSync = new FolderSync();
//	folderSync.getSynchronization(FOLDER_PATH, model);
//
//	IPropertyGetter propertyGetter = model.getPropertyGetter();
//	Object modelElement = model.getElementById("_zQ-iAKRVEemMKZVsE_d7nA");
//	String valueOfAttributeInTheModel = null;
//	try {
//		valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "journalistReport");
//	} catch (EolRuntimeException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	// Check to see the result...
//	
//	//assertEquals("test 2", "Hello", valueOfAttributeInTheModel);
//
//}

//------------------------------------------------------------- Test 3------------------------------------------------------------------------------		
// Scenario 3, There are three or more sync regions with two or more different values from the one in the model.	

//@Test
//public void test5() throws IOException {
//	String pathString = FOLDER_PATH + "/Premier League .html";
//
//	Path path = Paths.get(pathString);
//
//	BufferedReader original = new BufferedReader(new FileReader(pathString));
//
//	String line;
//	int lineNumber = 1;
//
//	while ((line = original.readLine()) != null && !line.contains("_bcxmYaJ7EemFaP2SuqnJFg, name"))
//		lineNumber++;
//
//	original.close();
//
//	List<String> lines = Files.readAllLines(path);
//
//	lines.set(lineNumber, "hello");
//
//	Files.write(path, lines);
//
//	FolderSync folderSync = new FolderSync();
//	folderSync.getSynchronization(FOLDER_PATH, model);
//
//	IPropertyGetter propertyGetter = model.getPropertyGetter();
//	Object modelElement = model.getElementById("_bcxmYaJ7EemFaP2SuqnJFg");
//	String valueOfAttributeInTheModel;
//	try {
//		valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "name");
//	} catch (EolRuntimeException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	// check if the value in the model remains the same because there are two or
//	// more different values in sync regions
//
//	// assertEquals("test 3", "3", valueOfAttributeInTheModel);
//
//}

//------------------------------------------------------------- Test 4---------------------------------------------------------------------------------------------		
// There are two sync regions but I removed //endsync what will happen. close test 2 before run this..	

//@Test
//public void test4() throws IOException {
//	String pathString = FOLDER_PATH + "/Premier League .html";
//
//	Path path = Paths.get(pathString);
//
//	BufferedReader original = new BufferedReader(new FileReader(pathString));
//
//	String line;
//	int lineNumber = 1;
//	List<String> lines = null;
//	int count = 0;
//	
//	while ((line = original.readLine()) != null && !line.contains("//sync _zQ-iAKRVEemMKZVsE_d7nA, journalistReport")) 
//		lineNumber++;
//
//	original.close();
//
//	lines = Files.readAllLines(path);
//
//	lines.set(lineNumber, "hello");
//
//
//	Files.write(path, lines);
//
//	FolderSync folderSync = new FolderSync();
//	folderSync.getSynchronization(FOLDER_PATH, model);
//
//	IPropertyGetter propertyGetter = model.getPropertyGetter();
//	Object modelElement = model.getElementById("_zQ-iAKRVEemMKZVsE_d7nA");
//	String valueOfAttributeInTheModel = null;
//	try {
//		valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "journalistReport");
//	} catch (EolRuntimeException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	// Check to see the result...
//	
//	//assertEquals("test 2", "Hello", valueOfAttributeInTheModel);
//assertTrue(valueOfAttributeInTheModel.compareTo("hi") == 0 || valueOfAttributeInTheModel.compareTo("hello") == 0);
//
//}






package org.eclipse.epsilon.egl.sync;

import java.io.File;
import java.io.FileReader;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;

public class SyncApp {
	
	public static void main(String[] args) throws Exception {
	
		
		EmfModel model = new EmfModel();
		model.setName("M");

		//University-Last-Project    //this works and automatically generates the files without need to all url and with updating
		model.setMetamodelFile(new File("SyncTests/Model-University/University.ecore").getAbsolutePath());
		model.setModelFile(new File("SyncTests/Model-University/University.model").getAbsolutePath());	
		
//		// for the boiler test
//		model.setMetamodelFile(new File("/org.eclipse.epsilon.examples.egl.comps/comps.ecore").getAbsolutePath());
//		model.setModelFile(new File("/org.eclipse.epsilon.examples.egl.comps/BoilerController.model").getAbsolutePath());
		
//		// this is for the boiler test in this workspace == it works
//		model.setMetamodelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/boiler/comps.ecore").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/boiler/BoilerController.model").getAbsolutePath());
		
		
//		// for the boiler test in other workspace
//		model.setMetamodelFile(new File("/Users/sultanalmutairi/git/Epsilon-Source/org.eclipse.epsilon/examples/org.eclipse.epsilon.examples.egl.comps/comps.ecore").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/git/Epsilon-Source/org.eclipse.epsilon/examples/org.eclipse.epsilon.examples.egl.comps/BoilerController.model").getAbsolutePath());

		
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(true);
		model.load();
		
		// Getting an element from an ID   
		Object openState = model.getElementById("_a7rXYF25EeiOVIR7pFwT6g");
		System.out.println(openState);
		
		IPropertySetter propertySetter = model.getPropertySetter();
		
		// Updating the action property of the open state
		propertySetter.setObject(openState);
		propertySetter.setProperty("action");
		propertySetter.invoke("Hello from Java");
		
		model.dispose();
			
	}	
}







































/// Data types
//int type = 0;
//
//Object modelElement = model.getElementById(id);
//
//Object valueOfAttributeInTheModel = null;
//try {
//
//	if (propertyGetter.invoke(modelElement, attribute) instanceof String) {
//
//		valueOfAttributeInTheModel = propertyGetter.invoke(modelElement, attribute).toString();
//		type =0;
//	} else if (propertyGetter.invoke(modelElement, attribute) instanceof Integer) {
//		valueOfAttributeInTheModel = Integer.parseInt(propertyGetter.invoke(modelElement, attribute).toString());
//		type =1;	
//	} 
//	else if (propertyGetter.invoke(modelElement, attribute) instanceof Double) {
//		valueOfAttributeInTheModel = Double.parseDouble(propertyGetter.invoke(modelElement, attribute).toString());
//		type = 2;
//	}
//	else if (propertyGetter.invoke(modelElement, attribute) instanceof Float) {
//
//		valueOfAttributeInTheModel = Float.parseFloat(propertyGetter.invoke(modelElement, attribute).toString());
//		type = 3;
//	}
//	else if (propertyGetter.invoke(modelElement, attribute) instanceof Boolean) {
//
//		valueOfAttributeInTheModel = Boolean.parseBoolean(propertyGetter.invoke(modelElement, attribute).toString());
//		type = 4;
//	}
//	else if (propertyGetter.invoke(modelElement, attribute) instanceof Long) {
//
//		valueOfAttributeInTheModel = Long.parseLong(propertyGetter.invoke(modelElement, attribute).toString());
//		type = 5;
//	}
//	else if (propertyGetter.invoke(modelElement, attribute) instanceof Short) {
//
//		valueOfAttributeInTheModel = Short.parseShort(propertyGetter.invoke(modelElement, attribute).toString());
//		type = 6;
//	}
//	else if (propertyGetter.invoke(modelElement, attribute) instanceof Byte) {
//
//		valueOfAttributeInTheModel = Byte.parseByte(propertyGetter.invoke(modelElement, attribute).toString());
//		type = 7;
//	}
//	else {
//		System.err.println("Other wrong type");
//	}



//try {
//// Integer.parseInt
//if (type == 0) {
//	propertySetter.invoke(values.get(0));		
//} else
//if (type == 1) {
//	propertySetter.invoke(Integer.parseInt(values.get(0)));	
//} else
//if (type == 2) {
//	propertySetter.invoke(Double.parseDouble(values.get(0)));
//	
//} else
//if (type == 3) {
//	propertySetter.invoke(Float.parseFloat(values.get(0)));
//} else
//if (type == 4) {
//	propertySetter.invoke(Boolean.parseBoolean(values.get(0)));
//} else
//if (type == 5) {
//	propertySetter.invoke(Long.parseLong(values.get(0)));
//} else
//if (type == 6) {
//	propertySetter.invoke(Short.parseShort(values.get(0)));
//} else
//if (type == 7) {
//	propertySetter.invoke(Byte.parseByte(values.get(0)));
//}
//
//} catch (EolRuntimeException e) {
//e.printStackTrace();
//}


//try {
//if (type == 0) {
//	propertySetter.invoke(values.get(1));
//	
//}
//if (type == 1) {
//	propertySetter.invoke(Integer.parseInt(values.get(1)));	
//}
//if (type == 2) {
//	propertySetter.invoke(Double.parseDouble(values.get(1)));
//	
//}
//if (type == 3) {
//	propertySetter.invoke(Float.parseFloat(values.get(1)));
//}
//if (type == 4) {
//	propertySetter.invoke(Boolean.parseBoolean(values.get(1)));
//}
//if (type == 5) {
//	propertySetter.invoke(Long.parseLong(values.get(1)));
//}
//if (type == 6) {
//	propertySetter.invoke(Short.parseShort(values.get(1)));
//}
//if (type == 7) {
//	propertySetter.invoke(Byte.parseByte(values.get(1)));
//}
//} catch (EolRuntimeException e) {
//e.printStackTrace();
//}




//try {
//	if (type == 0) {
//		propertySetter.invoke(values.get(0));
//		
//	}
//	if (type == 1) {
//		propertySetter.invoke(Integer.parseInt(values.get(0)));	
//	}
//	if (type == 2) {
//		propertySetter.invoke(Double.parseDouble(values.get(0)));
//		
//	}
//	if (type == 3) {
//		propertySetter.invoke(Float.parseFloat(values.get(0)));
//	}
//	if (type == 4) {
//		propertySetter.invoke(Boolean.parseBoolean(values.get(0)));
//	}
//	if (type == 5) {
//		propertySetter.invoke(Long.parseLong(values.get(0)));
//	}
//	if (type == 6) {
//		propertySetter.invoke(Short.parseShort(values.get(0)));
//	}
//	if (type == 7) {
//		propertySetter.invoke(Byte.parseByte(values.get(0)));
//	}
//
//} catch (EolRuntimeException e) {
//	e.printStackTrace();
//}





//without creating new object call modelElement1 we already create 1 call modelElement in the top of this method

//Object modelElement1 = model.getElementById(id);
//IPropertySetter propertySetter = model.getPropertySetter();
//propertySetter.setObject(modelElement1);
//propertySetter.setProperty(attribute);






























//@Test
//public void modifiyAndUpdateTheValueOfSyncRegion() throws IOException{ 
//	
//	//File file = tempFolder.newFolder("testfolder");
//	String pathString = FOLDER_PATH + "/Premier League .html";
//
//	Path path = Paths.get(pathString);
//	// Read the file.
//	BufferedReader original = new BufferedReader(new FileReader(pathString));
//
//	// Find the content for the sync region.
//	String line;
//	int lineNumber = 1;
//	
//	// loop through all the lines in the file until //sync
//	while((line = original.readLine()) != null && !line.contains("//sync") )
//		lineNumber++;
//			
//	original.close();
//	
//	List<String> lines = Files.readAllLines(path);
//	
//	// add the new value to the generated file.
//	lines.set(lineNumber, "hello");
//	
//	
//	// Save the file.
//	Files.write(path, lines);
//	
//	// send new value back to the model
//	FolderSync folderSync = new FolderSync();
//
//    //assertTrue(folderSync.updateTheModel(model, folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH)));
//
//		
//}	












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
////test first scenario when there are two or more different values
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
















////test first scenario when the value equals 1 
//@Test
//public void test1() throws IOException{ 
//	
//	
//	FolderSync folderSync = new FolderSync();
//	
//	String value = folderSync.getSynchronization(FOLDER_PATH, model);
//	
//	String output = null;
//	for(Map.Entry<String, Set<String>> hMap : map.entrySet()) {
//		if("_zQ-iAKRVEemMKZVsE_d7nA.refereeReport".equalsIgnoreCase(hMap.getKey())) {
//			output = String.join(",", hMap.getValue());
//		}
//	}
//	
//	assertEquals("test 1", "hi this is new text", output);
//	
//	//assertEquals();
//	
//}
////test first scenario when the value equals 2 / 
//@Test
//public void test2() throws IOException{ 
//	
//	FolderSync folderSync = new FolderSync();
//	
//	Map<String, Set<String>> map = folderSync.getSynchronization(FOLDER_PATH, model);
//	
//	/* Iterate the map
//	 * get the Key and value for [1]
//	 * assert your test result
//	 *  */
//	
//	System.out.println("test");
//	
//	//assertEquals("test 1", "hi", map.get(id)))
//	
//	//assertEquals();
//}
////test first scenario when there are two or more different values / here just return message 
//@Test
//public void test3() throws IOException{
//	
//	FolderSync folderSync = new FolderSync();
//	
//	Map<String, Set<String>> map = folderSync.getSynchronization(FOLDER_PATH, model);

//	
//	System.out.println("test");
//	
//	//assertEquals("test 1", "hi", map.get(id)))
//	
//	//assertEquals();
//		
//}




































//
//@Test
//public void runTheReverseProssesToUpdateTheModel() {
//// here i should pass the folderSync in order to update the model
//	FolderSync folderSync = new FolderSync();
//	
//	
//	//assertTrue(folderSync.updateTheModel(model, allTheSyncsRegionOfTheFolder));
//	
//	
//}

//   @Test
//   public void testWrite() throws IOException {
//     // Create a temporary file.
//     final File tempFile = tempFolder.newFile("tempFile.txt");
//   
//     // Write something to it.
//     FileUtils.writeStringToFile(tempFile, "hello world");
//   
//     // Read it from temp file
//     final String s = FileUtils.readFileToString(tempFile);
//   
//     // Verify the content
//     Assert.assertEquals("hello world", s);
//      
//     //Note: File is guaranteed to be deleted after the test finishes.
//   }
   
//@Test
//public void testCreateFolder() throws IOException{
//    File file = tempFolder.newFolder("testfolder");
//    assertTrue(file.exists());
//}
// 
//@Test
//public void testDeleteFolder() throws IOException{
//    File file = tempFolder.newFile("testfolder");
//    file.delete();
//    assertFalse(file.exists());
//}	
//@Test
//// Here I expect to have 4 regions as i have in the html file/ it works as
//public void getNumberOfSyncRegions() {
//	FolderSync folderSync = new FolderSync();
//	int expected = 4;// sync regions
//	List<Synchronization> syncRegions = folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//	int actual = syncRegions.size();
//	
//	assertEquals(expected, actual);
//}



//// In html file I expected 4 managers..
////@Test
////public void testManagers() throws SAXException, IOException, ParserConfigurationException {
////	//var regions = syncReader.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
////	FolderSync folderSync = new FolderSync();
////	int expected = 4 ;// sync managers
////	int num = folderSync.getManager();
////	 
////	int actual = num;
////			
////	assertEquals(expected, actual);	
////	
////}
////
////// In html file I expected Goalkeeper for Manchester is David de Gea and his age is 25.. 
////@Test
////public void testOnePlayer() {
////	FolderSync folderSync = new FolderSync();
////	String[] expected = {"David de Gea" , "Goalkeeper", "25"}; 
////	var syncRegions = folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
////	int actual = syncRegions.
////			
////	assertEquals(expected, actual);	
////}
//////How many games we have
////@Test
////public void testGame1Goals() {
////	FolderSync folderSync = new FolderSync();
////	int expectedTeam1Score = 2; 
////	var syncRegions = folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
////	int actual = syncRegions.
////			
////	assertEquals(expectedTeam1Score, actual);	
////}
//
//@Test
//// I expect all the values are equals/ it works
//public void testContentsEquals() {
//	var regions = syncReader.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//	Map<String, String> expected = new HashMap<String, String>();
//	for (var sync : regions) {
//		String key = sync.getId() + "." + sync.getAttribute();
//		var value = sync.getContent();
//		expected.put(key, value);
//	}
//
//	syncReader.checkSyncs(model, regions);
//
//	for (var sync : regions) {
//		var newContent = sync.getContent();
//		String key = sync.getId() + "." + sync.getAttribute();
//		var oldContent = expected.get(key);
//
//		assertEquals(newContent, oldContent);
//
//	}
//}
//
////// I want to test when i change the content in sync1 it also change in the model
//@Test
//public void testContentsChanged() {
//
//	var regions = syncReader.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//	Map<String, String> expected = new HashMap<String, String>();
//	for (var sync : regions) {
//		String key = sync.getId() + "." + sync.getAttribute();
//		var value = sync.getContent();
//		expected.put(key, value);
//	}
//	syncReader.checkSyncs(model, regions);
//
//	HashMap<String, String> actual = new HashMap<String, String>();
//	for (var sync : regions) {
//		String key = sync.getId() + "." + sync.getAttribute();
//		var value = sync.getContent();
//		actual.put(key, value);
//	}
//
//	assertNotEquals(expected.values(), actual.values());
//
//}
//
////To test we there are three different values for one attribute 
//@Test
//public void testOneSyncHasThreeDiffValues() {
//	var regions = syncReader.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//	Map<String, String> expected = new HashMap<String, String>();
//
//	int expectedValue = 3;
//	int actualValue = 0;
//
//	for (var sync : regions) {
//
//		String key = sync.getId() + "." + sync.getAttribute();
//		var value = sync.getContent();
//
//		if (expected.containsKey(key)) {
//			if (!expected.get(key).equals(value)) {
//				expected.put(key, expected.get(key) + "@" + value);
//			}
//
//		} else {
//			expected.put(key, value);
//		}
//
//	}
//
//	for (String key : expected.keySet()) {
//		String value = expected.get(key);
//		String[] values = value.split("@");
//
//		int numValue = values.length;
//
//		System.out.format("%s %s", key, value);
//		System.out.println();
//
//		if (numValue >= 2) {
//			actualValue = numValue;
//			// break;
//		}
//	}
//	assertEquals(expectedValue, actualValue);
//
//}


//@Test
//Here i want to test how many generated file i have, it should be 2 files but doesnt work 
//public void getNumberOfFiles() {
//FolderSync folderSync = new FolderSync();
//int expected = 2 ;// sync regions
//var numberOfFiles = folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//int actual = numberOfFiles.
//assertEquals(expected, actual);
//}

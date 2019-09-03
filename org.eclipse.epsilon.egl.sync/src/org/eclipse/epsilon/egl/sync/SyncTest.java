package org.eclipse.epsilon.egl.sync;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.xml.sax.SAXException;


public class SyncTest {
	private static final String FOLDER_PATH = "./SimpleExample/GeneratedFileFromLeague";
	EmfModel model;
	FolderSync syncReader;

	@Before
	public void init() {
		model = new EmfModel();
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
		syncReader = new FolderSync();
	}
	
	// createModule()
	public IEolModule createModule() {
	try {
				EglFileGeneratingTemplateFactory templateFactory = new EglFileGeneratingTemplateFactory();
				//templateFactory.setOutputRoot(new File("/SimpleExample/GeneratedFileFromLeague"));
				templateFactory.setOutputRoot("/SimpleExample/GeneratedFileFromLeague");
				return new EgxModule(templateFactory);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	
	// Here want to create temporary folder in order to put all the contents of files in it and read it to test 
	 @Rule
	   public TemporaryFolder tempFolder = new TemporaryFolder();
	   


	@Test
	public void executeStep1() { 
		//i should test when i run egx i will get one html file, 
		
		IEolModule module = createModule(); // The createModule() method follows
		module.getContext().getModelRepository().addModel(model); // The model parameter is the EmfModel you already create so you need to include that code as well.
		//module.parse(getFileURI("/League/League.egx"));
		try {
			module.parse("/League/League.egx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			module.execute();
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}
		
		/*
		public IEolModule createModule() {
		try {
					EglFileGeneratingTemplateFactory templateFactory = new EglFileGeneratingTemplateFactory();
					templateFactory.setOutputRoot(new File("/SimpleExample/GeneratedFileFromLeague"));
					return new EgxModule(templateFactory);
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			} */
		
	}

	@Test
	public void executeStep2() throws IOException{
	// i should write what i want to add to sync region ( an automated manually)
		FolderSync folderSync = new FolderSync();
		File file = tempFolder.newFolder("testfolder");
   
       // Verify the content
//	    assertEquals();
        
		assertTrue(file.exists());
		
		//folderSync.updateTheModel(model, allTheSyncsRegionOfTheFolder);
	
	}
	
	@Test
	public void executeStep3() {
	// here i should pass the folderSync in order to update the model
		FolderSync folderSync = new FolderSync();
		
		
		//assertTrue(folderSync.updateTheModel(model, allTheSyncsRegionOfTheFolder));
		
		
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	   @Test
//	   public void testWrite() throws IOException {
//	     // Create a temporary file.
//	     final File tempFile = tempFolder.newFile("tempFile.txt");
//	   
//	     // Write something to it.
//	     FileUtils.writeStringToFile(tempFile, "hello world");
//	   
//	     // Read it from temp file
//	     final String s = FileUtils.readFileToString(tempFile);
//	   
//	     // Verify the content
//	     Assert.assertEquals("hello world", s);
//	      
//	     //Note: File is guaranteed to be deleted after the test finishes.
//	   }
	   
//    @Test
//    public void testCreateFolder() throws IOException{
//        File file = tempFolder.newFolder("testfolder");
//        assertTrue(file.exists());
//    }
//     
//    @Test
//    public void testDeleteFolder() throws IOException{
//        File file = tempFolder.newFile("testfolder");
//        file.delete();
//        assertFalse(file.exists());
//    }	
//	@Test
//	// Here I expect to have 4 regions as i have in the html file/ it works as
//	public void getNumberOfSyncRegions() {
//		FolderSync folderSync = new FolderSync();
//		int expected = 4;// sync regions
//		List<Synchronization> syncRegions = folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//		int actual = syncRegions.size();
//		
//		assertEquals(expected, actual);
//	}
	
	
	
//	// In html file I expected 4 managers..
////	@Test
////	public void testManagers() throws SAXException, IOException, ParserConfigurationException {
////		//var regions = syncReader.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
////		FolderSync folderSync = new FolderSync();
////		int expected = 4 ;// sync managers
////		int num = folderSync.getManager();
////		 
////		int actual = num;
////				
////		assertEquals(expected, actual);	
////		
////	}
////	
////	// In html file I expected Goalkeeper for Manchester is David de Gea and his age is 25.. 
////	@Test
////	public void testOnePlayer() {
////		FolderSync folderSync = new FolderSync();
////		String[] expected = {"David de Gea" , "Goalkeeper", "25"}; 
////		var syncRegions = folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
////		int actual = syncRegions.
////				
////		assertEquals(expected, actual);	
////	}
////	//How many games we have
////	@Test
////	public void testGame1Goals() {
////		FolderSync folderSync = new FolderSync();
////		int expectedTeam1Score = 2; 
////		var syncRegions = folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
////		int actual = syncRegions.
////				
////		assertEquals(expectedTeam1Score, actual);	
////	}
//
//	@Test
//	// I expect all the values are equals/ it works
//	public void testContentsEquals() {
//		var regions = syncReader.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//		Map<String, String> expected = new HashMap<String, String>();
//		for (var sync : regions) {
//			String key = sync.getId() + "." + sync.getAttribute();
//			var value = sync.getContent();
//			expected.put(key, value);
//		}
//
//		syncReader.checkSyncs(model, regions);
//
//		for (var sync : regions) {
//			var newContent = sync.getContent();
//			String key = sync.getId() + "." + sync.getAttribute();
//			var oldContent = expected.get(key);
//
//			assertEquals(newContent, oldContent);
//
//		}
//	}
//
////	// I want to test when i change the content in sync1 it also change in the model
//	@Test
//	public void testContentsChanged() {
//
//		var regions = syncReader.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//		Map<String, String> expected = new HashMap<String, String>();
//		for (var sync : regions) {
//			String key = sync.getId() + "." + sync.getAttribute();
//			var value = sync.getContent();
//			expected.put(key, value);
//		}
//		syncReader.checkSyncs(model, regions);
//
//		HashMap<String, String> actual = new HashMap<String, String>();
//		for (var sync : regions) {
//			String key = sync.getId() + "." + sync.getAttribute();
//			var value = sync.getContent();
//			actual.put(key, value);
//		}
//
//		assertNotEquals(expected.values(), actual.values());
//
//	}
//
//// To test we there are three different values for one attribute 
//	@Test
//	public void testOneSyncHasThreeDiffValues() {
//		var regions = syncReader.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//		Map<String, String> expected = new HashMap<String, String>();
//
//		int expectedValue = 3;
//		int actualValue = 0;
//
//		for (var sync : regions) {
//
//			String key = sync.getId() + "." + sync.getAttribute();
//			var value = sync.getContent();
//
//			if (expected.containsKey(key)) {
//				if (!expected.get(key).equals(value)) {
//					expected.put(key, expected.get(key) + "@" + value);
//				}
//
//			} else {
//				expected.put(key, value);
//			}
//
//		}
//
//		for (String key : expected.keySet()) {
//			String value = expected.get(key);
//			String[] values = value.split("@");
//
//			int numValue = values.length;
//
//			System.out.format("%s %s", key, value);
//			System.out.println();
//
//			if (numValue >= 2) {
//				actualValue = numValue;
//				// break;
//			}
//		}
//		assertEquals(expectedValue, actualValue);
//
//	}
	
}





























































//@Test
//Here i want to test how many generated file i have, it should be 2 files but doesnt work 
//public void getNumberOfFiles() {
//	FolderSync folderSync = new FolderSync();
//	int expected = 2 ;// sync regions
//	var numberOfFiles = folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//	int actual = numberOfFiles.
//	assertEquals(expected, actual);
//}

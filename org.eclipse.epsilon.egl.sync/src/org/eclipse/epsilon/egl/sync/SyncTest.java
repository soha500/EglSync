package org.eclipse.epsilon.egl.sync;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.junit.Before;
import org.junit.Test;

public class SyncTest {
	private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/GeneratedFileFromLeague";
	EmfModel model;
	FolderSync syncReader;
	//FileSync fileSync;
	
	@Before
	public void init() {
		model = new EmfModel();
		model.setName("M");
		model.setMetamodelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/ModelLeague/League.ecore").getAbsolutePath());
		model.setModelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/ModelLeague/League.model").getAbsolutePath());			
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(true);

		try {
			model.load();
		} catch (EolModelLoadingException e2) {
			e2.printStackTrace();
		}
		
		syncReader = new FolderSync();
		//fileSync = new FileSync(file);
	}
	
	@Test
	// Here I expect to have 20 regions as i have in the generated file/ it works as i expect and get green bar in Junit
	public void getNumberOfSyncRegions() {
		FolderSync folderSync = new FolderSync();
		int expected = 20 ;// sync regions
		var syncRegions = folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
		int actual = syncRegions.size();
		assertEquals(expected, actual);
	}

	
	@Test
	//Here I did not change any value so i expect all the values are equals/ it works and get green bar in Junit
	public void testEquals() {
		var regions = syncReader.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
		Map<String, String> expected = new HashMap<String, String>();
		for(var sync: regions) {
			String key = sync.getId() + "." + sync.getAttribute();
			var value = sync.getContent();
			expected.put(key, value);
		}
		
		syncReader.checkSyncs(model, regions);
				
		for(var sync: regions) {
			var newValue = sync.getContent();
			String key = sync.getId() + "." + sync.getAttribute();
			var oldValue = expected.get(key); // x => x
			
			// if dosen t update
			assertEquals(newValue, oldValue);

		}

//		assertEquals(expected, actual);
	}
	
//	@Test
	// Here i want to test how many generated file i have, it should be 1 file but doesnt work 
//	public void getNumberOfFiles() {
//		FolderSync folderSync = new FolderSync();
//		int expected = 1 ;// sync regions
//		var numberOfFiles = folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//		int actual = numberOfFiles.
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void testUpdate() {
//		var regions = syncReader.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//		// some expected state of model before running checkSyncs
//		Map<String, String> expected = new HashMap<String, String>();
//		for(var sync: regions) {
//			String key = sync.getId() + "." + sync.getAttribute();
//			var value = sync.getContent();
//			expected.put(key, value);
//		}
//		
//		syncReader.checkSyncs(model, regions);
//				
//		for(var sync: regions) {
//			var newValue = sync.getContent();
//			String key = sync.getId() + "." + sync.getAttribute();
//			var oldValue = expected.get(key); 
//
//			var expectedValue = " "; 
//			assertEquals(newValue, expectedValue);
//		}
//
////		assertEquals(expected, actual);
//	}
	


}

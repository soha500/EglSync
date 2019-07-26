//package org.eclipse.epsilon.egl.sync;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//
//import org.eclipse.epsilon.emc.emf.EmfModel;
//import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
//import org.junit.Before;
//import org.junit.Test;
//
//public class SyncTestNew {
//
//	private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/GeneratedFileFromLeague";
//	EmfModel model;
//	FolderSync syncReader;
//	
//	@Before
//	void init() {
//		model = new EmfModel();
//		model.setName("M");
//	
//		// League ecore  and model
//		model.setMetamodelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/ModelLeague/League.ecore").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/ModelLeague/League.model").getAbsolutePath());		
//			
//		model.setReadOnLoad(true);
//		model.setStoredOnDisposal(true);
//
//		try {
//			model.load();
//		} catch (EolModelLoadingException e2) {
//			e2.printStackTrace();
//		}
//		syncReader = new FolderSync();
//	}
//
//	@Test
//	void testGetAllTheSyncsRegionsOfTheFolder() {
//		FolderSync folderSync = new FolderSync();
//		int expected = 20 ;// sync regions
//		var syncRegions = folderSync.getAllTheSyncsRegionsOfTheFolder(FOLDER_PATH);
//		int actual = syncRegions.size();
//		assertEquals(expected, actual);
//	}
//}

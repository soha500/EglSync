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
	// this works with updating 
	private static final String FOLDER_PATH = System.getProperty("user.dir") + "/SyncTests/GeneratedFilesFromUniversity/Test1";
//	private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/GeneratedFilesFromUniversity";

	// this is for the boiler test in this workspace, it works
	//private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/boiler/src-gen-sync-regions/syncregions";

	// this is for the boiler test in other workspace, it works
	//private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/Epsilon-Source/org.eclipse.epsilon/examples/org.eclipse.epsilon.examples.egl.comps/src-gen-sync-regions/syncregions";
	
//	private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/org.eclipse.epsilon.examples.egl.comps/src-gen-sync-regions/syncregions";
	//this works and automatically generates the files without need to all url but 
	// dose not work with updating after i added ./ it works with updating
	//private static final String FOLDER_PATH ="./SimpleExample/GeneratedFilesFromUniversity";
	
	public static void main(String[] args) throws EolModelLoadingException, IOException {

		EmfModel model = new EmfModel();
		model.setName("M");
		// for the university
		model.setMetamodelFile(new File("SyncTests/Model-University/University.ecore").getAbsolutePath());
		model.setModelFile(new File("SyncTests/Model-University/University.model").getAbsolutePath());
	
//	    System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
		// this is for the boiler test in this workspace == it works
//		model.setMetamodelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/boiler/comps.ecore").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/boiler/BoilerController.model").getAbsolutePath());
	
		
//		// for the boiler test in other workspace
//		model.setMetamodelFile(new File("/Users/sultanalmutairi/git/Epsilon-Source/org.eclipse.epsilon/examples/org.eclipse.epsilon.examples.egl.comps/comps.ecore").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/git/Epsilon-Source/org.eclipse.epsilon/examples/org.eclipse.epsilon.examples.egl.comps/BoilerController.model").getAbsolutePath());
//		

	
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(true);

		try {
			model.load();
		} catch (EolModelLoadingException e2) {
			System.err.println("The model element is not found in model: " + e2.getMessage());
		} 

		FolderSync syncReader = new FolderSync();
		syncReader.getSynchronization(FOLDER_PATH, model);

	}
}
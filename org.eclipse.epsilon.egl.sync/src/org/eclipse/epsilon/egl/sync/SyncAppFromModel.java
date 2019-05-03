package org.eclipse.epsilon.egl.sync;

import java.io.File;
import java.io.IOException;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

public class SyncAppFromModel {

	private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/All-Generated-Files";

	public static void main(String[] args) throws EolModelLoadingException, IOException {

		//List<Synchronization> listObjects = new ArrayList<Synchronization>();
		
		EmfModel model = new EmfModel();
		model.setName("M");
		model.setMetamodelFile(new File("Statemachine.ecore").getAbsolutePath());
		model.setModelFile(new File("Statemachine.model").getAbsolutePath());
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(true);

		try {
			model.load();
		} catch (EolModelLoadingException e2) {
			e2.printStackTrace();
		}
		
		FolderSync syncReader = new FolderSync();
		syncReader.sync(FOLDER_PATH, model);


	}
}


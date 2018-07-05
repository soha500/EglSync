package org.eclipse.epsilon.egl.sync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

public class SyncAppFromModel {

	public static void main(String[] args) throws EolModelLoadingException, IOException {
		
		String line = null;
		// Read Windows.java
		FileReader fileReader = new FileReader("C:\\Users\\dell\\workspace\\more_functionality\\org.eclipse.epsilon.egl.sync\\gen\\src\\Windows.java");
		BufferedReader buffer = new BufferedReader(fileReader);
		// Load windows.model
		EmfModel model = new EmfModel();
		model.setName("M");
		model.setMetamodelFile(new File("statemachine.ecore").getAbsolutePath());
		model.setModelFile(new File("windows.model").getAbsolutePath());
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(true);
		model.load();
		
		
		
		// Identify all //sync areas in Windows.java
		while((line = buffer.readLine()) != null) {
            if (line.contains("//sync")) {
				System.out.println(line );
			}
        } 
		// for each sync area
		//   get its content
		//   update the respective attribute of the model element where it comes from
		// end for
		
		
	}
}

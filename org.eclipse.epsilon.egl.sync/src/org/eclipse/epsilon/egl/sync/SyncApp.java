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
		model.setMetamodelFile(new File("/Users/sultanalmutairi/Projects/runtime-New_configuration(1)/StateMachineProject/Statemachine.ecore").getAbsolutePath());
		model.setModelFile(new File("/Users/sultanalmutairi/Projects/runtime-New_configuration(1)/StateMachineProject/Statemachine.model").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/Statemachine.model").getAbsolutePath());

		//League Model
//		model.setMetamodelFile(new File("/Users/sultanalmutairi/Documents/Workspaces/runtime-New_configuration/League/League.ecore").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/Documents/Workspaces/runtime-New_configuration/League/League.model").getAbsolutePath());

		
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

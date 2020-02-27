package org.eclipse.epsilon.egl.sync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.util.Scanner;

import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.egl.output.IOutputBuffer;
import org.eclipse.epsilon.egl.output.IOutputBufferFactory;
import org.eclipse.epsilon.emc.emf.EmfModel;

public class App {
	
	public static void main(String[] args) throws Exception {
		EglFileGeneratingTemplateFactory factory = new EglFileGeneratingTemplateFactory();
		factory.setOutputRoot(new File("gen").getAbsolutePath());
		EgxModule module = new EgxModule(factory);
		module.parse(new File("statemachine2java.egx"));
		
		/*
		module.getContext().setOutputBufferFactory(new IOutputBufferFactory() {
			
			@Override
			public IOutputBuffer create() {
				return new OutputBufferSync();
			}
		});*/
		
		EmfModel model = new EmfModel();
		model.setName("M");
//		model.setMetamodelFile(new File("/Users/sultanalmutairi/Projects/runtime-New_configuration(1)/StateMachineProject/Statemachine.ecore").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/Projects/runtime-New_configuration(1)/StateMachineProject/Statemachine.model").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/Statemachine.model").getAbsolutePath());
		
		//League Model
//		model.setMetamodelFile(new File("/Users/sultanalmutairi/Documents/Workspaces/runtime-New_configuration/League/League.ecore").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/Documents/Workspaces/runtime-New_configuration/League/League.model").getAbsolutePath());

		//University Model
//		model.setMetamodelFile(new File("/Users/sultanalmutairi/Documents/Workspaces/runtime-New_configuration/University-Project/University.ecore").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/Documents/Workspaces/runtime-New_configuration/University-Project/University.model").getAbsolutePath());

		//University-Last-Project    //this works and automatically generates the files without need to all url 
		// and with updating
//		model.setMetamodelFile(new File("SimpleExample/Model-University/University.ecore").getAbsolutePath());
//		model.setModelFile(new File("SimpleExample/Model-University/University.model").getAbsolutePath());
		
		
		// this is for the boiler test in this workspace == it works
//		model.setMetamodelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/boiler/comps.ecore").getAbsolutePath());
//		model.setModelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/boiler/BoilerController.model").getAbsolutePath());
		
//		// for the boiler test in other workspace
		model.setMetamodelFile(new File("/Users/sultanalmutairi/git/Epsilon-Source/org.eclipse.epsilon/examples/org.eclipse.epsilon.examples.egl.comps/comps.ecore").getAbsolutePath());
		model.setModelFile(new File("/Users/sultanalmutairi/git/Epsilon-Source/org.eclipse.epsilon/examples/org.eclipse.epsilon.examples.egl.comps/BoilerController.model").getAbsolutePath());

		
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(false);
		model.load();
		
		module.getContext().getModelRepository().addModel(model);
		
		module.execute();
	}
	
}

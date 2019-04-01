package org.eclipse.epsilon.egl.sync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.util.Scanner;

import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfModel;

public class App {
	
	public static void main(String[] args) throws Exception {
		EglFileGeneratingTemplateFactory factory = new EglFileGeneratingTemplateFactory();
		factory.setOutputRoot(new File("gen").getAbsolutePath());
		EgxModule module = new EgxModule(factory);
		module.parse(new File("statemachine2java.egx"));
		
		
		EmfModel model = new EmfModel();
		model.setName("M");
		model.setMetamodelFile(new File("/Users/sultanalmutairi/Projects/runtime-New_configuration(1)/StateMachineProject/Statemachine.ecore").getAbsolutePath());
		model.setModelFile(new File("/Users/sultanalmutairi/Projects/runtime-New_configuration(1)/StateMachineProject/Statemachine.model").getAbsolutePath());
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(false);
		model.load();
		
		module.getContext().getModelRepository().addModel(model);
		
		module.execute();
	}
	
}
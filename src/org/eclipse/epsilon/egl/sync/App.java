package org.eclipse.epsilon.egl.sync;

import java.io.File;

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
		model.setMetamodelFile(new File("statemachine.ecore").getAbsolutePath());
		model.setModelFile(new File("windows.model").getAbsolutePath());
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(false);
		model.load();
		
		module.getContext().getModelRepository().addModel(model);
		
		module.execute();
	}
	
}

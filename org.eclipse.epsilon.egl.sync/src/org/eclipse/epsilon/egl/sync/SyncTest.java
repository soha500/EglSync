package org.eclipse.epsilon.egl.sync;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
import org.eclipse.epsilon.egl.EgxModule;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.xml.sax.SAXException;

import org.eclipse.epsilon.emc.emf.EmfUtil;
import org.eclipse.epsilon.emc.emf.EmfModelFactory;

public class SyncTest {
	/*
	 * This is work and the one below also works but in case i avoid breaking test i
	 * will use this until I need to change it.
	 */
	private static final String FOLDER_PATH = System.getProperty("user.dir") + "/SyncTests/GeneratedFilesFromUniversity";
	
	EmfModel model;
	FolderSync syncReader;
	EmfModel tempModel;
	static List<String> orginalNewLines;
	
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Before
	public void init() {
		/*
		 * This works and automatically generates the files without need to all URL for
		 * the Ecore and Model as well.
		 */
		model = new EmfModel();
		model.setName("M");
		model.setMetamodelFile(new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.ecore").getAbsolutePath());
		model.setModelFile(new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.model").getAbsolutePath());
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(true);
		try {
			model.load();
		} catch (EolModelLoadingException e2) {
			e2.printStackTrace();
		}
		syncReader = new FolderSync();
		
		/*
		 * For temporary copy of the model
		 */
		
		File orginalFile = new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.model");
		File tempFile = new File(System.getProperty("user.dir") + "/SyncTests/Model-University/tempUni.model");
		try {
			Files.copy(orginalFile.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tempModel = new EmfModel();
		tempModel.setName("M");
		tempModel.setMetamodelFile(new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.ecore").getAbsolutePath());
		tempModel.setModelFile(tempFile.getAbsolutePath());
		tempModel.setReadOnLoad(true);

		try {
			tempModel.load();
		} catch (EolModelLoadingException e2) {
			e2.printStackTrace();
		}
		tempFile.deleteOnExit();

	}

	/*
	 * Here want to create temporary folder in order to put all the contents of
	 * files in it and read it before each test
	 */

//------------------------------------------------------------- Run the generator

	// createModule()
	public IEolModule createModule() {
		/*
		 * This works but i should change it to be generalise with absolute bath this
		 * still not able to generate files without it 20/02/20
		 */
		try {
			EglFileGeneratingTemplateFactory templateFactory = new EglFileGeneratingTemplateFactory();
			templateFactory.setOutputRoot(System.getProperty("user.dir") + "/SyncTests/GeneratedFilesFromUniversity/");

			return new EgxModule(templateFactory);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	public void runTheGenerator() {
		/*
		 * IEolModule module = createModule(); // The createModule() method follows
		 * //كوستس module.getContext().getFrameStack().put(new Variable("self",null));
		 * .module.setoutput
		 */
		IEolModule module = createModule(); // The createModule() method follows
		module.getContext().getModelRepository().addModel(model); // The model parameter is the EmfModel you already
		// create so you need to include that code as well.
		try {
			// this works and automatically generates the files without need to all URL 20/02/20
			module.parse(new File("SyncTests/Model-University/main.egx"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			module.execute();
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Scenario 1, There is one sync regions which contains different value from the
	 * one in the model.
	 */

	@Test
	public void test1() throws IOException {
		/*
		 * Here I passed the model in temporary file, But I commented here and added in the inti() method 
		 * in the top also the last line for deleting the temporary tempUni i took it and put it in the top
		 * I did this for each the first three test, but the rest it used to work but when i added the temp file 
		 * it broke.
		 */
		System.out.println("\n----------------------------- Test 1 :\n");

//		File orginalFile = new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.model");
//		File tempFile = new File(System.getProperty("user.dir") + "/SyncTests/Model-University/tempUni.model");
//		Files.copy(orginalFile.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//		tempModel = new EmfModel();
//		tempModel.setName("M");
//		tempModel.setMetamodelFile(new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.ecore").getAbsolutePath());
//		tempModel.setModelFile(tempFile.getAbsolutePath());
//		tempModel.setReadOnLoad(true);
//
//		try {
//			tempModel.load();
//		} catch (EolModelLoadingException e2) {
//			e2.printStackTrace();
//		}

		String pathString = FOLDER_PATH + "/Test1/MDE101.html";

		Path path = Paths.get(pathString);
		BufferedReader original = new BufferedReader(new FileReader(pathString));

		String line;

		List<String> newLines = new LinkedList<String>();

		while ((line = original.readLine()) != null)
			if (!line.contains("//sync _OeCHMPxQEemsbtndia47ww, description"))
				newLines.add(line);
			else {
				newLines.add(line);
				newLines.add("hello");

				while (!line.contains("//endSync"))
					line = original.readLine();
				// don't do anything, just loop
				newLines.add(line);

			}

		original.close();
		Files.write(path, newLines);

		// Update the model with values taken from the generated file.
		FolderSync folderSync = new FolderSync();
		folderSync.getSynchronization(FOLDER_PATH + "/Test1/", tempModel);

		// Now that you are done, go to the model and check if the value is updated.
		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
		String valueOfAttributeInTheModel = null;
		try {
			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
		} catch (EolRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("test 1", "hello", valueOfAttributeInTheModel);
		//tempFile.deleteOnExit();

	}

	/*
	 * Scenario 2, There are three sync regions but contains the same value in the
	 * model.
	 */

	@Test
	public void test2() throws IOException {
		System.out.println("\n-------------------------- Test 2 :\n");
//		File orginalFile = new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.model");
//		File tempFile = new File(System.getProperty("user.dir") + "/SyncTests/Model-University/tempUni.model");
//		Files.copy(orginalFile.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//		tempModel = new EmfModel();
//		tempModel.setName("z");
//		tempModel.setMetamodelFile(new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.ecore").getAbsolutePath());
//		tempModel.setModelFile(tempFile.getAbsolutePath());
//		tempModel.setReadOnLoad(true);
//		try {
//			tempModel.load();
//		} catch (EolModelLoadingException e2) {
//			e2.printStackTrace();
//		}

		String pathString = FOLDER_PATH + "/Test2/MDE102.html";

		Path path = Paths.get(pathString);

		BufferedReader original = new BufferedReader(new FileReader(pathString));
		String line;
		List<String> newLines = new LinkedList<String>();
		int count = 0;

		while ((line = original.readLine()) != null)
			if (!line.contains("//sync _OeCHMPxQEemsbtndia47ww, description"))
				newLines.add(line);
			else {
				newLines.add(line);
				if (count == 0) {
					newLines.add("hi");
					count = 1;
				} else {
					newLines.add("hello");
				}
				count = 2;

				while (!line.contains("//endSync"))
					line = original.readLine();
				// don't do anything, just loop
				newLines.add(line);

			}
		original.close();

		Files.write(path, newLines);

		FolderSync folderSync = new FolderSync();
		folderSync.getSynchronization(FOLDER_PATH + "/Test2/", tempModel);

		tempModel.store();
		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
		String valueOfAttributeInTheModel = null;
		try {
			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

		assertEquals("test 2", "hi", valueOfAttributeInTheModel);
		//tempFile.deleteOnExit();

	}

	/*
	 * Scenario 3, There are two different values from the one in the model and
	 * between them selves
	 */

	@Test
	public void test3() throws IOException {
		System.out.println("\n-------------------------------- Test 3 :\n");

//		File orginalFile = new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.model");
//		File tempFile = new File(System.getProperty("user.dir") + "/SyncTests/Model-University/tempUni.model");
//		Files.copy(orginalFile.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//		tempModel = new EmfModel();
//		tempModel.setName("M");
//		tempModel.setMetamodelFile(new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.ecore").getAbsolutePath());
//		tempModel.setModelFile(tempFile.getAbsolutePath());
//		tempModel.setReadOnLoad(true);
//
//		try {
//			tempModel.load();
//		} catch (EolModelLoadingException e2) {
//			e2.printStackTrace();
//		}

		String pathString = FOLDER_PATH + "/Test3/MDE103.html";
		Path path = Paths.get(pathString);
		BufferedReader original = new BufferedReader(new FileReader(pathString));

		String line;
		List<String> newLines = new LinkedList<String>();
		int count = 0;

		while ((line = original.readLine()) != null)
			if (!line.contains("//sync _OeCHMPxQEemsbtndia47ww, description"))
				newLines.add(line);
			else {
				newLines.add(line);
				if (count == 0) {
					newLines.add("hi");
					count = 1;
				} else if (count == 1) {
					newLines.add("hello");
					count = 2;
				} else {
					newLines.add("Welcome");
				}
				while (!line.contains("//endSync"))
					line = original.readLine();
				newLines.add(line);

			}

		original.close();
		Files.write(path, newLines);

		FolderSync folderSync = new FolderSync();
		folderSync.getSynchronization(FOLDER_PATH + "/Test3/", tempModel);

		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
		String valueOfAttributeInTheModel = null;
		try {
			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

		assertEquals("test 3", "hello ", valueOfAttributeInTheModel);
		//tempFile.deleteOnExit();

	}

// --------------------------------------------------------------- Test 4, test the Id is exist in the files
	@Test
	public void test4() throws IOException {
		System.out.println("\n------------------------ Test 4 :\n");
		File orginalFile = new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.model");
		File tempFile = new File(System.getProperty("user.dir") + "/SyncTests/Model-University/tempUni.model");
		Files.copy(orginalFile.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		tempModel = new EmfModel();
		tempModel.setName("M");
		tempModel.setMetamodelFile(new File(System.getProperty("user.dir") + "/SyncTests/Model-University/University.ecore").getAbsolutePath());
		tempModel.setModelFile(tempFile.getAbsolutePath());
		tempModel.setReadOnLoad(true);

		try {
			tempModel.load();
		} catch (EolModelLoadingException e2) {
			e2.printStackTrace();
		}

		String pathString = FOLDER_PATH + "/Test4/";
		syncReader = new FolderSync();
		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, tempModel);

		assertEquals("test 4", "The respictive element not found", valueOfAttributeInTheModel);
	}

// --------------------------------------------------------------- Test 5, test the attribute is exist in the files 
	@Test
	public void test5() throws IOException {
		System.out.println("\n------------------------ Test 5 :\n");

		String pathString = FOLDER_PATH + "/Test5/";
		syncReader = new FolderSync();
		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, model);

		assertEquals("test 5", "The respective attribute is not found", valueOfAttributeInTheModel);
	}

	// ----------------------------------------------------------- Test 44, test all
	// sync regions are correct in the folder
	@Test
	public void test6() throws IOException {
		System.out.println("\n------------------------ Test 6 :\n");

		String pathString = FOLDER_PATH + "/Test6/";
		syncReader = new FolderSync();
		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, model);

		assertEquals("test 6", "Misformated or incompleted", valueOfAttributeInTheModel);
	}

	// ----------------------------------------------------------- Test 6, the type
	// is compatible Integer == Integer
	@Test
	public void test7() throws IOException {
		System.out.println("\n------------------------ Test 7 :\n");

		String pathString = FOLDER_PATH + "/Test7/";
		syncReader = new FolderSync();
		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, model);

		assertEquals("test 7", "Incompatible type", valueOfAttributeInTheModel);

	}

}













































/*
 * before test 2 File tempFile = tempFolder.newFile("file.txt"); File tempFolder
 * = tempFolder.newFolder("folder"); // System.out.println("Test folder: " +
 * testFolder.getRoot());
 * 
 * // Create a temporary file. final File tempFile =
 * tempFolder.newFile("tempFile.txt");
 * 
 * // Write something to it. FileUtils.writeStringToFile(tempFile,
 * "hello world");
 * 
 * // Read it from temp file final String s =
 * FileUtils.readFileToString(tempFile);
 * 
 * // Verify the content Assert.assertEquals("hello world", s); //Note: File is
 * guaranteed to be deleted after the test finishes.
 */

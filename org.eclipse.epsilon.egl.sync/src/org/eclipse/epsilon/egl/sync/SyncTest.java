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

// Run the generator method

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
	 * Scenario 1, There is one sync regions which contains the same value 
	 * in the model.
	 */
	
	@Test
	public void test1() throws IOException {
		/*
		 * Here I passed the model in temporary file, But I commented here and added in the inti() method 
		 * in the top also the last line for deleting the temporary tempUni i took it and put it in the top
		 * I did this for each the first three test, but the rest it used to work but when i added the temp file 
		 * it broke.
		 */
		System.out.println("\n Test 1 : One sync region with the same value in the model\n");

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
				newLines.add(line);

			}

		original.close();
		Files.write(path, newLines);
		
		// Update the model with values taken from the generated file.
		FolderSync folderSync = new FolderSync();
		folderSync.getSynchronization(FOLDER_PATH + "/Test1/", tempModel);

		// Now that you are done, go to the model and check if the value is updated.
		tempModel.store();
		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
		String valueOfAttributeInTheModel = null;
		try {
			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

		assertEquals("test 1", "hello", valueOfAttributeInTheModel);

	}
	
	/*
	 * Scenario 2, There is one sync region with different value from the one in the model.
	 */
	
	@Test
	public void test2() throws IOException {
		System.out.println("\n Test 2 : One sync region with different value from the one in the model.\n");

		String pathString = FOLDER_PATH + "/Test2/MDE102.html";

		Path path = Paths.get(pathString);
		BufferedReader original = new BufferedReader(new FileReader(pathString));

		String line;

		List<String> newLines = new LinkedList<String>();

		while ((line = original.readLine()) != null)
			if (!line.contains("//sync _OeCHMPxQEemsbtndia47ww, description"))
				newLines.add(line);
			else {
				newLines.add(line);
				newLines.add("hi");

				while (!line.contains("//endSync"))
					line = original.readLine();
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
	}

	/*
	 * Scenario 3, There are two sync regions with the same value from the one in the model.
	 */
	
	@Test
	public void test3() throws IOException {
		System.out.println("\n Test 3 : Two sync regions with the same values from the one in the model.\n");

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
					newLines.add("hello");
					count = 1;
				} else {
					newLines.add("hello");
				}
				count = 2;

				while (!line.contains("//endSync"))
					line = original.readLine();
				newLines.add(line);
			}
		original.close();

		Files.write(path, newLines);

		FolderSync folderSync = new FolderSync();
		folderSync.getSynchronization(FOLDER_PATH + "/Test3/", tempModel);

		tempModel.store();
		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
		String valueOfAttributeInTheModel = null;
		try {
			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

		assertEquals("test 3", "hello", valueOfAttributeInTheModel);
	}

	/*
	 * Scenario 4, There are two sync regions with one different value from the one in the model.
	 */

	@Test
	public void test4() throws IOException {
		System.out.println("\n Test 4 : Two sync regions with one different value from the one in the model.\n");

		String pathString = FOLDER_PATH + "/Test4/MDE104.html";
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
					newLines.add("hi");
					count = 2;
				}	
				while (!line.contains("//endSync"))
					line = original.readLine();
				newLines.add(line);

			}

		original.close();
		Files.write(path, newLines);

		FolderSync folderSync = new FolderSync();
		folderSync.getSynchronization(FOLDER_PATH + "/Test4/", tempModel);
		
		tempModel.store();
		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
		String valueOfAttributeInTheModel = null;
		try {
			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

		assertEquals("test 4", "hi", valueOfAttributeInTheModel);
	}

	/*
	 * Scenario 5, There are two sync regions but have one different value from the one in the model.
	 */

	@Test
	public void test5() throws IOException {
		System.out.println("\n Test 5 : Two sync regions but have one different value from the one in the model.\n");

		String pathString = FOLDER_PATH + "/Test5/MDE105.html";

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
				newLines.add(line);

			}
		original.close();

		Files.write(path, newLines);

		FolderSync folderSync = new FolderSync();
		folderSync.getSynchronization(FOLDER_PATH + "/Test5/", tempModel);

		tempModel.store();
		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
		String valueOfAttributeInTheModel = null;
		try {
			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

//		assertEquals("test 5", "hi", valueOfAttributeInTheModel);
		assertEquals("test 5", "hello", valueOfAttributeInTheModel);

	}

	/*
	 * Scenario 6, There are two sync regions but have two different values from the one in the model.
	 */

	@Test
	public void test6() throws IOException {
//		System.out.println("\n Test 6 : Two sync regions but have also two different values from the one in the model.\n");
//
//		String pathString = FOLDER_PATH + "/Test6/MDE106.html";
//
//		Path path = Paths.get(pathString);
//
//		BufferedReader original = new BufferedReader(new FileReader(pathString));
//		String line;
//		List<String> newLines = new LinkedList<String>();
//		int count = 0;
//
//		while ((line = original.readLine()) != null)
//			if (!line.contains("//sync _OeCHMPxQEemsbtndia47ww, description"))
//				newLines.add(line);
//			else {
//				newLines.add(line);
//				if (count == 0) {
//					newLines.add("hi");
//					count = 1;
//				} else {
//					newLines.add("welcome");
//				}
//				count = 2;
//
//				while (!line.contains("//endSync"))
//					line = original.readLine();
//				newLines.add(line);
//
//			}
//		original.close();
//
//		Files.write(path, newLines);
//
//		FolderSync folderSync = new FolderSync();
//		folderSync.getSynchronization(FOLDER_PATH + "/Test6/", tempModel);
//
//		tempModel.store();
//		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
//		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
//		String valueOfAttributeInTheModel = null;
//		try {
//			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
//		} catch (EolRuntimeException e) {
//			e.printStackTrace();
//		}
//		
//		assertEquals("test 6", "hello", valueOfAttributeInTheModel);
	}

	/*
	 * Scenario 7, There are three sync regions but contains the same value in the
	 * model.
	 */

	@Test
	public void test7() throws IOException {
		System.out.println("\n Test 7 : Three sync regions but same values from the one in the model.\n");

		String pathString = FOLDER_PATH + "/Test7/MDE107.html";

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
					newLines.add("hello");
					count = 1;
				} else if (count == 1) {
					newLines.add("hello");
					count = 2;
				} else {
					newLines.add("hello");
				}
				
				while (!line.contains("//endSync"))
					line = original.readLine();
				newLines.add(line);

			}
		original.close();

		Files.write(path, newLines);

		FolderSync folderSync = new FolderSync();
		folderSync.getSynchronization(FOLDER_PATH + "/Test7/", tempModel);

		tempModel.store();
		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
		String valueOfAttributeInTheModel = null;
		try {
			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

		assertEquals("test 7", "hello", valueOfAttributeInTheModel);
	}
	
	/*
	 * Scenario 8, There are three sync regions but with one different value from the one in the model.
	 */
	
	@Test
	public void test8() throws IOException {
		System.out.println("\n Test 8 : Three sync regions but with one different value from the one in the model.\n");

		String pathString = FOLDER_PATH + "/Test8/MDE108.html";

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
					newLines.add("hi");
					count = 2;
				} else {
					newLines.add("hi");
				}
				
				while (!line.contains("//endSync"))
					line = original.readLine();
				newLines.add(line);

			}
		original.close();

		Files.write(path, newLines);

		FolderSync folderSync = new FolderSync();
		folderSync.getSynchronization(FOLDER_PATH + "/Test8/", tempModel);

		tempModel.store();
		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
		String valueOfAttributeInTheModel = null;
		try {
			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

		assertEquals("test 8", "hi", valueOfAttributeInTheModel);
	}

	/*
	 * Scenario 9, There are three sync regions but with two different values, but one of them same the value in the model.
	 */

	@Test
	public void test9() throws IOException {
		System.out.println("\n Test 9 : Three sync regions but with two different values, but one of them same the value in the model.\n");

		String pathString = FOLDER_PATH + "/Test9/MDE109.html";

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
					newLines.add("hello");
					count = 1;
				} else if (count == 1) {
					newLines.add("hello");
					count = 2;
				}  else {
					newLines.add("hi");
				}

				while (!line.contains("//endSync"))
					line = original.readLine();
				newLines.add(line);

			}
		original.close();

		Files.write(path, newLines);

		FolderSync folderSync = new FolderSync();
		folderSync.getSynchronization(FOLDER_PATH + "/Test9/", tempModel);

		tempModel.store();
		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
		String valueOfAttributeInTheModel = null;
		try {
			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}

//		assertEquals("test 9", "hi", valueOfAttributeInTheModel);
		assertEquals("test 9", "hello", valueOfAttributeInTheModel);

	}
	/*
	 * Scenario 10, There are three sync regions but with two or more different values from the value in the model.
	 */

	@Test
	public void test10() throws IOException {
//		System.out.println("\n Test 10 : Three sync regions but with two or more different values from the value in the model.\n");
//
//		String pathString = FOLDER_PATH + "/Test10/MDE10.html";
//
//		Path path = Paths.get(pathString);
//
//		BufferedReader original = new BufferedReader(new FileReader(pathString));
//		String line;
//		List<String> newLines = new LinkedList<String>();
//		int count = 0;
//
//		while ((line = original.readLine()) != null)
//			if (!line.contains("//sync _OeCHMPxQEemsbtndia47ww, description"))
//				newLines.add(line);
//			else {
//				newLines.add(line);
//				if (count == 0) {
//					newLines.add("hi");
//					count = 1;
//				} else if (count == 1) {
//					newLines.add("bye");
//					count = 2;
//				}  else {
//					newLines.add("welcome");
//				}
//
//				while (!line.contains("//endSync"))
//					line = original.readLine();
//				newLines.add(line);
//
//			}
//		original.close();
//
//		Files.write(path, newLines);
//
//		FolderSync folderSync = new FolderSync();
//		folderSync.getSynchronization(FOLDER_PATH + "/Test10/", tempModel);
//
//		tempModel.store();
//		IPropertyGetter propertyGetter = tempModel.getPropertyGetter();
//		Object modelElement = tempModel.getElementById("_OeCHMPxQEemsbtndia47ww");
//		String valueOfAttributeInTheModel = null;
//		try {
//			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
//		} catch (EolRuntimeException e) {
//			e.printStackTrace();
//		}
//
//		assertEquals("test 10", "hello", valueOfAttributeInTheModel);
	}

	/*
	 * Scenario 11, if the respective element is not found from the one in the model.
	 */
	
	@Test
	public void test11() throws IOException {
		System.out.println("\n Test 11 :\n");
		String pathString = FOLDER_PATH + "/Test11/";
		syncReader = new FolderSync();
		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, tempModel);

		assertEquals("test 11", "The respictive element not found", valueOfAttributeInTheModel);
	}
	
	/*
	 * Scenario 12, if the attribute name is not found in the model.
	 */

	@Test
	public void test12() throws IOException {
		System.out.println("\n Test 12 : if the attribute name is not correct from the one in the model.\n");

		String pathString = FOLDER_PATH + "/Test12/";
		syncReader = new FolderSync();
		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, model);

		assertEquals("test 12", "The respective attribute is not found", valueOfAttributeInTheModel);
	}

	/*
	 * Scenario 13, if the respective attribute was deleted from the model.
	 */
	@Test
	public void test13() throws IOException {
		System.out.println("\n Test 13 : if the respective attribute was deleted from the model.\n");

		String pathString = FOLDER_PATH + "/Test13/";
		syncReader = new FolderSync();
		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, model);

		assertEquals("test 13", "Misformated or incompleted", valueOfAttributeInTheModel);
	}

	/*
	 * Scenario 14, if the type is not compatible with the type in the respective element in the model.
	 */

	@Test
	public void test14() throws IOException {
		System.out.println("\n Test 14 : if the type is not compatible with the type in the respective element in the model.\n");

		String pathString = FOLDER_PATH + "/Test14/";
		syncReader = new FolderSync();
		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, model);

		assertEquals("test 14", "Incompatible type", valueOfAttributeInTheModel);
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

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.ecore.util.EcoreUtil;
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

	private static final String FOLDER_PATH ="/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/GeneratedFilesFromUniversity";
	// University example //this works and automatically generates the files without need to all url
	//private static final String FOLDER_PATH = "./SimpleExample/GeneratedFilesFromUniversity/";
	// Here I added the . to see if it works or not
	// private static final String FOLDER_PATH ="./GeneratedFilesFromUniversity/";
//
	EmfModel model;
	FolderSync syncReader;
	EmfModel tempModel;
	static List<String> orginalNewLines;
//
	@BeforeClass
	public static void setup() {
//		 String pathString = FOLDER_PATH + "/MDE101.html" ;
//
//		 Path path = Paths.get(pathString);
//		 BufferedReader original = null;
//		try {
//			original = new BufferedReader(new FileReader(pathString));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			String line;
//			orginalNewLines = new LinkedList<String>();
//			
//			try {
//				while ((line = original.readLine()) != null) {
//					orginalNewLines.add(line);	
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

//			try {
//				model.load();			
//			} catch (EolModelLoadingException e2) {
//				e2.printStackTrace();
//			}
	}

	@Before
	public void init() {
//		String pathString = FOLDER_PATH + "/MDE101.html" ;
//
//		 Path path = Paths.get(pathString);
//		try {
//			Files.write(path, orginalNewLines);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		model = new EmfModel();
		model.setName("M");

		// University //this works and automatically generates the files without need to
		// all url
		model.setMetamodelFile(new File("SimpleExample/Model-University/University.ecore").getAbsolutePath());
		model.setModelFile(new File("SimpleExample/Model-University/University.model").getAbsolutePath());

		model.setReadOnLoad(true);
		model.setStoredOnDisposal(true);
//		
//		tempModel = EmfModelFactory.getInstance().createEmfModel("orginal",new File("SimpleExample/Model-University/University.ecore") ,new File("SimpleExample/Model-University/University.model") );
//		tempModel.setReadOnLoad(true);
//		tempModel.setStoredOnDisposal(true);

		try {
			model.load();
		} catch (EolModelLoadingException e2) {
			e2.printStackTrace();
		}
//		FolderSync folderSync = new FolderSync();
//		folderSync.getSynchronization(FOLDER_PATH, model);
		syncReader = new FolderSync();
	}

// Here want to create temporary folder in order to put all the contents of files in it and read it to test 
	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

//------------------------------------------------------------- Run the generator

// createModule()
//	public IEolModule createModule() {
//		try {
//			EglFileGeneratingTemplateFactory templateFactory = new EglFileGeneratingTemplateFactory();
//			// League
//			// templateFactory.setOutputRoot("/SimpleExample/GeneratedFileFromLeague/");
//
//			// This works but i should change it to be generalise with absolute bath
//			// this still not able to generate files without it
//			templateFactory.setOutputRoot(
//					"/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/SimpleExample/GeneratedFilesFromUniversity/");
//
//			// University
//			// templateFactory.setOutputRoot("./SimpleExample/GeneratedFilesFromUniversity/");
//			// templateFactory.setOutputRoot(pathString);
//			// setOutputRoot(FOLDER_PATH);
//
//			return new EgxModule(templateFactory);
//		} catch (Exception ex) {
//			throw new RuntimeException(ex);
//		}
//	}

	@Test
	public void runTheGenerator() {
//
//		IEolModule module = createModule(); // The createModule() method follows
//
//		module.getContext().getModelRepository().addModel(model); // The model parameter is the EmfModel you already
//		// create so you need to include that code as well.
//		try {
//			// University
//			// module.parse(new File("SimpleExample/Model-University/University.egx"));
//
//			// this works and automatically generates the files without need to all url
//			module.parse(new File("SimpleExample/Model-University/main.egx"));
//			// and this work
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			module.execute();
//		} catch (EolRuntimeException e) {
//			e.printStackTrace();
//		}
	}

// ------------------------------------------------------------- Test 1,
	// Scenario 1, There is one sync regions which contains different value from the one in the model.
	@Test
	public void test1() throws IOException {
//		String pathString = FOLDER_PATH + "/Test1/MDE101.html";
//		Path path = Paths.get(pathString);
//		BufferedReader original = new BufferedReader(new FileReader(pathString));
//		
//		String line;
//
//		List<String> newLines = new LinkedList<String>();
//
//		while ((line = original.readLine()) != null)
//			if (!line.contains("//sync _OeCHMPxQEemsbtndia47ww, description"))
//				newLines.add(line);
//			else {
//				newLines.add(line);
//				newLines.add("hi");
//
//				while (!line.contains("//endSync"))
//					line = original.readLine();
//				// don't do anything, just loop
//				newLines.add(line);
//
//			}
//
//		original.close();
//		Files.write(path, newLines);
//
//		// Update the model with values taken from the generated file.
//		FolderSync folderSync = new FolderSync();
//		folderSync.getSynchronization(FOLDER_PATH, model);
//
//		// Now that you are done, go to the model and check if the value is updated.
//		IPropertyGetter propertyGetter = model.getPropertyGetter();
//		Object modelElement = model.getElementById("_OeCHMPxQEemsbtndia47ww");
//		String valueOfAttributeInTheModel = null;
//		try {
//			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
//		} catch (EolRuntimeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		assertEquals("test 2", "hi", valueOfAttributeInTheModel);
//
//	
	}
//------------------------------------------------------------- Test 2---------------------------------------------------------------------------------------------	
// Scenario 2, There are three sync regions but contains the same value in the model.	
/*  File tempFile = tempFolder.newFile("file.txt");
    File tempFolder = tempFolder.newFolder("folder");
	// System.out.println("Test folder: " + testFolder.getRoot());

    // Create a temporary file.
    final File tempFile = tempFolder.newFile("tempFile.txt");
  
    // Write something to it.
    FileUtils.writeStringToFile(tempFile, "hello world");
  
    // Read it from temp file
    final String s = FileUtils.readFileToString(tempFile);
  
    // Verify the content
    Assert.assertEquals("hello world", s);     
    //Note: File is guaranteed to be deleted after the test finishes.
*/
	@Test
	public void test2() throws IOException {
//
//		// File tempFolder = tempFolder.newFolder("folder");
//
//		// EmfModel tempModel = model;
//		String pathString = FOLDER_PATH + "/Test2";
//
//		Path path = Paths.get(pathString);
//
//		// final File tempFile = tempFolder.newFile(path.toString());
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
//					newLines.add("hello");
//				}
//				count = 2;
//
//				while (!line.contains("//endSync"))
//					line = original.readLine();
//				// don't do anything, just loop
//				newLines.add(line);
//
//			}
//		original.close();
//
//		Files.write(path, newLines);
//
//		FolderSync folderSync = new FolderSync();
//		folderSync.getSynchronization(FOLDER_PATH, model);
//
//		IPropertyGetter propertyGetter = model.getPropertyGetter();
//		Object modelElement = model.getElementById("_OeCHMPxQEemsbtndia47ww");
//		String valueOfAttributeInTheModel = null;
//		try {
//			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "description");
//		} catch (EolRuntimeException e) {
//			e.printStackTrace();
//		}
//
//		assertEquals("test 2", "hello", valueOfAttributeInTheModel);

	}

//---------------------------------------------------------------- Test 3 
// Scenario 3, There are two different values from the one in the model and between them selves
	@Test
	public void test3() throws IOException {
//		String pathString = FOLDER_PATH + "/Test3";
//
//		Path path = Paths.get(pathString);
//		BufferedReader original = new BufferedReader(new FileReader(pathString));
//
//		String line;
//		List<String> newLines = new LinkedList<String>();
//		int count = 0;
//
//		while ((line = original.readLine()) != null)
//			if (!line.contains("//sync _PDVMoPxQEemsbtndia47ww, name"))
//				newLines.add(line);
//			else {
//				newLines.add(line);
//				if (count == 0) {
//					newLines.add("Tom");
//					count = 1;
//				} else if (count == 1) {
//					newLines.add("Mark");
//					count = 2;
//				} else {
//					newLines.add("Smith");
//				}
//				while (!line.contains("//endSync"))
//					line = original.readLine();
//				newLines.add(line);
//
//			}
//
//		original.close();
//		Files.write(path, newLines);
//
//// Update the model with values taken from the generated file.
//		FolderSync folderSync = new FolderSync();
//		folderSync.getSynchronization(FOLDER_PATH, model);
//
//// Now that you are done, go to the model and check if the value is updated.
//		IPropertyGetter propertyGetter = model.getPropertyGetter();
//		Object modelElement = model.getElementById("_PDVMoPxQEemsbtndia47ww");
//		String valueOfAttributeInTheModel = null;
//		try {
//			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "name");
//		} catch (EolRuntimeException e) {
//			e.printStackTrace();
//		}
//
//		assertEquals("test 2", "Tom", valueOfAttributeInTheModel);

	}

// --------------------------------------------------------------- Test 4, test the Id is exist in the files
	@Test
	public void test4() throws IOException {
		String pathString = FOLDER_PATH + "/Test4";

		syncReader = new FolderSync();
		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, model);


		assertEquals("test 4", "The respictive element not found", valueOfAttributeInTheModel);
	}

// --------------------------------------------------------------- Test 5, test the attribute is exist in the files 
	@Test
	public void test5() throws IOException {
		String pathString = FOLDER_PATH + "/Test5";

		syncReader = new FolderSync();
		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, model);


		assertEquals("test 5", "The respective attribute is not found", valueOfAttributeInTheModel);
	}

	// ----------------------------------------------------------- Test 44, test all sync regions are correct in the folder
	@Test
	public void test44() throws IOException {
		String pathString = FOLDER_PATH + "/Test6";

		syncReader = new FolderSync();
		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, model);


		assertEquals("test 44", "Misformated or incompleted", valueOfAttributeInTheModel);
	}

	// ----------------------------------------------------------- Test 6, the type is compatible Integer == Integer
	@Test
	public void test7() throws IOException {
//		String pathString = FOLDER_PATH + "/Test7";
//
//		syncReader = new FolderSync();
//		String valueOfAttributeInTheModel = syncReader.getSynchronization(pathString, model);
//
//
//		assertEquals("test 7", "Incompatible type", valueOfAttributeInTheModel);
//		
	}


}

















//
//@Test
//public void test2() throws IOException {
//	String pathString = FOLDER_PATH + "/MDE101.html";
//
//	Path path = Paths.get(pathString);
//	BufferedReader original = new BufferedReader(new FileReader(pathString));
//
//	String line;
//	List<String> newLines = new LinkedList<String>();
//	int count = 0;
//
//	while ((line = original.readLine()) != null)
//		if (!line.contains("//sync _NzfVYPxQEemsbtndia47ww, grade"))
//			newLines.add(line);
//		else {
//			newLines.add(line);
//			if (count == 0) {
//				newLines.add("60");
//				count = 1;
//			} else {
//				newLines.add("70");
//			}
//			while (!line.contains("//endSync"))
//				line = original.readLine();
//			// don't do anything, just loop
//			newLines.add(line);
//
//		}
//
//	original.close();
//	Files.write(path, newLines);
//
//	FolderSync folderSync = new FolderSync();
//	folderSync.getSynchronization(FOLDER_PATH, model);
//
//	IPropertyGetter propertyGetter = model.getPropertyGetter();
//	Object modelElement = model.getElementById("_NzfVYPxQEemsbtndia47ww");
//	String valueOfAttributeInTheModel = null;
//	try {
//		valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "grade");
//	} catch (EolRuntimeException e) {
//		e.printStackTrace();
//	}
//
//	assertEquals("test 2", "70" , valueOfAttributeInTheModel);
//}

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////// Boolean	
////boolean isModelBoolean = true;
////boolean isSyncBoolean = true;
////try {
////Integer.parseInt(valueOfAttributeInTheModel);
////} catch (NumberFormatException e) {
////isModelBoolean = false;
////}
////try {
////Integer.parseInt(sync.content);
////} catch (NumberFormatException e) {
////isSyncBoolean = false;
////}
////if ((isModelBoolean && isSyncBoolean) || (!isModelBoolean && !isSyncBoolean)) {
////System.out.println("type is compatible");
////
////// Float	
////boolean isModelFloat = true;
////boolean isSyncFloat = true;
////try {
////Integer.parseInt(valueOfAttributeInTheModel);
////} catch (NumberFormatException e) {
////isModelFloat = false;
////}
////try {
////Integer.parseInt(sync.content);
////} catch (NumberFormatException e) {
////isSyncFloat = false;
////}
////if ((isModelFloat && isSyncFloat) || (!isModelFloat && !isSyncFloat)) {
////System.out.println("type is compatible");
////
//
//
//
////// new for boolean
////else if (propertyGetter.invoke(modelElement, attribute) instanceof Boolean) {
////	valueOfAttributeInTheModel = Integer.parseInt(propertyGetter.invoke(modelElement, attribute).toString());
////} 
////// new for float
////else if (propertyGetter.invoke(modelElement, attribute) instanceof Float) {
////	valueOfAttributeInTheModel = Integer.parseInt(propertyGetter.invoke(modelElement, attribute).toString());
////}
////// new for char
////else if (propertyGetter.invoke(modelElement, attribute) instanceof Boolean) {
////	valueOfAttributeInTheModel = Integer.parseInt(propertyGetter.invoke(modelElement, attribute).toString());
////}
////// new for long not yet
////else if (propertyGetter.invoke(modelElement, attribute) instanceof Boolean) {
////	valueOfAttributeInTheModel = Integer.parseInt(propertyGetter.invoke(modelElement, attribute).toString());
////}
//
//
//
//
//
//
//
//
//
////package org.eclipse.epsilon.egl.sync;
////
////import static org.junit.Assert.*;
////
////import java.io.BufferedReader;
////import java.io.File;
////import java.io.FileReader;
////import java.io.IOException;
////import java.nio.charset.StandardCharsets;
////import java.nio.file.Files;
////import java.nio.file.Path;
////import java.nio.file.Paths;
////import java.util.ArrayList;
////import java.util.HashMap;
////import java.util.LinkedList;
////import java.util.List;
////import java.util.Map;
////import java.util.Map.Entry;
////import java.util.Set;
////
////import javax.xml.parsers.ParserConfigurationException;
////
////import org.eclipse.epsilon.egl.EglFileGeneratingTemplateFactory;
////import org.eclipse.epsilon.egl.EgxModule;
////import org.eclipse.epsilon.emc.emf.EmfModel;
////import org.eclipse.epsilon.eol.IEolModule;
////import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
////import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
////import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
////import org.junit.Before;
////import org.junit.Rule;
////import org.junit.Test;
////import org.junit.rules.TemporaryFolder;
////import org.xml.sax.SAXException;
////
////
////public class SyncTest {
////	private static final String FOLDER_PATH = "./SimpleExample/GeneratedFileFromLeague";
////	EmfModel model;
////	FolderSync syncReader;
////	File modelFile = new File("SimpleExample/ModelLeague/League.model");
////
////	@Before
////	public void init() {
////		//System.out.println("init model");
////		model = new EmfModel();
////		model.setName("M");
////		model.setMetamodelFile(new File("SimpleExample/ModelLeague/League.ecore").getAbsolutePath());
////		model.setModelFile(modelFile.getAbsolutePath());
////		model.setReadOnLoad(true);
////		model.setStoredOnDisposal(true);
////
////		try {
////			model.load();
////		} catch (EolModelLoadingException e2) {
////			e2.printStackTrace();
////		}
////		syncReader = new FolderSync();
////	}
////	// Here want to create temporary folder in order to put all the contents of files in it and read it to test 
////	 @Rule
////	 public  TemporaryFolder tempFolder = new TemporaryFolder();
////	 @Rule
////	 public  TemporaryFolder tempFolder2 = new TemporaryFolder();
////	 @Rule
////	 public  TemporaryFolder tempFolder3 = new TemporaryFolder();
////	   
////
////	// createModule()
////	public IEolModule createModule() {
////	try {
////				EglFileGeneratingTemplateFactory templateFactory = new EglFileGeneratingTemplateFactory();
////				// work as an absolute path/ 
////				// work as relative path
////				templateFactory.setOutputRoot("/SimpleExample/GeneratedFileFromLeague/");
////
////				return new EgxModule(templateFactory);
////			} catch (Exception ex) {
////				throw new RuntimeException(ex);
////			}
////		}
////	//------------------------------------------------------------- Run the generator -------------------------------------------------------------------		
////
////	@Test
////	public void runTheGenerator() {
////		// i should test when i run egx i will get one html file,
////
////		IEolModule module = createModule(); // The createModule() method follows
////		module.getContext().getModelRepository().addModel(model); // The model parameter is the EmfModel you already
////																	// create so you need to include that code as well.
////		try {
////			// work as an absolute path
////			// work as relative path
////			module.parse(new File("SimpleExample/ModelLeague/League.egx").getAbsolutePath());
////
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		try {
////			module.execute();
////		} catch (EolRuntimeException e) {
////			e.printStackTrace();
////		}
////	}
////	
//////------------------------------------------------------------- Test 1------------------------------------------------------------------------------------		
////	// Scenario 1, There is one sync regions which contains different  value  from the one in the model.	
////
////	@Test
////	public void test1() throws IOException {
////		 
////		String pathString = FOLDER_PATH + "/Premier League .html";
////
////		BufferedReader original = new BufferedReader(new FileReader(pathString));
////
////		String line;
////
////		List<String> newLines = new LinkedList<String>();
////
////		while ((line = original.readLine()) != null)
////			if (!line.contains("//sync _zQ-iAKRVEemMKZVsE_d7nA, refereeReport"))
////				newLines.add(line);
////			else {
////				newLines.add(line);
////				newLines.add("hello");
////
////				while (!line.contains("//endSync"))
////					line = original.readLine();
////				newLines.add(line);
////
////			}
////
////		original.close(); 
////		
////		
////		File tfile = tempFolder.newFile();
////		
////		Files.write(tfile.toPath(), newLines);
////
////		// Update the model with values taken from the generated file.
////		FolderSync folderSync = new FolderSync();
////		folderSync.getSynchronization(tempFolder.getRoot().getPath(), model);
////
////
////		// Now that you are done, go to the model and check if the value is updated.
////		IPropertyGetter propertyGetter = model.getPropertyGetter();
////		Object modelElement = model.getElementById("_zQ-iAKRVEemMKZVsE_d7nA");
////		
////		String valueOfAttributeInTheModel = null;
////		try {
////			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "refereeReport");
////		} catch (EolRuntimeException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		assertEquals("test 1", "hello", valueOfAttributeInTheModel);
////
////	}
////
////	// ------------------------------------------------------------- Test 2---------------------------------------------
////	// Scenario 2, There are two sync regions but one of them contains different value from the one in the model.
////
////	@Test
////	public void test2() throws IOException {
////		modelFile = File.createTempFile("aab", "zzz");
////		
////		 //File tempFile = tempFolder2.newFile();
////
////	
////		String pathString = FOLDER_PATH + "/Premier League .html";
////
////		Path path = Paths.get(pathString);
////		BufferedReader original = new BufferedReader(new FileReader(pathString));
////
////		String line;
////		List<String> newLines = new LinkedList<String>();
////		int count = 0;
////
////		while ((line = original.readLine()) != null)
////			if (!line.contains("//sync _zQ-iAKRVEemMKZVsE_d7nA, journalistReport"))
////				newLines.add(line);
////			else {
////				newLines.add(line);
////				if (count == 0) {
////					newLines.add("hello");
////					count = 1;
////				} else {
////					newLines.add("hi");
////				}
////				while (!line.contains("//endSync"))
////					line = original.readLine();
////				newLines.add(line);
////
////			}
////		
////		original.close();
////
////		// temp file and writing it
////		
////				File tfile = tempFolder2.newFile();
////				
////				Files.write(tfile.toPath(), newLines);
////
////		// Update the model with values taken from the generated file.
////		FolderSync folderSync = new FolderSync();
////		folderSync.getSynchronization(tempFolder2.getRoot().getPath(), model);
////
////		// Now that you are done, go to the model and check if the value is updated.
////		IPropertyGetter propertyGetter = model.getPropertyGetter();
////		Object modelElement = model.getElementById("_zQ-iAKRVEemMKZVsE_d7nA");
////		
////		String valueOfAttributeInTheModel = null;
////		try {
////			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "journalistReport");
////		} catch (EolRuntimeException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////
////		assertEquals("test 2", "hello", valueOfAttributeInTheModel);
////		// assertTrue(valueOfAttributeInTheModel.compareTo("hi") == 0 ||
////		// valueOfAttributeInTheModel.compareTo("hello") == 0);
////
////	}
////
////	// ------------------------------------------------------------- Test 3 ----------------------------------------
////	// Scenario 2, There are three sync regions with tow or more different value in the model. manchester 
////	
////	@Test
////	public void test3() throws IOException {
////	
////		String pathString = FOLDER_PATH + "/Premier League .html";
////
////		Path path = Paths.get(pathString);
////
////		BufferedReader original = new BufferedReader(new FileReader(pathString));
////
////		String line;
////
////		List<String> newLines = new LinkedList<String>();
////		int count = 0;
////
////		while ((line = original.readLine()) != null)
////			if (!line.contains("//sync _ocovEKJ7EemFaP2SuqnJFg, name"))
////				newLines.add(line);
////			else {
////				newLines.add(line);
////				if (count == 0) {
////					newLines.add("mark");
////					count = 1;
////				} else if (count == 1) {
////					newLines.add("edward");
////					count = 2;
////				} else {
////					newLines.add("jack");
////				}
////				while (!line.contains("//endSync"))
////					line = original.readLine();
////				newLines.add(line);
////
////			}
////
////		original.close();
////		
////		File tfile = tempFolder3.newFile();
////		
////		Files.write(tfile.toPath(), newLines);
////		// Update the model with values taken from the generated file.
////		FolderSync folderSync = new FolderSync();
////		folderSync.getSynchronization(tempFolder3.getRoot().getPath(), model);
////
////		// Now that you are done, go to the model and check if the value is updated.
////		IPropertyGetter propertyGetter = model.getPropertyGetter();
////		Object modelElement = model.getElementById("_ocovEKJ7EemFaP2SuqnJFg");
////		
////		String valueOfAttributeInTheModel = null;
////		try {
////			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "name");
////		} catch (EolRuntimeException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////
////		assertEquals("test 3", "mark", valueOfAttributeInTheModel);
////
////	}
////
//////------------------------------------------------------------- Test 4---------------------------------------------------------------------------------------------		
////// There are two sync regions but I removed //endsync what will happen. close test 2 before run this..	
//////	@Test
//////	public void test4() throws IOException {
//////		String pathString = FOLDER_PATH + "/Premier League .html";
//////
//////		Path path = Paths.get(pathString);
//////		BufferedReader original = new BufferedReader(new FileReader(pathString));
//////
//////		String line;
//////		List<String> newLines = new LinkedList<String>();
//////		int count = 0;
//////
//////		while ((line = original.readLine()) != null)
//////			if (!line.contains("//sync _ocovEaJ7EemFaP2SuqnJFg, name"))
//////				newLines.add(line);
//////			else {
//////				newLines.add(line);
//////				if (count == 0) {
//////					newLines.add("bob");
//////					count = 1;
//////				} else {
//////					newLines.add("willaim");
//////				}
//////				while (!line.contains("//endSync"))
//////					line = original.readLine();
//////				newLines.add(line);
//////
//////			}
//////
//////		original.close();
//////		// temp file and writing it
//////		
//////		File tfile = tempFolder.newFile();
//////		
//////		Files.write(tfile.toPath(), newLines);
//////
//////		// Update the model with values taken from the generated file.
//////		FolderSync folderSync = new FolderSync();
//////		folderSync.getSynchronization(tempFolder.getRoot().getPath(), model);
//////
//////		// Now that you are done, go to the model and check if the value is updated.
//////		IPropertyGetter propertyGetter = model.getPropertyGetter();
//////		Object modelElement = model.getElementById("_ocovEaJ7EemFaP2SuqnJFg");
//////
//////		String valueOfAttributeInTheModel = null;
//////		try {
//////			valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, "name");
//////		} catch (EolRuntimeException e) {
//////			// TODO Auto-generated catch block
//////			e.printStackTrace();
//////		}
//////
//////		assertEquals("test 4", "mark", valueOfAttributeInTheModel);
//////
//////	}
////
////}
//
//// //endSync -->
//
////  	<!--
////  	<!--//sync _zQ-iAKRVEemMKZVsE_d7nA, journalistReport  -->
////
////    <!--//endSync --></td>
////  	-->

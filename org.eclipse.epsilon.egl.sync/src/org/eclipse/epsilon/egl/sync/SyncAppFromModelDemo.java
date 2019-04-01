package org.eclipse.epsilon.egl.sync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;

public class SyncAppFromModelDemo { // An object class
	FileInputStream fIn;
	public BufferedReader bRead;

	public SyncAppFromModelDemo() {
		try {

			fIn = new FileInputStream("example.txt");
			bRead = new BufferedReader(new InputStreamReader(fIn));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void fileReset() {
		try {
			fIn.getChannel().position(0);
			bRead = new BufferedReader(new InputStreamReader(fIn));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws EolModelLoadingException, IOException {

		// Task 1 : Read and print all the example.txt

		System.out.println('\n' + "Task 1" + " Read and print all the example.txt: " + '\n');

		SyncAppFromModelDemo bufferReader = new SyncAppFromModelDemo();

		List<Synchronization> listObjects = new ArrayList<Synchronization>();

		StringBuilder sb = new StringBuilder();
		try {
			while (true) {
				String generatedFile = bufferReader.bRead.readLine();

				if (generatedFile == null) {
					bufferReader.fileReset();
					break;
				}

				sb.append(' ').append(generatedFile);

				System.out.println(generatedFile);

			}
		} catch (IOException ex) {
			System.out.println("Can't read file.");
		}
		// Task 2: Identify all //sync lines (in example.txt)

		System.out.println('\n' + "Task 2" + " Identify all //sync lines in example.txt (Only): " + '\n');
		String line;

		while ((line = bufferReader.bRead.readLine()) != null) {
			if (line.contains("//sync")) {
				System.out.println(line);
			}
			if (line.contains("//endsync")) {
				System.out.println(line);
			}
		}

		// Task 3: get the content of each //sync (in example.txt)

		System.out.println('\n' + "Task 3" + " Printing the content between //sync and //endsync (Only): " + '\n');

		String content = null;

		bufferReader.fileReset();
		while ((content = bufferReader.bRead.readLine()) != null) {
			if (content.contains("//sync")) {
				while (!(content = bufferReader.bRead.readLine()).contains("//endsync"))
					System.out.println(content);

				System.out.println();
			}
		}


		
		EmfModel model = new EmfModel();
		model.setName("M");
		model.setMetamodelFile(new File("Statemachine.ecore").getAbsolutePath());
		model.setModelFile(new File("Statemachine.model").getAbsolutePath());
		model.setReadOnLoad(true);
		model.setStoredOnDisposal(true);
		model.load();
		//model.getElementById(id)
		bufferReader.fileReset();

		while (true) {
			String generatedLine = bufferReader.bRead.readLine();

			if (generatedLine == null) {
				break;
			}

			if (generatedLine.contains("//sync")) {

				String[] contentLine = null;

				Pattern p = Pattern.compile("\\s.\\w\\d\\w+.\\s\\w+"); 
				Matcher m = p.matcher(generatedLine);

				if (m.find())
					contentLine = (String[]) (m.group(0)).split(",");
				Synchronization sync = new Synchronization(contentLine[0].trim(), contentLine[1].trim());
				
				while (!(generatedLine = bufferReader.bRead.readLine()).contains("//endsync"))
					if(generatedLine != "")
						sync.setContent(generatedLine);
				
				
				listObjects.add(sync);
				
				Object modelElement = model.getElementById(sync.getId());
				
				IPropertySetter propertySetter = model.getPropertySetter();
				propertySetter.setObject(modelElement);
				propertySetter.setProperty(sync.getAttribute());
				try {
					propertySetter.invoke(sync.getContent());
				} catch (EolRuntimeException e) {
					e.printStackTrace();
				}
				
				//Object temp = resource.;
				System.out.println(sync.getId());

				System.out.println(sync.getAttribute());
				
				System.out.println(sync.getContent());

			}
		}
		
		model.store();
		
//	if(updatecontent == "id")
//	{	end updatecontent;
//	
//	else(updatecontent != "id")
//	new == updatecontent;
//	
//	}
	}
	
}

// Task 4: update the respective attribute of the model element where it comes
// from

//System.out.println(
//		'\n' + "Task 4" + " Update the respective attribute of the model element where it comes from: " + '\n');
//
//bufferReader.fileReset();
//String content1 = null;
//
//String generatedLine1 = bufferReader.bRead.readLine();
//
//if (generatedLine1 == null) {
//	break;
//}
//
//if (generatedLine1.contains("//sync")) {
//
//	String[] contentLine1 = null;
//	
//while ((content1 = bufferReader.bRead.readLine()) != null) {
//	if (content1.contains("//sync")) {
//		while (!(content1 = bufferReader.bRead.readLine()).contains("//endsync"))
//
//		String contRegion = null;
//
//		Pattern p = Pattern.compile("(^\\w+)"); 
//		Matcher m = p.matcher(contentLine1);
//
//		if (m.find())
//		contRegion = (String) (m.group(1));
//		Synchronization syncContent = new Synchronization(contRegion);
//
//		listObjects.add(syncContent);
//
//		System.out.println(syncContent.getContent());
//
//		System.out.println();
//	}
//}
/*
 * regex expressions 
 * 1- to print the whole line i should use..\w+\w\s.\w\d\w+.\s\w+ 
 * 2- to print the id and attribute i should use with only common in the middle \\s.\\w\\d\\w+.\\s\\w+ 
 * 3- to print the id and attribute and the content i should use .\w+. 4- to print the whole sync
 * region i should use ..\w+.
 */

//EmfModel model = new EmfModel();
//model.setName("M");
//model.setMetamodelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/Statemachine.ecore").getAbsolutePath());
//model.setModelFile(new File("/Users/sultanalmutairi/git/EglSync/org.eclipse.epsilon.egl.sync/Statemachine.model").getAbsolutePath());
//model.setReadOnLoad(true);
//model.setStoredOnDisposal(true);
//model.load();

///////////////////////////////////////////////////////

//String allFile = sb.toString();
//if (allFile.contains("//sync")) {
//	Pattern pattern2 = Pattern.compile("//sync");
//	Matcher matcher2 = pattern2.matcher(allFile);
//	int count = 0;
//	while (matcher2.find()) {
//		count++;
//	}
//	System.out.println(count);
//}
//if (allFile.contains("//endsync")) {
//	Pattern pattern = Pattern.compile("//endsync");
//	Matcher matcher = pattern.matcher(allFile);
//	int count = 0;
//	while (matcher.find()) {
//		count++;
//	}
//	System.out.println(count);
//}

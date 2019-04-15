package org.eclipse.epsilon.egl.sync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.models.IModel;

public class FileSync {

	public FileSync(String fileName, IModel model) {
		try {
			this.model = model;
			fIn = new FileInputStream(fileName);
			bRead = new BufferedReader(new InputStreamReader(fIn));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	// Checking Git history

	FileInputStream fIn;
	public BufferedReader bRead;
	public IModel model;


	public void reusefile() {
		try {
			fIn.getChannel().position(0);
			bRead = new BufferedReader(new InputStreamReader(fIn));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//	public void readAllFiles() {
//		System.out.println('\n' + "Task 1" + " Read and print all the files in the folder: " + '\n');
//		StringBuilder sb = new StringBuilder();
//
//		try {
//			while (true) {
//				String generatedFile = this.bRead.readLine();
//
//				if (generatedFile == null) {
//					break;
//				}
//
//				sb.append(' ').append(generatedFile);
//
//				System.out.println(generatedFile);
//
//			}
//		} catch (IOException ex) {
//			System.out.println("Can't read file.");
//		}
//
//	}
//
//	public void printSyncLines() {
//		System.out.println('\n' + "Task 2" + " Identify all //sync and //endsync lines in all files in the folder (Only): " + '\n');
//		String line;
//		this.reusefile();
//
//		try {
//			while ((line = this.bRead.readLine()) != null) {
//				if (line.contains("//sync")) {
//					System.out.println(line);
//				}
//				if (line.contains("//endsync")) {
//					System.out.println(line);
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void getSyncContent() {
//		System.out.println('\n' + "Task 3" + " Printing the content between //sync and //endsync in all files in the folder: " + '\n');
//
//		String content = null;
//
//		this.reusefile();
//		try {
//			while ((content = this.bRead.readLine()) != null) {
//				if (content.contains("//sync")) {
//					while (!(content = this.bRead.readLine()).contains("//endsync"))
//						System.out.println(content);
//
//					System.out.println();
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public void updateModel() {
		System.out.println('\n' + "Task 4" + " Update the respective attribute of the model element where it comes from: " + '\n');



		this.reusefile();

		while (true) {
			String generatedLine = null;
			try {
				generatedLine = this.bRead.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

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

				try {
					while (!(generatedLine = this.bRead.readLine()).contains("//endsync"))
						sync.addContent(generatedLine);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				//listObjects.add(sync);

				Object modelElement = model.getElementById(sync.getId());

				IPropertySetter propertySetter = model.getPropertySetter();
				propertySetter.setObject(modelElement);
				propertySetter.setProperty(sync.getAttribute());
				try {
					propertySetter.invoke(sync.getContent());
				} catch (EolRuntimeException e) {
					e.printStackTrace();
				}

				System.out.println(sync.getId());
				System.out.println(sync.getAttribute());
				System.out.println(sync.getContent());

			}
		}
		model.store();
	}
}

/*
 * regex expressions 
 * 1- to print the whole line i should use..\w+\w\s.\w\d\w+.\s\w+ 
 * 2- to print the id and attribute i should use with only common in the middle \\s.\\w\\d\\w+.\\s\\w+ 
 * 3- to print the id and attribute and the content i should use .\w+. 
 * 4- to print the whole sync region i should use ..\w+.
 */
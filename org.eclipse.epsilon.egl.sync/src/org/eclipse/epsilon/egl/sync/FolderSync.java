
package org.eclipse.epsilon.egl.sync;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
import org.eclipse.epsilon.eol.models.IModel;

import com.sun.glass.ui.CommonDialogs.Type;

public class FolderSync {

	public List<Synchronization> getAllTheSyncsRegionsOfTheFolder(String folder) {

		Path folderPath = Paths.get(folder);
		List<String> fileNames = new ArrayList<>();

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
			for (Path path : directoryStream) {
				fileNames.add(path.toString());
			}
		} catch (IOException ex) {
			System.err.println("Error reading files");
		}

		// create data structure for all files's names and contents in the folder
		Map<String, List<String>> namesAndContents = new TreeMap<String, List<String>>();

		List<Synchronization> allTheSyncRegionsInTheFolder = new ArrayList<Synchronization>();

		for (String file : fileNames) {

			try {
				List<String> content = Files.readAllLines(Paths.get(file));

				namesAndContents.put(file, content);

//				FileSync fileSync = new FileSync(file);
//				List<Synchronization> syncRegionsOfThisFile = fileSync.getAllTheSyncRegionsOfTheFile();
//				allTheSyncRegionsInTheFolder.addAll(syncRegionsOfThisFile);
				// new
				FileSync fileSync = new FileSync(file);
				List<Synchronization> syncRegionsOfThisFile = fileSync.getAllTheSyncRegionsOfTheFile();
				if (syncRegionsOfThisFile == null) {
					return null;
				}
				allTheSyncRegionsInTheFolder.addAll(syncRegionsOfThisFile);


			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return allTheSyncRegionsInTheFolder;

	}	

//------------------------------------------------------old method
	
	public void checkSyncs(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {
		// create a data structure
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		IPropertyGetter propertyGetter = model.getPropertyGetter();

		for (Synchronization sync : allTheSyncsRegionOfTheFolder) {

			//try {
				if (model.getElementById(sync.getId()) == null) {
					System.err.println("Sorry! There's no respictive id in the model: " + sync.getId());
					System.exit(0);

				}

				Object modelElement = model.getElementById(sync.getId());

				String valueOfAttributeInTheModel;
//
//				if (propertyGetter.invoke(modelElement, sync.getAttribute()) instanceof String) {
//					valueOfAttributeInTheModel = propertyGetter.invoke(modelElement, sync.getAttribute()).toString();
//				} else if (propertyGetter.invoke(modelElement, sync.getAttribute()) instanceof Integer) {
//					valueOfAttributeInTheModel = propertyGetter.invoke(modelElement, sync.getAttribute()).toString();
//				} else {
//					System.err.println("Other wrong type");
//				}

				String valueOfAttributeInSyncRegion = (String) sync.getContent();
				// new array without duplicated values
				Set<String> valuesInSyncRegionWithoutDuplactie = new HashSet<>();

				// Concatenation Id and attribute in model to have one key
				String key = sync.getId() + "." + sync.getAttribute();

				valuesInSyncRegionWithoutDuplactie.add(valueOfAttributeInSyncRegion);

				if (map.containsKey(key))
					map.get(key).add(valueOfAttributeInSyncRegion);
				else
					map.put(key, valuesInSyncRegionWithoutDuplactie);
//
//			} catch (EolRuntimeException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			catch (NullPointerException e1) {
//				System.err.println("Sorry, such model doesn't exist");
//			}

		}

		for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			String[] id_attr = (String[]) key.split("\\.");
			String id = id_attr[0];
			String attribute = id_attr[1];
			
			ArrayList<String> values = new ArrayList(entry.getValue());
			
			int type = 0;
			
			Object modelElement = model.getElementById(id);

			Object valueOfAttributeInTheModel = " ";
			try {

				if (propertyGetter.invoke(modelElement, attribute) instanceof String) {

					valueOfAttributeInTheModel = propertyGetter.invoke(modelElement, attribute).toString();
					type =0;
				} else if (propertyGetter.invoke(modelElement, attribute) instanceof Integer) {
					valueOfAttributeInTheModel = Integer.parseInt(propertyGetter.invoke(modelElement, attribute).toString());
					type =1;	
				} 
				else if (propertyGetter.invoke(modelElement, attribute) instanceof Double) {
					valueOfAttributeInTheModel = Double.parseDouble(propertyGetter.invoke(modelElement, attribute).toString());
					type = 2;
				}
				else if (propertyGetter.invoke(modelElement, attribute) instanceof Float) {

					valueOfAttributeInTheModel = Float.parseFloat(propertyGetter.invoke(modelElement, attribute).toString());
					type = 3;
				}
				else if (propertyGetter.invoke(modelElement, attribute) instanceof Boolean) {

					valueOfAttributeInTheModel = Boolean.parseBoolean(propertyGetter.invoke(modelElement, attribute).toString());
					type = 4;
				}
				else if (propertyGetter.invoke(modelElement, attribute) instanceof Long) {

					valueOfAttributeInTheModel = Long.parseLong(propertyGetter.invoke(modelElement, attribute).toString());
					type = 5;
				}
				else if (propertyGetter.invoke(modelElement, attribute) instanceof Short) {

					valueOfAttributeInTheModel = Short.parseShort(propertyGetter.invoke(modelElement, attribute).toString());
					type = 6;
				}
				else if (propertyGetter.invoke(modelElement, attribute) instanceof Byte) {

					valueOfAttributeInTheModel = Byte.parseByte(propertyGetter.invoke(modelElement, attribute).toString());
					type = 7;
				}
				else {
					System.err.println("Other wrong type");
				}

				if (values.size() == 1) {
					if (valueOfAttributeInTheModel.equals(values.get(0))) {

						System.out.println("size 1, same value in the model: " + valueOfAttributeInTheModel);

					} else {
						System.out.println("size 1, but differnt value from the one that in the model: " + valueOfAttributeInTheModel);

						Object modelElement1 = model.getElementById(id);
						IPropertySetter propertySetter = model.getPropertySetter();
						propertySetter.setObject(modelElement1);
						propertySetter.setProperty(attribute);
						try {
							// Integer.parseInt
							if (type == 0) {
								propertySetter.invoke(values.get(0));		
							}
							if (type == 1) {
								propertySetter.invoke(Integer.parseInt(values.get(0)));	
							}
							if (type == 2) {
								propertySetter.invoke(Double.parseDouble(values.get(0)));
								
							}
							if (type == 3) {
								propertySetter.invoke(Float.parseFloat(values.get(0)));
							}
							if (type == 4) {
								propertySetter.invoke(Boolean.parseBoolean(values.get(0)));
							}
							if (type == 5) {
								propertySetter.invoke(Long.parseLong(values.get(0)));
							}
							if (type == 6) {
								propertySetter.invoke(Short.parseShort(values.get(0)));
							}
							if (type == 7) {
								propertySetter.invoke(Byte.parseByte(values.get(0)));
							}
						
						} catch (EolRuntimeException e) {
							e.printStackTrace();
						}
						model.store();
					}

				} else if (values.size() == 2) {
					System.out.println((valueOfAttributeInTheModel.toString()).equals(values.get(0)));
					System.out.println(!valueOfAttributeInTheModel.equals(values.get(1)));
					
					System.out.println(valueOfAttributeInTheModel);
					System.out.println(values.get(0));
					System.out.println(values.get(1));

					

					if ((valueOfAttributeInTheModel.toString()).equals(values.get(0)) && !(valueOfAttributeInTheModel.toString()).equals(values.get(1))) {
						System.out.println("Size 2, there are two different values but one of them same the one that in the modelA: "
										+ valueOfAttributeInTheModel);
						Object modelElement1 = model.getElementById(id);

						IPropertySetter propertySetter = model.getPropertySetter();
						propertySetter.setObject(modelElement1);
						propertySetter.setProperty(attribute);
						try {
							if (type == 0) {
								propertySetter.invoke(values.get(1));
								
							}
							if (type == 1) {
								propertySetter.invoke(Integer.parseInt(values.get(1)));	
							}
							if (type == 2) {
								propertySetter.invoke(Double.parseDouble(values.get(1)));
								
							}
							if (type == 3) {
								propertySetter.invoke(Float.parseFloat(values.get(1)));
							}
							if (type == 4) {
								propertySetter.invoke(Boolean.parseBoolean(values.get(1)));
							}
							if (type == 5) {
								propertySetter.invoke(Long.parseLong(values.get(1)));
							}
							if (type == 6) {
								propertySetter.invoke(Short.parseShort(values.get(1)));
							}
							if (type == 7) {
								propertySetter.invoke(Byte.parseByte(values.get(1)));
							}
						} catch (EolRuntimeException e) {
							e.printStackTrace();
						}
						model.store();
						

					} else if ((valueOfAttributeInTheModel.toString()).equals(values.get(1)) && !(valueOfAttributeInTheModel.toString()).equals(values.get(0))) {
						System.out.println("Size 2, there are two different values but one of them same the one that in the modelB : " + valueOfAttributeInTheModel);
						Object modelElement1 = model.getElementById(id);

						IPropertySetter propertySetter = model.getPropertySetter();
						propertySetter.setObject(modelElement1);
						propertySetter.setProperty(attribute);
						try {
							if (type == 0) {
								propertySetter.invoke(values.get(0));
								
							}
							if (type == 1) {
								propertySetter.invoke(Integer.parseInt(values.get(0)));	
							}
							if (type == 2) {
								propertySetter.invoke(Double.parseDouble(values.get(0)));
								
							}
							if (type == 3) {
								propertySetter.invoke(Float.parseFloat(values.get(0)));
							}
							if (type == 4) {
								propertySetter.invoke(Boolean.parseBoolean(values.get(0)));
							}
							if (type == 5) {
								propertySetter.invoke(Long.parseLong(values.get(0)));
							}
							if (type == 6) {
								propertySetter.invoke(Short.parseShort(values.get(0)));
							}
							if (type == 7) {
								propertySetter.invoke(Byte.parseByte(values.get(0)));
							}
					
						} catch (EolRuntimeException e) {
							e.printStackTrace();
						}
						model.store();

					} else {
						System.err.println("Size 2, there are two different values from the one in the model2.");
						System.exit(0);
					}

				} else {
					System.err.println("Sorry! there are more than two different values.");
					System.exit(0);
				}
			} catch (EolRuntimeException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/////////////////////////////////////////old check model method
	
	public String checkModelAgainstEachSyncRegion(IModel model, List<Synchronization> allTheSyncsRegionsOfTheFolder) {

		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		DataTypes dt = new DataTypes();
		IPropertyGetter propertyGetter = model.getPropertyGetter();

		for (Synchronization sync : allTheSyncsRegionsOfTheFolder) {

			String valueOfAttributeInTheModel;

			// a.The respective element is found
			//if ((model.getElementById(sync.id) != null) && (model.getElementById(sync.attribute) != null)) {
			if ((model.getElementById(sync.id) != null) ) {

				System.out.println("The respective element is found");
				// b. The value of the region is checked against the type of the respective property
				try {
					valueOfAttributeInTheModel = propertyGetter.invoke(model.getElementById(sync.id), sync.getAttribute()).toString();
				} catch (EolRuntimeException e1) {
					System.err.println("Sorry! There's no respictive attribute in the model: " + sync.getAttribute());
					//System.exit(0);
					return "The respective attribute is not found";
				}
/////----------------------------------------------------- for all types

				if (dt.isCompatibale(sync.content, valueOfAttributeInTheModel)){
					
					System.out.println("type is compatible");
						
				}
				else {
					System.err.println(" Sorry! The value's types are not compatible ");
					//System.exit(0);
					return "Incompatible type";
				}
				
			} else {
				System.err.println("The respective element not found");
				//System.exit(0);
				return "The respictive element not found";
			}
		}
		return "finish";
	}
	
    public String updateTheModel(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {

		String stepCheck = "finish";
		stepCheck = checkModelAgainstEachSyncRegion(model, allTheSyncsRegionOfTheFolder);
		if(!stepCheck.equals("finish")) return stepCheck;
		checkSyncs(model, allTheSyncsRegionOfTheFolder);
		System.out.println("All sync regions are without conflicts or errors. Thus, model has been updated.");
		return stepCheck;
	}

	public String getSynchronization(String folder, IModel model) {
		
		List<Synchronization> allTheSyncRegionsInTheFolder = new ArrayList<Synchronization>();

		allTheSyncRegionsInTheFolder = getAllTheSyncsRegionsOfTheFolder(folder);
		
		if(allTheSyncRegionsInTheFolder == null) return "Misformated or incompleted";
		String result = updateTheModel(model, allTheSyncRegionsInTheFolder);
	
		return result;
	}
}

































	
//	public void checkSyncs(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {
//		// create a data structure
//		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
//		IPropertyGetter propertyGetter = model.getPropertyGetter();
//
//		for (Synchronization sync : allTheSyncsRegionOfTheFolder) {
//			
//			Object modelElement = model.getElementById(sync.getId());
//			
////			String valueOfAttributeInTheModel;
////			try {
//////start
////					if (model.getElementById(sync.getId()) == null) {
////						System.err.println("Sorry! There's no respictive id in the model: " + sync.getId());
////						System.exit(0);
////				
////					}
////				
////					Object modelElement = model.getElementById(sync.getId());
////				
////					String valueOfAttributeInTheModel;
//// end
////				if (propertyGetter.invoke(modelElement, sync.getAttribute()) instanceof String) {
////					valueOfAttributeInTheModel = propertyGetter.invoke(modelElement, sync.getAttribute()).toString();
////				} else if (propertyGetter.invoke(modelElement, sync.getAttribute()) instanceof Integer) {
////					valueOfAttributeInTheModel = propertyGetter.invoke(modelElement, sync.getAttribute()).toString();
////				} else {
////					System.err.println("Other wrong type");
////				}
////			} catch (EolRuntimeException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			
//				String valueOfAttributeInSyncRegion = (String) sync.getContent();
//				// new array without duplicated values
//				Set<String> valuesInSyncRegionWithoutDuplactie = new HashSet<>();
//				// Concatenation Id and attribute in model to have one key
//				String key = sync.getId() + "." + sync.getAttribute();
//
//				valuesInSyncRegionWithoutDuplactie.add(valueOfAttributeInSyncRegion);
/////start
//					if (map.containsKey(key))
//						map.get(key).add(valueOfAttributeInSyncRegion);
//					else
//						map.put(key, valuesInSyncRegionWithoutDuplactie);
//				
//				} catch (EolRuntimeException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (NullPointerException e1) {
//					System.err.println("Sorry, such model doesn't exist");
//				}
///////end
//		} 
//
//		for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
//			String key = entry.getKey();
//			String[] id_attr = (String[]) key.split("\\.");
//			String id = id_attr[0];
//			String attribute = id_attr[1];
//			
//			/*ArrayList<Object> values = new ArrayList<>();
//			TypeParser tp = new TypeParser();
//			for(String s : entry.getValue()) {
//				if(s!=null || s.equals("")) {
//					Object o = tp.getType(s);
//					values.add(o);
//				}
//			}*/
//			//values.addAll(entry.getValue());
//			ArrayList<String> values = new ArrayList(entry.getValue());
//
//			Object modelElement = model.getElementById(id);
//
//			Object valueOfAttributeInTheModel = " ";
//			try {
/////start
////				if (propertyGetter.invoke(modelElement, attribute) instanceof String) {
////			
////					valueOfAttributeInTheModel = propertyGetter.invoke(modelElement, attribute).toString();
////				} else if (propertyGetter.invoke(modelElement, attribute) instanceof Integer) {
////			
////					valueOfAttributeInTheModel = Integer
////							.parseInt(propertyGetter.invoke(modelElement, attribute).toString());
////				} else {
////					System.err.println("Other wrong type");
////				}
//////end	the next line he use it
//				valueOfAttributeInTheModel = propertyGetter.invoke(modelElement, attribute).toString();
//
////------------------------------------------------------------------ case value = 1
//				if (values.size() == 1) {
//					if (valueOfAttributeInTheModel.equals(((List<String>) values).get(0))) {
//					//if (valueOfAttributeInTheModel.equals(values.get(0))) {
//
//						System.out.println("size 1, same value in the model: " + valueOfAttributeInTheModel.toString());
//
//					} else {
//						System.out.println("size 1, but differnt value from the one that in the model: " + valueOfAttributeInTheModel.toString());
//
//						Object modelElement1 = model.getElementById(id);
//						IPropertySetter propertySetter = model.getPropertySetter();
//						propertySetter.setObject(modelElement1);
//						propertySetter.setProperty(attribute);
////						try {
////							propertySetter.invoke(values.get(0));
////						} catch (EolRuntimeException e) {
////							e.printStackTrace();
////						}
//							// in old method
//							try {
//								// Integer.parseInt
//								propertySetter.invoke(Integer.parseInt(values.get(0)));
//							} catch (EolRuntimeException e) {
//								e.printStackTrace();
//							}
//						model.store();
//					}
////------------------------------------------------------------------ case value = 2					
//				} else if (values.size() == 2) {  
//
//					if (valueOfAttributeInTheModel.equals(values.get(0)) && !valueOfAttributeInTheModel.equals(values.get(1))) {
//						System.out.println("Size 2, there are two different values but one of them same the one that in the model. ");
//						
//						Object modelElement1 = model.getElementById(id);
//
//						IPropertySetter propertySetter = model.getPropertySetter();
//						propertySetter.setObject(modelElement1);
//						propertySetter.setProperty(attribute);
//						try {
//							propertySetter.invoke(values.get(1));
//						} catch (EolRuntimeException e) {
//							e.printStackTrace();
//						}
//						model.store();
//
//					} else if (valueOfAttributeInTheModel.equals(values.get(1)) && !valueOfAttributeInTheModel.equals(values.get(0))) {
//						System.out.println("Size 2, there are two different values but one of them same the one that in the model. ");
//						Object modelElement1 = model.getElementById(id);
//
//						IPropertySetter propertySetter = model.getPropertySetter();
//						propertySetter.setObject(modelElement1); 
//						propertySetter.setProperty(attribute);
//						try {
//							propertySetter.invoke(values.get(0));
//						} catch (EolRuntimeException e) {
//							e.printStackTrace();
//						}
//						model.store();
//						
//
//					} else {
//						System.err.println("Size 2, there are two different values from the one in the model.. ");
//						System.exit(0);
//
//					}
//				// case 3 value 2 or more different
//				} else {
//					System.err.println("Sorry! there are more than two different values....");
//					System.exit(0);
//				}
//			} catch (EolRuntimeException e1) {
//				e1.printStackTrace();
//			}
//		}
//	}
	
	//-----------------------------------------------------------------------------------------------------------

	//------------------------------------------------------------------------------------------------------------
	
//	public void checkModelAgainstEachSyncRegion(IModel model, List<Synchronization> allTheSyncsRegionsOfTheFolder) {
//		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
//
//		IPropertyGetter propertyGetter = model.getPropertyGetter();
//		
//		DataTypes dt = new DataTypes();
//		
//		for (Synchronization sync : allTheSyncsRegionsOfTheFolder) {
//
//			String valueOfAttributeInTheModel;
//
//			// a.The respective element is found
//			if ((model.getElementById(sync.id) != null) ) {
//				System.out.println("The respective element is found");
//				// b. The value of the region is checked against the type of the respective property
//				
//				try {
//					valueOfAttributeInTheModel = propertyGetter.invoke(model.getElementById(sync.id), sync.getAttribute()).toString();
//				} catch (EolRuntimeException e1) {
//					System.err.println("Sorry! There's no respictive attribute in the model: " + sync.getAttribute());
//					System.exit(0);
//					break;
//				}
//				if (dt.isCompatibale(sync.content, valueOfAttributeInTheModel)){
//					
//					System.out.println("type is compatible");
//						
//				}
////				boolean isModelInt = true;
////				boolean isSyncInt = true;
////
////				try {
////					Integer.parseInt(valueOfAttributeInTheModel);
////				} catch (NumberFormatException e) {
////					isModelInt = false;
////				}
////
////				try {
////					Integer.parseInt(sync.content);
////				} catch (NumberFormatException e) {
////					isSyncInt = false;
////				}
////
////				if ((isModelInt && isSyncInt) || (!isModelInt && !isSyncInt)) {
////				
////				System.out.println("type is compatible");
////				}
//				else {
//					System.err.println("Sorry! The value's types are not compatible ");
//					System.exit(0);
//				}
//			} else {
//					System.err.println("Sorry! There's no respictive id in the model: " + sync.getId());
//					System.exit(0);
//			}
//		}
//	}
















































////------------------------------------------------------ 1- only for Integer 
//boolean isValueInModelInt = true;
//boolean isValueInSyncInt = true;
//
//try {
//	Integer.parseInt(valueOfAttributeInTheModel);
//} catch (NumberFormatException e) {
//	isValueInModelInt = false;
//}
//
//try {
//	Integer.parseInt(sync.content);
//} catch (NumberFormatException e) {
//	isValueInSyncInt = false;
//}
//
//if ((isValueInModelInt && isValueInSyncInt) || (!isValueInModelInt && !isValueInSyncInt)) {
//	System.out.println("type is compatible");
//} 
////-------------------------------------------------------- 2- only for Float
//boolean isValueInModelFloat = true;
//boolean isValueInSyncFloat = true;
//
//try {
//	Integer.parseInt(valueOfAttributeInTheModel);
//} catch (NumberFormatException e) {
//	isValueInModelFloat = false;
//}
//
//try {
//	Integer.parseInt(sync.content);
//} catch (NumberFormatException e) {
//	isValueInSyncFloat = false;
//}
//
//if ((isValueInModelFloat && isValueInSyncFloat) || (!isValueInModelFloat && !isValueInSyncFloat)) {
//	System.out.println("type is compatible");
//}
//





















//String valueOfAttributeInTheModel;
//if (propertyGetter.invoke(modelElement, sync.getAttribute()) instanceof String) {
//	valueOfAttributeInTheModel = propertyGetter.invoke(modelElement, sync.getAttribute()).toString();
//} else if (propertyGetter.invoke(modelElement, sync.getAttribute()) instanceof Integer) {
//	valueOfAttributeInTheModel = propertyGetter.invoke(modelElement, sync.getAttribute()).toString();
//} else {
//	System.err.println("Other wrong type");
//}















//		for (Synchronization sync : allTheSyncsRegionOfTheFolder) {
//			
//			IPropertyGetter propertyGetter = model.getPropertyGetter();
//			Object modelElement = model.getElementById(sync.getId());
//			try {
//				System.out.println(propertyGetter.invoke(modelElement, sync.getAttribute()));
//				//System.out.println();
//
//			} catch (EolRuntimeException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

//File path= new File("SimpleExample/GeneratedFileFromLeague/Premier_League.html");
//
//
//DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
//Document doc = dbBuilder.parse(path);
//doc.getDocumentElement().normalize();
//
//NodeList nList = doc.getElementsByTagName("coach");
//
//return  nList.getLength();

// works fine 13/11
//package org.eclipse.epsilon.egl.sync;
//
//import java.io.IOException;
//import java.nio.file.DirectoryStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeMap;
//
//import org.eclipse.epsilon.emc.emf.EmfModel;
//import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
//import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
//import org.eclipse.epsilon.eol.execute.introspection.IPropertySetter;
//import org.eclipse.epsilon.eol.models.IModel;
//
//public class FolderSync {
//	
//
//	public List<Synchronization> getAllTheSyncsRegionsOfTheFolder(String folder) {
//
//		Path folderPath = Paths.get(folder);
//		//System.out.println("Path: " + folderPath);
//		// call all list of the files in the folder
//		List<String> fileNames = new ArrayList<>();
//
//		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(folderPath)) {
//			for (Path path : directoryStream) {
//				fileNames.add(path.toString());
//			}
//		} catch (IOException ex) {
//			System.err.println("Error reading files");
//			ex.printStackTrace();
//		}
//
//		// create data structure for all files's names and contents in the folder
//		Map<String, List<String>> namesAndContents = new TreeMap<String, List<String>>();
//
//		List<Synchronization> allTheSyncRegionsInTheFolder = new ArrayList<Synchronization>();
//
//		for (String file : fileNames) {
//
//			try {
//				// System.out.println("File: " + file);
//				// put the generated file's name and its content into the data structure
//				List<String> content = Files.readAllLines(Paths.get(file));
//				namesAndContents.put(file, content);
//
//				FileSync fileSync = new FileSync(file);
//				List<Synchronization> syncRegionsOfThisFile = fileSync.getAllTheSyncRegionsOfTheFile();
//				allTheSyncRegionsInTheFolder.addAll(syncRegionsOfThisFile);
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//		return allTheSyncRegionsInTheFolder;
//
//	}
//
//	public void updateTheModel(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {
//
//		checkSyncs(model, allTheSyncsRegionOfTheFolder);
//		//return checkSyncs(model, allTheSyncsRegionOfTheFolder);
//
//		
//	}
//	/*
//	 * Check for all sync format,  Collect all them
//	 * 
//	 * When sync block missing something, you need to defined some validated rules that says I will process a file only:
//	 * 
//	 * 1- If all start //sync finish with //endsync
//	 * 2- If there are no kind of nested //sync 
//	 * 3- If //sync is followed by Id',' and attribute name
//	 * 
//	 * If file does not follow these rules just return error message  
//	 */
//
////	/*
////	 * - Make sure all files are well defined 
////	 * - Make sure no conflux between each sync block 
////	 * - Make sure no type inconsistent between the value in files and model 
////	 * - make sure not missing model Ids 
////	 */
////	
////	// Check the type for the sync regions equal the type in the model 
////	// EX, String in the model the type must be String in the region that wants to update this attribute. 
//
//
//
//	public void getSynchronization(String folder, IModel model) {
//
//		List<Synchronization> allTheSyncRegionsInTheFolder = new ArrayList<Synchronization>();
//
//		allTheSyncRegionsInTheFolder = getAllTheSyncsRegionsOfTheFolder(folder);
//
//		for (Synchronization sync : allTheSyncRegionsInTheFolder) {
//
//		}
//
//		updateTheModel(model, allTheSyncRegionsInTheFolder);
//		//return updateTheModel(model, allTheSyncRegionsInTheFolder);
//		//return true;
//	}
//	public void checkSyncs(IModel model, List<Synchronization> allTheSyncsRegionOfTheFolder) {
//
//		// create a data structure
//		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
//		IPropertyGetter propertyGetter = model.getPropertyGetter();
//
//		for (Synchronization sync : allTheSyncsRegionOfTheFolder) {
//
//			try {
//
//				Object modelElement = model.getElementById(sync.getId());
//
//				String valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, sync.getAttribute());
//
//				String valueOfAttributeInSyncRegion = (String) sync.getContent();
//				//System.out.println("Attribute: " + sync.getAttribute());
//				//System.out.println(valueOfAttributeInSyncRegion);
//				//String valueOfAttributeInTheModel = "4";
//
//				//String valueOfAttributeInSyncRegion = (String) sync.getContent();
//
//				// new array without duplicated values
//				Set<String> valuesInSyncRegionWithoutDuplactie = new HashSet<>();
//
//				// Concatenation Id and attribute in model to have one key
//				String key = sync.getId() + "." + sync.getAttribute();
//
//				valuesInSyncRegionWithoutDuplactie.add(valueOfAttributeInSyncRegion);
//
//				if (map.containsKey(key))
//					map.get(key).add(valueOfAttributeInSyncRegion);
//				else
//					map.put(key, valuesInSyncRegionWithoutDuplactie);
//
//			} catch (EolRuntimeException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//
//		for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
//			String key = entry.getKey();
//			String[] id_attr = (String[]) key.split("\\.");
//			String id = id_attr[0];
//			String attribute = id_attr[1];
//			ArrayList<String> values = new ArrayList(entry.getValue());
//			Object modelElement = model.getElementById(id);
//
//			String valueOfAttributeInTheModel;
//			try {
//				valueOfAttributeInTheModel = (String) propertyGetter.invoke(modelElement, attribute);
//
//				if (values.size() == 1) {
//					if (valueOfAttributeInTheModel.equals(values.get(0))) {
//
//						System.out.println("size 1, same value in the model " + valueOfAttributeInTheModel);
//
//					} else {
//						System.out.println("size 1, but differnt value from the one that in the model "
//								+ valueOfAttributeInTheModel);
//
//						Object modelElement1 = model.getElementById(id);
//						IPropertySetter propertySetter = model.getPropertySetter();
//						propertySetter.setObject(modelElement1);
//						propertySetter.setProperty(attribute);
//						try {
//							propertySetter.invoke(values.get(0));
//						} catch (EolRuntimeException e) {
//							e.printStackTrace();
//						}
//						model.store();
//					}
//
//				} else if (values.size() == 2) {
//
//					if (valueOfAttributeInTheModel.equals(values.get(0))) {
//						System.out.println("two different values but one of them same the one that in the model ");
//						Object modelElement1 = model.getElementById(id);
//
//						IPropertySetter propertySetter = model.getPropertySetter();
//						propertySetter.setObject(modelElement1);
//						propertySetter.setProperty(attribute);
//						try {
//							propertySetter.invoke(values.get(1));
//						} catch (EolRuntimeException e) {
//							e.printStackTrace();
//						}
//						model.store();
//
//					} else if (valueOfAttributeInTheModel.equals(values.get(1))) {
//						Object modelElement1 = model.getElementById(id);
//
//						IPropertySetter propertySetter = model.getPropertySetter();
//						propertySetter.setObject(modelElement1);
//						propertySetter.setProperty(attribute);
//						try {
//							propertySetter.invoke(values.get(0));
//						} catch (EolRuntimeException e) {
//							e.printStackTrace();
//						}
//						model.store();
//
//					} else {
//						System.out.println("two different values ");
//
//					}
//
//				} else {
//					System.out.println("more than two values");
//				}
//			} catch (EolRuntimeException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				//return false;
//			}
//		}
//		// return true;
//	}
//	
//}
//
//
//
//
//
//

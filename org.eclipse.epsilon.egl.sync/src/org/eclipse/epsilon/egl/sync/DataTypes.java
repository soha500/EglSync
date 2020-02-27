package org.eclipse.epsilon.egl.sync;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.epsilon.egl.parse.Egx_EolParserRules.returnStatement_return;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.models.IModel;

//comprae between all the types 
public class DataTypes {

	public boolean isCompatibale(String sFile, String sModel) {

		Object s = getType(sFile);
		Object m = getType(sModel);
		return s.getClass() == m.getClass();

	}

	public Object getType(String s) {
		//
		// Pattern.matches("\\d+\\.*\\,*", "122,a");
		// Long Integer
		Pattern pLong = Pattern.compile("\\d+");

		// Float Double
		Pattern pDouble = Pattern.compile("\\d+\\.*\\,*");

		Matcher mLong = pLong.matcher(s);
		Matcher mDouble = pDouble.matcher(s);

		// Boolean
		if (s.equals("true") || s.equals("false"))
			return Boolean.parseBoolean(s);
		else if (mLong.matches()) {
			return Long.parseLong(s);
		} else if (mDouble.matches()) {
			return Double.parseDouble(s);
		} else
			return s;
	}

	public Object getValueType(Object v) {
		System.out.println(v);

		if (v instanceof Integer) {
			return Integer.parseInt(v.toString());

		} else if (v instanceof Double) {
			return Double.parseDouble(v.toString());
		} else if (v instanceof Float) {

			return Float.parseFloat(v.toString());
		} else if (v instanceof Boolean) {

			return Boolean.parseBoolean(v.toString());
		} else if (v instanceof Long) {

			return Long.parseLong(v.toString());
		} else if (v instanceof Short) {

			return Short.parseShort(v.toString());
		} else if (v instanceof Byte) {

			return Byte.parseByte(v.toString());
		} else {
			return v.toString();
		}
	}
}
	




























////comprae between all the types 
//public class DataTypes {
////convet values of attribute to their type
//public Object getType2(IModel model, IPropertyGetter propertyGetter, String id, String attribute, Object valueOfAttributeInTheModel) {
//	
//	Object modelElement = model.getElementById(id);
//
//	try {
//
//		if (propertyGetter.invoke(modelElement, attribute) instanceof String) {
//			valueOfAttributeInTheModel = propertyGetter.invoke(modelElement, attribute).toString();	
//		} else if (propertyGetter.invoke(modelElement, attribute) instanceof Integer) {
//			valueOfAttributeInTheModel = Integer.parseInt(propertyGetter.invoke(modelElement, attribute).toString());
//		} 
//		else if (propertyGetter.invoke(modelElement, attribute) instanceof Double) {
//			valueOfAttributeInTheModel = Double.parseDouble(propertyGetter.invoke(modelElement, attribute).toString());
//		}
//		else if (propertyGetter.invoke(modelElement, attribute) instanceof Float) {
//
//			valueOfAttributeInTheModel = Float.parseFloat(propertyGetter.invoke(modelElement, attribute).toString());
//		}
//		else if (propertyGetter.invoke(modelElement, attribute) instanceof Boolean) {
//
//			valueOfAttributeInTheModel = Boolean.parseBoolean(propertyGetter.invoke(modelElement, attribute).toString());
//		}
//		else if (propertyGetter.invoke(modelElement, attribute) instanceof Long) {
//
//			valueOfAttributeInTheModel = Long.parseLong(propertyGetter.invoke(modelElement, attribute).toString());
//		}
//		else if (propertyGetter.invoke(modelElement, attribute) instanceof Short) {
//
//			valueOfAttributeInTheModel = Short.parseShort(propertyGetter.invoke(modelElement, attribute).toString());
//		}
//		else if (propertyGetter.invoke(modelElement, attribute) instanceof Byte) {
//
//			valueOfAttributeInTheModel = Byte.parseByte(propertyGetter.invoke(modelElement, attribute).toString());
//		}
//		else {
//			System.err.println("Other wrong type");
//		}
//	}catch(Exception e){
//		
//	}
//
//	return valueOfAttributeInTheModel;
//}
//// convert value to specific type
//
//public Object convert(String type, Object value) {
//
//		if (type.contains("String")) {
//			return String.valueOf(value);		
//		} else
//		if (type.contains("Integer")) {
//			return Integer.parseInt(value.toString());	
//		} else
//		if (type.contains("Double")) {
//			return Double.parseDouble(value.toString());
//		} else
//		if (type.contains("Float")) {
//			return Float.parseFloat(value.toString());
//		} else
//		if (type.contains("Boolean")) {
//			return Boolean.parseBoolean(value.toString());
//		} else
//		if (type.contains("Long")) {
//			return Long.parseLong(value.toString());
//		} else
//		if (type.contains("Short")) {
//			return Short.parseShort(value.toString());
//		} else
//		if (type.contains("Byte")) {
//			return Byte.parseByte(value.toString());
//		}
//		return null;
//}
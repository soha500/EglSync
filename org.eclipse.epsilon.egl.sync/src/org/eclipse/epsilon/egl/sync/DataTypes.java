package org.eclipse.epsilon.egl.sync;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// comprae between all the types 
public class DataTypes {
	//string 
	//
	public boolean isCompatibale(String sFile, String sModel) {
		
		Object s = getType(sFile);
		Object m = getType(sModel);
		//System.out.println(s.getClass().getTypeName());
		//System.out.println(m.getClass().getTypeName());
		return  s.getClass() == m.getClass();
		
	}
	
	public Object getType(String s) {
		//
		//Pattern.matches("\\d+\\.*\\,*", "122,a");
		//Long Integer
		Pattern pLong = Pattern.compile("\\d+"); 
		
		//Float Double
		Pattern pDouble = Pattern.compile("\\d+\\.*\\,*");

		Matcher mLong = pLong.matcher(s);
		Matcher mDouble = pDouble.matcher(s);
		
		//Boolean
		if(s.equals("true") || s.equals("false"))
			return Boolean.parseBoolean(s);
		else if(mLong.matches()) {
			return Long.parseLong(s);
		}else if(mDouble.matches()){
			return Double.parseDouble(s);
		}
		else return s;
	}
	
	public Object getValueType(Object v) {
		System.out.println(v);

			if (v instanceof Integer) {
				return Integer.parseInt(v.toString());
					
			} 
			else if (v instanceof Double) {
				return Double.parseDouble(v.toString());
			}
			else if (v instanceof Float) {

				return Float.parseFloat(v.toString());
			}
			else if (v instanceof Boolean) {

				return Boolean.parseBoolean(v.toString());
			}
			else if (v instanceof Long) {

				return Long.parseLong(v.toString());
			}
			else if (v instanceof Short) {

				return Short.parseShort(v.toString());
			}
			else if (v instanceof Byte) {

				return Byte.parseByte(v.toString());
			}
			else {
				return v.toString();
			}
	}
}

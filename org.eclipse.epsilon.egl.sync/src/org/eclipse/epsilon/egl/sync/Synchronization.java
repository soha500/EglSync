package org.eclipse.epsilon.egl.sync;

public class Synchronization {

	String id;
	String attribute;
	String content;
	String updatecontent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String contentCopy) {
		this.content += contentCopy;
		
	}

	public Synchronization(String idCopy, String attributeCopy) { // , String contentCopy
		id = idCopy;
		attribute = attributeCopy;
		content = "";
	}

	
	//	public Synchronization(String contentCopy) { // , String contentCopy
//		content = contentCopy;
//	}


}
//public void updatecontent(String Updatecontent)
//{
//	this.Updatecontent = Updatecontent;
//}
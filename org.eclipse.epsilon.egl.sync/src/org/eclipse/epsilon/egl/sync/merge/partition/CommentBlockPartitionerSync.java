//package org.eclipse.epsilon.egl.sync.merge.partition;
//
//
//import org.eclipse.epsilon.egl.merge.output.Output;
//import org.eclipse.epsilon.egl.merge.output.RegionType;
//import org.eclipse.epsilon.egl.merge.partition.CommentBlockPartitioner;
//import org.eclipse.epsilon.egl.merge.partition.Partitioner;
//
//
////package org.eclipse.epsilon.egl.merge.partition;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.eclipse.epsilon.egl.merge.output.LocatedRegion;
//import org.eclipse.epsilon.egl.merge.output.Region;
//import org.eclipse.epsilon.egl.util.FileUtil;
//
//public class CommentBlockPartitionerSync extends CommentBlockPartitioner {
// 	
//	private final String startComment;
//	private final String endComment;
//	
//	private String firstLine;
//	private String contents = "(.*^[\\s]*)";
//	private String lastLine;
//	
//	private static String escape(String text) {
//		String escaped;
//		
//		escaped = text.replaceAll("\\*", "\\\\*");
//		
//		return escaped;
//	}
//	
//	
//	public CommentBlockPartitionerSync(String startComment, String endComment) {
//	this.startComment = startComment == null ? "" : startComment;
//	this.endComment   = endComment   == null ? "" : endComment;
//	init();
//	}
//
//	private void init() {
//		initFirstLine();
//		initLastLine();
//	}
//	
//	
//	private void initFirstLine() {
//		final StringBuilder regex = new StringBuilder();
//		
//		if (startComment.length() > 0) {
//			// The start comment delimiter
//			regex.append(escape(startComment));
//			
//			// whitespace
//			regex.append("[\\s]*");
//		}
//		
//		// The protected region literal
//		regex.append("(controlled|protected) region ");
//		
//		// The region's id, matched reluctantly and terminated with a space
//		regex.append("(.*?) ");
//		
//		// on or off followed by begin
//		regex.append("(on|off) begin");
//	
//		if (endComment.length() > 0) {
//			// whitespace
//			regex.append("[\\s]*");
//	
//			// The end comment delimiter
//			regex.append(escape(endComment));
//		}
//		
//		// Any amount of whitespace, up to the end of the line
//		regex.append("[\\s]*^");
//		
//		firstLine = regex.toString();
//	}
//	
//	private void initLastLine() {
//		final StringBuilder regex = new StringBuilder();
//		
//		if (startComment.length() > 0) {
//			// The start comment delimiter
//			regex.append(escape(startComment));
//			
//			// whitespace
//			regex.append("[\\s]*");
//		}
//		
//		// The protected region literal
//		regex.append("\\1 region ");
//		
//		// The region's id terminated with a space
//		regex.append("\\2 ");
//		
//		// end
//		regex.append("end");
//		
//		if (endComment.length() > 0) {
//			// whitespace
//			regex.append("[\\s]*");
//	
//			// The end comment delimiter
//			regex.append(escape(endComment));
//		}
//		
//		lastLine = regex.toString();
//	}
//	
//	public String getStartComment() {
//		return startComment;
//	}
//	
//	
//	//--------------------------------------------- getFirstLine() 
//
//	public String getFirstLine(String id, String property, RegionType regionType) {
//		final StringBuilder builder = new StringBuilder();
//		
//		// Build starting comment
//		if (startComment.length() > 0) {
//			builder.append(startComment);
//			//builder.append(' ');
//		}
//		// I have added the // to sync in order to make it print as //sync this is new changes 
//		builder.append("sync ");
////		builder.append(regionTypeToString(regionType) + " sync region ");
//		builder.append(id);
//		builder.append(", ");
//		builder.append(property);
//		builder.append(" ");
////		builder.append(contents ? "on" : "off");
////		builder.append(" begin");
//		if (endComment.length() > 0) {
//			builder.append(' ');
//			builder.append(endComment);
//		}
//		
//		return builder.toString();
//	}
//	//________________________________________________ start-getLastLine()
//	
//	public String getLastLine(RegionType regionType) {
//		final StringBuilder builder = new StringBuilder();
//		
//		// Build ending comment
//		if (startComment.length() > 0) {
//			builder.append(startComment);
////		    builder.append(' ');
//		}
//		builder.append("endSync");
////		builder.append(regionTypeToString(regionType) + " sync region ");
////		builder.append(id);
////		builder.append(" end end of sync region ");
//		if (endComment.length() > 0) {
//			builder.append(' ');
//			builder.append(endComment);
//		}
//		
//		return builder.toString();
//	}
//	
//}
//

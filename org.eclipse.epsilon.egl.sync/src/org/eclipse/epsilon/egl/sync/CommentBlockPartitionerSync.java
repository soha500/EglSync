package org.eclipse.epsilon.egl.sync;
//package org.eclipse.epsilon.egl.sync;
//package org.eclipse.epsilon.egl.sync;
//
//import org.eclipse.epsilon.egl.merge.output.RegionType;
//
//public class CommentBlockPartitionerSync extends CommentBlockPartitioner{
//
//	//____________________________________________ getFirstLine() ______________________________________________________
//
//	public String getFirstLine(String id, String property, RegionType regionType) {
//		final StringBuilder builder = new StringBuilder();
//		
//		// Build starting comment
//		if (startComment.length() > 0) {
//			builder.append(startComment);
//			//builder.append(' ');
//		}
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
//	
//	//________________________________________________ getLastLine() _________________________________________________________
//	
//		public String getLastLine(RegionType regionType) {
//			final StringBuilder builder = new StringBuilder();
//			
//			// Build ending comment
//			if (startComment.length() > 0) {
//				builder.append(startComment);
////			    builder.append(' ');
//			}
//			builder.append("endSync");
////			builder.append(regionTypeToString(regionType) + " sync region ");
////			builder.append(id);
////			builder.append(" end end of sync region ");
//			if (endComment.length() > 0) {
//				builder.append(' ');
//				builder.append(endComment);
//			}
//			
//			return builder.toString();
//		}
//		
//}

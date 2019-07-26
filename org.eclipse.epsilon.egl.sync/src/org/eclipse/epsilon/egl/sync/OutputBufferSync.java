//package org.eclipse.epsilon.egl.sync;
//package org.eclipse.epsilon.egl.sync;
//
//import org.eclipse.epsilon.egl.exceptions.EglRuntimeException;
//import org.eclipse.epsilon.egl.merge.output.RegionType;
//import org.eclipse.epsilon.egl.merge.partition.CommentBlockPartitioner;
//import org.eclipse.epsilon.egl.output.IOutputBuffer;
//import org.eclipse.epsilon.egl.output.OutputBuffer;
//
///* Begins a sync region by appending the start marker to the buffer with the given identifier and property name. 
//	 * Uses the first two parameters as start and end markers.
//	 */
//
//public class OutputBufferSync extends OutputBuffer implements IOutputBuffer{
//	@Override
//	public String startSync(String startComment, String id, String property) throws EglRuntimeException {
//
//		return startLocate(startComment, id, property, RegionType.Protected);
//	}
//
//	@Override
//	public String startControl(String startComment, String id, String property) throws EglRuntimeException {
//
//		return startLocate(startComment, id, property, RegionType.Controlled);
//	}
//
//	public String startLocate(String startComment, String id, String property, RegionType regionType)
//			throws EglRuntimeException {
//
//		assertNoMixedRegions(regionType);
//
//		if (lastLine != null)
//			throw new EglRuntimeException("The current region must be stopped before another region may begin.",
//					context.getModule());
//
//		final CommentBlockPartitioner customPartitioner = new CommentBlockPartitioner(startComment, lastLine);
//		lastLine = customPartitioner.getLastLine(regionType);
//
//		context.getPartitioner().addPartitioner(customPartitioner);
//		customPartitioners.add(customPartitioner);
//
//		return customPartitioner.getFirstLine(id, property, regionType);
//	}
//
//	protected void assertNoMixedRegions1(RegionType regionType) throws EglRuntimeException {
//		if (regionType == RegionType.Controlled)
//			hasControlledRegions = true;
//		else if (regionType == RegionType.Protected)
//			hasProtectedRegions = true;
//
//		if (hasControlledRegions && hasProtectedRegions) {
//			throw new EglRuntimeException("Templates cannot contain both protected and controlled regions",
//					context.getModule());
//		}
//	}
//
//	@Override
//	public String startSync(String startComment, String endComment, String id, String property)
//			throws EglRuntimeException {
//
//		return startLocate(startComment, endComment, id, property, RegionType.Protected);
//	}
//
//	@Override
//	public String startControl(String startComment, String endComment, String id, String property)
//			throws EglRuntimeException {
//
//		return startLocate(startComment, endComment, id, property, RegionType.Controlled);
//	}
//
//	public String startLocate(String startComment, String endComment, String id, String property, RegionType regionType)
//			throws EglRuntimeException {
//
//		assertNoMixedRegions(regionType);
//
//		if (lastLine != null)
//			throw new EglRuntimeException("The current region must be stopped before another region may begin.",
//					context.getModule());
//
//		final CommentBlockPartitioner customPartitioner = new CommentBlockPartitioner(startComment, endComment);
//		lastLine = customPartitioner.getLastLine(regionType);
//
//		context.getPartitioner().addPartitioner(customPartitioner);
//		customPartitioners.add(customPartitioner);
//
//		return customPartitioner.getFirstLine(id, property, regionType);
//	}
//
//	protected void assertNoMixedRegions2(RegionType regionType) throws EglRuntimeException {
//		if (regionType == RegionType.Controlled)
//			hasControlledRegions = true;
//		else if (regionType == RegionType.Protected)
//			hasProtectedRegions = true;
//
//		if (hasControlledRegions && hasProtectedRegions) {
//			throw new EglRuntimeException("Templates cannot contain both protected and controlled regions",
//					context.getModule());
//		}
//	}
//
//	/*
//	 * Ends the current protected region by appending the end marker to the buffer.
//	 * This operation should be invoked only if there a protected region is
//	 * currently open (i.e. has been started by invoking startPreserve but not yet
//	 * stopped by invoking stopPreserve).
//	 */
//	@Override
//	public String endSync() throws EglRuntimeException {
//		return stopLocate();
//	}
//}
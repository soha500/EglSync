package org.eclipse.epsilon.egl.sync;

import java.io.File;

public class Memory {

	public static void main(String[] args) {
		System.out.println("----------------");
        
        /* Total number of processors or cores available to the JVM */
        System.out.println("Available processors (cores): " + 
            Runtime.getRuntime().availableProcessors());

        /* Total amount of free memory available to the JVM */
        System.out.println("Free memory (bytes): " + 
            Runtime.getRuntime().freeMemory());

        /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory1 = Runtime.getRuntime().maxMemory();
        /* Maximum amount of memory the JVM will attempt to use */
        System.out.println("Maximum memory (bytes): " + 
            (maxMemory1 == Long.MAX_VALUE ? "no limit" : maxMemory1));

        /* Total memory currently available to the JVM */
        System.out.println("Total memory available to JVM (bytes): " + 
            Runtime.getRuntime().totalMemory());

        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();

        /* For each filesystem root, print some info */
        for (File root : roots) {
          System.out.println("File system root: " + root.getAbsolutePath());
          System.out.println("Total space (bytes): " + root.getTotalSpace());
          System.out.println("Free space (bytes): " + root.getFreeSpace());
          System.out.println("Usable space (bytes): " + root.getUsableSpace());
        }
   }

	}


package edu.ncsu.csc216.shipping_simulator.simulation;

import edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip;

/**
 * Log class gathers data from shipments as they leave a shipment process station. It updates the
 * amount of shipments completed, the total wait time of all shipments, and the total process time of all shipments.
 * @author Aurora Bravo
 */
public class Log {

	/** The number of items that have logged their information and left the simulation */
	private int numCompleted;
	
	/** The sum of all wait times logged by items */
	private int totalWaitTime;
	
	/** The sum of all times that items took to do actual processing */
	private int totalProcessTime;
	
	/**
	 * Constructor for the Log class. Sets all of the fields to zero.
	 */
	public Log() {
		numCompleted = 0;
		totalWaitTime = 0;
		totalProcessTime = 0;
		
	}
	
	/**
	 * Getter for the number of packages that have completed processing and have left the simulation.
	 * @return numCompleted int number of packages completed.
	 */
	public int getNumCompleted() {
		return numCompleted;
	}
	
	/**
	 * Updates the three data members from the parameter.
	 * Checks that the ItemToShip is not null.
	 * @param item ItemToShip package
	 */
	public void logItem(ItemToShip item) {
		//Make sure that ItemToShip is not null
		if (item != null) {
			//ItemToShip is used to record the item's statistics
			numCompleted++;
			totalWaitTime += item.getWaitTime();
			totalProcessTime += item.getProcessTime();
		}
		
	}
	
	/**
	 * Calculates average wait time using total wait time and number of items completed.
	 * Checks that number of items completed is not zero.
	 * @return averageWaitTime int average wait time of all shipments in the simulation
	 */
	public double averageWaitTime() {
		if (numCompleted == 0) {
			return 0;
		}
		double averageWaitTime = (double)totalWaitTime / (double)numCompleted;
		
		return averageWaitTime;
		
	}
	
	/**
	 * Calculates average process time using total process time and number of items completed.
	 * @return averageProcessTime int average process time of all shipments in the simulation.
	 */
	public double averageProcessTime() {
		if (numCompleted == 0) {
			return 0;
		}
		double averageProcessTime = (double)totalProcessTime / (double)numCompleted;
		
		return averageProcessTime;
		
		
	}
}

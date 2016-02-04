package edu.ncsu.csc216.shipping_simulator.queues;

import java.util.NoSuchElementException;

import edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip;
import edu.ncsu.csc216.shipping_simulator.simulation.Log;

/**
 * ShipmentProcessStation class represents a shipment station that will contain a line of ItemToShip shipments.
 * This class implements LineOfItems interface. 
 * @author Aurora Bravo
 *
 */
public class ShipmentProcessStation implements LineOfItems {
	
	/** ShipmentQueue line of ItemToShip packages awaiting processing at this station */
	private ShipmentQueue line;
	/** Log log used to gather data from packages as they leave the shipment process station */
	private Log log;
	/** int timeWhenAvailable time the queue for this station will be clear of all the items in the queue */
	private int timeWhenAvailable;

	/**
	 * ShipmentProcessStation constructor. Initializes the Log, and constructs the ShipmentQueue
	 * @param log Log used to log data from packages as they leave a shipment process station
	 */
	public ShipmentProcessStation(Log log) {
		// TODO Auto-generated constructor stub
		this.log = log;
		//Construction of queue also happens here (how & how many?)
		line = new ShipmentQueue();
		
		
	}

	/**
	 * Returns the number of items still in the queue
	 * @return int number of items in the queue.
	 */
	public int size() {
		return line.size();
	}
	
	/**
	 * Removes the front item from the queue, logging its information in the process.
	 * The removed item is returned.
	 * @return item ItemToShip shipment removed from the ShipmentQueue.
	 */
	public ItemToShip processNext() {
		if (line == null) {
			throw new NoSuchElementException();
		}
		ItemToShip item = line.remove();
		log.logItem(item);
		item.removeFromWaitingLine();
		return item;  
	}
	
	/**
	 * Returns true if the queue is not empty.
	 * @return boolean true if queue is not empty.
	 */
	public boolean hasNext() {
		return (!line.isEmpty());
		
	}
	
	/**
	 * Tells when the item at the front of the queue (currently being processed) will
	 * finish its processing and leave the simulation.
	 * If the queue is empty, return Integer.MAX_VALUE.
	 * @return departTime int time when a shipment will leave the shipment process station.
	 */
	public int departTimeNext() {
		int departTime = Integer.MAX_VALUE;
		if (!line.isEmpty()){
			ItemToShip item = line.front();
			departTime = item.getArrivalTime() + item.getProcessTime() + item.getWaitTime();
		}
		return departTime;
		
	}
	
	/**
	 * Adds an item to the end of the queue, updating the item's waitTime
	 * as well as the time when the items in the line will be clear of all
	 * current items.
	 * @param item ItemToship shipment to be added to a shipment process station queue.
	 */
	public void addItemToLine(ItemToShip item) {
		line.add(item);
		if (timeWhenAvailable <= item.getArrivalTime()){ //if wait time is negative, set wait time to 0
			int waitTime = 0;
			item.setWaitTime(waitTime); //could be more efficient
			timeWhenAvailable = item.getArrivalTime() + item.getProcessTime();
		} else {
			int waitTime = timeWhenAvailable - item.getArrivalTime();
			item.setWaitTime(waitTime);
			timeWhenAvailable = timeWhenAvailable + item.getProcessTime();
		}
 
	}
}

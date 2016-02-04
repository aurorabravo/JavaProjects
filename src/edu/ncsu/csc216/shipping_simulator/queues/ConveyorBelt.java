package edu.ncsu.csc216.shipping_simulator.queues;

import java.util.NoSuchElementException;

import edu.ncsu.csc216.shipping_simulator.pkg.BookShipmentFactory;
import edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip;

/**
 * ConveyorBelt class represents a line of shipments where shipments go before they go into a shipment process station
 * This class implements LineOfItems interface.
 * @author Aurora Bravo
 */
public class ConveyorBelt implements LineOfItems {
	
	/** ShipmentQueue queueFromFactory queue of packages */
	private ShipmentQueue queueFromFactory;
	/** ShipmentProcessStation[] station array of stations used by processNext */
	private ShipmentProcessStation[] station;

	/**
	 * Constructor for ConveyorBelt. It initializes the station array and adds ItemToShip shipments to the queue
	 * using BookShipmentFactory.generateBookShipments().
	 * @param numberOfItems int amount of shipments to be created.
	 * @param station ShipmentProcessStation array of stations.
	 */
	public ConveyorBelt(int numberOfItems, ShipmentProcessStation[] station) {
		this.station = station; //assigns the station
		
		//creating the queue and adding itemToShip to queue (with arrival time)
		queueFromFactory = new ShipmentQueue(); 
		for (int i = 0; i < numberOfItems; i++) {
			queueFromFactory.add(BookShipmentFactory.generateBookShipment());
		}
	}


	/**
	 * Returns the size of the ShipmentQueue
	 * @return int size of shipment queue
	 */
	public int size() {
		return queueFromFactory.size();
	}
	
	/**
	 * Returns true if ShipmentQueue is not empty
	 * @return boolean true of ShipmentQueue is not empty
	 */
	public boolean hasNext() {
		return (!queueFromFactory.isEmpty());
		
	}
	
	/**
	 * Returns the next ItemToShip that needs to be processed. Removes the item from the ShipmentQueue and 
	 * adds it to the ShipmentProcessStation.
	 * @return ItemToShip item to be processed next.
	 */
	public ItemToShip processNext() {
		if (queueFromFactory == null) {
			throw new NoSuchElementException();
		}
		ItemToShip item = queueFromFactory.remove();
		item.getInLine(station);
		return item;
		
	}
	
	/**
	 * Tells when the item at the front of the ConveyorBelt queue will depart that queue
	 * and subsequently enter a ShipmentProcessStation queue.
	 * If the queue is empty, return Integer.MAX_VALUE.
	 * @return int departTime time when the item at the front of the conveyor belt will depart.
	 */
	public int departTimeNext() {
		int departTime = Integer.MAX_VALUE;
		if (!queueFromFactory.isEmpty()){
			ItemToShip item = queueFromFactory.front();
			departTime = item.getArrivalTime();
		}
		return departTime;
		
	}
	
}


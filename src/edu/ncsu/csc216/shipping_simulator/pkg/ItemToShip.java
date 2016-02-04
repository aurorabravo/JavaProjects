package edu.ncsu.csc216.shipping_simulator.pkg;

import java.awt.Color;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;

/**
 * ItemToShip class represents an shipment in the simulation. It starts at the conveyor belt, then it gets sent
 * to a shipment process station, and it logs its information when it leaves the station. Each ItemToShip type has
 * a different color and slightly different properties, like the choosing of which station index to go into.
 * @author Aurora Bravo
 */
public abstract class ItemToShip {
	
	/** The initial station index that all ItemToShip objects have when they are created */
	public static final int INITIAL_STATION_IDX = -1;
	/** Time when when the item enters a shipment process station line */
	private int arrivalTime;
	/** Number of seconds the item waits in a shipment process station queue before processing */
	private int waitTime;
	/** Time required to process an item */
	private int processTime;
	/** The index of the ShipmentProcessStation that the item has selected */
	private int stationIndex;
	/** Indicates whether an item is currently on a ShipmentProcessStation queue */
	private boolean waitingProcessing;
	
	/**
	 * Constructor for ItemToShip. It initializes arrivalTime, and waitTime, as well as stationIndex to -1.
	 * It also sets waitingProcessing to false.
	 * @param arrivalTime int arrival time of the package to a station from a conveyor belt
	 * @param processTime int process time of the package
	 */
	public ItemToShip(int arrivalTime, int processTime) {
		this.arrivalTime = arrivalTime;
		this.processTime = processTime;
		stationIndex = INITIAL_STATION_IDX;
		waitingProcessing = false;
		
	}
	
	/**
	 * Getter for the arrival time.
	 * @return arrivalTime int arrival time of the package to a station from a conveyor belt
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	/**
	 * Getter for the wait time.
	 * @return waitTime int wait time that a given package waits at a shipment process station
	 */
	public int getWaitTime() {
		return waitTime;
	}

	/**
	 * Setter for wait time. Sets the wait time to the parameter's wait time.
	 * @param waitTime int wait time that a given package waits at a shipment process station
	 */
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	/**
	 * Getter for process time.
	 * @return processTime int time that a package takes to process.
	 */
	public int getProcessTime(){
		return processTime;
	}
	
	/**
	 * Getter for station index.
	 * @return stationIndex int index in which a package is in the shipment process station array.
	 */
	public int getStationIndex() {
		return stationIndex;
	}
	
	/**
	 * Returns true if a package is waiting in line at a shipment process station.
	 * @return waitingProcessing boolean.
	 */
	public boolean isWaitingInLineAtStation() {
		return waitingProcessing;
	}
	
	/**
	 * Removes a shipment from a waiting line. Sets waitingProcessing to false.
	 */
	public void removeFromWaitingLine() {
		waitingProcessing = false;
		
	}
	
	/**
	 * Setter for stationIndex and waitingProcessing. Sets station index to the parameter's station index
	 * Checks that a given index is not negative, and sets waitingProcessing to true.
	 * @param stationIdx int index of the station that the package is in.
	 */
	protected void setStationIndex(int stationIdx) {
		stationIndex = stationIdx;
		if (stationIndex >= 0) {
			waitingProcessing = true;
		}
	}
	
	/**
	 * Abstract method to be extended by children. Shipments choose which station to go into.
	 * @param station ShipmentProcessStation[] array of stations
	 */
	public abstract void getInLine(ShipmentProcessStation[] station);
	
	/**
	 * Abstract method to be extended by children. Returns shipment's color.
	 * @return Color of the package.
	 */
	public abstract Color getColor();
	
	
}

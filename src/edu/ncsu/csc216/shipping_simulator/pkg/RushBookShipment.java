package edu.ncsu.csc216.shipping_simulator.pkg;

import java.awt.Color;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;

/**
 * RushBookShipment is a child of ItemToShip. It represents a rush shipment
 * in the simulation. Rush shipments can go in all types of process stations.
 * These shipments are also colored black.
 * @author Aurora Bravo
 */
public class RushBookShipment extends ItemToShip {
	
	/** Color color the color used for the shipment */
	private Color color;

	/**
	 * The constructor for RushBookShipment. It calls ItemToShip's constructor
	 * This constructor also throws an IllegalArgumentException if arrivalTime is less than 0 or
	 * if processTime is less than 0. Sets color to Black.
	 * @param arrivalTime int arrival time of the package to a station from a conveyor belt
	 * @param processTime int process time of the package
	 */
	public RushBookShipment(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		if (arrivalTime < 0 || processTime < 0) {
			throw new IllegalArgumentException();
		}
		color = Color.BLACK;
	}
	
	/**
	 * Getter method for the color field
	 * @return color Color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Overrides the getInLine method from ItemToShip. This method selects which array
	 * the package will go into. Rush packages can only go in rush shipment process stations of the station array.
	 * @param station ShipmentProcessStation[] array of stations
	 */
	@Override
	public void getInLine(ShipmentProcessStation[] station) {
		//find the station with the fewest number of packages
		//if two or more have fewest, package goes to smallest index
		int fewest = 0;
		for (int i = 0; i < station.length; i++){
			if (station[i].size() < station[fewest].size()) { //remember < (smaller) & <= 
				fewest = i;
			}
		}
		//add item to selected station
		super.setStationIndex(fewest);
		station[fewest].addItemToLine(this);
		
	}
	
	

}

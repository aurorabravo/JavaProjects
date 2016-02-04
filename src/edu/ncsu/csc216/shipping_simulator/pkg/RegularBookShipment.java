package edu.ncsu.csc216.shipping_simulator.pkg;

import java.awt.Color;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;

/**
 * RegularBookShipment is a child of ItemToShip. It represents a regular book shipment in the simulation.
 * Regular shipments can go in regular and international shipment process stations.
 * These shipments are also colored blue
 * @author Aurora Bravo
 */
public class RegularBookShipment extends ItemToShip {
	
	/** Color color the color used for the shipment */
	private Color color;

	/**
	 * The constructor for RegularBookShipment. It calls ItemToShip's constructor
	 * This constructor also throws an IllegalArgumentException if arrivalTime is less than 0 or
	 * if processTime is less than 0. Sets color to blue.
	 * @param arrivalTime int arrival time of the package to a station from a conveyor belt
	 * @param processTime int process time of the package
	 */
	public RegularBookShipment(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		if (arrivalTime < 0 || processTime < 0) {
			throw new IllegalArgumentException();
		}
		this.color = Color.BLUE;
	}

	/**
	 * Overrides the getInLine method from ItemToShip. This method selects which array
	 * the package will go into. Regular packages go in international and regular shipment
	 * process stations of the station array.
	 * @param station ShipmentProcessStation[] array of stations
	 */
	@Override
	public void getInLine(ShipmentProcessStation[] station) {
		int fewest = 1;
		for (int i = 1; i < station.length; i++){
			if (station[i].size() < station[fewest].size()) { //remember < (smaller) & <= 
				fewest = i;
			}
		}
		//add item to selected station
		super.setStationIndex(fewest);
		station[fewest].addItemToLine(this);
		
	}

	/**
	 * Getter method for the color field
	 * @return color Color of the package
	 */
	@Override
	public Color getColor() {
		return color;
	}

}

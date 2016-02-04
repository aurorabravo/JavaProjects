package edu.ncsu.csc216.shipping_simulator.pkg;

import java.awt.Color;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;

/**
 * InternationalBookShipment is a child of ItemToShip. It represents an international shipment
 * in the simulation. International shipments can only go in international process stations.
 * These shipments are also colored red.
 * @author Aurora Bravo
 */
public class InternationalBookShipment extends ItemToShip {
	
	/** Color color the color used for the shipment */
	private Color color;
	
	/**
	 * The constructor for InternationalBookShipment. It calls ItemToShip's constructor
	 * This constructor also throws an IllegalArgumentException if arrivalTime is less than 0 or
	 * if processTime is less than 0. Sets color to Red.
	 * @param arrivalTime int arrival time of the package to a station from a conveyor belt
	 * @param processTime int process time of the package
	 */
	public InternationalBookShipment(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		if (arrivalTime < 0 || processTime < 0) {
			throw new IllegalArgumentException();
		}
		color = Color.RED;
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
	 * the package will go into. International packages can only go in international shipment process stations of the station array.
	 * @param station ShipmentProcessStation[] array of stations
	 */
	@Override
	public void getInLine(ShipmentProcessStation[] station) {
		int fewest = station.length - (int) Math.ceil(station.length * 0.25);
		for (int i = fewest; i < station.length; i++){
			if (station[i].size() < station[fewest].size()) { //remember < (smaller) & <= 
				fewest = i;
			}
		}
		//add item to selected station
		super.setStationIndex(fewest);
		station[fewest].addItemToLine(this);
		
	}
}

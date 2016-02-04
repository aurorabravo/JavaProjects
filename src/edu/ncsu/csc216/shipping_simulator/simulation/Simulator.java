package edu.ncsu.csc216.shipping_simulator.simulation;

import java.awt.Color;

import edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip;
import edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt;
import edu.ncsu.csc216.shipping_simulator.queues.LineOfItems;
import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;

/**
 * Simulator class runs the simulation step-by-step. It controls the adding and leaving of packages into the simulation
 * And it uses a Log to gather data from each package.
 * @author Aurora Bravo
 */
public class Simulator {
	
	/** Minimum number of stations in the simulator*/
	private static final int MIN_NUM_STATIONS = 3;
	/** Maximum number of stations in the simulator */
	private static final int MAX_NUM_STATIONS = 9;
	/** Number of stations */
	private int numStations;
	/** Number of shipments */
	private int numShipments;
	/** Amount of steps taken */
	private int stepsTaken;
	/** The conveyor belt used in the simulation */
	private ConveyorBelt theBelt;
	/** ShipmentProcessStation array of stations */
	private ShipmentProcessStation[] station;
	/** Log used to gather data by shipment items */
	private Log myLog;
	/** EventCalendar myCalendar used to keep track of events */
	private EventCalendar myCalendar;
	/** ItemToShip currentShipment shipment currently being used by the Simulator */
	private ItemToShip currentShipment;
	
	/**
	 * Constructor for Simulator class. It initializes the number of shipments and number of stations to the parameter's value.
	 * It also sets stepsTaken to zero, currentShipment to null. It creates a Log, a ShipmentProcessStation array, a ConveyorBelt
	 * and a Calendar. It also throws an IllegalArgumentException if number of stations is less than 3 or greater than 9.
	 * Throws an IllegalArgumentException if the number of shipments is less than 1.
	 * @param numStations int number of stations in the simulation.
	 * @param numShipments int number of shipments int he simulation.
	 */
	public Simulator(int numStations, int numShipments) {
		if (numStations < MIN_NUM_STATIONS || numStations > MAX_NUM_STATIONS || numShipments < 1) {
			throw new IllegalArgumentException();
		}
		this.numShipments = numShipments;
		this.numStations = numStations;
		stepsTaken = 0;
		
		currentShipment = null;
		myLog = new Log();
		station = new ShipmentProcessStation[numStations];
		for (int i = 0; i < numStations; i++){
			station[i] = new ShipmentProcessStation(myLog);
		}
		
		theBelt = new ConveyorBelt(numShipments, station);
		myCalendar = new EventCalendar(station, theBelt);
	}
	
	/**
	 * Handles the next event from the EventCalendar
	 * updates currentShipment to the shipment that needs to be processed next.
	 * Increments stepsTaken by one.
	 */
	public void step() {
		currentShipment = null;
		LineOfItems line = myCalendar.nextToBeProcessed();
		currentShipment = line.processNext();
		stepsTaken++;
	}
	
	/**
	 * Returns the number of steps taken so far.
	 * @return stepsTaken int amount of steps taken so far.
	 */
	public int getStepsTaken() {
		return stepsTaken;
		
	}
	
	/**
	 * Returns total number of steps in the simulation. (number of shipments * 2)
	 * @return numShipments int total number of steps in the simulation.
	 */
	public int totalNumberOfSteps() {
		return numShipments * 2; 
	}
	
	/**
	 * Returns true if the simulation has not yet finished.
	 * Returns false if it has.
	 * @return boolean true if simulation has not finished, false otherwise.
	 */
	public boolean moreSteps() {
		if (stepsTaken < totalNumberOfSteps()){
			return true;
		}
		
		return false;
		
	}

	/**
	 * Returns index of ShipmentProcessStations selected by the currentShipment,
	 * or -1 if currentShipment is null.
	 * @return idx int index of the current shipment.
	 */
	public int getCurrentIndex() {
		if (currentShipment == null) {
			return -1;
		}
		return currentShipment.getStationIndex();
		
	}
	
	/**
	 * Color of the currentShipment or null if
	 * currentShipment is null.
	 * @return Color of the current shipment.
	 */
	public Color getCurrentShipmentColor() {
		if (currentShipment == null) {
			return null;
		}
		return currentShipment.getColor();
	}
	
	/**
	 * Returns true if the most recently handled item completed
	 * processing and left a shipment process station line.
	 * Returns false if the most recently handled item left the conveyor belt
	 * to join a shipment process station line, or if there is no current item.
	 * @return boolean true if item is no longer waiting in line for processing.
	 */
	public boolean itemLeftSimulation() {
		if (currentShipment == null){
			return false;
		} 
		return !currentShipment.isWaitingInLineAtStation();
	}
	
	/**
	 * Average number of seconds items had to wait in a shipment
	 * process station line prior to their actual processing.
	 * @return averageWaitTime double average wait time of all the shipments in the simulation.
	 */
	public double averageWaitTime() {
		return myLog.averageWaitTime();
	}
	
	/**
	 * Average number of seconds items spent in actual processing.
	 * @return averageProcessTime double average process time of all shipments in the simulation.
	 */
	public double averageProcessTime() {
		return myLog.averageProcessTime();
	}
}

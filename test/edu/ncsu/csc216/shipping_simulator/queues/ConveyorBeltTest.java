/**
 * 
 */
package edu.ncsu.csc216.shipping_simulator.queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.shipping_simulator.pkg.BookShipmentFactory;
import edu.ncsu.csc216.shipping_simulator.simulation.Log;

/**
 * Tests each method of the ConveyorBelt class (minus the constructor).
 * @author Aurora Bravo
 */
public class ConveyorBeltTest {
	
	/** ShipmentQueue queue from the factory that contains shipments */
	private ShipmentQueue queue;
	
	/** ShipmentProcessStation[] station array that has all the process stations */
	private ShipmentProcessStation[] station;
	
	/** ConveyorBelt belt used to generate packages */
	private ConveyorBelt belt;
	
	/** Log log used to log the information of packagaes as they are being processed */
	private Log log;

	/**
	 * Sets up the ConveyorBelt Test by creating a shipmentProcessStation array, a shipmentQueue, and a Log
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		int numStations = 3;
		//create array of stations
		station = new ShipmentProcessStation[numStations];
		for (int i = 0; i < numStations; i++){
			station[i] = new ShipmentProcessStation(log);
		}
		int numberOfItems = 3;
		belt = new ConveyorBelt(numberOfItems, this.station);
		//create the queue and add itemToShip to queue
		queue = new ShipmentQueue();
		for (int i = 0; i < numberOfItems; i++) {
			queue.add(BookShipmentFactory.generateBookShipment());
		}
		
		belt = new ConveyorBelt(numberOfItems, this.station);
		
	}

	/**
	 * Tests that the Size() method returns the right size of the belt
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(3, belt.size());
	}

	/**
	 * Tests the hasNext() method by checking that there are items left in the belt
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#hasNext()}.
	 */
	@Test
	public void testHasNext() {
		assertEquals(true, belt.hasNext());
	}

	/**
	 * Tests the processNext() method by checking that the belt is size of three at the beginning
	 * and that it has a size of 2 after a package is processed.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#processNext()}.
	 */
	@Test
	public void testProcessNext() {
		//Test that size is 3 at the beginning
		assertEquals(3, belt.size());
		belt.processNext();
		assertEquals(2, belt.size());
	}

	/**
	 * Tests the departTime() method by checking that the departTime returns the right amount of seconds.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ConveyorBelt#departTimeNext()}.
	 */
	@Test
	public void testDepartTimeNext() {
		BookShipmentFactory.resetFactory();
		assertEquals(78, belt.departTimeNext());
	}

}

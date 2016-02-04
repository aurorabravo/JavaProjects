/**
 * 
 */
package edu.ncsu.csc216.shipping_simulator.queues;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.shipping_simulator.pkg.BookShipmentFactory;
import edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip;
import edu.ncsu.csc216.shipping_simulator.simulation.Log;

/**
 * Tests each method of ShipmentProcessStation class (minus the constructor).
 * @author Aurora Bravo
 *
 */
public class ShipmentProcessStationTest {
	
	/** Log log used to gather itemToShip processing data that is at the front of the line */
	private Log log;
	
	/** Shipment station process station used for this test */
	private ShipmentProcessStation station;
	
	/**
	 * Sets up the test by creating a Log, and a ShipmentProcessStation.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		log = new Log();
		station = new ShipmentProcessStation(log);
	}

	/**
	 * Tests the size of the station by checking that it is zero at the beginning. 
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(0, station.size());
	}

	/**
	 * Tests the processNext() method by checking that a null station was not processed.
	 * This test also checks that a shipment was processed after calling processNext().
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation#processNext()}.
	 */
	@Test
	public void testProcessNext() {
		try {
			station.processNext();
			fail(); //should never get here
		} catch (NoSuchElementException e) {
			//check the payment was returned and the balance was not added to the register
			assertEquals(0, station.size());
			
		}
		ItemToShip item = BookShipmentFactory.generateBookShipment();
		station.addItemToLine(item);
		//Test that item was added
		assertEquals(1, station.size());
		//Now remove item from line using processNext()
		station.processNext();
		//Test that item was removed
		assertEquals(0, station.size());
	}

	/**
	 * Tests hasNext() by checking that the station is not empty.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation#hasNext()}.
	 */
	@Test
	public void testHasNext() {
		//Test that station is not empty
		assertEquals(false, station.hasNext());
	}

	/**
	 * Tests departTimeNext() by checking that the departTime is 10 (processTime) + 3 (arrivalTime)
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation#departTimeNext()}.
	 */
	@Test
	public void testDepartTimeNext() {
		BookShipmentFactory.resetFactory();
		assertEquals(2147483647, station.departTimeNext());
		ItemToShip item = BookShipmentFactory.generateBookShipment();
		station.addItemToLine(item);
		assertEquals(13, station.departTimeNext());
	}

	/**
	 * Tests addItemToLine() by checking that a package was added after calling addItemToLine().
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation#addItemToLine(edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip)}.
	 */
	@Test
	public void testAddItemToLine() {
		ItemToShip item = BookShipmentFactory.generateBookShipment();
		station.addItemToLine(item);
		//Test that item was added
		assertEquals(1, station.size());
	}

}

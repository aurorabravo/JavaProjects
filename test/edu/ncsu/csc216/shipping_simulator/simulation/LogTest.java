/**
 * 
 */
package edu.ncsu.csc216.shipping_simulator.simulation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.shipping_simulator.pkg.BookShipmentFactory;
import edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip;

/**
 * Tests each method in Log class minus the constructor
 * @author Aurora Bravo
 *
 */
public class LogTest {

	/** Log log object used to log data of packages as they are processed */
	Log log;
	
	/**
	 * Sets up the test by creating a log object.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		log = new Log();
	}

	/**
	 * Tests getNumCompleted() by asserting that there are zero packages completed. 
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Log#getNumCompleted()}.
	 */
	@Test
	public void testGetNumCompleted() {
		assertEquals(0, log.getNumCompleted());
	}

	/**
	 * Tests logItem(ItemToShip) by checking that numCompleted was increased by one.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Log#logItem(edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip)}.
	 */
	@Test
	public void testLogItem() {
		ItemToShip item = BookShipmentFactory.generateBookShipment();
		log.logItem(item);
		assertEquals(1, log.getNumCompleted());
		
	}

	/**
	 * Tests averageWaitTime() by checking that the method returns the right average wait time of two ItemToShip shipments.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Log#averageWaitTime()}.
	 */
	@Test
	public void testAverageWaitTime() {
		assertEquals(0, log.averageWaitTime(), 1e-15);
		ItemToShip item = BookShipmentFactory.generateBookShipment();
		log.logItem(item);
		int waitTime = item.getWaitTime();
		item = BookShipmentFactory.generateBookShipment();
		log.logItem(item);
		waitTime += item.getWaitTime();
		assertEquals(2, log.getNumCompleted());
		
		assertEquals(waitTime / 2.0, log.averageWaitTime(), 1e-15);
	}

	/**
	 * Tests averageProcessTime() by checking that the method returns the right average process time of two ItemToShip shipments.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Log#averageProcessTime()}.
	 */
	@Test
	public void testAverageProcessTime() {
		assertEquals(0, log.averageProcessTime(), 1e-15);
		ItemToShip item = BookShipmentFactory.generateBookShipment();
		log.logItem(item);
		int processTime = item.getProcessTime();
		item = BookShipmentFactory.generateBookShipment();
		log.logItem(item);
		processTime += item.getProcessTime();
		assertEquals(2, log.getNumCompleted());
		
		assertEquals((double)processTime / 2.0, log.averageProcessTime(), 1e-15);
	}

}

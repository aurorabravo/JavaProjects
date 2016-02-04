/**
 * 
 */
package edu.ncsu.csc216.shipping_simulator.pkg;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;
import edu.ncsu.csc216.shipping_simulator.simulation.Log;

/**
 * Tests InternaionlaBookShipment methods (minus the constructor).
 * @author Aurora Bravo
 *
 */
public class InternationalBookShipmentTest {
	
	/** InternationalBookShipment internationalPackage used for testing the methods */
	private InternationalBookShipment internationalPackage;
	/** InternationalBookShipment internationalPackage4 used for testing the methods */
	private InternationalBookShipment internationalPackage4;
	/** Log log used to log packages as they are processed */
	private Log log;
	/** ShipmentProcessStation[] station array of stations used for tests */
	private ShipmentProcessStation[] station;

	/**
	 * Sets up the InternaionlaBookShipmentTest by creating  InternationalBookShipment packages, a log, and an array of stations 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		int arrivalTime = 0;
		int processTime = 15;
		int numStations = 3;
		internationalPackage = new InternationalBookShipment(arrivalTime, processTime);
		int arrivalTimeOfPackage4 = 1;
		internationalPackage4 = new InternationalBookShipment(arrivalTimeOfPackage4, processTime);
		log = new Log();
		station = new ShipmentProcessStation[numStations];
		for (int i = 0; i < numStations; i++){
			station[i] = new ShipmentProcessStation(log);
		}
	}

	/**
	 * Tests the getInLine method of InternationalBookShipment by checking that the station incrases by one when one package is added. 
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.InternationalBookShipment#getInLine(edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation[])}.
	 */
	@Test
	public void testGetInLine() {
		int numStations = 3;
		//test that all three shipment stations are empty at the beginning
		for (int i = 0; i < numStations; i++) {
			assertEquals(0, station[i].size());
		}
		//adds international package to shipment process station line
		internationalPackage.getInLine(station);
		//test that international package was added to index 2
		assertEquals(1, station[2].size());
		
		//add international package to shipment process station line
		internationalPackage.getInLine(station);
		//Test that this rush package was added to shipment station index 2
		assertEquals(2, station[2].size());
	}

	/**
	 * Tests getColor() by checking that internationalPaclage color is indeed red.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.InternationalBookShipment#getColor()}.
	 */
	@Test
	public void testGetColor() {
		assertEquals(Color.RED, internationalPackage.getColor());
	}

	/**
	 * Tests getArrivalTime() by checking that arrivalTime is zero at the beginning.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getArrivalTime()}.
	 */
	@Test
	public void testGetArrivalTime() {
		//Test that internationalPackage has arrival time of 0
		assertEquals(0, internationalPackage.getArrivalTime());
	}

	/**
	 * Tests getWaitTime() by checking that waitTime is zero at the beginning.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getWaitTime()}.
	 */
	@Test
	public void testGetWaitTime() {
		assertEquals(0, internationalPackage.getArrivalTime());
	}

	/**
	 * Tests setWaitTime by checking that the package's wait time is correctly updated.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setWaitTime(int)}.
	 */
	@Test
	public void testSetWaitTime() {
		internationalPackage.getInLine(station);
		internationalPackage4.getInLine(station);
		//Test that international packages were added to station 3
		assertEquals(2, station[2].size());
		//check for wait time (previous package arrival time = 0 + process time = 15) - arrival time = 1
		assertEquals(14, internationalPackage4.getWaitTime());
	}

	/**
	 * Tests getProcessTime() by checking that the processTime of the first package is indeed 15.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getProcessTime()}.
	 */
	@Test
	public void testGetProcessTime() {
		assertEquals(15, internationalPackage.getProcessTime());
	}

	/**
	 * Tests getStationIndex() by checking that the international package went to the station with index of 2.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getStationIndex()}.
	 */
	@Test
	public void testGetStationIndex() {
		internationalPackage.getInLine(station);
		assertEquals(2, internationalPackage.getStationIndex());
	}

	/**
	 * Tests isWaitingInLineAtStation() by checking that the first package is not waiting in line
	 * and that isWaitingInLineAtStation() returns false.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#isWaitingInLineAtStation()}.
	 */
	@Test
	public void testIsWaitingInLineAtStation() {
		assertEquals(false, internationalPackage.isWaitingInLineAtStation());
	}

	/**
	 * Tests removeFromWaitingLine() by checking that the item is removed from the waiting line.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#removeFromWaitingLine()}.
	 */
	@Test
	public void testRemoveFromWaitingLine() {
		internationalPackage.getInLine(station);
		assertEquals(1, station[2].size());
		//now remove the item from waiting line
		internationalPackage.removeFromWaitingLine();
		assertEquals(false, internationalPackage.isWaitingInLineAtStation());
	}

	/**
	 * Tests setStationIndex() by checking that the given index station is the same index returned from
	 * getStationIndex().
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setStationIndex(int)}.
	 */
	@Test
	public void testSetStationIndex() {
		internationalPackage.setStationIndex(2);
		assertEquals(2, internationalPackage.getStationIndex());
	}

}

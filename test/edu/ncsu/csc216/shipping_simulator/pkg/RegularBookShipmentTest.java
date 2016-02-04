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
 * Tests RegularBookShipment methods (minus the constructor).
 * @author Aurora Bravo
 */
public class RegularBookShipmentTest {
	
	/** RegularBookShipment regularPackage used to test regularBookShipment methods */
	private RegularBookShipment regularPackage;
	/** RegularBookShipment regularPackage used to test regularBookShipment methods */
	private RegularBookShipment regularPackage2;
	/** RegularBookShipment regularPackage used to test regularBookShipment methods */
	private RegularBookShipment regularPackage3;
	/** RegularBookShipment regularPackage used to test regularBookShipment methods */
	private RegularBookShipment regularPackage4;
	/** Log log used to log packages are they are processed */
	private Log log;
	/** ShipmentProcessStation[] station array of stations used for tests */
	private ShipmentProcessStation[] station;


	/**
	 * Sets up the RegularBookShipment test by creating RegularBookShipment packages, a log, and an array of shipment process stations.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		int arrivalTime = 0;
		int processTime = 15;
		int numStations = 3;
		regularPackage = new RegularBookShipment(arrivalTime, processTime);
		regularPackage2 = new RegularBookShipment(arrivalTime, processTime);
		regularPackage3 = new RegularBookShipment(arrivalTime, processTime);
		int arrivalTimeOfPackage4 = 1;
		regularPackage4 = new RegularBookShipment(arrivalTimeOfPackage4, processTime);
		log = new Log();
		station = new ShipmentProcessStation[numStations];
		for (int i = 0; i < numStations; i++){
			station[i] = new ShipmentProcessStation(log);
		}
		
	}

	/**
	 * Tests the getInLine method of RegularBookShipment by checking that the station's size increases by one when one package is added.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RegularBookShipment#getInLine(edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation[])}.
	 */
	@Test
	public void testGetInLine() {
		int numStations = 3;
		//test that all five shipment stations are empty at the beginning
		for (int i = 0; i < numStations; i++) {
			assertEquals(0, station[i].size());
		}
		//adds regular package to shipment process station line
		regularPackage.getInLine(station);
		//test that rush package was added to index 1
		assertEquals(1, station[1].size());
		
		//add regularpackage2 to shipment process station line
		regularPackage2.getInLine(station);
		//test that this regular package was added to shipment station index 2 (empty station)
		assertEquals(1, station[2].size());
		
		
	}

	/**
	 * Tests getColor() by checking that regularPackage color is indeed blue. 
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RegularBookShipment#getColor()}.
	 */
	@Test
	public void testGetColor() {
		//test that regular package is of color blue
		assertEquals(Color.BLUE, regularPackage.getColor());
	}

	/**
	 * Tests getArrivalTime() by checking that the arrival time is zero at the beginning.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getArrivalTime()}.
	 */
	@Test
	public void testGetArrivalTime() {
		//Test that regularPackage has arrival time of 0
		assertEquals(0, regularPackage.getArrivalTime());
	}

	/**
	 * Tests the getWaitTime by checking that the wait time is zero at the beginning.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getWaitTime()}.
	 */
	@Test
	public void testGetWaitTime() {
		//Test that regularPackage has a wait time of 0
		assertEquals(0, regularPackage.getWaitTime());
	}

	/**
	 * Tests setWaitTime by checking that the package's wait time is correctly updated.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setWaitTime(int)}.
	 */
	@Test
	public void testSetWaitTime() {
		//Add all packages to stations
		regularPackage.getInLine(station); //arrival time: 0, process time: 15
		regularPackage2.getInLine(station); //arrival time: 0, process time: 15
		regularPackage3.getInLine(station); //arrival time: 0, process time: 15
		regularPackage4.getInLine(station); //arrival time: 1, process time:15
		//Test that station 1 is empty (rush)
		assertEquals(0, station[0].size());
		//Test that station 2 through 3 are not empty (both should have 2 packages)
		assertEquals(2, station[1].size());
		assertEquals(2, station[2].size());
		//Test that regularPackage4 has a wait time of 14
		//(previous package arrival time = 0) + (previous package process time = 15) - (current package arrival time = 1)
		assertEquals(14, regularPackage4.getWaitTime());
	}

	/**
	 * Tests getProcessTime() by checking that the process time is indeed 15.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getProcessTime()}.
	 */
	@Test
	public void testGetProcessTime() {
		assertEquals(15, regularPackage.getProcessTime());
	}

	/**
	 * Tests getStationIndex() by checking that the station of a regularShipment is indeed 1 (not zero).
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getStationIndex()}.
	 */
	@Test
	public void testGetStationIndex() {
		regularPackage.getInLine(station);
		assertEquals(1, regularPackage.getStationIndex());
	}

	/**
	 * Tests isWaitingInLineAtTheStation() by checking that the package is not waiting in line.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#isWaitingInLineAtStation()}.
	 */
	@Test
	public void testIsWaitingInLineAtStation() {
		assertEquals(false, regularPackage.isWaitingInLineAtStation());
	}

	/**
	 * Tests removeFromWaitingLine()  by checking that the item is removed from the waiting line
	 * and that isWaitingInLineAtStation() returns false. 
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#removeFromWaitingLine()}.
	 */
	@Test
	public void testRemoveFromWaitingLine() {
		regularPackage.getInLine(station);
		//check that station 2 is of size 1
		assertEquals(1, station[1].size());
		//now remove item from waiting line
		regularPackage.removeFromWaitingLine();
		assertEquals(false, regularPackage.isWaitingInLineAtStation());
	}

	/**
	 * Tests setStationIndex() by checking that the given station is the same station returned by getStationIndex().
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setStationIndex(int)}.
	 */
	@Test
	public void testSetStationIndex() {
		regularPackage.setStationIndex(1);
		assertEquals(1, regularPackage.getStationIndex());
	}

}

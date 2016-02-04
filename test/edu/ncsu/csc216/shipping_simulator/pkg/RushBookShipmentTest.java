
package edu.ncsu.csc216.shipping_simulator.pkg;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation;
import edu.ncsu.csc216.shipping_simulator.simulation.Log;

/**
 * Tests RushBookShipment methods (minus the constructor)
 * @author Aurora Bravo
 */
public class RushBookShipmentTest {

	/** RushBookShipment rushPackage used to test rushBookShipment methods */
	private RushBookShipment rushPackage;
	/** RushBookShipment rushPackage2 used to test rushBookShipment methods */
	private RushBookShipment rushPackage2;
	/** RushbookShipment rushPackage 3 used to test rushBookShipment methods */
	private RushBookShipment rushPackage3;
	/** RushBookShipment rushPackage3 used to test rushBookShipment methods */
	private RushBookShipment rushPackage4;
	/** RushBookShipment rushPackage4 used to test rushBookShipment methods */
	private RushBookShipment rushPackage5;
	/** RushBookShipment rushPackage 5 used to test rushBookShipment methods */
	private RushBookShipment rushPackage6;
	/** Log log used to log packages as they are processed */
	private Log log;
	/** ShipmentProcessStation[] station array of stations used for tests */
	private ShipmentProcessStation[] station;
	
	/**
	 * Sets up the RushBookShipmentTest by creating RushBookShipment packages, a log, and an array of shipment process stations
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		int arrivalTime = 0;
		int processTime = 15;
		int numStations = 5;
		rushPackage = new RushBookShipment(arrivalTime, processTime);
		rushPackage2 = new RushBookShipment(arrivalTime, processTime);
		rushPackage3 = new RushBookShipment(arrivalTime, processTime);
		rushPackage4 = new RushBookShipment(arrivalTime, processTime);
		rushPackage5 = new RushBookShipment(arrivalTime, processTime);
		int arrivalTimeOfPackage6 = 1;
		rushPackage6 = new RushBookShipment(arrivalTimeOfPackage6, processTime);
		log = new Log();
		station = new ShipmentProcessStation[numStations];
		for (int i = 0; i < numStations; i++){
			station[i] = new ShipmentProcessStation(log);
		}
		
	}

	/**
	 * Tests the getInLine method of RushBookShipment by checking that the station's size increases by one when one package is added.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RushBookShipment#getInLine(edu.ncsu.csc216.shipping_simulator.queues.ShipmentProcessStation[])}.
	 */
	@Test
	public void testGetInLine() {
		int numStations = 5;
		//test that all five shipment stations are empty at the beginning
		for (int i = 0; i < numStations; i++) {
			assertEquals(0, station[i].size());
		}
		//adds rush package to shipment process station line
		rushPackage.getInLine(station);
		//test that rush package was added to index 0
		assertEquals(1, station[0].size());
		
		//add rush package2 to shipment process station line
		rushPackage2.getInLine(station);
		//Test that this rush package was added to shipment station index 1(empty station);
		assertEquals(1, station[1].size());
		
	}

	/**
	 * Tests getColor() by checking that rushPackage color is indeed black.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.RushBookShipment#getColor()}.
	 */
	@Test
	public void testGetColor() {
		//test that rush package is of color black
		assertEquals(Color.BLACK, rushPackage.getColor());
	}

	/**
	 * Tests getArrivalTime() by checking that arrivalTime is zero at the beginning.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getArrivalTime()}.
	 */
	@Test
	public void testGetArrivalTime() {
		//Test that rushPackage has arrival time of 0
		assertEquals(0, rushPackage.getArrivalTime());
	}

	/**
	 * Tests getWaitTime() by checking that waitTime is zero at the beginning.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getWaitTime()}.
	 */
	@Test
	public void testGetWaitTime() {
		//Test that rushPackage has wait time of 0
		assertEquals(0, rushPackage.getWaitTime());
	}

	/**
	 * Tests setWaitTime by checking that the package's wait time is correctly updated.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setWaitTime(int)}.
	 */
	@Test
	public void testSetWaitTime() {
		//add all packages to stations
		rushPackage.getInLine(station);
		rushPackage2.getInLine(station);
		rushPackage3.getInLine(station);
		rushPackage4.getInLine(station);
		rushPackage5.getInLine(station);
		rushPackage6.getInLine(station); //arrival time of 1, process time: 15
		//Test the other stations are not empty
		assertEquals(1, station[1].size()); //station 2
		assertEquals(1, station[2].size()); //station 3
		assertEquals(1, station[3].size()); //station 4
		assertEquals(1, station[4].size()); //station 5
		//Test that RushPackage6 was added to shipment station with index 0
		assertEquals(2, station[0].size());
		//Test that RushPackage6 has a wait time of 15(package1 process time) + 0 (package1 arrival time) - arrivalTime of package (1) = 14
		assertEquals(14, rushPackage6.getWaitTime()); 
	}

	/**
	 * Tests getProcessTime() by checking that the processTime of the first package is indeed 15.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getProcessTime()}.
	 */
	@Test
	public void testGetProcessTime() {
		assertEquals(15, rushPackage.getProcessTime());
	}

	/**
	 * Tests getStationIndex() by checking that the rush package went to the station with index of 0.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#getStationIndex()}.
	 */
	@Test
	public void testGetStationIndex() {
		rushPackage.getInLine(station);
		assertEquals(0, rushPackage.getStationIndex());
	}

	/**
	 * Tests isWaitingInLineAtStation() by checking that the first package is not waiting in line.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#isWaitingInLineAtStation()}.
	 */
	@Test
	public void testIsWaitingInLineAtStation() {
		assertEquals(false, rushPackage.isWaitingInLineAtStation());
	}

	/**
	 * Tests removeFromWaitingLine() by checking that the item is removed from the waiting line
	 * and that isWaitingInLineAtStation() returns false.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#removeFromWaitingLine()}.
	 */
	@Test
	public void testRemoveFromWaitingLine() {
		rushPackage.getInLine(station);
		//check that station 0 is of size 1
		assertEquals(1, station[0].size());
		//now remove item from waiting line
		rushPackage.removeFromWaitingLine();
		assertEquals(false, rushPackage.isWaitingInLineAtStation());
	}

	/**
	 * Tests setStationIndex() by checking that the given index station is the same index returned from
	 * getStationIndex().
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.pkg.ItemToShip#setStationIndex(int)}.
	 */
	@Test
	public void testSetStationIndex() {
		rushPackage.setStationIndex(0);
		assertEquals(0, rushPackage.getStationIndex());
	}

}

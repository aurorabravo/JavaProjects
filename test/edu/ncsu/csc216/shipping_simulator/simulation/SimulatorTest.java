/**
 * 
 */
package edu.ncsu.csc216.shipping_simulator.simulation;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Simulator class and each of its methods (minus the constructor) 
 * @author Aurora Bravo
 */
public class SimulatorTest {
	
	/** Simulator simulator used to test simulator methods */
	private Simulator simulator;
	
	/**
	 * Sets up the test by creating a simulator object, a station array, and a log
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		int numShipments = 3;
		int numStations = 3;
		simulator = new Simulator(numStations, numShipments);
	}

	/**
	 * Tests Step() by checking the amount of steps before and after a step
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#step()}.
	 */
	@Test
	public void testStep() {
		assertEquals(0, simulator.getStepsTaken());
		simulator.step();
		assertEquals(1, simulator.getStepsTaken());
	}

	/**
	 * Tests getStepsTaken() by checking that the number of steps is 0
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#getStepsTaken()}.
	 */
	@Test
	public void testGetStepsTaken() {
		assertEquals(0, simulator.getStepsTaken());
	}

	/**
	 * Tests the totalNumberOfSteps() by checking that the steps is twice the number of packages
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#totalNumberOfSteps()}.
	 */
	@Test
	public void testTotalNumberOfSteps() {
		simulator.step();
		simulator.step();
		simulator.step();
		assertEquals(6, simulator.totalNumberOfSteps());
	}

	/**
	 * Tests moreSteps() that there are more steps after calling steps step() three times
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#moreSteps()}.
	 */
	@Test
	public void testMoreSteps() {
		//Test that steps taken is 0 from beginning
		assertEquals(0, simulator.getStepsTaken());
		//now make some steps
		simulator.step();
		simulator.step();
		simulator.step();
		//steps taken:3 total number of steps: 6, so true
		assertEquals(true, simulator.moreSteps());
	}

	/**
	 * Tests getCurrentIndex() by checking that -1 is returned if null shipment.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#getCurrentIndex()}.
	 */
	@Test
	public void testGetCurrentIndex() {
		assertEquals(-1, simulator.getCurrentIndex());
		/*int arrivalTime = 0;
		int processTime = 10;
		simulator.step();
		assertEquals(2, simulator.getCurrentIndex()); */
	}

	/**
	 * Tests getCurrentShipment() by checking that null is returned if 
	 * shipment is null, and that the correct color is returned for the type of package
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#getCurrentShipmentColor()}.
	 */
	@Test
	public void testGetCurrentShipmentColor() {
		assertEquals(null, simulator.getCurrentShipmentColor());
		simulator.step();
		assertEquals(Color.BLUE, simulator.getCurrentShipmentColor());
	
	}

	/**
	 * Tests itemLeftSimulation() by checking that it returns false if item hasn't left the simulation
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#itemLeftSimulation()}.
	 */
	@Test
	public void testItemLeftSimulation() {
		assertEquals(false, simulator.itemLeftSimulation());
	}

	/**
	 * Test averageWaitTime() by checking that zero is returned
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#averageWaitTime()}.
	 */
	@Test
	public void testAverageWaitTime() {
		assertEquals(0, simulator.averageWaitTime(), 1e-15);
	}

	/**
	 * Test averageProcessTime by checking that zero is returned.
	 * Test method for {@link edu.ncsu.csc216.shipping_simulator.simulation.Simulator#averageProcessTime()}.
	 */
	@Test
	public void testAverageProcessTime() {
		assertEquals(0, simulator.averageProcessTime(), 1e-15);
	}

}

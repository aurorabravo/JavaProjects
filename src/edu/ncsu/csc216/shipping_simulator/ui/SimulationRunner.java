package edu.ncsu.csc216.shipping_simulator.ui;

import java.util.Scanner;

import edu.ncsu.csc216.shipping_simulator.simulation.Simulator;

/**
 * Simple class to run shipping package simulations. 
 *   Input is from standard input. Output is to standard output.
 *   No error checking is performed. 
 * @author Jo Perry
 */
public class SimulationRunner {
	
	/**
	 * Starts the command line simple simulation.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		System.out.print("Number of book packages: ");
		int numberOfPackages = sc.nextInt();
		System.out.print("Number of process stations: ");
		int numberOfStations = sc.nextInt();
		Simulator s = new Simulator(numberOfStations, numberOfPackages);
		while (s.moreSteps())
			s.step();
		System.out.printf("Average Wait Time: %1$.2f seconds%n", s.averageWaitTime());
		System.out.printf("Average Process Time: %1$.2f seconds%n", s.averageProcessTime());
		sc.close();
	}

}
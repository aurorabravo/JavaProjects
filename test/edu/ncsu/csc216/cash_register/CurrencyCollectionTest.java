/**
 * 
 */
package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * CurrencyCollectionTest tests every method in CurrencyCollection (minus constructor)
 * @author Aurora Bravo
 */
public class CurrencyCollectionTest {
	
	/** penny Currency object with the value of a penny */
	private Currency penny;
	/** collectionTest CurrencyCollection of currencies*/
	private CurrencyCollection collectionTest;

	/**
	 * Sets up the CurrencyCollectionTest by creating an array of currencies,
	 * a penny object, and a CurrencyCollection called collectionTest.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		penny = new Currency(CurrencyCollection.PENNY_VALUE, CurrencyCollection.PENNY_NAME, 10);
		
		collectionTest = new CurrencyCollection(10);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyAtIdx(int)}.
	 * Tests getCurrencyAtIdx method
	 * The tests determine if the value of a currency at given index is
	 * The same as what it is assigned to.
	 * The tests also determine if an IndexOutOfBoundsException is thrown.
	 */
	@Test
	public void testGetCurrencyAtIdx() {
		//Test that penny value is the same value at currency's 0 index
		assertEquals(penny.getValue(), collectionTest.getCurrencyAtIdx(0).getValue());
		//Test an index outside of bounds
		try {
			collectionTest.getCurrencyAtIdx(8);
			fail(); //should never get here
		} catch (IndexOutOfBoundsException e) {
			//test the upper-bound of the array
			assertEquals(2000, collectionTest.getCurrencyAtIdx(7).getValue());
			
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#modifyDenomination(int, int)}.
	 * Tests modifyDenomination method
	 * The tests determine if modifyDenomination modifies the given denomination
	 * The tests also determine if an IllegalArgumentException is thrown if a
	 * denomination is negative.
	 */
	@Test
	public void testModifyDenomination() {
		//Balance before it is modified
		int currentBalance = collectionTest.getBalance();
		
		//adds one ten dollar bill to the currency slot
		collectionTest.modifyDenomination(CurrencyCollection.TEN_VALUE, 1);
		//tests if currency slot at index 6 is 11.
		assertEquals(11, collectionTest.getCurrencyAtIdx(6).getCount());
		
		//tests if the new balance (1000) was added to the old balance
		assertEquals(currentBalance + 1000, collectionTest.getBalance());
		
		//test if count is negative
		try {
			collectionTest.modifyDenomination(CurrencyCollection.TEN_VALUE, -1);
			fail(); //should never get here
		} catch (IllegalArgumentException e) {
			//test that balance didn't change (that the -1 ten dollar bill was not added)
			assertEquals(currentBalance + 1000, collectionTest.getBalance());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#depositCurrencyCollection(edu.ncsu.csc216.cash_register.CurrencyCollection)}.
	 * Tests depositCurrencyCollection method
	 * Tests determine if the appropriate currencies were added to the given currency collection
	 * Tests also determines if the balance increased by the appropriate amount
	 */
	@Test
	public void testDepositCurrencyCollection() {
		//balance before any changes
		int currentBalance = collectionTest.getBalance();
		//tests that count at index 6 is really 10 from the beginning
		assertEquals(10, collectionTest.getCurrencyAtIdx(6).getCount());
		//currency collection with an initial count of 1 for each denomination
		CurrencyCollection deposit = new CurrencyCollection(1);
		//deposits the deposit currency collection into the collectionTest
		collectionTest.depositCurrencyCollection(deposit);
		//count for 10 dollar bills should be 11
		assertEquals(11, collectionTest.getCurrencyAtIdx(6).getCount());
		//new balance should be old balance (36410) + balance of deposit collection (3641)
		assertEquals(currentBalance + 3641, collectionTest.getBalance());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#refundByAmount(int)}.
	 * Tests the refundByAmount method
	 * Tests determine if refundByAmount handles a CurrencyCollection appropiately
	 * Tests also determine if an IllegalArgumentException is thrown if 
	 * the refund amount is not equal to 0.
	 */
	@Test
	public void testRefundByAmount() {
		//balance before any changes
		int currentBalance = collectionTest.getBalance();
		//tests that currency count at index 6 is really 10 from the beginning
		assertEquals(collectionTest.getCurrencyAtIdx(6).getCount(), 10);
		//refund amount of 10 dollars
		int refund = 1000;
		//takes out the refund currency collection from collectionTest
		collectionTest.refundByAmount(refund);
		//count for 10 dollar bills should be 11
		assertEquals(collectionTest.getCurrencyAtIdx(6).getCount(), 9);
		//new balance should be old balance (36410) - balance of refund (1000) = 35410
		assertEquals(collectionTest.getBalance(), currentBalance - 1000);
		
		currentBalance = collectionTest.getBalance();
		//refund of 0 dollars
		refund = 40000;
		//try to run refundByAmount with a refund that is not 0;
		try {
			collectionTest.refundByAmount(refund);
			fail(); //should never get here
		} catch (IllegalArgumentException e) {
			//test that the 4000 were not added to the current balance
			assertEquals(35410, currentBalance);
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyCollection()}.
	 * Tests getCurrencyCollection method
	 * Tests determine if the CurrencyCollection array is not null
	 * and if it has a length of 8.
	 */
	@Test
	public void testGetCurrencyCollection() {
		//check that it is not an empty array
		assertFalse(collectionTest.getCurrencyCollection() == null);
		//check for length
		assertEquals(collectionTest.getCurrencyCollection().length, 8);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getBalance()}.
	 * Tests getBalance method
	 * Tests determine if the balance is what it is assigned to.
	 */
	@Test
	public void testGetBalance() {
		assertEquals(36410, collectionTest.getBalance());
	}

}

/**
 * 
 */
package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * CashRegister has tests for each method of CashRegister class
 * (minus the constructor)
 * @author Aurora Bravo
 */
public class CashRegisterTest {

	/** CashRegister object */
	CashRegister register;
	
	/**
	 * Sets up the CashRegister test by making a CashRegister
	 * object called register.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		register = new CashRegister();
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#getCurrentBalance()}.
	 * Tests getCurrentBalance method
	 * Test determines if the current balance of the register is what it is assigned to.
	 */
	@Test
	public void testGetCurrentBalance() {
		assertEquals(36410, register.getCurrentBalance());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#processPurchase(int, edu.ncsu.csc216.cash_register.CurrencyCollection)}.
	 * Tests processPurchase method
	 * Tests determine if the balance of the register changes by the appropriate amount.
	 * Test also determines if an IllegalArgumentException is thrown if 
	 * payment is less than the purhcase amount.
	 */
	@Test
	public void testProcessPurchase() {
		//purchase amount is 10 dollars
		int purchaseAmount = 1000;
		//currency collection payment
		CurrencyCollection payment = new CurrencyCollection(0);
		//Add 1 ten dollar bill to the payment currency collection
		payment.modifyDenomination(CurrencyCollection.TEN_VALUE, 1);
		//process purchase with payment given
		register.processPurchase(purchaseAmount, payment);
		//test if payment amount (1000) was added to the balance (36410)
		assertEquals(37410, register.getCurrentBalance());
		
		//test illegal argument exception by not paying with enough money
		//Creates new currency collection
		CurrencyCollection payment2 = new CurrencyCollection(0);
		//Add the payment of one five dollar bill to the collection
		payment2.modifyDenomination(CurrencyCollection.FIVE_VALUE, 1);
		//try to process purchase with payment amount
		try {
			register.processPurchase(purchaseAmount, payment2);
			fail(); //should never get here
		} catch (IllegalArgumentException e) {
			//check the payment was returned and the balance was not added to the register
			assertEquals(37410, register.getCurrentBalance());
			
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.CashRegister#processRefund(int)}.
	 * Tests processRefund method
	 * Tests determine if the appropriate refund amount is taken 
	 * out of the register balance.
	 */
	@Test
	public void testProcessRefund() {
		//purchase amount is 5 dollars
		int refundAmount = 500;
		//Subtracts refund amount from the register
		register.processRefund(refundAmount);
		//test if amount (500) was subtracted from balance (36410)
		assertEquals(35910, register.getCurrentBalance());
	}

}

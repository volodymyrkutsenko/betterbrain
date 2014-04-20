package org.straightweb.betterbrain.arithmetics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EquationSheetGenerateRequestTest {
	
	@Test
	public void testRequestParametersSetting() {
		EquationSheetGenerateRequest request = new EquationSheetGenerateRequest();
		org.junit.Assert.assertEquals(100, request.getEquationsOnSheet());
		org.junit.Assert.assertEquals(0, request.getDuplicatesMaxQuantity());
		org.junit.Assert.assertEquals(4, request.getZeroArgumentedEquationsMaxQuantity());
		org.junit.Assert.assertEquals(4, request.getZeroResultEquationsMaxQuantity());
		
		request = new EquationSheetGenerateRequest(500);
		org.junit.Assert.assertEquals(500, request.getEquationsOnSheet());
		org.junit.Assert.assertEquals(0, request.getDuplicatesMaxQuantity());
		org.junit.Assert.assertEquals(4, request.getZeroArgumentedEquationsMaxQuantity());
		org.junit.Assert.assertEquals(4, request.getZeroResultEquationsMaxQuantity());
		
		request = new EquationSheetGenerateRequest(200, 2);
		org.junit.Assert.assertEquals(200, request.getEquationsOnSheet());
		org.junit.Assert.assertEquals(2, request.getDuplicatesMaxQuantity());
		org.junit.Assert.assertEquals(4, request.getZeroArgumentedEquationsMaxQuantity());
		org.junit.Assert.assertEquals(4, request.getZeroResultEquationsMaxQuantity());
		
		request = new EquationSheetGenerateRequest(300, 3, 3);
		org.junit.Assert.assertEquals(300, request.getEquationsOnSheet());
		org.junit.Assert.assertEquals(3, request.getDuplicatesMaxQuantity());
		org.junit.Assert.assertEquals(3, request.getZeroArgumentedEquationsMaxQuantity());
		org.junit.Assert.assertEquals(4, request.getZeroResultEquationsMaxQuantity());
		
		request = new EquationSheetGenerateRequest(400, 4, 3, 3);
		org.junit.Assert.assertEquals(400, request.getEquationsOnSheet());
		org.junit.Assert.assertEquals(4, request.getDuplicatesMaxQuantity());
		org.junit.Assert.assertEquals(3, request.getZeroArgumentedEquationsMaxQuantity());
		org.junit.Assert.assertEquals(3, request.getZeroResultEquationsMaxQuantity());
	}

	@Test
	public void testZeroEquationsQuantity() {
		try {
			new EquationSheetGenerateRequest(0);
			org.junit.Assert.fail("EquationSheetGenerateRequest should have thrown an exception b/c of 0 as the equationsOnSheet parameter!");
		} catch (Exception e) {
			// test passes
		}
	}
	
	@Test
	public void testNegativeEquationsQuantity() {
		try {
			new EquationSheetGenerateRequest(-1);
			org.junit.Assert.fail("EquationSheetGenerateRequest should have thrown an exception b/c of of the negative equationsOnSheet parameter!");
		} catch (Exception e) {
			// test passes
		}
	}
	
	@Test
	public void testNegativeDuplicatesMaxQuantity() {
		try {
			new EquationSheetGenerateRequest(100, -1);
			org.junit.Assert.fail("EquationSheetGenerateRequest should have thrown an exception b/c of the negative duplicatesMaxQuantity parameter!");
		} catch (Exception e) {
			// test passes
		}
	}
	
	@Test
	public void testNegativeZeroArgumentedMaxQuantity() {
		try {
			new EquationSheetGenerateRequest(100, 1, -1);
			org.junit.Assert.fail("EquationSheetGenerateRequest should have thrown an exception b/c of the negative zeroArgumentedMaxQuantity parameter!");
		} catch (Exception e) {
			// test passes
		}
	}
	
	@Test
	public void testNegativeZeroResultMaxQuantity() {
		try {
			new EquationSheetGenerateRequest(100, 1, 4, -1);
			org.junit.Assert.fail("EquationSheetGenerateRequest should have thrown an exception b/c of the negative zeroResultMaxQuantity parameter!");
		} catch (Exception e) {
			// test passes
		}
	}
}

package io.letsrunit.junit.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StringHelperTest {

	private StringHelper helper;

	@Before
	public void setup() {
		helper = new StringHelper();
	}

	@Test
	public void givenAinFirst2Positions_WhenTruncateAInFirst2Positions_ShouldTruncateA() {
		assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
	}

	@Test
	public void givenAinFirstPosition_WhenTruncateAInFirst2Positions_ShouldTruncateA() {
		assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
	}

	@Test
	public void givenFirstAndLast2CharsSame_WhenAreFirstAndLastTwoCharsSame_ShouldReturnTrue() {
		assertTrue(helper.areFirstAndLastTwoCharactersTheSame("BABA"));
	}

	@Test
	public void givenFirstAndLast2CharsDiff_WhenAreFirstAndLastTwoCharsSame_ShouldReturnFalse() {
		assertFalse(helper.areFirstAndLastTwoCharactersTheSame("ABCD"));
	}
}

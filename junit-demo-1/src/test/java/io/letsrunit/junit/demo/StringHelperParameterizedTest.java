package io.letsrunit.junit.demo;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StringHelperParameterizedTest {

	private StringHelper helper = new StringHelper();

	private String input;
	private String output;

	public StringHelperParameterizedTest(String input, String output) {
		this.input = input;
		this.output = output;
	}

	@Parameters
	public static Collection<String[]> testConditions() {
		String expectedOutputs[][] = { { "AACD", "CD" }, { "ACD", "CD" } };
		return Arrays.asList(expectedOutputs);
	}

	@Test
	public void givenAinFirst2Positions_WhenTruncateAInFirst2Positions_ShouldTruncateA() {
		assertEquals(output, helper.truncateAInFirst2Positions(input));
	}

	@Test
	public void givenAinFirstPosition_WhenTruncateAInFirst2Positions_ShouldTruncateA() {
		assertEquals(output, helper.truncateAInFirst2Positions(input));
	}

}

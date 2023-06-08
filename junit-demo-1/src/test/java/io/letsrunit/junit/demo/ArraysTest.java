package io.letsrunit.junit.demo;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class ArraysTest {

	@Test
	public void givenNumbers_WhenSort_ShouldSortNumbers() {
		int[] numbers = { 1, 12, 3, 9 };
		Arrays.sort(numbers);
		assertArrayEquals(new int[] { 1, 3, 9, 12 }, numbers);
	}

	@Test(expected = NullPointerException.class)
	public void givenNullArray_WhenSort_ShouldThrowException() {
		int[] numbers = null;
		Arrays.sort(numbers);
	}

	@Test(timeout = 300)
	public void givenMultipleArrayOfNumbers_WhenSort_ShouldSortNumbers() {
		int[] array = { 3, 10, 2 };
		for (int i = 0; i < 10000000; i++) {
			array[0] = i;
			Arrays.sort(array);
		}
	}
}

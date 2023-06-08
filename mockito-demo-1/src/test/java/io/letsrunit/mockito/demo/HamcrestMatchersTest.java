package io.letsrunit.mockito.demo;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestMatchersTest {

	@Test
	public void test() {
		List<Integer> scores = Arrays.asList(10, 40, 70, 90, 101, 32, 99);
		assertThat(scores, hasSize(7));
		assertThat(scores, hasItems(99, 101));
		assertThat(scores, everyItem(greaterThan(9)));
		assertThat(scores, everyItem(lessThan(200)));

		assertThat("", isEmptyString());
		assertThat(null, isEmptyOrNullString());

		Integer[] marks = { 1, 2, 3 };
		assertThat(marks, arrayWithSize(3));
	}

}

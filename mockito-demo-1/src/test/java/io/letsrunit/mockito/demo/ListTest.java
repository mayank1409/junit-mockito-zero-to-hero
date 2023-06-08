package io.letsrunit.mockito.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void testListSize() {
		List mockList = mock(List.class);
		when(mockList.size()).thenReturn(2);
		assertEquals(2, mockList.size());
	}

	@Test
	public void testListSize_multipleReturnValues() {
		List mockList = mock(List.class);
		when(mockList.size()).thenReturn(2).thenReturn(3);
		assertEquals(2, mockList.size());
		assertEquals(3, mockList.size());
	}

	@Test
	public void testListGet() {
		List mockList = mock(List.class);
		when(mockList.get(0)).thenReturn("letsrunit");
		assertEquals("letsrunit", mockList.get(0));
	}

	@Test
	public void testListGet_withAnyInt() {
		List mockList = mock(List.class);
		when(mockList.get(anyInt())).thenReturn("letsrunit");
		assertEquals("letsrunit", mockList.get(0));
		assertEquals("letsrunit", mockList.get(1));
	}
	
	@Test
	public void testListGet_usingBDD() {
		// GIVEN
		List<String> mockList = mock(List.class);
		given(mockList.get(anyInt())).willReturn("letsrunit");
		
		// WHEN
		String firstElement = mockList.get(0);
		
		// THEN
		assertThat(firstElement, is("letsrunit"));	
	}

	@Test(expected = RuntimeException.class)
	public void testListGet_ThrowRuntimeException() {
		List mockList = mock(List.class);
		when(mockList.get(anyInt())).thenThrow(new RuntimeException("Some error happened"));
		mockList.get(0);
	}
}

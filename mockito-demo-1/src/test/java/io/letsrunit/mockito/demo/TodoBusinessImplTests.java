package io.letsrunit.mockito.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplTests {

	@Mock
	private TodoService todoService;

	@InjectMocks
	private TodoBusinessImpl todoBusinessImpl;

	@Captor
	private ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

	@Test
	public void testretrieveTodosRelatedToSpring_usingAmock() {
		when(todoService.retrieveTodos(anyString()))
				.thenReturn(Arrays.asList("Learn Spring JPA", "Learn Spring Boot", "Learn Spring Core"));
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(3, filteredTodos.size());
	}

	@Test
	public void testretrieveTodosRelatedToSpring_usingBDD() {

		// GIVEN
		List<String> todos = Arrays.asList("Learn Spring JPA", "Learn Spring Boot", "Learn Spring Core");
		given(todoService.retrieveTodos(anyString())).willReturn(todos);

		// WHEN
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");

		// THEN
		assertThat(filteredTodos.size(), is(3));
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD() {

		// GIVEN
		List<String> todos = Arrays.asList("Learn Spring JPA", "Learn Spring Boot", "Learn Spring Core");
		given(todoService.retrieveTodos(anyString())).willReturn(todos);

		// WHEN
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		// THEN
		verify(todoService, never()).deleteTodo(anyString());
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_ArgumentCaptor() {

		// GIVEN
		List<String> todos = Arrays.asList("Learn Spring JPA", "Learn Spring Boot", "Learn Spring Core",
				"Learn To dance");
		given(todoService.retrieveTodos(anyString())).willReturn(todos);

		// WHEN
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		// THEN
		then(todoService).should().deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue(), is("Learn To dance"));
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_ArgumentCaptor_MultipleTimes() {

		// GIVEN
		List<String> todos = Arrays.asList("Learn Spring JPA", "Learn Spring Boot", "Learn Spring Core",
				"Learn To dance", "Learn to rock and roll");
		given(todoService.retrieveTodos(anyString())).willReturn(todos);

		// WHEN
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		// THEN
		then(todoService).should(times(2)).deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getAllValues().size(), is(2));
	}
}
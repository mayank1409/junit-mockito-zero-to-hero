package io.letsrunit.mockito.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class TodoBusinessImplMockitoTest {

	@Test
	public void testretrieveTodosRelatedToSpring_usingAmock() {
		TodoService mockTodoService = mock(TodoService.class);
		when(mockTodoService.retrieveTodos(anyString()))
				.thenReturn(Arrays.asList("Learn Spring JPA", "Learn Spring Boot", "Learn Spring Core"));

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");

		assertEquals(3, filteredTodos.size());

	}

	@Test
	public void testretrieveTodosRelatedToSpring_usingBDD() {

		// GIVEN
		TodoService mockTodoService = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring JPA", "Learn Spring Boot", "Learn Spring Core");
		given(mockTodoService.retrieveTodos(anyString())).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);

		// WHEN
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");

		// THEN
		assertThat(filteredTodos.size(), is(3));
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD() {

		// GIVEN
		TodoService mockTodoService = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring JPA", "Learn Spring Boot", "Learn Spring Core");
		given(mockTodoService.retrieveTodos(anyString())).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);

		// WHEN
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		// THEN
		verify(mockTodoService, never()).deleteTodo(anyString());
	}

	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_ArgumentCaptor() {

		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

		// GIVEN
		TodoService mockTodoService = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring JPA", "Learn Spring Boot", "Learn Spring Core",
				"Learn To dance");
		given(mockTodoService.retrieveTodos(anyString())).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);

		// WHEN
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		// THEN
		then(mockTodoService).should().deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue(), is("Learn To dance"));
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_ArgumentCaptor_MultipleTimes() {

		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

		// GIVEN
		TodoService mockTodoService = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring JPA", "Learn Spring Boot", "Learn Spring Core",
				"Learn To dance", "Learn to rock and roll");
		given(mockTodoService.retrieveTodos(anyString())).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(mockTodoService);

		// WHEN
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		// THEN
		then(mockTodoService).should(times(2)).deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getAllValues().size(), is(2));
	}
}
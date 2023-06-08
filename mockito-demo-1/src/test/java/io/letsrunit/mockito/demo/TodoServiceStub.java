package io.letsrunit.mockito.demo;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {
	
	public List<String> retrieveTodos(String user) {
		return Arrays.asList("Learn Spring MVC", "Learn Spring",
				"Learn to Dance");
	}

	@Override
	public void deleteTodo(String todo) {
		
	}
}
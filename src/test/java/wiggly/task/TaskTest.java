package wiggly.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
	@Test
	public void testCreateFromDataTodo(){
		Task test = Task.createFromData("T|true|read book");
		assertEquals("[T][X] read book", test.toString());
	}

	@Test
	public void testCreateFromDataDeadline(){
		Task test = Task.createFromData("D|false|return book|2024-06-06");
		assertEquals("[D][ ] return book (by: 6 Jun 2024)", test.toString());
	}

	@Test
	public void testCreateFromDataEvent(){
		Task test = Task.createFromData("E|false|project meeting|2024-08-06|2024-08-07");
		assertEquals("[E][ ] project meeting (from: 6 Aug 2024 to 7 Aug 2024)", test.toString());
	}

	@Test
	public void testInvalidTaskType(){
		try {
			Task test = Task.createFromData("A|false|project meeting|2024-08-06");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid task type found in save file", e.getMessage());
		}
	}

	@Test
	public void testInvalidDate(){
		try {
			Task test = Task.createFromData("E|false|project meeting|INVALID DATE|2024-08-06");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid date format found in save file", e.getMessage());
		}
	}
}
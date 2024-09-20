package kobe.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test // Test that a Todo object is correctly created with the specified name.
    public void testTodoCreation() {
        Todo todo = new Todo("Read book");
        assertEquals("Read book", todo.name, "Todo name should be 'Read book'.");
    }

    @Test // Tests that marking a Todo as done updates its string representation.
    public void testMarkAsDone() {
        Todo todo = new Todo("Read book");
        todo.markAsDone();
        assertEquals("[T][X] Read book", todo.toString(), "Todo should be marked as done.");
    }

    @Test // Tests that marking a Todo as not done updates its string representation.
    public void testMarkAsNotDone() {
        Todo todo = new Todo("Read book");
        todo.markAsDone(); // First mark it done
        todo.markAsNotDone(); // Then unmark it
        assertEquals("[T][ ] Read book", todo.toString(), "Todo should be marked as not done.");
    }
}

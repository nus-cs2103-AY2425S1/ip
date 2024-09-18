package nether.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;


public class ToDoTaskTest {
    // Test the constructor and inherited methods
    @Test
    void constructor_withValidDescription_createsTodoTask() {
        TodoTask task = new TodoTask("Complete assignment", "School");
        assertEquals("Complete assignment", task.getDescription(), "Description should match the "
                + "input provided.");
        assertFalse(task.isDone(), "Newly created TodoTask should not be marked as done.");
    }

    // Test the toSaveFormat() method
    @Test
    void toSaveFormat_withUndoneTask_returnsCorrectFormat() {
        TodoTask task = new TodoTask("Buy groceries", "Chores");
        assertEquals("T| |Buy groceries", task.toSaveFormat(), "toSaveFormat should return correct "
                + "save string for an undone task.");
    }

    @Test
    void toSaveFormat_withDoneTask_returnsCorrectFormat() {
        TodoTask task = new TodoTask("Read a book", "");
        task.markAsDone();
        assertEquals("T|X|Read a book", task.toSaveFormat(), "toSaveFormat should return correct save "
                + "string for a done task.");
    }

    // Test the toString() method
    @Test
    void toString_withUndoneTask_returnsCorrectString() {
        TodoTask task = new TodoTask("Go jogging", "Exercise");
        assertEquals("[T][ ] Go jogging", task.toString(), "toString should match the expected format "
                + "for an undone task.");
    }

    @Test
    void toString_withDoneTask_returnsCorrectString() {
        TodoTask task = new TodoTask("Do the laundry", "Chores");
        task.markAsDone();
        assertEquals("[T][X] Do the laundry", task.toString(), "toString should match the expected "
                + "format for a done task.");
    }
}

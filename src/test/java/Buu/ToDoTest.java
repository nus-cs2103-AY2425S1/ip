package Buu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for the ToDo class in the GPT application.
 */
public class ToDoTest {

    @Test
    public void testToFileFormat_withValidToDo() {
        // Arrange
        ToDo todo = new ToDo("Submit assignment");

        // Act
        String actual = todo.toFileFormat();

        // Assert
        String expected = "T | 0 | Submit assignment | 1"; // Default priority is 1 (Low)
        assertEquals(expected, actual);
    }

    @Test
    public void testToString_withValidToDo() {
        // Arrange
        ToDo todo = new ToDo("Submit assignment");

        // Act
        String actual = todo.toString();

        // Assert
        String expected = "[T][ ] Submit assignment (Priority: Low Priority)";
        assertEquals(expected, actual);
    }

    @Test
    public void testMarkAsDone() {
        // Arrange
        ToDo todo = new ToDo("Submit assignment");

        // Act
        todo.markAsDone();
        String actual = todo.toString();

        // Assert
        String expected = "[T][X] Submit assignment (Priority: Low Priority)";
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPriority() {
        // Arrange
        ToDo todo = new ToDo("Submit assignment");

        // Act
        todo.setPriority(2);
        String actual = todo.toString();

        // Assert
        String expected = "[T][ ] Submit assignment (Priority: Medium Priority)";
        assertEquals(expected, actual);
    }
}

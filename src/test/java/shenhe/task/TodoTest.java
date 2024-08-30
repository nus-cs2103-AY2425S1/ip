package shenhe.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {

    @Test
    public void testToFileFormat() {
        // Arrange
        Todo todo = new Todo("Read book", false);

        // Act
        String fileFormat = todo.toFileFormat();

        // Assert
        assertEquals("T | 0 | Read book", fileFormat);
    }

    @Test
    public void testToString() {
        // Arrange
        Todo todo = new Todo("Read book", false);

        // Act
        String todoString = todo.toString();

        // Assert
        assertEquals("[T][0] Read book", todoString);
    }

    @Test
    public void testMarkAsDone() {
        // Arrange
        Todo todo = new Todo("Read book", false);

        // Act
        todo.markAsDone();

        // Assert
        assertTrue(todo.isDone());
        assertEquals("[T][1] Read book", todo.toString());
    }

    @Test
    public void testMarkAsUndone() {
        // Arrange
        Todo todo = new Todo("Read book", true);

        // Act
        todo.markAsUndone();

        // Assert
        assertFalse(todo.isDone());
        assertEquals("[T][0] Read book", todo.toString());
    }
}

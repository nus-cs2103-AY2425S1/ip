package task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest extends Task {
    public ToDoTest(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Test
    public void testToDoDescription() {
        ToDoTest todo = new ToDoTest("Read a book");
        assertEquals("Read a book", todo.description, "Correct description");
    }

    @Test
    public void testToStringNotMarked() {
        ToDoTest todo = new ToDoTest("Read a book");
        String expectedOutput = "[T][ ] Read a book";

        assertEquals(expectedOutput, todo.toString());
    }

    @Test
    public void testToStringMarked() {
        ToDoTest todo = new ToDoTest("Read a book");
        todo.markAsDone();  // Mark the task as done
        String expectedOutput = "[T][X] Read a book";

        assertEquals(expectedOutput, todo.toString());
    }

}
package parser;

import darwin.parser.TaskParser;
import darwin.parser.ToDoParser;
import darwin.exception.IllegalTaskArgumentException;
import org.junit.jupiter.api.Test;
import darwin.task.Task;
import darwin.task.ToDo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoParserTest {

    private TaskParser parser = new ToDoParser();
    private Task mockTask = new ToDo("mock");

    @Test
    public void handleTaskStatus_writtenCorrectly() throws IllegalTaskArgumentException {
        parser.handleTaskStatus(this.mockTask, "1");
        assertTrue(this.mockTask.isDone());
        parser.handleTaskStatus(this.mockTask, "0");
        assertFalse(this.mockTask.isDone());
    }

    @Test
    public void handleTaskStatus_exceptionThrown() {
        IllegalTaskArgumentException e = assertThrows(
                IllegalTaskArgumentException.class,
                () -> parser.handleTaskStatus(this.mockTask, "2"));
        assertEquals("", e.getMessage());
    }

    @Test
    public void parse_writtenCorrectly() throws IllegalTaskArgumentException {
        Task t = parser.parse("mock");
        assertEquals(this.mockTask.getName(), t.getName());
    }

    @Test
    public void parse_exceptionThrown() {
        IllegalTaskArgumentException e = assertThrows(
                IllegalTaskArgumentException.class,
                () -> parser.parse(""));
        assertEquals(
                "Wrong task description, ensure that it follows the different task types",
                e.getMessage());
    }
}

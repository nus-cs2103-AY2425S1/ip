package orion.parser;

import orion.task.DeadlineDetails;
import orion.task.EventDetails;
import orion.tasklist.TaskList;
import orion.orionExceptions.FileInitializationException;
import orion.orionExceptions.InvalidDeadlineException;
import orion.orionExceptions.InvalidDeleteException;
import orion.orionExceptions.InvalidEventException;
import orion.orionExceptions.InvalidListException;
import orion.orionExceptions.InvalidMarkException;
import orion.orionExceptions.InvalidTodoException;
import orion.storage.Storage;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void testValidateListCommand_Valid() throws InvalidListException {
        String[] parts = { "list" };
        assertTrue(parser.validateListCommand(parts));
    }

    @Test
    void testValidateListCommand_Invalid() {
        String[] parts = { "list", "extra" };
        assertThrows(InvalidListException.class, () -> parser.validateListCommand(parts));
    }

    @Test
    void testIsInteger_Valid() {
        assertTrue(parser.isInteger("123"));
        assertFalse(parser.isInteger("abc"));
        assertFalse(parser.isInteger(null));
    }

    @Test
    void testValidateMarkAndUnMarkCommand_Valid() throws Exception, FileInitializationException {
        TaskList taskList = new TaskList(new Storage()); // Assuming Storage is properly set up for testing
        taskList.addTodo("Sample Task"); // Assuming addTodo adds a task to the list

        String[] parts = { "mark", "1" };
        int index = parser.validateMarkAndUnMarkCommand(taskList, parts);
        assertEquals(1, index);
    }

    @Test
    void testValidateMarkAndUnMarkCommand_Invalid() throws FileInitializationException {
        TaskList taskList = new TaskList(new Storage());

        String[] parts = { "mark", "abc" };
        assertThrows(InvalidMarkException.class, () -> parser.validateMarkAndUnMarkCommand(taskList, parts));
    }

    @Test
    void testValidateTodoCommand_Valid() throws InvalidTodoException {
        String[] parts = { "todo", "Sample Task" };
        assertEquals("Sample Task", parser.validateTodoCommand(parts));
    }

    @Test
    void testValidateTodoCommand_Invalid() {
        String[] parts = { "todo" };
        assertThrows(InvalidTodoException.class, () -> parser.validateTodoCommand(parts));
    }

    @Test
    void testValidateEventCommand_Valid() throws Exception {
        String[] parts = { "event", "Meeting /from 01/01/2024 1000 /to 01/01/2024 1200" };
        EventDetails details = parser.validateEventCommand(parts);
        assertEquals("Meeting", details.getDescription());
        assertEquals(LocalDateTime.of(2024, 1, 1, 10, 0), details.getFrom());
        assertEquals(LocalDateTime.of(2024, 1, 1, 12, 0), details.getTo());
    }

    @Test
    void testValidateEventCommand_Invalid() {
        String[] parts = { "event", "Meeting" };
        assertThrows(InvalidEventException.class, () -> parser.validateEventCommand(parts));
    }

    @Test
    void testValidateDeadlineCommand_Valid() throws Exception {
        String[] parts = { "deadline", "Submit report /by 02/02/2024 2359" };
        DeadlineDetails details = parser.validateDeadlineCommand(parts);
        assertEquals("Submit report", details.getDescription());
        assertEquals(LocalDateTime.of(2024, 2, 2, 23, 59), details.getBy());
    }

    @Test
    void testValidateDeadlineCommand_Invalid() {
        String[] parts = { "deadline", "Submit report" };
        assertThrows(InvalidDeadlineException.class, () -> parser.validateDeadlineCommand(parts));
    }

    @Test
    void testValidateDeleteCommand_Valid() throws Exception, FileInitializationException {
        TaskList taskList = new TaskList(new Storage()); // Assuming Storage is properly set up for testing
        taskList.addTodo("Sample Task"); // Assuming addTodo adds a task to the list

        String[] parts = { "delete", "1" };
        int index = parser.validateDeleteCommand(taskList, parts);
        assertEquals(1, index);
    }

    @Test
    void testValidateDeleteCommand_Invalid() throws FileInitializationException {
        TaskList taskList = new TaskList(new Storage());

        String[] parts = { "delete", "abc" };
        assertThrows(InvalidDeleteException.class, () -> parser.validateDeleteCommand(taskList, parts));

    }
}

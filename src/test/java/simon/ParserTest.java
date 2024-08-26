package simon;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseListCommand() throws Exception {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testParseMarkCommand() throws Exception {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testParseUnmarkCommand() throws Exception {
        Command command = Parser.parse("unmark 2");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void testParseDeadlineCommand() throws Exception {
        Command command = Parser.parse("deadline Homework /by 26/08/2024 1530");
        assertTrue(command instanceof DeadlineCommand);
        DeadlineCommand deadlineCommand = (DeadlineCommand) command;
        assertEquals("Homework", deadlineCommand.name);
        LocalDateTime expectedDeadline = LocalDateTime.of(2024, 8, 26, 15, 30);
        assertEquals(expectedDeadline, deadlineCommand.deadline);
    }

    @Test
    public void testParseEventCommand() throws Exception {
        Command command = Parser.parse("event Meeting /from 26/08/2024 0900 /to 26/08/2024 1100");
        assertTrue(command instanceof EventCommand);
        EventCommand eventCommand = (EventCommand) command;
        assertEquals("Meeting", eventCommand.name);
        assertEquals("26/08/2024 0900", eventCommand.from);
        assertEquals("26/08/2024 1100", eventCommand.to);
    }

    @Test
    public void testParseToDoCommand() throws Exception {
        Command command = Parser.parse("todo Buy groceries");
        assertTrue(command instanceof ToDoCommand);
        ToDoCommand toDoCommand = (ToDoCommand) command;
        assertEquals("Buy groceries", toDoCommand.name);
    }

    @Test
    public void testParseDeleteCommand() throws Exception {
        Command command = Parser.parse("delete 3");
        assertTrue(command instanceof DeleteCommand);
        DeleteCommand deleteCommand = (DeleteCommand) command;
        assertEquals(2, deleteCommand.index);
    }

    @Test
    public void testParseInvalidCommand() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parse("invalid command");
        });
    }

    @Test
    public void testParseInvalidDeadline() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parse("deadline /by 26/08/2024 1530");
        });
    }

    @Test
    public void testParseInvalidDateFormat() {
        assertThrows(DateTimeParseException.class, () -> {
            Parser.parse("deadline Homework /by 26-08-2024 1530");
        });
    }
}

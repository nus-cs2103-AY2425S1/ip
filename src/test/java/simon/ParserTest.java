package simon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testParseListCommand() throws Exception {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseUnmarkCommand() throws Exception {
        Command command = Parser.parse("unmark 2");
        assertInstanceOf(MarkCommand.class, command);
    }
    @Test
    public void testParseMarkCommand() throws Exception {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }


    @Test
    public void testParseDeadlineCommand() throws Exception {
        Command command = Parser.parse(
                "deadline Homework /by 26/08/2024 1530");
        assertInstanceOf(DeadlineCommand.class, command);
        DeadlineCommand deadlineCommand = (DeadlineCommand) command;
        assertEquals("Homework", deadlineCommand.getName());
        LocalDateTime expectedDeadline = LocalDateTime.of(2024, 8,
                26, 15, 30);
        assertEquals(expectedDeadline, deadlineCommand.getDeadline());
    }

    @Test
    public void testParseEventCommand() throws Exception {
        Command command = Parser.parse(
                "event Meeting /from 26/08/2024 0900 /to 26/08/2024 1100");
        assertInstanceOf(EventCommand.class, command);
        EventCommand eventCommand = (EventCommand) command;
        assertEquals("Meeting", eventCommand.getName());
        assertEquals("26/08/2024 0900", eventCommand.getFrom());
        assertEquals("26/08/2024 1100", eventCommand.getTo());
    }

    @Test
    public void testParseToDoCommand() throws Exception {
        Command command = Parser.parse("todo Buy groceries");
        assertInstanceOf(ToDoCommand.class, command);
        ToDoCommand toDoCommand = (ToDoCommand) command;
        assertEquals("Buy groceries", toDoCommand.getName());
    }

    @Test
    public void testParseDeleteCommand() throws Exception {
        Command command = Parser.parse("delete 3");
        assertInstanceOf(DeleteCommand.class, command);
        DeleteCommand deleteCommand = (DeleteCommand) command;
        assertEquals(2, deleteCommand.getIndex());
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

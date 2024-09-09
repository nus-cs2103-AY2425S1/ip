import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import charlotte.command.AddCommand;
import charlotte.command.Command;
import charlotte.exception.CharlotteException;
import charlotte.parser.Parser;
import charlotte.task.Deadline;
import charlotte.task.Event;
import charlotte.task.ToDo;



class ParserTest {
    @Test
    void parse_todoCommand_returnsAddCommand() throws CharlotteException {
        String command = "todo read book";
        Command result = Parser.parse(command);
        assertTrue(result instanceof AddCommand, "Expected an AddCommand instance");
        AddCommand addCommand = (AddCommand) result;
        assertTrue(addCommand.getTask() instanceof ToDo, "Expected a ToDo task");
        ToDo task = (ToDo) addCommand.getTask();
        assertEquals("read book", task.getDescription(), "Description should be 'read book'");
    }

    @Test
    void parse_deadlineCommand_returnsAddCommand() throws CharlotteException {
        String command = "deadline return book /by 2019-10-15";
        Command result = Parser.parse(command);
        LocalDate expectedDate = LocalDate.parse("2019-10-15");

        assertTrue(result instanceof AddCommand, "Expected an AddCommand instance");
        AddCommand addCommand = (AddCommand) result;
        assertTrue(addCommand.getTask() instanceof Deadline, "Expected a Deadline task");
        Deadline task = (Deadline) addCommand.getTask();
        assertEquals("return book", task.getDescription(), "Description should be 'return book'");
        assertEquals(expectedDate, task.getBy(), "Deadline should be '2019-10-15'");
    }

    @Test
    void parse_eventCommand_returnsAddCommand() throws CharlotteException {
        String command = "event project meeting /from 2024-08-01 /to 2024-08-03";
        Command result = Parser.parse(command);
        LocalDate expectedFrom = LocalDate.parse("2024-08-01");
        LocalDate expectedTo = LocalDate.parse("2024-08-03");

        assertTrue(result instanceof AddCommand, "Expected an AddCommand instance");
        AddCommand addCommand = (AddCommand) result;
        assertTrue(addCommand.getTask() instanceof Event, "Expected an Event task");
        Event task = (Event) addCommand.getTask();
        assertEquals("project meeting", task.getDescription(), "Description should be 'project meeting'");
        assertEquals(expectedFrom, task.getFrom(), "Start date should be '2024-08-01'");
        assertEquals(expectedTo, task.getTo(), "End date should be '2024-08-03'");
    }

    @Test
    void parse_invalidCommand_throwsException() {
        String command = "invalid command";

        Exception exception = assertThrows(CharlotteException.class, () -> {
            Parser.parse(command);
        });

        assertEquals("I'm sorry, I don't know what that means :( Please try again!", exception.getMessage(),
                "Exception message should be 'I'm sorry, I don't know what that means :( Please try again!'");
    }
}

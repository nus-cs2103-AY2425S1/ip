package jeff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jeff.command.Command;
import jeff.command.DeadlineCommand;
import jeff.command.EventCommand;
import jeff.command.ToDoCommand;
import jeff.exceptions.JeffException;


public class ParserTest {

    @Test
    public void parse_todoCommand_success() throws JeffException {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof ToDoCommand);
    }

    @Test
    public void parse_deadlineCommand_success() throws JeffException {
        Command command = Parser.parse("deadline return book /by 12/12/2022 1800");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    public void parse_eventCommand_success() throws JeffException {
        Command command = Parser.parse("event project meeting /from 12/12/2022 1800 /to 13/12/2022 1800");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        JeffException exception = assertThrows(JeffException.class, () -> {
            Parser.parse("invalidCommand some args");
        });
        assertEquals("Unknown command!", exception.getMessage());
    }
}

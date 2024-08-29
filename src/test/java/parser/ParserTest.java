package parser;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.Command;
import exceptions.InvalidCommandException;
import exceptions.TaskInputException;
import exceptions.TaskOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParseDeadlineCommandValid() throws InvalidCommandException, TaskInputException, TaskOutOfBoundsException {
        Command command = Parser.parse("deadline Submit assignment /by 2024-09-30 1800");
        assertTrue(command instanceof AddDeadlineCommand);
        AddDeadlineCommand deadlineCommand = (AddDeadlineCommand) command;
        assertEquals("Submit assignment", deadlineCommand.getDescription());
        assertEquals("2024-09-30 1800", deadlineCommand.getDateTimeString());
    }

    @Test
    void testParseEventCommandInvalidFormat() {
        TaskInputException thrown = assertThrows(TaskInputException.class, () -> {
            Parser.parse("event Project meeting /from 2024-09-30 1400");
        });
        assertEquals("""
                Error: The description and dates of an event cannot be empty.
                
                Please use the following format: event <name> /from <date> <time> /to <date> <time>
                """, thrown.getMessage());
    }
}

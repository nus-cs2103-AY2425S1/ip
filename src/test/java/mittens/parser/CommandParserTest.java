package mittens.parser;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import mittens.commands.AddCommand;
import mittens.commands.Command;
import mittens.commands.DeleteCommand;
import mittens.commands.ExitCommand;
import mittens.commands.ListCommand;
import mittens.commands.MarkCommand;
import mittens.commands.UnmarkCommand;

public class CommandParserTest {
    public CommandParser parser = new CommandParser();

    @Test
    public void parse_listCommand_parsedCorrectly() {
        try {
            Command c = parser.parse("list");
            assertInstanceOf(ListCommand.class, c);
        } catch (BadInputException e) {
            fail();
        }
    }
    
    @Test
    public void parse_addCommandTodo_parsedCorrectly() {
        try {
            Command c = parser.parse("todo taskName");
            assertInstanceOf(AddCommand.class, c);
        } catch (BadInputException e) {
            fail();
        }
    }

    @Test
    public void parse_addCommandDeadline_parsedCorrectly() {
        try {
            Command c = parser.parse("deadline taskName /by 2024-01-31");
            assertInstanceOf(AddCommand.class, c);
        } catch (BadInputException e) {
            fail();
        }
    }

    @Test
    public void parse_addCommandEvent_parsedCorrectly() {
        try {
            Command c = parser.parse("event taskName /from 2024-01-31 /to 2024-02-01");
            assertInstanceOf(AddCommand.class, c);
            // Different flag ordering
            c = parser.parse("event taskName /to 2024-02-01 /from 2024-01-31");
            assertInstanceOf(AddCommand.class, c);
        } catch (BadInputException e) {
            fail();
        }
    }
    
    @Test
    public void parse_markCommand_parsedCorrectly() {
        try {
            Command c = parser.parse("mark 1");
            assertInstanceOf(MarkCommand.class, c);
        } catch (BadInputException e) {
            fail();
        }
    }
    
    @Test
    public void parse_unmarkCommand_parsedCorrectly() {
        try {
            Command c = parser.parse("unmark 1");
            assertInstanceOf(UnmarkCommand.class, c);
        } catch (BadInputException e) {
            fail();
        }
    }
    
    @Test
    public void parse_deleteCommand_parsedCorrectly() {
        try {
            Command c = parser.parse("delete 1");
            assertInstanceOf(DeleteCommand.class, c);
        } catch (BadInputException e) {
            fail();
        }
    }
    
    @Test
    public void parse_exitCommand_parsedCorrectly() {
        try {
            Command c = parser.parse("bye");
            assertInstanceOf(ExitCommand.class, c);
        } catch (BadInputException e) {
            fail();
        }
    }
    
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        try {
            Command c = parser.parse("unknown");
            fail();
        } catch (BadInputException e) {
            assertEquals("'unknown' is not a known command", e.getMessage());
        }
    }

    @Test
    public void parse_addCommandDeadlineInvalidDateFormat_exceptionThrown() {
        try {
            Command c = parser.parse("deadline taskName /by 20240131");
            fail();
        } catch (BadInputException e) {
            assertEquals("Invalid date format for 'by' flag", e.getMessage());
        }
    }
    
    @Test
    public void parse_addCommandDeadlineUnknownFlag_exceptionThrown() {
        try {
            Command c = parser.parse("deadline taskName /unknown 2024-01-31");
            fail();
        } catch (BadInputException e) {
            assertEquals("'unknown' is not a known flag", e.getMessage());
        }
    }
    
    @Test
    public void parse_addCommandDeadlineDuplicateFlag_exceptionThrown() {
        try {
            Command c = parser.parse("deadline taskName /by 2024-01-31 /by 2024-02-01");
            fail();
        } catch (BadInputException e) {
            assertEquals("Found duplicate of 'by' flag", e.getMessage());
        }
    }
    
    @Test
    public void parse_addCommandEventNoToFlag_exceptionThrown() {
        try {
            Command c = parser.parse("event taskName /from 2024-01-31");
            fail();
        } catch (BadInputException e) {
            assertEquals("Command 'event' must have a 'to' flag", e.getMessage());
        }
    }
}

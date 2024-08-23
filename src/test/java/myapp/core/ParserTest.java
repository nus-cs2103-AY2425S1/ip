package myapp.core;

import myapp.command.Command;
import myapp.command.ListCommand;
import myapp.command.MarkCommand;
import myapp.command.ToDoCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testParseCommand() throws BingBongException {
        Command commandToDo = Parser.parseCommand("todo read book");
        Command commandList = Parser.parseCommand("list");
        Command commandMark = Parser.parseCommand("mark 3");

        assertTrue(commandToDo instanceof ToDoCommand);
        assertTrue(commandList instanceof ListCommand);
        assertTrue(commandMark instanceof MarkCommand);
    }

    @Test
    public void parseToDo_noDescription_exceptionThrown() throws BingBongException {
        try {
            Command command = Parser.parseCommand("todo ");
            fail();
        } catch (Exception e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseInvalidCommand() throws BingBongException {
        try {
            Command command = Parser.parseCommand("drive");
            fail();
        } catch (Exception e) {
            assertEquals("Command not recognized. Please try again...", e.getMessage());
        }
    }
}

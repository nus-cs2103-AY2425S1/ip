package lexi.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import lexi.command.Command;
import lexi.command.Commands;
import lexi.exception.LexiException;


public class ParserTest {
    @Test
    void parse_markCommand_correctCommandReturned() throws LexiException {

        Command result = Parser.parse("mark 1");
        assertNotNull(result);
        assertEquals("MARK", result.getCommandName());
    }

    @Test
    void parse_todoCommand_correctCommandReturned() throws LexiException {

        Command result = Parser.parse("todo read book");
        assertNotNull(result);
        assertEquals("ADD", result.getCommandName());
    }

    @Test
    void parse_deadlineCommand_correctCommandReturned() throws LexiException {

        Command result = Parser.parse("deadline submit assignment /by 13/01/2002 1700");
        assertNotNull(result);
        assertEquals("ADD", result.getCommandName());
    }

    @Test
    void parse_eventCommand_correctCommandReturned() throws LexiException {

        Command result = Parser.parse("event project meeting /from 13/01/2002 1700 /to 13/01/2002 1800");
        assertNotNull(result);
        assertEquals("ADD", result.getCommandName());
    }

    @Test
    void parse_deleteCommand_correctCommandReturned() throws LexiException {

        Command result = Parser.parse("delete 1");
        assertNotNull(result);
        assertEquals("DELETE", result.getCommandName());
    }

    @Test
    void parse_listCommand_correctCommandReturned() throws LexiException {

        Command result = Parser.parse("list");
        assertNotNull(result);
        assertEquals("LIST", result.getCommandName());
    }

    @Test
    void parse_byeCommand_correctCommandReturned() throws LexiException {

        Command result = Parser.parse("bye");
        assertNotNull(result);
        assertEquals("BYE", result.getCommandName());
    }

    @Test
    void parse_unrecognizedCommand_exceptionThrown() {
        LexiException exception = assertThrows(LexiException.class, () -> {
            Parser.parse("unknownCommand");
        });
        assertEquals("Please enter one of the following commands:\n"
                + Commands.printCommands(), exception.getMessage());
    }

}

package parser;

import commands.DeadlineCommand;
import commands.EventCommand;
import commands.TodoCommand;
import exception.PrimoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void getCommandType_delete_deleteCommandType() throws PrimoException {
        assertEquals(Parser.CommandType.DELETE, Parser.CommandType.getCommandType("delete"));
    }

    @Test
    public void parse_todo_todoCommand() throws PrimoException {
        assertInstanceOf(TodoCommand.class, Parser.parse("todo _____"));
    }

    @Test
    public void parse_deadline_deadlineCommand() throws PrimoException {
        assertInstanceOf(DeadlineCommand.class, Parser.parse("deadline _____ /by 1996-08-14"));
    }

    @Test
    public void parse_deadline_exceptionThrown() throws PrimoException {
        try {
            assertInstanceOf(DeadlineCommand.class, Parser.parse("deadline _____ /by invalid"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            String expected = "deadline time empty or wrong formatting! Expected deadline <string> " +
                    "/by YYYY-MM-DD";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void parse_event_eventCommand() throws PrimoException {
        assertInstanceOf(EventCommand.class, Parser.parse("event _____ /from 1996-08-14 /to 1996-08-14"));
    }

}

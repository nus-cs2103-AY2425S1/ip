package jag;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ParserTest {
    @Test
    public void parse_list_test() throws AExceptions {
        Command actual = Parser.parse("list");
        assertTrue(actual instanceof ListCommand, "Expected an instance of ListCommand");
    }

    @Test
    public void parse_mark_test() throws AExceptions {
        Command actual = Parser.parse("mark ");
        assertTrue(actual instanceof MarkCommand, "Expected an instance of ListCommand");
    }

    @Test
    public void parse_unmark_test() throws AExceptions {
        Command actual = Parser.parse("unmark ");
        assertTrue(actual instanceof MarkCommand, "Expected an instance of ListCommand");
    }

    @Test
    public void parse_todods_test() throws AExceptions {
        Command actual = Parser.parse("todo ");
        assertTrue((actual instanceof AddCommand), "Expected an instance of ListCommand");
    }

    @Test
    public void parse_deadline_test() throws AExceptions {
        Command actual = Parser.parse("deadline ");
        assertTrue((actual instanceof AddCommand), "Expected an instance of ListCommand");
    }

    @Test
    public void parse_event_test() throws AExceptions {
        Command actual = Parser.parse("event ");
        assertTrue((actual instanceof AddCommand), "Expected an instance of ListCommand");
    }

    @Test
    public void parse_delete_test() throws AExceptions {
        Command actual = Parser.parse("delete ");
        assertTrue((actual instanceof DeleteCommand), "Expected an instance of ListCommand");
    }

    /**
     * Test to check whether with the user input of "find", will the Parser
     * Class return a FindCommand instance.
     *
     *
     * @throws AExceptions In the event that the input received is not in any of the
     * matching switch cases mentioned
     */
    @Test
    public void parse_find_test() throws AExceptions {
        Command actual = Parser.parse("find ");
        assertTrue((actual instanceof FindCommand), "Expected an instance of FindCommand");
    }

    @Test
    public void parse_bye_test() throws AExceptions {
        Command actual = Parser.parse("bye ");
        assertTrue((actual instanceof ExitCommand), "Expected an instance of ListCommand");
    }

    @Test
    public void parse_exceptionThrown_test() {
        try {
            String unknownCommand = "this is totally not a command :)";
            Parser.parse(unknownCommand);
        } catch (AExceptions e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getErrorMessage());
        }
    }

}
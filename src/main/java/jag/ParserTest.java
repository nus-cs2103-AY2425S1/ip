package jag;

public class ParserTest {
    @Test
    public void parse_list_test() throws AExceptions {
        Command actual = Parser.parse("list");
        assertEquals(actual instanceof ListCommand, "Expected an instance of ListCommand");
    }

    @Test
    public void parse_mark_test() throws AExceptions {
        Command actual = Parser.parse("mark ");
        assertEquals(actual instanceof MarkCommand, "Expected an instance of ListCommand");
    }

    @Test
    public void parse_unmark_test() throws AExceptions {
        Command actual = Parser.parse("unmark ");
        assertEquals(actual instanceof MarkCommand, "Expected an instance of ListCommand");
    }

    @Test
    public void parse_todods_test() throws AExceptions {
        Command actual = Parser.parse("todo ");
        assertEquals(actual instanceof AddCommand, "Expected an instance of ListCommand");
    }

    @Test
    public void parse_deadline_test() throws AExceptions {
        Command actual = Parser.parse("deadline ");
        assertEquals(actual instanceof AddCommand, "Expected an instance of ListCommand");
    }

    @Test
    public void parse_event_test() throws AExceptions {
        Command actual = Parser.parse("event ");
        assertEquals(actual instanceof AddCommand, "Expected an instance of ListCommand");
    }

    @Test
    public void parse_delete_test() throws AExceptions {
        Command actual = Parser.parse("delete ");
        assertEquals(actual instanceof DeleteCommand, "Expected an instance of ListCommand");
    }

    @Test
    public void parse_bye_test() throws AExceptions {
        Command actual = Parser.parse("bye ");
        assertEquals(actual instanceof ExitCommand, "Expected an instance of ListCommand");
    }

    @Test
    public void parse_exceptionThrown_test() {
        try {
            String unknownCommand = "this is totally not a command :)";
            Parser.parse(unknownCommand);
            fail(); // the test should not reach this line
        } catch (AExceptions e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getErrorMessage());
        }
    }

}
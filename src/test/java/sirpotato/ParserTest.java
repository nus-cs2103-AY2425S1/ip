package sirpotato;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseCommand_validNoWhitespace_success() throws DukeException {

        Command command = Parser.parseCommand("todo Read book");

        assertTrue(command instanceof AddCommand);
        AddCommand addCommand = (AddCommand) command;
        Todo todo = (Todo) addCommand.getTask();
        assertEquals("Read book", todo.displayDescription());

        command = Parser.parseCommand("deadline return book /by 10-10-2020");
        assertTrue(command instanceof AddCommand);
        Deadline deadline = (Deadline) ((AddCommand) command).getTask();
        assertEquals("return book ", deadline.displayDescription());
        assertEquals(LocalDate.of(2020, 10, 10), deadline.getByDate());

        command = Parser.parseCommand("event meeting /from 10-10-2020 /to 11-10-2020");
        assertTrue(command instanceof AddCommand);
        Event event = (Event) ((AddCommand) command).getTask();
        assertEquals("meeting", event.displayDescription());
        assertEquals(LocalDate.of(2020, 10, 10), event.getFromDate());
        assertEquals(LocalDate.of(2020, 10, 11), event.getToDate());

        command = Parser.parseCommand("delete 1");
        assertTrue(command instanceof DeleteCommand);

        command = Parser.parseCommand("find book");
        assertTrue(command instanceof FindCommand);

        command = Parser.parseCommand("sort deadline");
        assertTrue(command instanceof SortCommand);

        command = Parser.parseCommand("mark 1");
        assertTrue(command instanceof MarkCommand);

        command = Parser.parseCommand("unmark 1");
        assertTrue(command instanceof UnmarkCommand);

        command = Parser.parseCommand("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parseCommand_todoWithWhitespace_success() throws DukeException {
        Command command = Parser.parseCommand("   todo read book   ");
        assertTrue(command instanceof AddCommand);
        Todo todo = (Todo) ((AddCommand) command).getTask();
        assertEquals("read book", todo.displayDescription());
    }

    @Test
    public void parseCommand_deadlineWithWhitespace_success() throws DukeException {
        Command command = Parser.parseCommand("   deadline return book /by 10-10-2020   ");
        assertTrue(command instanceof AddCommand);
        Deadline deadline = (Deadline) ((AddCommand) command).getTask();
        assertEquals("return book ", deadline.displayDescription());
        assertEquals(LocalDate.of(2020, 10, 10), deadline.getByDate());
    }

    @Test
    public void parseCommand_eventWithWhitespace_success() throws DukeException {
        Command command = Parser.parseCommand("   event meeting /from 10-10-2020 /to 11-10-2020   ");
        assertTrue(command instanceof AddCommand);
        Event event = (Event) ((AddCommand) command).getTask();
        assertEquals("meeting", event.displayDescription());
        assertEquals(LocalDate.of(2020, 10, 10), event.getFromDate());
        assertEquals(LocalDate.of(2020, 10, 11), event.getToDate());
    }

    @Test
    public void parseCommand_deleteWithWhitespace_success() throws DukeException {
        Command command = Parser.parseCommand("   delete 2   ");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parseCommand_findWithWhitespace_success() throws DukeException {
        Command command = Parser.parseCommand("   find book   ");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    public void parseCommand_markWithWhitespace_success() throws DukeException {
        Command command = Parser.parseCommand("   mark 1   ");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parseCommand_unmarkWithWhitespace_success() throws DukeException {
        Command command = Parser.parseCommand("   unmark 1   ");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    public void parseCommand_sortWithWhitespace_success() throws DukeException {
        Command command = Parser.parseCommand("   sort deadline   ");
        assertTrue(command instanceof SortCommand);
    }

    @Test
    public void parseCommand_byeWithWhitespace_success() throws DukeException {
        Command command = Parser.parseCommand("   bye   ");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parseCommand_listWithWhitespace_success() throws DukeException {
        Command command = Parser.parseCommand("   list   ");
        assertTrue(command instanceof ListCommand);
    }



    // Test for missing fields causing DukeException
    @Test
    public void parseCommand_missingFields_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parseCommand("todo"));

        assertThrows(DukeException.class, () -> Parser.parseCommand("deadline /by"));
        assertThrows(DukeException.class,
                () -> Parser.parseCommand("deadline return book /by"));
        assertThrows(DukeException.class,
                () -> Parser.parseCommand("deadline /by 10-10-2020"));

        assertThrows(DukeException.class,
                () -> Parser.parseCommand("event /from /to"));
        assertThrows(DukeException.class,
                () -> Parser.parseCommand("event meeting /from /to"));
        assertThrows(DukeException.class,
                () -> Parser.parseCommand("event /from 10-10-2020 /to"));
        assertThrows(DukeException.class,
                () -> Parser.parseCommand("event /from 10-10-2020 /to 10-10-2020"));

        assertThrows(DukeException.class, () -> Parser.parseCommand("delete"));
        assertThrows(DukeException.class, () -> Parser.parseCommand("mark"));
        assertThrows(DukeException.class, () -> Parser.parseCommand("unmark"));
        assertThrows(DukeException.class, () -> Parser.parseCommand("find"));
    }

    // Test for missing fields with whitespace causing DukeException
    @Test
    public void parseCommand_missingFieldsWhitespace_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parseCommand("todo "));

        assertThrows(DukeException.class, () -> Parser.parseCommand("deadline /by "));
        assertThrows(DukeException.class,
                () -> Parser.parseCommand("deadline return book /by "));
        assertThrows(DukeException.class,
                () -> Parser.parseCommand("deadline /by 10-10-2020"));

        assertThrows(DukeException.class,
                () -> Parser.parseCommand("event /from /to "));
        assertThrows(DukeException.class,
                () -> Parser.parseCommand("event meeting /from /to "));
        assertThrows(DukeException.class,
                () -> Parser.parseCommand("event /from 10-10-2020 /to "));
        assertThrows(DukeException.class,
                () -> Parser.parseCommand("event /from 10-10-2020 /to 10-10-2020 "));

        assertThrows(DukeException.class, () -> Parser.parseCommand("delete "));
        assertThrows(DukeException.class, () -> Parser.parseCommand("mark "));
        assertThrows(DukeException.class, () -> Parser.parseCommand("unmark "));
        assertThrows(DukeException.class, () -> Parser.parseCommand("find "));
    }


}

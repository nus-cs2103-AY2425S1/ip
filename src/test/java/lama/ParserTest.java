package lama;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import lama.command.AddCommand;
import lama.command.Command;
import lama.command.DeleteCommand;
import lama.command.ExitCommand;
import lama.command.FindCommand;
import lama.command.ListCommand;
import lama.command.MarkCommand;
import lama.command.UnmarkCommand;


/**
 * Test class for Parser
 * Contains unit test case for Parser class
 */
public class ParserTest {

    /**
     * Tests parsing of the "bye" command.
     * Verifies that the command is correctly parsed as an ExitCommand.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void parseByeTest() throws LamaException {
        Command command = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    /**
     * Tests parsing of the "list" command.
     * Verifies that the command is correctly parsed as a ListCommand.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void parseListTest() throws LamaException {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    /**
     * Tests parsing of the "mark 1" command.
     * Verifies that the command is correctly parsed as a MarkCommand.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void parseMarkTest() throws LamaException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    /**
     * Tests parsing of the "unmark 1" command.
     * Verifies that the command is correctly parsed as an UnmarkCommand.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void parseUnmarkTest() throws LamaException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    /**
     * Tests parsing of the "to-do" command.
     * Verifies that the command is correctly parsed as a TodoCommand.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void parseTodoTest() throws LamaException {
        Command command = Parser.parse("todo Read Book");
        assertInstanceOf(AddCommand.class, command);
    }

    /**
     * Tests parsing of the "deadline" command.
     * Verifies that the command is correctly parsed as a DeadlineCommand.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void parseDeadlineTest() throws LamaException {
        Command command = Parser.parse("deadline Return Book /by 2025-12-12");
        assertInstanceOf(AddCommand.class, command);
    }

    /**
     * Tests parsing of the "event" command.
     * Verifies that the command is correctly parsed as an EventCommand.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void parseEventTest() throws LamaException {
        Command command = Parser.parse("event Project Meeting /from 2025-12-12 2300 /to 2026-12-12 2300");
        assertInstanceOf(AddCommand.class, command);
    }

    /**
     * Tests parsing of the "delete" command.
     * Verifies that the command is correctly parsed as a DeleteCommand.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void parseDeleteTest() throws LamaException {
        Command command = Parser.parse("delete 2");
        assertInstanceOf(DeleteCommand.class, command);
    }

    /**
     * Test case for find command.
     * Verifies that when find is input, find command will be executed.
     * @throws LamaException Thrown if error occurs during execution
     */
    @Test
    public void parseFindCommandTest() throws LamaException {
        Command command = Parser.parse("find hi");
        assertInstanceOf(FindCommand.class, command);
    }

    /**
     * Tests parsing of the default cases
     * Verifies that the command is correctly being displayed.
     *
     * @throws LamaException Thrown if there is an error in running command.
     */
    @Test
    public void parseDefaultTest() throws LamaException {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("?");
        });

        String output = "Sorry, I don't know what you want to do!\n"
                + "You can either choose to use:\n"
                + "1. todo [Your TODO]\n"
                + "2. deadline [Your TODO] /by [date of deadline]\n"
                + "3. event [Your event] /from [start time] /to [end time]\n"
                + "4. list\n"
                + "5. mark [number of todo in the list]\n"
                + "6. unmark [number of todo in the list]\n"
                + "7. find [keywords]\n"
                + "8. alias [alias] [command]\n"
                + "9. command\n"
                + "10. bye";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Tests handling of missing argument for "mark" command.
     * Verifies that an appropriate LamaException is thrown with the expected error message.
     */
    @Test
    public void parseMarkFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("mark");
        });

        String output = "Please specify the number that wanted to be marked as done!";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Tests handling of missing argument for "unmark" command.
     * Verifies that an appropriate LamaException is thrown with the expected error message.
     */
    @Test
    public void parseUnmarkFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("unmark");
        });

        String output = "Please specify the number that wanted to be unmarked!";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Tests handling of missing argument for "to-do" command.
     * Verifies that an appropriate LamaException is thrown with the expected error message.
     */
    @Test
    public void parseTodoFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("todo");
        });

        String output = "Please specify the description of TODO!";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Tests handling of missing argument for "deadline" command.
     * Verifies that an appropriate LamaException is thrown with the expected error message.
     */
    @Test
    public void parseDeadlineFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("deadline");
        });

        String output = "Please specify the description of deadline!";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Tests handling of missing argument for "deadline" command.
     * Verifies that an appropriate LamaException is thrown with the expected error message.
     */
    @Test
    public void parseDeadlineWithoutDateFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("deadline /by");
        });

        String output = "Please specify the date of deadline in the format of:\n"
                + "deadline [description] /by [date]";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Tests handling of incorrect date format for "deadline" command.
     * Verifies that an appropriate LamaException is thrown with the expected error message.
     */
    @Test
    public void parseDeadlineFormatFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("deadline Return Book /by Aug");
        });

        String output = "Date format should follow yyyy-MM-dd";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Tests handling of missing argument for "event" command.
     * Verifies that an appropriate LamaException is thrown with the expected error message.
     */
    @Test
    public void parseEventFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("event");
        });

        String output = "Please specify the description of event in the format of:\n"
                + "event [description] /from [start time] /to [end time]";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Tests handling of missing argument for "event" command.
     * Verifies that an appropriate LamaException is thrown with the expected error message.
     */
    @Test
    public void parseEventWithoutFromFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("event Read Book /from");
        });

        String output = "Please specify the start time of event in the format of:\n"
                + "event [description] /from [start time] /to [end time]";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Tests handling of missing argument for "event" command.
     * Verifies that an appropriate LamaException is thrown with the expected error message.
     */
    @Test
    public void parseEventWithoutToFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("event Read Book /from 2025-11-11 2000 /to");
        });

        String output = "Please specify the end time of event in the format of:\n"
                + "event [description] /from [start time] /to [end time]";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Tests handling of incorrect date format for "event" command.
     * Verifies that an appropriate LamaException is thrown with the expected error message.
     */
    @Test
    public void parseEventFormatFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("event Read Book /from 2025-11-11 /to 2020-10-10 2000");
        });

        String output = "Date time format should follow yyyy-MM-dd HHmm";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Tests handling of missing argument for "delete" command.
     * Verifies that an appropriate LamaException is thrown with the expected error message.
     */
    @Test
    public void parseDeleteFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("delete");
        });

        String output = "Please specify the number that wanted to delete!";

        assertEquals(output, lamaException.getMessage());
    }

    /**
     * Test case for find command.
     * Verifies that no task return when no keyword given.
     */
    @Test
    public void parseFindFailTest() {
        LamaException lamaException = assertThrows(LamaException.class, () -> {
            Parser.parse("find");
        });

        String output = "Please specify the keyword you wanted to search!";

        assertEquals(output, lamaException.getMessage());
    }
}

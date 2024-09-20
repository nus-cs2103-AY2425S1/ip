package bobby.parser;
// Static imports
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bobby.command.AddTaskCommand;
import bobby.command.ByeCommand;
import bobby.command.CommandEnum;
import bobby.command.DeleteTasksCommand;
import bobby.command.ListCommand;
import bobby.command.MarkTasksCommand;
import bobby.command.UnmarkTasksCommand;
import bobby.exceptions.BobbyException;
import bobby.exceptions.EmptyDeadlineException;
import bobby.exceptions.EmptyEventException;
import bobby.exceptions.EmptyTodoException;
import bobby.exceptions.InvalidDateException;
import bobby.exceptions.InvalidInputException;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;




/**
 * Test class for the Parser.
 */
public class TestParser {
    private Parser parser;

    /**
     * Sets up a new {@code Parser} instance before each test.
     */
    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    /**
     * Tests parsing of a command string to a {@code CommandEnum}.
     * Verifies that the correct enum constant is returned for a given command.
     *
     * @throws InvalidInputException if the command string cannot be parsed
     */
    @Test
    public void testParseCommand() throws InvalidInputException {
        assertEquals(CommandEnum.TODO, parser.parseCommand("todo"));
        assertEquals(CommandEnum.BYE, parser.parseCommand("bye"));
        assertEquals(CommandEnum.LIST, parser.parseCommand("list"));
        assertEquals(CommandEnum.MARK, parser.parseCommand("mark"));
        assertEquals(CommandEnum.UNMARK, parser.parseCommand("unmark"));
        assertEquals(CommandEnum.DELETE, parser.parseCommand("delete"));
        assertEquals(CommandEnum.DEADLINE, parser.parseCommand("deadline"));
        assertEquals(CommandEnum.EVENT, parser.parseCommand("event"));
        assertEquals(CommandEnum.FIND, parser.parseCommand("find"));
        assertEquals(CommandEnum.SEARCHDATE, parser.parseCommand("searchdate"));
    }

    /**
     * Tests parsing of task indices from a command string.
     * Verifies that the correct task indices are extracted from the input.
     *
     * @throws InvalidInputException if the command string cannot be parsed
     */
    @Test
    public void testParseTaskIndices() {
        String[] indices = parser.parseTaskIndices("mark 1 2 3");
        assertArrayEquals(new String[]{"1", "2", "3"}, indices);
    }

    /**
     * Tests parsing of a valid date input.
     * Verifies that the correct {@code LocalDate} object is returned for special keywords
     * and absolute dates.
     *
     * @throws InvalidDateException if the date input is invalid
     */
    @Test
    public void testParseDate() throws InvalidDateException {
        assertEquals(LocalDate.now(), parser.parseDate("today"));
        assertEquals(LocalDate.now().plusDays(1), parser.parseDate("tomorrow"));
        assertEquals(LocalDate.of(2024, 9, 15), parser.parseDate("2024-09-15"));
    }

    /**
     * Tests parsing of an invalid date input.
     * Verifies that an {@code InvalidDateException} is thrown for an incorrect date format.
     */
    @Test
    public void testParseDate_invalidDate() {
        assertThrows(InvalidDateException.class, () -> parser.parseDate("invalid-date"));
    }

    /**
     * Tests creation of a {@code Todo} task from user input.
     * Verifies that a {@code Todo} task is created with the correct description.
     *
     * @throws EmptyTodoException if the description is missing
     */
    @Test
    public void testCreateTodo() throws EmptyTodoException {
        Task todo = parser.createTodo("todo Read book");
        assertEquals(new Todo("Read book").toString(), todo.toString());
    }

    /**
     * Tests creation of a {@code Deadline} task from user input.
     * Verifies that a {@code Deadline} task is created with the correct description and date.
     *
     * @throws EmptyDeadlineException if the description or deadline is missing
     * @throws InvalidDateException if the date format is invalid
     */
    @Test
    public void testCreateDeadline() throws EmptyDeadlineException, InvalidDateException {
        Task deadline = parser.createDeadline("deadline Submit report /by 2024-09-15");
        assertEquals(new Deadline("Submit report", LocalDate.of(2024, 9, 15)).toString(),
                deadline.toString());
    }

    /**
     * Tests creation of an {@code Event} task from user input.
     * Verifies that an {@code Event} task is created with the correct description, start date, and end date.
     *
     * @throws EmptyEventException if the description, start date, or end date is missing
     * @throws InvalidDateException if the date format is invalid
     */
    @Test
    public void testCreateEvent() throws EmptyEventException, InvalidDateException {
        Task event = parser.createEvent("event Team meeting /from 2024-09-14 /to 2024-09-15");
        assertEquals(new Event("Team meeting", LocalDate.of(2024, 9, 14),
                LocalDate.of(2024, 9, 15)).toString(), event.toString());
    }

    /**
     * Tests creation of a {@code Todo} task with an empty description.
     * Verifies that an {@code EmptyTodoException} is thrown if the description is missing.
     */
    @Test
    public void testCreateTodo_empty() {
        assertThrows(EmptyTodoException.class, () -> parser.createTodo("todo "));
    }

    /**
     * Tests creation of a {@code Deadline} task with missing date.
     * Verifies that an {@code EmptyDeadlineException} is thrown if the date is missing.
     *
     * @throws EmptyDeadlineException if the description or deadline is missing
     */
    @Test
    public void testCreateDeadline_missingDate() throws EmptyDeadlineException {
        assertThrows(EmptyDeadlineException.class, () -> parser.createDeadline("deadline Submit report /by "));
    }

    /**
     * Tests creation of an {@code Event} task with an invalid date format.
     * Verifies that an {@code InvalidDateException} is thrown if the date format is incorrect.
     *
     * @throws EmptyEventException if the description, start date, or end date is missing
     */
    @Test
    public void testCreateEvent_invalidDate() throws EmptyEventException {
        assertThrows(InvalidDateException.class, () ->
                parser.createEvent("event Team meeting /from 2024-09-14 /to invalid-date"));
    }

    /**
     * Tests extracting a keyword for search from user input.
     * Verifies that the correct keyword or date string is extracted for searching.
     *
     * @throws InvalidInputException if the input is invalid or the keyword is missing
     */
    @Test
    public void testFindKeyword() throws InvalidInputException {
        assertEquals("important", parser.findKeyword("find important"));
        assertEquals("2024-09-15", parser.findKeyword("searchdate 2024-09-15"));
    }

    /**
     * Tests finding a date from user input.
     * Verifies that the correct {@code LocalDate} object is extracted for search date commands.
     *
     * @throws InvalidDateException if the provided date format is invalid
     * @throws InvalidInputException if the date is missing in the input
     */
    @Test
    public void testFindDate() throws InvalidDateException, InvalidInputException {
        assertEquals(LocalDate.of(2024, 9, 15), parser.findDate("searchdate 2024-09-15"));
    }

    /**
     * Tests parsing a user command string to a {@code Command} object.
     * Verifies that the correct {@code Command} object is created based on the user input.
     *
     * @throws BobbyException if the user input is invalid or does not match any command
     */
    @Test
    public void testParseUserCommand() throws BobbyException {
        assertInstanceOf(ByeCommand.class, parser.parseUserCommand("bye"));
        assertInstanceOf(ListCommand.class, parser.parseUserCommand("list"));
        assertInstanceOf(MarkTasksCommand.class, parser.parseUserCommand("mark 0"));
        assertInstanceOf(UnmarkTasksCommand.class, parser.parseUserCommand("unmark 0"));
        assertInstanceOf(AddTaskCommand.class, parser.parseUserCommand("todo hello"));
        assertInstanceOf(AddTaskCommand.class, parser.parseUserCommand("deadline hello /by 2001-12-19"));
        assertInstanceOf(AddTaskCommand.class, parser.parseUserCommand("event hello /from 2001-02-19 /to 2001-02-20"));
        assertInstanceOf(DeleteTasksCommand.class, parser.parseUserCommand("delete 1 2 3"));
    }

    /**
     * Tests parsing of an invalid user command.
     * Verifies that an {@code InvalidInputException} is thrown if the command is invalid.
     *
     * @throws BobbyException if the user input is invalid or does not match any command
     */
    @Test
    public void testParseUserCommandInvalid() {
        assertThrows(InvalidInputException.class, () -> parser.parseUserCommand("invalid command"));
    }
}

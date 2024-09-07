package nugget;

import nugget.command.AddTaskCommand;
import nugget.command.Command;
import nugget.command.ListCommand;
import nugget.command.MarkTaskCommand;
import nugget.exception.EmptyDescriptionException;
import nugget.exception.InvalidTaskNumberException;
import nugget.exception.NuggetException;
import nugget.exception.UnknownCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private Parser parser;
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        // Initialize TaskList and Parser before each test
        taskList = new TaskList();
        parser = new Parser(taskList);
    }

    @Test
    void parse_validListCommand_returnsListCommand() throws NuggetException {
        Command command = parser.parse("list");
        assertTrue(command instanceof ListCommand, "Expected ListCommand for 'list' input");
    }

    @Test
    void parse_invalidTodoCommand_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> parser.parse("todo"));
    }

    @Test
    void parse_unknownCommand_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> parser.parse("unknowncommand"));
    }

    @Test
    void parse_validTodoCommand_returnsAddTaskCommand() throws NuggetException {
        Command command = parser.parse("todo Read book");
        assertTrue(command instanceof AddTaskCommand, "Expected AddTaskCommand for 'todo' input");
    }

    @Test
    void parse_validDeadlineCommand_returnsAddTaskCommand() throws NuggetException {
        Command command = parser.parse("deadline Return book /by 2024-05-24 1500");
        assertTrue(command instanceof AddTaskCommand, "Expected AddTaskCommand for 'deadline' input");
    }

    @Test
    void parse_invalidDeadlineCommand_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> parser.parse("deadline"));
    }

    @Test
    void parse_validEventCommand_returnsAddTaskCommand() throws NuggetException {
        Command command = parser.parse("event Meeting /from 2024-08-23 1500 /to 2024-08-23 1700");
        assertTrue(command instanceof AddTaskCommand, "Expected AddTaskCommand for 'event' input");
    }

    @Test
    void parse_invalidEventCommand_throwsEmptyDescriptionException() {
        assertThrows(EmptyDescriptionException.class, () -> parser.parse("event"));
    }

    @Test
    void parse_validMarkCommand_returnsMarkTaskCommand() throws NuggetException {
        taskList.addTask(new Todo("Read book")); // Add a task to mark
        Command command = parser.parse("mark 1");
        assertTrue(command instanceof MarkTaskCommand, "Expected MarkTaskCommand for 'mark' input");
    }

    @Test
    void parse_invalidMarkCommand_throwsInvalidTaskNumberException() {
        assertThrows(InvalidTaskNumberException.class, () -> parser.parse("mark 5"));
    }
}

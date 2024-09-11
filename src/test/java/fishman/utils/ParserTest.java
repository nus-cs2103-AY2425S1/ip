package fishman.utils;

import fishman.command.Command;
import fishman.command.DeleteCommand;
import fishman.command.ExitCommand;
import fishman.command.ListCommand;
import fishman.command.MarkCommand;
import fishman.exception.FishmanException;
import fishman.task.Deadline;
import fishman.task.Event;
import fishman.task.TaskList;
import fishman.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private TaskList tasks;
    private Ui ui;
    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
    }
    @Test
    void parse_byeCommand_returnsExitCommand() throws FishmanException {
        Command command = Parser.parse("bye", tasks);
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void parse_listCommandWithTasks_returnsListCommand() throws FishmanException {
        tasks.addTask(new ToDo("Sample Task", false));
        Command command = Parser.parse("list", tasks);
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parse_listCommandWithoutTasks_throwsEmptyListException() {
        FishmanException e = assertThrows(FishmanException.EmptyListException.class, () -> Parser.parse("list", tasks));
        assertEquals("The list is empty. Unable to perform operation.", e.getMessage());
    }

    @Test
    void parse_markCommandWithValidIndex_returnsMarkedTask() throws FishmanException {
        tasks.addTask(new ToDo("Sample Task", false));
        Command command = Parser.parse("mark 1", tasks);
        assertInstanceOf(MarkCommand.class, command);
        command.execute(tasks, ui);
        assertTrue(tasks.getTask(0).getTaskStatus());
    }

    @Test
    void parse_markCommandWithInvalidIndex_throwsIndexOutOfBoundsException() {
        tasks.addTask(new ToDo("Sample Task", false));
        FishmanException e = assertThrows(FishmanException.IndexOutOfBoundsException.class, () -> { Command command = Parser.parse("mark 5", tasks); command.execute(tasks, ui);});
        assertEquals("The index provided is out of bounds for the task list. Index provided: 5", e.getMessage());
    }

    @Test
    void parse_unmarkCommandWithValidIndex_returnsUnmarkedTask() throws FishmanException {
        tasks.addTask(new ToDo("Sample Task", false));
        Command command = Parser.parse("unmark 1", tasks);
        assertInstanceOf(MarkCommand.class, command);
        assertFalse(tasks.getTask(0).getTaskStatus());
    }

    @Test
    void parse_TodoCommand_returnsTodoTask() throws FishmanException {
        Command command = Parser.parse("todo Sample Task", tasks);
        command.execute(tasks, ui);
        assertEquals(1, tasks.size());
        assertEquals("Sample Task", tasks.getTask(0).getTaskDescription());
        assertInstanceOf(ToDo.class, tasks.getTask(0));
    }

    @Test
    void parse_deadlineCommand_returnsDeadlineTask() throws FishmanException {
        Command command = Parser.parse("deadline Sample Task /by 2024-09-01 2359", tasks);
        command.execute(tasks, ui);
        assertEquals(1, tasks.size());
        assertInstanceOf(Deadline.class, tasks.getTask(0));
        assertEquals("Sample Task", tasks.getTask(0).getTaskDescription());
    }


    @Test
    void parse_deadlineCommandMissingByDate_throwsMissingArgumentException() {
        FishmanException e = assertThrows(FishmanException.MissingArgumentException.class,
                () -> Parser.parse("deadline Sample Task", tasks));
        assertEquals("Deadline command requires a description or a /by date", e.getMessage());
    }

    @Test
    void parse_eventCommand_returnsEventTask() throws FishmanException {
        Command command = Parser.parse("event Sample Task /from 2024-09-01 1400 /to 2024-09-01 1600", tasks);
        command.execute(tasks, ui);
        assertEquals(1, tasks.size());
        assertInstanceOf(Event.class, tasks.getTask(0));
        assertEquals("Sample Task", tasks.getTask(0).getTaskDescription());
    }

    @Test
    void parse_eventCommandMissingFromOrToDate_throwsMissingArgumentException() {
        FishmanException e = assertThrows(FishmanException.MissingArgumentException.class,
                () -> Parser.parse("event Sample Task /from 2024-09-01 1400", tasks));
        assertEquals("Event command requires a description or /from and /to dates", e.getMessage());
    }

    @Test
    void parse_deleteCommandWithValidIndex_returnsDeletedTask() throws FishmanException {
        tasks.addTask(new ToDo("Sample Task", false));
        Command command = Parser.parse("delete 1", tasks);
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void parse_deleteCommandWithInvalidIndex_throwsIndexOutOfBoundsException() {
        FishmanException e = assertThrows(FishmanException.IndexOutOfBoundsException.class,
                () -> Parser.parse("delete 1", tasks));
        assertEquals("The index provided is out of bounds for the task list. Index provided: 1", e.getMessage());
    }

    @Test
    void parse_invalidCommand_throwsInvalidCommandException() {
        FishmanException e = assertThrows(FishmanException.InvalidCommandException.class,
                () -> Parser.parse("invalidCommand", tasks));
        assertEquals("Please enter a valid command such as 'list' or 'bye' :(", e.getMessage());
    }

    @Test
    void parse_emptyInput_throwsInvalidCommandException() {
        FishmanException e = assertThrows(FishmanException.InvalidCommandException.class,
                () -> Parser.parse("", tasks));
        assertEquals("Please enter a valid command such as 'list' or 'bye' :(", e.getMessage());
    }

    @Test
    void parse_nullInput_throwsInvalidCommandException() {
        FishmanException e = assertThrows(FishmanException.InvalidCommandException.class,
                () -> Parser.parse(null, tasks));
        assertEquals("Please enter a valid command such as 'list' or 'bye' :(", e.getMessage());
    }

    @Test
    void parse_invalidDateFormat_throwsInvalidDateFormatException() {
        FishmanException e = assertThrows(FishmanException.InvalidDateFormatException.class,
                () -> Parser.parse("deadline Submit assignment /by 01-09-2024 23:59", tasks));
        assertEquals("Invalid Date format here: 01-09-2024 23:59", e.getMessage());
    }
}

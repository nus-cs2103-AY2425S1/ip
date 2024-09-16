package thanos.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import thanos.exceptions.InvalidCommandException;
import thanos.stubs.StorageStub;
import thanos.tasks.TaskList;
import thanos.tasks.Todo;

public class UnmarkCommandTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new StorageStub());
    }

    @Test
    public void execute_noIndexProvided_throwsInvalidCommandException() {
        UnmarkCommand command = new UnmarkCommand("");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }

    @Test
    public void execute_multipleArgumentsProvided_throwsInvalidCommandException() {
        UnmarkCommand command = new UnmarkCommand("1 2");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }

    @Test
    public void execute_validTaskIndex_unmarkTaskSuccess() throws InvalidCommandException {
        Todo todo1 = new Todo("read book");
        Todo todo2 = new Todo("write code");
        taskList.add(todo1);
        taskList.add(todo2);
        taskList.mark(1);
        UnmarkCommand command = new UnmarkCommand("2");
        String result = command.execute(taskList);

        String expected = String.format("OK, I've marked this task as not done yet:\n  %s\n", todo2);
        assertEquals(expected, result);
        assertEquals(todo2.toString(), taskList.getTaskList().get(1).toString());
    }

    @Test
    public void execute_nonIntegerTaskIndex_throwsInvalidCommandException() {
        UnmarkCommand command = new UnmarkCommand("one");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }

    @Test
    public void execute_outOfBoundsTaskIndex_throwsInvalidCommandException() {
        taskList.add(new Todo("read book"));
        UnmarkCommand command = new UnmarkCommand("2");
        assertThrows(InvalidCommandException.class, () -> command.execute(taskList));
    }
}

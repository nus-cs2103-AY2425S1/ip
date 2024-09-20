package Johnson.command;

import Johnson.task.TaskList;
import Johnson.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnmarkCommandTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        Command.setTaskList(taskList);
    }

    @Test
    public void testExecuteCommandWithValidIndex() {
        ToDo task = new ToDo("read book");
        task.setTaskStatus(true);
        taskList.addTask(task);
        UnmarkCommand unmarkCommand = new UnmarkCommand(1);
        String result = unmarkCommand.executeCommand();
        assertTrue(result.contains("Damn, must've missed. Watch out for this one:"));
        assertTrue(result.contains("1:  [T][ ] read book"));
        assertFalse(taskList.getTask(0).getTaskStatus());
    }

    @Test
    public void testExecuteCommandWithInvalidIndex() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(1);
        String result = unmarkCommand.executeCommand();
        assertTrue(result.contains("You're reaching for the stars, Chief. That task doesn't exist!"));
    }

    @Test
    public void testExecuteCommandWithNegativeIndex() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(-1);
        String result = unmarkCommand.executeCommand();
        assertTrue(result.contains("Hit your head, Chief? Gotta give me an index above 0!"));
    }
}
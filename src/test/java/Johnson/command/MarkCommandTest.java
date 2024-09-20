package Johnson.command;

import Johnson.task.TaskList;
import Johnson.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarkCommandTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        Command.setTaskList(taskList);
    }

    @Test
    public void testExecuteCommandWithValidIndex() {
        taskList.addTask(new ToDo("read book"));
        MarkCommand markCommand = new MarkCommand(1);
        String result = markCommand.executeCommand();
        assertTrue(result.contains("Bullseye! Bogey down:"));
        assertTrue(result.contains("1 :[T][X] read book"));
        assertTrue(taskList.getTask(0).getTaskStatus());
    }

    @Test
    public void testExecuteCommandWithInvalidIndex() {
        MarkCommand markCommand = new MarkCommand(1);
        String result = markCommand.executeCommand();
        assertTrue(result.contains("You're reaching for the stars, Chief. That task doesn't exist!"));
    }

    @Test
    public void testExecuteCommandWithNegativeIndex() {
        MarkCommand markCommand = new MarkCommand(-1);
        String result = markCommand.executeCommand();
        assertTrue(result.contains("Hit your head, Chief? Gotta give me an index above 0!"));
    }
}
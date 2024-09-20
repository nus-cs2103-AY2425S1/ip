package Johnson.command;

import Johnson.task.TaskList;
import Johnson.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListCommandTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        Command.setTaskList(taskList);
    }

    @Test
    public void testExecuteCommandWithNoTasks() {
        ListCommand listCommand = new ListCommand();
        String result = listCommand.executeCommand();
        assertTrue(result.contains("Damn, party's over already? Coast is clear, Chief."));
    }

    @Test
    public void testExecuteCommandWithTasks() {
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("write report"));

        ListCommand listCommand = new ListCommand();
        String result = listCommand.executeCommand();

        assertTrue(result.contains("1. [T][ ] read book"));
        assertTrue(result.contains("2. [T][ ] write report"));
    }
}
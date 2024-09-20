package Johnson.command;

import Johnson.task.TaskList;
import Johnson.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommandTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        Command.setTaskList(taskList);
    }

    @Test
    public void testExecuteCommandWithValidIndex() {
        taskList.addTask(new ToDo("read book"));
        DeleteCommand deleteCommand = new DeleteCommand(1);
        String result = deleteCommand.executeCommand();
        assertTrue(result.contains("Roger that! Taking out the trash:"));
        assertTrue(result.contains("1: [T][ ] read book"));
        assertEquals(0, taskList.taskCount());
    }

    @Test
    public void testExecuteCommandWithInvalidIndex() {
        DeleteCommand deleteCommand = new DeleteCommand(1);
        String result = deleteCommand.executeCommand();
        assertTrue(result.contains("You're reaching for the stars, Chief. That task doesn't exist!"));
    }

    @Test
    public void testExecuteCommandWithNegativeIndex() {
        DeleteCommand deleteCommand = new DeleteCommand(-1);
        String result = deleteCommand.executeCommand();
        assertTrue(result.contains("Hit your head, Chief? Gotta give me an index above 0!"));
    }
}
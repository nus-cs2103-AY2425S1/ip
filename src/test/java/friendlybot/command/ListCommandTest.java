package friendlybot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import friendlybot.FriendlyBotStub;
import friendlybot.task.TaskList;
import friendlybot.task.ToDo;

/**
 * A JUnit test for ListCommand.
 * Used GitHub CoPilot to assist with generating test cases for ListCommand.
 */
public class ListCommandTest {
    private FriendlyBotStub friendlyBotStub;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        friendlyBotStub = new FriendlyBotStub();
        taskList = friendlyBotStub.getTasks();
    }

    /**
     * Tests executing ListCommand with tasks in the list.
     */
    @Test
    public void testExecuteWithTasks() {
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("write code"));

        Command listCommand = new ListCommand();
        String response = listCommand.execute(taskList, friendlyBotStub.getUi(), friendlyBotStub.getStorage());

        assertTrue(response.contains("Here are the tasks in your list:"));
        assertTrue(response.contains("1.[T][ ] read book"));
        assertTrue(response.contains("2.[T][ ] write code"));
    }

    /**
     * Tests executing ListCommand with no tasks in the list.
     */
    @Test
    public void testExecuteWithNoTasks() {
        Command listCommand = new ListCommand();
        String response = listCommand.execute(taskList, friendlyBotStub.getUi(), friendlyBotStub.getStorage());

        assertEquals("You do not have any tasks in your list!", response);
    }
}

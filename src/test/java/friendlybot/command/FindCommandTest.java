package friendlybot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import friendlybot.FriendlyBotStub;
import friendlybot.task.Task;
import friendlybot.task.ToDo;

/**
 * A JUnit test for FindCommand.
 * Used GitHub CoPilot to assist in generating test cases for FindCommand.
 */
public class FindCommandTest {
    private FriendlyBotStub friendlyBotStub;

    @BeforeEach
    public void setUp() {
        friendlyBotStub = new FriendlyBotStub();
        friendlyBotStub.getTasks().addTask(new ToDo("read book"));
        friendlyBotStub.getTasks().addTask(new ToDo("write code"));
        friendlyBotStub.getTasks().addTask(new ToDo("book flight"));
    }

    /**
     * Tests finding tasks with a specific keyword.
     */
    @Test
    public void testFindCommandWithKeyword() {
        Command findCommand = new FindCommand("book");
        String response = findCommand.execute(friendlyBotStub.getTasks(), friendlyBotStub.getUi(),
                friendlyBotStub.getStorage());
        List<Task> relatedTasks = friendlyBotStub.getTasks().findTasks("book");

        assertEquals(2, relatedTasks.size());
        assertTrue(response.contains("read book"));
        assertTrue(response.contains("book flight"));
    }

    /**
     * Tests finding tasks with a keyword that has no matches.
     */
    @Test
    public void testFindCommandWithNoMatches() {
        Command findCommand = new FindCommand("exercise");
        String response = findCommand.execute(friendlyBotStub.getTasks(), friendlyBotStub.getUi(),
                friendlyBotStub.getStorage());

        assertEquals("There are no matching tasks in your list!", response);
    }

    /**
     * Tests the empty constructor of FindCommand.
     */
    @Test
    public void testFindCommandEmptyConstructor() {
        FindCommand findCommand = new FindCommand();
        assertNull(findCommand.getKeyword());
    }
}

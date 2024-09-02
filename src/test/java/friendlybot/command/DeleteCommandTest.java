package friendlybot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import friendlybot.FriendlyBotStub;
import friendlybot.task.ToDo;

/**
 * A JUnit test for DeleteCommand.
 */
public class DeleteCommandTest {
    /**
     * Tests deleting a Task.
     */
    @Test
    public void testDeleteCommand() {
        Command deleteCommand = new DeleteCommand(1);
        FriendlyBotStub friendlyBotStub = new FriendlyBotStub();
        friendlyBotStub.getTasks().addTask(new ToDo("test task"));
        assertEquals(1, friendlyBotStub.getTasks().getNumTasks());
        deleteCommand.execute(friendlyBotStub.getTasks(), friendlyBotStub.getUi(), friendlyBotStub.getStorage());
        assertEquals(0, friendlyBotStub.getTasks().getNumTasks());
    }
}

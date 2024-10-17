package friendlybot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import friendlybot.FriendlyBotStub;
import friendlybot.task.ToDo;

/**
 * A JUnit test for MarkCommand.
 */
public class MarkCommandTest {
    /**
     * Tests marking and unmarking a Task.
     */
    @Test
    public void testMarkCommand() {
        Command markCommand = new MarkCommand(true, 1);
        FriendlyBotStub friendlyBotStub = new FriendlyBotStub();
        friendlyBotStub.getTasks().addTask(new ToDo("test task"));
        markCommand.execute(friendlyBotStub.getTasks(), friendlyBotStub.getUi(), friendlyBotStub.getStorage());
        assertEquals("X", friendlyBotStub.getTasks().getTask(1).getStatusIcon());
        Command unmarkCommand = new MarkCommand(false, 1);
        unmarkCommand.execute(friendlyBotStub.getTasks(), friendlyBotStub.getUi(), friendlyBotStub.getStorage());
        assertEquals(" ", friendlyBotStub.getTasks().getTask(1).getStatusIcon());
    }
}

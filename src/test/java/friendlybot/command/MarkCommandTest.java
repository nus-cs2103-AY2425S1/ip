package friendlybot.command;

import friendlybot.FriendlyBot;
import friendlybot.FriendlyBotStub;
import friendlybot.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {
    @Test
    public void testMarkCommand() {
        Command markCommand = new MarkCommand(true, 1);
        FriendlyBotStub friendlyBotStub = new FriendlyBotStub();
        friendlyBotStub.tasks.addTask(new ToDo("test task"));
        markCommand.execute(friendlyBotStub.tasks, friendlyBotStub.ui, friendlyBotStub.storage);
        assertEquals("X", friendlyBotStub.tasks.getTask(1).getStatusIcon());
        Command unmarkCommand = new MarkCommand(false, 1);
        unmarkCommand.execute(friendlyBotStub.tasks, friendlyBotStub.ui, friendlyBotStub.storage);
        assertEquals(" ", friendlyBotStub.tasks.getTask(1).getStatusIcon());
    }
}

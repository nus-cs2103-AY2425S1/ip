package friendlybot.command;

import friendlybot.FriendlyBotStub;
import friendlybot.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void testDeleteCommand() {
        Command deleteCommand = new DeleteCommand(1);
        FriendlyBotStub friendlyBotStub = new FriendlyBotStub();
        friendlyBotStub.tasks.addTask(new ToDo("test task"));
        assertEquals(1, friendlyBotStub.tasks.getNumTasks());
        deleteCommand.execute(friendlyBotStub.tasks, friendlyBotStub.ui, friendlyBotStub.storage);
        assertEquals(0, friendlyBotStub.tasks.getNumTasks());
    }
}

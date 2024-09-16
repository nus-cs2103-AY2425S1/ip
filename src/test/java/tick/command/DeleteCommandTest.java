package tick.command;

import org.junit.jupiter.api.Test;

import tick.commands.DeleteCommand;
import tick.exceptions.TickException;
import tick.storage.Storage;
import tick.storage.TaskList;
import tick.tasks.ToDo;
import tick.ui.Ui;

public class DeleteCommandTest {
    @Test
    public void testDelete() {
        TaskList taskList = new TaskList(new ToDo("test"));
        DeleteCommand deleteCommand = new DeleteCommand(0);
        try {
            deleteCommand.execute(taskList, new Ui(), new Storage("data/test.txt"));
        } catch (TickException e) {
            assert false : "Exception thrown when it should not have been thrown.";
        }
        assert taskList.getSize() == 0 : "Task was not deleted.";
    }
}

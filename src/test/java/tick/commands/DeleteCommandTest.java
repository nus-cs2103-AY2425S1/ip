package tick.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import tick.exceptions.TickException;
import tick.storage.Storage;
import tick.storage.TaskList;
import tick.tasks.ToDo;
import tick.ui.Ui;

public class DeleteCommandTest {
    @Test
    public void executeDelete_correctInput() {
        try {
            Storage storage = new Storage("data/test.txt");
            TaskList taskList = new TaskList(storage.loadData());
            taskList.addTask(new ToDo("homework"));
            DeleteCommand deleteCommand = new DeleteCommand(0);
            deleteCommand.execute(taskList, new Ui(), new Storage("data/test.txt"));
            assertEquals(0, taskList.getSize());
        } catch (TickException | IOException e) {
            fail("Exception thrown when it should not have been thrown.");
        }
    }
}

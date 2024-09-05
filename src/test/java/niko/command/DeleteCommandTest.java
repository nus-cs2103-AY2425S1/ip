package niko.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import niko.main.Storage;
import niko.main.Ui;
import niko.task.Task;
import niko.task.TaskList;
import niko.task.Todo;




class DeleteCommandTest {

    @Test
    void execute_deletesTaskAndUpdatesStorage() throws Exception {
        Task task = new Todo("Test Task");
        TaskList taskList = new TaskList();
        taskList.addTask(task);

        Ui ui = new Ui();
        Storage storage = new Storage("test_storage.txt");

        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.execute(taskList, ui, storage);

        assertEquals(0, taskList.getTaskCount());

    }
}

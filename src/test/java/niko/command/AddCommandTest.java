package niko.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import niko.main.Storage;
import niko.main.Ui;
import niko.task.Task;
import niko.task.TaskList;
import niko.task.Todo;





class AddCommandTest {

    @Test
    void execute_addsTaskAndUpdatesStorage() throws Exception {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test_storage.txt");

        Task task = new Todo("Test Task");
        AddCommand addCommand = new AddCommand(task);
        addCommand.execute(taskList, ui, storage);

        assertEquals(1, taskList.getTaskCount());
        assertEquals(task, taskList.getTasks().get(0));


    }
}

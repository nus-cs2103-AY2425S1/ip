package friday.command;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import friday.task.Task;
import friday.task.TaskList;
import friday.task.Todo;
import friday.util.Storage;
import friday.util.Ui;

public class DeleteCommandTest {

    @Test
    public void execute_taskDeletedSuccessfully() throws IOException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        Task task = new Todo("Read book");
        tasks.addTask(task);
        String[] inputs = { "delete", "1" };
        Command deleteCommand = new DeleteCommand(inputs); // Assuming task index starts at 1
        deleteCommand.execute(tasks, ui, storage);

        Assertions.assertEquals(0, tasks.getTasks().size());
    }
}

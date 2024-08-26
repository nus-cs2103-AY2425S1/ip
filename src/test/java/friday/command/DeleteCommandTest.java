package friday.command;

import friday.task.Task;
import friday.task.TaskList;
import friday.task.Todo;
import friday.util.Storage;
import friday.util.Ui;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(0, tasks.getTasks().size());
    }
}

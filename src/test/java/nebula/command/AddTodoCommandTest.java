package nebula.command;

import nebula.exception.NebulaException;
import nebula.task.TaskList;
import nebula.storage.Storage;
import nebula.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddTodoCommandTest {

    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage("data/tasks.txt");

    @Test
    public void addTodo_success() {
        // Create an AddTodoCommand with a description for a task
        AddTodoCommand command = new AddTodoCommand("todo laundry");

        // Execute the command and capture any outputs (if the command returns anything)
        command.execute(tasks, ui, storage);

        // Assert that the task was added to the task list
        assertEquals(1, TaskList.getTaskListLength()); // Check that one task was added
        assertEquals("laundry", tasks.getTask(0)); // Verify the task description is correct
    }
}

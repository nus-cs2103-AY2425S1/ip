package bob.command;

import bob.exception.BobException;
import bob.storage.Storage;
import bob.tasks.Task;
import bob.tasks.Todo;
import bob.ui.Ui;
import bob.tasklist.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteCommandTest {

    private ArrayList<Task> tasks;
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
        storage = new Storage("./data/test_bob.csv");
        ui = new Ui();
        taskList = new TaskList(tasks);
    }

    @Test
    void execute_validIndex_taskDeletedSuccessfully() throws BobException, IOException {
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        tasks.add(task1);
        tasks.add(task2);
        storage.save(tasks);
        DeleteCommand deleteCommand = new DeleteCommand(0);


        deleteCommand.execute(taskList.getTasks(), storage, ui);

        assertEquals(1, tasks.size(), "TaskList size should be 1 after deletion");
        assertEquals(task2.getDescription(), tasks.get(0).getDescription(), "Remaining task: 'Task 2'");

        ArrayList<Task> reloadedTasks = storage.load();
        assertEquals(1, reloadedTasks.size(), "Reloaded TaskList size should be 1");
        assertEquals(task2.getDescription(), reloadedTasks.get(0).getDescription(), "Reloaded task: 'Task 2'");
    }

    @Test
    void execute_invalidIndex_throwsException() {
        Task task1 = new Todo("Task 1");
        tasks.add(task1);
        DeleteCommand deleteCommand = new DeleteCommand(1);

        BobException exception = assertThrows(BobException.class, () -> {
            deleteCommand.execute(taskList.getTasks(), storage, ui);
        });

        assertEquals("Invalid index", exception.getMessage(), "Exception message should match");
        assertEquals(1, tasks.size(), "TaskList size should remain 1 after failed deletion");
    }
}

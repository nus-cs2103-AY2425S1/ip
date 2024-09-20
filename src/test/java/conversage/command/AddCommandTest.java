package conversage.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.task.ToDo;
import conversage.ui.Ui;


class AddCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("data/conversage.txt");
    }

    @Test
    void execute_addToDo_success() throws ConverSageException {
        ToDo todo = new ToDo("test task");
        Command command = new AddCommand(todo);
        command.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("[T][ ] test task", tasks.getTask(0).toString());
    }

    @Test
    void execute_addMultipleTasks_success() throws ConverSageException {
        ToDo todo1 = new ToDo("task 1");
        ToDo todo2 = new ToDo("task 2");
        Command command1 = new AddCommand(todo1);
        Command command2 = new AddCommand(todo2);
        command1.execute(tasks, ui, storage);
        command2.execute(tasks, ui, storage);

        assertEquals(2, tasks.size());
        assertEquals("[T][ ] task 1", tasks.getTask(0).toString());
        assertEquals("[T][ ] task 2", tasks.getTask(1).toString());
    }

    @Test
    void execute_addEmptyTask_success() throws ConverSageException {
        ToDo todo = new ToDo("");
        Command command = new AddCommand(todo);
        command.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("[T][ ] ", tasks.getTask(0).toString());
    }

    @Test
    void execute_addDuplicateTask_success() throws ConverSageException {
        ToDo todo1 = new ToDo("duplicate task");
        ToDo todo2 = new ToDo("duplicate task");
        Command command1 = new AddCommand(todo1);
        Command command2 = new AddCommand(todo2);
        command1.execute(tasks, ui, storage);
        command2.execute(tasks, ui, storage);

        assertEquals(2, tasks.size());
        assertEquals("[T][ ] duplicate task", tasks.getTask(0).toString());
        assertEquals("[T][ ] duplicate task", tasks.getTask(1).toString());
    }

    @Test
    void execute_addTaskWithSpecialCharacters_success() throws ConverSageException {
        ToDo todo = new ToDo("task with special characters: !@#$%^&*()");
        Command command = new AddCommand(todo);
        command.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("[T][ ] task with special characters: !@#$%^&*()", tasks.getTask(0).toString());
    }

    @Test
    void execute_addTaskWithLongDescription_success() throws ConverSageException {
        String longDescription = "a".repeat(1000);
        ToDo todo = new ToDo(longDescription);
        Command command = new AddCommand(todo);
        command.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("[T][ ] " + longDescription, tasks.getTask(0).toString());
    }
}

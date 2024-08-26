package wolfie.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wolfie.exception.WolfieException;
import wolfie.task.TaskList;
import wolfie.util.Storage;
import wolfie.util.Ui;

class AddTodoCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("data/testTasks.txt");
    }

    @Test
    void testExecute_addTodo() throws IOException, WolfieException {
        AddTodoCommand command = new AddTodoCommand("Test task");
        command.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("Test task", tasks.get(0).getDescription());
    }
}

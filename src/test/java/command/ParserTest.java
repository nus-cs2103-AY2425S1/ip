
package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import storage.Storage;
import task.ToDo;
import task.Task;
import task.TaskList;




public class ParserTest {

    private Parser parser;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
        taskList = new TaskList();
        storage = new Storage("./data/chatterbox.txt");
        ui = new Ui();
    }

    @Test
    public void testMarkCommand() {
        Task task = new ToDo("sample task");
        taskList.addTask(task);
        parser.parseExecute("mark 1", taskList, storage, ui);
        assertEquals(true, task.isDone());
    }

    @Test
    public void testUnmarkCommand() {
        Task task = new ToDo("sample task");
        taskList.addTask(task);
        parser.parseExecute("unmark 1", taskList, storage, ui);
        assertEquals(false, task.isDone());
    }

    @Test
    public void testTodoCommand() {
        parser.parseExecute("todo Buy milk", taskList, storage, ui);
        assertEquals(1, taskList.size());
        Task task = taskList.getTask(0);
        assertEquals("Buy milk", task.getDescription());
    }

    public void testDeleteCommand() {
        Task task = new ToDo("Sample task");
        taskList.addTask(task);

        parser.parseExecute("delete 1", taskList, storage, ui);

        assertEquals(0, taskList.size());  // Task list should be empty after deletion
    }

}

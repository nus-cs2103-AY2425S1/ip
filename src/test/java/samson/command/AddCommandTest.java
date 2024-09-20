package samson.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import samson.task.TaskList;
import samson.task.ToDo;
import samson.Ui;
import samson.Storage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("./data/testfile.txt");
    }

    @Test
    public void execute_addToDo_success() throws Exception {
        ToDo todo = new ToDo("Write JUnit tests");
        AddCommand addCommand = new AddCommand(todo);

        addCommand.execute(taskList, ui, storage);

        assertEquals(1, taskList.size());
        assertEquals("Write JUnit tests", taskList.get(0).getDescription());
        assertFalse(taskList.get(0).isDone());
    }
}


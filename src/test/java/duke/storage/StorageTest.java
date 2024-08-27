package duke.storage;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void save_tasksSavedCorrectly() throws IOException {
        Storage storage = new Storage("data/test_tasks.txt");
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("Sample task"));
        storage.save(taskList.getTasks());
        TaskList loadedTaskList = new TaskList(storage.load());
        assertEquals(1, loadedTaskList.size(), "The saved task should be loaded correctly.");
    }
}


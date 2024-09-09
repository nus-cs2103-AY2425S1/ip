package alice.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import alice.storage.Storage;
import alice.storage.TaskList;
import alice.task.InvalidTaskException;
import alice.task.Task;
import alice.task.ToDo;

public class DeleteTaskTest {
    private static final String DATA_DIRECTORY_PATH = "./data";
    private static final String TASKS_FILE_NAME = "test.jsonl";

    @Test
    public void deleteTaskTest() {
        Storage storage = new Storage(DATA_DIRECTORY_PATH, TASKS_FILE_NAME);
        TaskList taskList = new TaskList(storage);
        taskList.getTasks().clear();
        try {
            Task task = new ToDo("todo abc");
            taskList.addTask(task);
        } catch (IOException | InvalidTaskException e) {
            fail("TaskList error.");
        }
        // before
        boolean isEmpty = taskList.getTasks().isEmpty();
        boolean expected = false;
        assertEquals(expected, isEmpty);
        // after
        String input = "delete 1";
        Command command = Command.fromInput(input, taskList);
        try {
            command.execute(input);
        } catch (Exception e) {
            fail();
        }
        isEmpty = taskList.getTasks().isEmpty();
        expected = true;
        assertEquals(expected, isEmpty);
    }

    @Test
    public void deleteInvalidTaskTest() {
        Storage storage = new Storage(DATA_DIRECTORY_PATH, TASKS_FILE_NAME);
        TaskList taskList = new TaskList(storage);
        taskList.getTasks().clear();
        String input = "delete 1";
        Command command = Command.fromInput(input, taskList);
        assertThrowsExactly(AssertionError.class, () -> command.execute(input));
    }
}

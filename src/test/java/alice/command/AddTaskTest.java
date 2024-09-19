package alice.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import alice.storage.Storage;
import alice.storage.TaskList;

public class AddTaskTest {
    private static final String DATA_DIRECTORY_PATH = "./data";
    private static final String TASKS_FILE_NAME = "test.jsonl";

    @Test
    public void addToDoTest() {
        Storage storage = new Storage(DATA_DIRECTORY_PATH, TASKS_FILE_NAME);
        TaskList taskList = new TaskList(storage);
        taskList.getTasks().clear();
        String input = "todo abc";
        Command command = Command.fromInput(input, taskList);
        try {
            command.execute(input);
        } catch (Exception e) {
            fail();
        }
        String taskString = taskList.getTasks().get(0).toString();
        String expected = "[T][ ] abc";
        assertEquals(expected, taskString);
    }

    @Test
    public void addDeadlineTest() {
        Storage storage = new Storage(DATA_DIRECTORY_PATH, TASKS_FILE_NAME);
        TaskList taskList = new TaskList(storage);
        taskList.getTasks().clear();
        String input = "deadline abc /by 2024-08-08 1800";
        Command command = Command.fromInput(input, taskList);
        try {
            command.execute(input);
        } catch (Exception e) {
            fail();
        }
        String taskString = taskList.getTasks().get(0).toString();
        String expected = "[D][ ] abc (by: 2024-08-08T18:00)";
        assertEquals(expected, taskString);
    }

    @Test
    public void addEventTest() {
        Storage storage = new Storage(DATA_DIRECTORY_PATH, TASKS_FILE_NAME);
        TaskList taskList = new TaskList(storage);
        taskList.getTasks().clear();
        String input = "event abc /from 2024-08-08 1800 /to 2024-08-08 1900";
        Command command = Command.fromInput(input, taskList);
        try {
            command.execute(input);
        } catch (Exception e) {
            fail();
        }
        String taskString = taskList.getTasks().get(0).toString();
        String expected = "[E][ ] abc (from: 2024-08-08T18:00, to: 2024-08-08T19:00)";
        assertEquals(expected, taskString);
    }
}

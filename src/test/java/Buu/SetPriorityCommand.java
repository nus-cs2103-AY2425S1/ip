package Buu;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SetPriorityCommandTest {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private Path tempFilePath;

    @BeforeEach
    void setUp() throws Exception {
        // Set up temporary file for storage
        tempFilePath = Files.createTempFile("test_tasks", ".txt");
        storage = new Storage(tempFilePath.toString());

        taskList = new TaskList();
        ui = new Ui();
        taskList.addTask(new ToDo("Test Task"));
    }
    @Test
    void invalidCommandFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SetPriorityCommand("priority 1"); // Missing priority value
        });
    }

    @Test
    void nonIntegerInputs_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SetPriorityCommand("priority one two"); // Non-integer inputs
        });
    }

    @Test
    void taskIndexOutOfBounds_throwsIllegalArgumentException() {
        // The task list only has one task (index 0), so index 1 is out of bounds
        SetPriorityCommand cmd = new SetPriorityCommand("priority 2 2"); // Index 1 (second task) doesn't exist

        // This should throw an IllegalArgumentException because the task index is out of bounds
        assertThrows(IllegalArgumentException.class, () -> cmd.execute(taskList, ui, storage));
    }


    @Test
    void priorityOutOfRange_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SetPriorityCommand("priority 1 4"); // Priority out of valid range (1-3)
        });
    }
}

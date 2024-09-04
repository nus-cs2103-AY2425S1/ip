package mira;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class StorageTest {
    private static final String TEST_DATA_FOLDER = "./src/test/data/StorageTest";

    /**
     * Test if createTaskFromFile throws MiraException with a specific message when unknown task type is found.
     */
    @Test
    public void testCreateTaskFromFile_unknownTaskType() {
        String filePath = TEST_DATA_FOLDER + "/UnknownTaskTypeData.txt";
        Storage storage = new Storage(filePath);

        MiraException exception = assertThrows(MiraException.class, () -> {
            storage.loadTasks();;
        });

        assertEquals("Invalid task type in file", exception.getMessage());
    }

    /**
     * Test if createTaskFromFile successfully loads Todo, Deadline, and Event tasks.
     */
    @Test
    public void testCreateTaskFromFile_successfulLoad() {
        String filePath = TEST_DATA_FOLDER + "/ValidData.txt";
        Storage storage = new Storage(filePath);
        try {
            ArrayList<Task> tasks = storage.loadTasks();
            StringBuilder result = new StringBuilder();
            for (Task task : tasks) {
                result.append(task.toFileString()).append("\n");
            }
            assertEquals("T | 1 | read book\n"
                    + "D | 0 | return book | 6/6/2019 1800\n"
                    + "E | 0 | project meeting | 6/8/2019 1400 | 6/8/2019 1600\n", result.toString());
        } catch (Exception e) {
            fail();
        }
    }
}

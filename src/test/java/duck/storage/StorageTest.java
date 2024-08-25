package duck.storage;

import duck.data.TaskList;
import duck.data.task.Task;
import duck.data.task.ToDo;
import duck.data.exception.DuckException;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the {@link Storage} class to verify file operations like writing and appending tasks.
 */
public class StorageTest {

    /**
     * Tests if the {@link Storage#writeTasks(TaskList)} method overwrites the file content.
     * The test is repeated 3 times to ensure consistency.
     *
     * @throws DuckException If there is an issue with the Duck-specific exception handling.
     * @throws IOException   If there is an issue with file operations.
     */
    @RepeatedTest(3)
    public void writeTasks_repeatedly_shouldOverWrite() throws DuckException, IOException {
        String testFilePath = "data/test.txt";
        File testFile = new File(testFilePath);

        // Initialize Storage and TaskList
        Storage storage = new Storage(testFilePath);
        TaskList tasks = new TaskList();

        // Create and add the first task
        Task task1 = new ToDo(false, "Walk the dog");
        tasks.add(task1);

        // Write the task to the file
        storage.writeTasks(tasks);

        // Verify that the file contains the first task
        try (Scanner sc = new Scanner(testFile)) {
            assertTrue(sc.hasNextLine());
            String line = sc.nextLine();
            assertEquals("T | 0 | Walk the dog", line);
        }

        // Create and add a second task
        Task task2 = new ToDo(true, "Read a book");
        tasks.add(task2);

        // Write both tasks to the file, overwriting the previous content
        storage.writeTasks(tasks);

        // Verify that the file contains the updated list of tasks
        try (Scanner sc = new Scanner(testFile)) {
            int lineCount = 0;

            // Check the first task
            assertTrue(sc.hasNextLine());
            lineCount++;
            String line1 = sc.nextLine();
            assertEquals("T | 0 | Walk the dog", line1);

            // Check the second task
            assertTrue(sc.hasNextLine());
            lineCount++;
            String line2 = sc.nextLine();
            assertEquals("T | 1 | Read a book", line2);

            // Ensure there are no additional lines
            while (sc.hasNextLine()) {
                sc.nextLine();
                lineCount++;
            }

            // Verify that the file contains exactly 2 lines (one for each task)
            assertEquals(2, lineCount);
        }
    }

    /**
     * Tests if the {@link Storage#appendTask(Task)} method correctly appends tasks to the file.
     * The test is repeated 3 times to verify that the append operation accumulates lines correctly.
     *
     * @param ri Information about the current repetition.
     * @throws DuckException If there is an issue with the Duck-specific exception handling.
     * @throws IOException   If there is an issue with file operations.
     */
    @RepeatedTest(3)
    public void appendTasks_repeatedly_lineCountShouldAccumulate(RepetitionInfo ri) throws DuckException, IOException {
        String testFilePath = "data/test.txt";
        File testFile = new File(testFilePath);

        int currentRep = ri.getCurrentRepetition();

        // Ensure that the test file starts fresh if it's the first repetition
        if (currentRep == 1 && testFile.exists()) {
            testFile.delete();
        }

        // Initialize Storage and TaskList
        Storage storage = new Storage(testFilePath);
        TaskList tasks = new TaskList();

        // Create and append the first task
        Task task1 = new ToDo(false, "Walk the dog");
        tasks.add(task1);
        storage.appendTask(task1);

        // Verify that the file contains the first task
        try (Scanner sc = new Scanner(testFile)) {
            assertTrue(sc.hasNextLine());
            String line = sc.nextLine();
            assertEquals("T | 0 | Walk the dog", line);
        }

        // Create and append a second task
        Task task2 = new ToDo(true, "Read a book");
        storage.appendTask(task2);

        // Verify that the file contains both tasks
        try (Scanner sc = new Scanner(testFile)) {
            int lineCount;

            // Skip lines from previous repetitions
            for (lineCount = 0; lineCount < (currentRep - 1) * 2; lineCount++) {
                assertTrue(sc.hasNextLine());
                sc.nextLine();
            }

            // Check the first task
            assertTrue(sc.hasNextLine());
            lineCount++;
            String line1 = sc.nextLine();
            assertEquals("T | 0 | Walk the dog", line1);

            // Check the second task
            lineCount++;
            assertTrue(sc.hasNextLine());
            String line2 = sc.nextLine();
            assertEquals("T | 1 | Read a book", line2);

            // Ensure there are no additional lines
            while (sc.hasNextLine()) {
                sc.nextLine();
                lineCount++;
            }

            // Verify that the line count matches the current repetition number
            assertEquals(currentRep * 2, lineCount);
        }
    }
}

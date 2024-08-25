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


public class StorageTest {
    @RepeatedTest(3)
    public void writeTasks_repeatedly_shouldOverWrite() throws DuckException, IOException {
        String testFilePath = "data/test.txt";
        File testFile = new File(testFilePath);

        Storage storage = new Storage(testFilePath);
        TaskList tasks = new TaskList();

        Task task1 = new ToDo(false, "Walk the dog");
        tasks.add(task1);

        storage.writeTasks(tasks);

        try (Scanner sc = new Scanner(testFile)) {
            assertTrue(sc.hasNextLine());
            String line = sc.nextLine();
            assertEquals("T | 0 | Walk the dog", line);

        }

        Task task2 = new ToDo(true, "Read a book");
        tasks.add(task2);

        storage.writeTasks(tasks);

        try (Scanner sc = new Scanner(testFile)) {
            int lineCount = 0;
            assertTrue(sc.hasNextLine());
            lineCount++;
            String line1 = sc.nextLine();
            assertEquals("T | 0 | Walk the dog", line1);

            assertTrue(sc.hasNextLine());
            lineCount++;
            String line2 = sc.nextLine();
            assertEquals("T | 1 | Read a book", line2);


            while (sc.hasNextLine()) {
                sc.nextLine();
                lineCount++;
            }

            // test that the file is overwritten instead of appended
            // by running test 3 times, without deleting file if exists
            assertEquals(2, lineCount);
        }

    }
    @RepeatedTest(3)
    public void appendTasks_repeatedly_lineCountShouldAccumulate(RepetitionInfo ri) throws DuckException, IOException {
        String testFilePath = "data/test.txt";
        File testFile = new File(testFilePath);

        int currentRep = ri.getCurrentRepetition();

        // ensure when user runs this test multiple times manually
        // it starts with a fresh testFile.
        if (currentRep == 1 && testFile.exists()) {
            testFile.delete();
        }


        Storage storage = new Storage(testFilePath);
        TaskList tasks = new TaskList();

        Task task1 = new ToDo(false, "Walk the dog");
        tasks.add(task1);

        storage.appendTask(task1);

        try (Scanner sc = new Scanner(testFile)) {
            assertTrue(sc.hasNextLine());
            String line = sc.nextLine();
            assertEquals("T | 0 | Walk the dog", line);
        }

        Task task2 = new ToDo(true, "Read a book");
        storage.appendTask(task2);

        try (Scanner sc = new Scanner(testFile)) {
            int lineCount;
            for (lineCount = 0; lineCount < (currentRep - 1) * 2; lineCount++) {
                assertTrue(sc.hasNextLine());
                sc.nextLine();
            }
            assertTrue(sc.hasNextLine());
            lineCount++;
            String line1 = sc.nextLine();
            assertEquals("T | 0 | Walk the dog", line1);

            lineCount++;
            assertTrue(sc.hasNextLine());
            String line2 = sc.nextLine();
            assertEquals("T | 1 | Read a book", line2);

            // test that if there are more lines in the file
            while (sc.hasNextLine()) {
                sc.nextLine();
                lineCount++;
            }

            // ensure that line count is correct as of current repetition
            assertEquals(currentRep * 2, lineCount);
        }
    }
}

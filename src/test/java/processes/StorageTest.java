package processes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;
import tasks.ToDo;
import tasks.DeadLine;
import tasks.Event;

import java.io.File;
import java.util.ArrayList;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    private Storage storage;
    private ArrayList<Task> taskList;

    @BeforeEach
    public void setUp() {
        String dirPath = "testDir";
        String filePath = "testDir/testTasks.txt";
        storage = new Storage(dirPath, filePath);
        taskList = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        File file = new File("testDir/testTasks.txt");
        if (file.exists()) {
            file.delete();
        }
        File dir = new File("testDir");
        if (dir.exists()) {
            dir.delete();
        }
    }

    @Test
    public void testLoadData_FileDoesNotExist() {
        storage.loadData(taskList);
        assertEquals(0, taskList.size());
    }

    @Test
    public void testSaveAndLoadData() throws Exception {
        storage.loadData(taskList);
        // Add tasks to taskList and save
        taskList.add(new ToDo("Task 1"));
        taskList.add(new DeadLine("Task 2 /by 2024-08-30"));
        taskList.add(new Event("Task 3 /from 2024-08-30 /to 2024-10-01"));

        storage.save(taskList);

        // Clear taskList and load data from file
        taskList.clear();
        storage.loadData(taskList);

        LocalDate start = LocalDate.parse("2024-08-30");
        LocalDate end = LocalDate.parse("2024-10-01");
        // Verify the tasks are correctly loaded
        System.out.println(taskList.size());
        assertEquals(3, taskList.size());
        assertEquals("[T][ ] Task 1", taskList.get(0).toString());
        assertEquals("[D][ ] Task 2" + " (by: " + start.toString() + ")", taskList.get(1).toString());
        assertEquals("[E][ ] Task 3" +
                " (from: " + start.toString() + " to: " + end.toString() + ")",
                taskList.get(2).toString());
    }
}

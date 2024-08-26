package lama;

import lama.task.Deadline;
import lama.task.Task;
import lama.task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    private static final String PATH = "./test.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        this.storage = new Storage(PATH);
    }

    @Test
    public void loadTaskTest() throws Exception {
        FileWriter fileWriter = new FileWriter(PATH);
        fileWriter.write("T | 0 | Read Book\n");
        fileWriter.write("D | 1 | Return Book | 2024-08-31\n");
        fileWriter.write("E | 0 | Project Meeting | 2024-08-31 1400 | 2024-08-31 1600\n");
        fileWriter.close();

        TaskList taskList = new TaskList(storage.loadTask());
        assertEquals(3, taskList.size());
        assertEquals("[T][ ] Read Book", taskList.get(0).toString());
        assertEquals("[D][X] Return Book (by: Aug 31 2024)", taskList.get(1).toString());
        assertEquals("[E][ ] Project Meeting (from: Aug 31 2024 14:00 to: Aug 31 2024 16:00)", taskList.get(2).toString());

    }

    @Test
    public void loadTaskInvalidDataSetTest() throws Exception {
        FileWriter fileWriter = new FileWriter(PATH);
        fileWriter.write("Invalid Data Set");
        fileWriter.close();

        assertThrows(LamaException.class, () -> storage.loadTask());
    }

    @Test
    public void saveTasksTest() throws Exception {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Read Book");
        Task deadline = new Deadline("Return Book", LocalDate.parse("2024-08-31"));
        taskList.add(todo);
        taskList.add(deadline);
        storage.saveTasks(taskList);
        TaskList tasks = new TaskList(storage.loadTask());
        assertEquals(2, tasks.size());
        assertEquals("[T][ ] Read Book", tasks.get(0).toString());
        assertEquals("[D][ ] Return Book (by: Aug 31 2024)", tasks.get(1).toString());
    }

    @Test
    public void addTaskTest() throws Exception {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Read Book");
        taskList.add(todo);
        storage.addTask(todo);

        TaskList tasks = new TaskList(storage.loadTask());
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] Read Book", tasks.get(0).toString());
    }

    @AfterEach
    public void reset() {
        try {
            Files.deleteIfExists(new File(PATH).toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

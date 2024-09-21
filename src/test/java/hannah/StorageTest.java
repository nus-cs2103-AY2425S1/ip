package hannah;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hannah.task.Deadline;
import hannah.task.TaskList;
import hannah.task.ToDos;
public class StorageTest {

    private Storage storage;

    @BeforeEach
    public void setUp() throws IOException {
        // Prepare a test file with sample tasks
        FileWriter writer = new FileWriter("src/data/HannahTest.txt");
        writer.write("T | 1 | read book\n");
        writer.write("D | 0 | return book | by 2023-10-10\n");
        writer.close();

        // Initialize the storage with the test file
        storage = new Storage("src/data/HannahTest.txt");
    }

    @Test
    public void testLoadFile() throws IOException {
        TaskList tasks = storage.loadFile();
        System.out.println("TESTING: ...");
        System.out.println(tasks.getTasks().toString());

        // Verify that the tasks are loaded correctly
        assertEquals(2, tasks.size());
        assertTrue(tasks.getTask(1) instanceof ToDos);
        assertEquals("read book", tasks.getTask(1).getName());
        assertTrue(tasks.getTask(1).isDone());

        // Verify the second task is a deadline task
        assertEquals("return book", tasks.getTask(2).getName());
        assertFalse(tasks.getTask(2).isDone());
        assertEquals("2023-10-10", ((Deadline) tasks.getTask(2)).getDeadline().toString());
    }
}

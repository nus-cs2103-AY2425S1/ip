package joe.main;

import joe.exceptions.CorruptedFileException;
import joe.tasks.TaskList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JoeTest {

    @Test
    public void testJoe_initialization_success() throws FileNotFoundException, CorruptedFileException {
        Joe joe = new Joe("data/test.txt");
        TaskList tasks = joe.getTasks();
        assertEquals(4, tasks.size());
        assertEquals("[T][X] read book", tasks.getTask(0).toString());
        assertEquals("[D][ ] return book (by: Dec/12/2019 18:00)", tasks.getTask(1).toString());
        assertEquals("[E][ ] project meeting (from: Dec/12/2019 18:00 to: Dec/12/2019 21:00)",
                tasks.getTask(2).toString());
        assertEquals("[T][X] join sports club", tasks.getTask(3).toString());
    }

    @Test
    public void testJoe_initialization_fileNotFound() {
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            new Joe("data/nonexistent.txt").initializeTasks();
        });
        assertEquals("java.io.FileNotFoundException: data\\nonexistent.txt "
                        + "(The system cannot find the file specified)",
                exception.toString());
    }

    @Test
    public void testJoe_initialization_corruptedFile() {
        Exception exception = assertThrows(CorruptedFileException.class, () -> {
            new Joe("data/corrupted.txt").initializeTasks();
        });
        assertEquals("joe.exceptions.CorruptedFileException: The file is corrupted", exception.toString());
    }
}

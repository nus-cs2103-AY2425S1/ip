package util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import MizzExceptions.InvalidDateException;
import MizzExceptions.MizzException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.nio.file.Path;
import java.util.List;

public class ParserTest {
    @TempDir
    Path tempDir;

    @Test
    public void testParseFromStorage_withValidData() {
        // Create a temporary file with valid entries
        Path filePath = tempDir.resolve("validData.txt");
        StorageStub storage = new StorageStub(filePath.toString(), new String[] {
                "[T][X] new task",
                "[D][ ] ddd (by: Aug 07 2024)",
                "[E][ ] eee (from: Aug 29 2024 to: Aug 30 2024)"
        });

        try {
            List<Task> tasks = Parser.parseFromStorage(storage);

            assertEquals(3, tasks.size());
            assertTrue(tasks.get(0) instanceof ToDo);
            assertTrue(tasks.get(1) instanceof Deadline);
            assertTrue(tasks.get(2) instanceof Event);

            ToDo todo = (ToDo) tasks.get(0);
            assertEquals("[T][X] new task", todo.toString());

            Deadline deadline = (Deadline) tasks.get(1);
            assertEquals("[D][ ] ddd (by: Aug 07 2024)", deadline.toString());

            Event event = (Event) tasks.get(2);
            assertEquals("[E][ ] eee (from: Aug 29 2024 to: Aug 30 2024)", event.toString());
        } catch (MizzException e) {
            fail("Exception should not have been thrown for valid data");
        }
    }

    @Test
    public void testParseFromStorage_withIncompleteEntry() {
        Path filePath = tempDir.resolve("incompleteEntry.txt");
        StorageStub storage = new StorageStub(filePath.toString(), new String[] {
                "[D][X] ddd ",
        });

        assertThrows(MizzException.class, () -> {
            Parser.parseFromStorage(storage);
        });
    }

    @Test
    public void testParseFromStorage_withInvalidDate() {
        Path filePath = tempDir.resolve("invalidDate.txt");
        StorageStub storage = new StorageStub(filePath.toString(), new String[] {
                "[D][X] ddd (by: InvalidDate)",
        });

        assertThrows(InvalidDateException.class, () -> {
            Parser.parseFromStorage(storage);
        });
    }

    @Test
    public void testParseStringInput_withValidData() {
        try {
            String[] value = Parser.parseStringInput("todo new task");
            assertArrayEquals(new String[] { "todo", "new task", null, null }, value);
        } catch (MizzException e) {
            fail("Should not have exception");
        }
    }

    @Test
    public void testParseStringInput_withInvalidData() {
        assertThrows(MizzException.class, () -> {
            Parser.parseStringInput("deadline /by");
        });
    }
}

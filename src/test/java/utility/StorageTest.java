package utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

class StorageTest {
    @Test
    public void load_emptyFile_emptyArrayList() throws IOException {
        Storage storage = new Storage("./src/test/java/utility/emptyFile.txt");
        assertEquals(new ArrayList<Task>(), storage.load());
    }

    @Test
    public void load_nonExistentFile_emptyArrayList() throws IOException {
        Storage storage = new Storage("some path");
        assertEquals(new ArrayList<Task>(), storage.load());
    }

    @Test
    public void load_nonEmptyFile_populatedArrayList() throws IOException {
        Storage storage = new Storage("./src/test/java/utility/nonEmptyFile.txt");
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Todo("some task"));
        expected.add(new DeadLine("some task", "Jan 01 2024", true));
        expected.add(new Event("some event", "Jan 01 2024", "Feb 01 2024"));
        ArrayList<Task> actual = storage.load();
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(true, expected.get(i).equals(actual.get(i)));
        }
    }
}

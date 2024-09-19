package storage;

import org.junit.jupiter.api.Test;

import rasputin.storage.Storage;
import rasputin.task.Task;
import rasputin.task.Todo;
import rasputin.task.Deadline;
import rasputin.task.Event;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    @Test
    public void testReadFile() {
        Storage storage = new Storage("data/storagetest.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        Deadline deadline1 = new Deadline("return book", "2024-09-29 1800");
        Event event = new Event("project meeting","2024-09-28 1400", "2024-09-28 1500");
        event.markAsDone();
        Todo todo = new Todo("read book");
        todo.markAsDone();
        Deadline deadline2 = new Deadline("update calendar", "2024-09-30");
        tasks.add(deadline1);
        tasks.add(event);
        tasks.add(todo);
        tasks.add(deadline2);
        assertEquals(tasks.toString(), storage.readFile().toString());
    }
}

import Task.Task;
import Task.TaskList;
import Task.ToDo;
import Task.Deadline;
import Task.Event;
import Utilities.Parser;
import Utilities.Storage;
import Utilities.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StorageTest {
    @Test
    public void testFormatTodo(){
        Storage storage = new Storage();
        Task task = new ToDo("test1");
        task.markDone();
        assertEquals("T | 1 | test1", storage.getString(task));
    }

    @Test
    public void testFormatDeadline(){
        Storage storage = new Storage();
        Parser parser = new Parser();
        Task task = new Deadline("test2", parser.parseDateTime("12/12/2000 1800"));
        assertEquals("D | 0 | test2 | 12/12/2000 1800", storage.getString(task));
    }

    @Test
    public void testFormatEvent(){
        Storage storage = new Storage();
        Parser parser = new Parser();
        Task task = new Event("test3", parser.parseDateTime("12/12/2000 1800"), parser.parseDateTime("12/12/2111 1800"));
        task.markDone();
        assertEquals("E | 1 | test3 | 12/12/2000 1800 | 12/12/2111 1800", storage.getString(task));
    }

}
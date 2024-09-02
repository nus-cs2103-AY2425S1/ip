package tissue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tissue.task.Deadline;
import tissue.task.Event;
import tissue.task.Task;
import tissue.task.ToDo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StorageTest {
    @Test
    public void test_event_conversion() {

        try {
            String path = "./test/data/";
            String fileName = "testEvent.csv";
            Files.deleteIfExists(Paths.get(path + fileName));
            Task event = new Event(true, "fly to Az", "20 Aug", "21 Aug");
            Storage testStorage = new Storage(path, fileName);
            testStorage.save(event);

            assertEquals(
                    new ArrayList<Task>(List.of(event)).toString(), testStorage.load().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_todo_conversion() {
        try {
            String path = "./test/data/";
            String fileName = "testToDo.csv";
            Files.deleteIfExists(Paths.get(path + fileName));

            Task toDo = new ToDo(true, "fly to Az");
            Storage testStorage = new Storage(path, fileName);
            testStorage.save(toDo);
            assertEquals(
                    new ArrayList<Task>(List.of(toDo)).toString(), testStorage.load().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_deadline_conversion() {
        try {
            String path = "./test/data/";
            String fileName = "testDeadline.csv";
            Files.deleteIfExists(Paths.get(path + fileName));
            Task deadline = new Deadline(true, "aaAz", "2021-12-03");
            Storage testStorage = new Storage(path, fileName);
            testStorage.save(deadline);

            assertEquals(
                    new ArrayList<Task>(List.of(deadline)).toString(),
                    testStorage.load().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

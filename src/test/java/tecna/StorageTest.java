package tecna;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    @Test
    public void load_taskListNotFound_exceptionThrown() {
        tecna.storage.Storage storage = new tecna.storage.Storage("data/tasklist_not_found.json");

        try {
            ArrayList<Task> tasks = new ArrayList<>();
            ToDo toDo = new ToDo("go to school", false);
            tasks.add(toDo);

            assertEquals(tasks, storage.load());

            fail();
        } catch (Exception e) {
            assertEquals("Cannot find the \"taskList\" array in the data file!", e.getMessage());
        }
    }

    @Test
    public void load_invalidTaskType_exceptionThrown() {
        tecna.storage.Storage storage = new tecna.storage.Storage("data/error_tecna.json");
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            ToDo toDo = new ToDo("borrow a book", true);
            tasks.add(toDo);

            assertEquals(tasks, storage.load());

            fail();
        } catch (Exception e) {
            assertEquals("Invalid Task type!", e.getMessage());
        }
    }

    @Test
    public void load_deadlineDataMissing_exceptionThrown() {
        tecna.storage.Storage storage = new tecna.storage.Storage("data/deadline_data_missing.json");
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            Deadline deadline = new Deadline("borrow a book", true, LocalDateTime.parse("2024-02-04 1700", pattern));
            tasks.add(deadline);

            assertEquals(tasks, storage.load());

            fail();
        } catch (Exception e) {
            assertEquals("There are missing values in the Deadline task(s) in the data file!", e.getMessage());
        }
    }
}

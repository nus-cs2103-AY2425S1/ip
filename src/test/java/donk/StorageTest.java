package donk;
import donk.task.Task;
import donk.task.TaskList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void load_success () throws FileNotFoundException {
        Storage storage = new Storage("./testSave.txt");
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.load();
        } catch(Exception e) {
            System.out.println("storage.load exception: " + e.getMessage());
        } finally {
            assertEquals(3, tasks.size());
        }

    }
}

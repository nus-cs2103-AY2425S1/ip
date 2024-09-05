package donk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import donk.task.Task;

public class StorageTest {

    @Test
    public void load_success() throws FileNotFoundException {
        Storage storage = new Storage("./testSave.txt");
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.load();
        } catch (Exception e) {
            System.out.println("storage.load exception: " + e.getMessage());
        } finally {
            assertEquals(3, tasks.size());
        }

    }
}

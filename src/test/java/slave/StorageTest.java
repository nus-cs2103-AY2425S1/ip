package slave;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import slave.task.Deadline;
import slave.task.Event;
import slave.task.RecurringTypeTask;
import slave.task.Task;
import slave.task.Todo;

public class StorageTest {
    @Test
    public void load1InvalidTest() {
        List<Task> list = new LinkedList<>();
        Storage storage = new Storage(list, "src/test/dataFiles/load_1_invalid.txt");
        storage.load();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void load3ValidTest() {
        List<Task> list = new LinkedList<>();
        Storage storage = new Storage(list, "./src/test/datafiles/load_3_valid.txt");
        storage.load();
        Assertions.assertEquals(3, list.size());
    }

    @Test
    public void load3Valid1InvalidTest() {
        List<Task> list = new LinkedList<>();
        Storage storage = new Storage(list, "./src/test/datafiles/load_3_valid_1_invalid.txt");
        storage.load();
        Assertions.assertEquals(3, list.size());
    }

    @Test
    public void saveAndLoadTest() {
        try {
            // reset the file to an empty file
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/test/datafiles/saveTest.txt"));
            writer.write("");
            writer.close();

            List<Task> list = new LinkedList<>();
            Storage storage = new Storage(list, "./src/test/datafiles/saveTest.txt");
            Todo todo = new Todo("fly", false);
            Deadline deadline = new Deadline("die", RecurringTypeTask.RecurringType.NEVER, LocalDate.parse("2022-02-02"));
            Event event = new Event("revive", RecurringTypeTask.RecurringType.NEVER, LocalDate.parse("2022-02-02"),
                    LocalDate.parse("2022-03-03"));
            storage.save();

            List<Task> newList = new LinkedList<>();
            Storage newStorage = new Storage(newList, "./src/test/datafiles/saveTest.txt");
            newStorage.load();
            Assertions.assertEquals(list, newList);
        } catch (IOException | InvalidChronologicalOrderException e) {
            System.out.println(e.getMessage());
        }

    }
}

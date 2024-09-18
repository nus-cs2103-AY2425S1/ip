package tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import exceptions.DelphiException;
import exceptions.HardDriveNotFoundException;
import storage.Storage;

public class TaskListTest {
    class StorageStub extends Storage {

        public StorageStub() {
            super("blank");
        }
        @Override
         public List<String> readFromHardDisk() throws HardDriveNotFoundException {
             List<String> res = new ArrayList<>();
             res.add("[D][ ] do hw (by: 30th august 2024, 4:00pm)");
             res.add("[T][ ] get food");
             res.add("[T][X] go for run");
             return res;
        }
    }
     private final TaskList testTaskList = new TaskList();
    @Test
    public void loadStorageToTasks_exceptionThrown() {
        try {
            StorageStub s = new StorageStub();
            testTaskList.loadStorageToTasks(s);

            assertEquals("[D][ ] do hw (by: 30th august 2024, 4:00pm)", testTaskList.getTask(1).toString());

            assertEquals("[T][ ] get food", testTaskList.getTask(2).toString());

            assertEquals("[T][X] go for run", testTaskList.getTask(3).toString());
        } catch (HardDriveNotFoundException e) {
            fail();
        }
    }

    @Test
    public void markTaskAsDone_exceptionThrown() {
        try {
            StorageStub s = new StorageStub();
            testTaskList.loadStorageToTasks(s);
            testTaskList.markTaskAsDone(2);
            assertEquals("[T][X] get food", testTaskList.getTask(2).toString());
            testTaskList.markTaskAsDone(5);
            fail();
        } catch (DelphiException e) {
            assertEquals("sorry, your task list doesn't have 5 items", e.getMessage());
        }
    }
}


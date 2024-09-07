package mortalreminder.backend;

import mortalreminder.backend.tasklistmanager.TaskList;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.tasks.ToDo;
import mortalreminder.tasks.ToDoStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    @Test
    public void loadTaskListFromFile_emptyFile() throws MortalReminderException {
        TaskList taskList = Storage.loadTaskListFromFile();
        assertEquals(0, taskList.getSize());
        Storage.clearListFile();
    }

    @Test
    public void loadTaskListFromFile_IOExceptionThrown_exceptionHandled() throws MortalReminderException {
        try {
            Storage.appendToListFile(new ToDoStub("Fake Task"));
            TaskList taskList = Storage.loadTaskListFromFile();
            fail();
            Storage.clearListFile();
        } catch (Exception e) {
            assertEquals("Corrupted storage file!", e.getMessage());
            Storage.clearListFile();
        }
    }

    @Test
    public void loadTaskListFromFile_success() throws MortalReminderException {
        Storage.appendToListFile(new ToDo("Fake task", true));
        Storage.appendToListFile(new ToDo("Fake task 2", true));
        TaskList taskList = Storage.loadTaskListFromFile();
        assertEquals(2, taskList.getSize());
        Storage.clearListFile();
    }

}

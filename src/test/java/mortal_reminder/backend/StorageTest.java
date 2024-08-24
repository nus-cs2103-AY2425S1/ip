package mortal_reminder.backend;

import mortal_reminder.tasks.ToDo;
import mortal_reminder.tasks.ToDoStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void loadTaskListFromFile_emptyFile() {
        TaskList taskList = Storage.loadTaskListFromFile();
        assertEquals(0, taskList.getSize());
        Storage.clearListFile();
    }

    @Test
    public void loadTaskListFromFile_IOExceptionThrown_exceptionHandled() {
        Storage.appendToListFile(new ToDoStub("Fake Task"));
        TaskList taskList = Storage.loadTaskListFromFile();
        assertEquals(0, taskList.getSize());
        Storage.clearListFile();
    }

    @Test
    public void loadTaskListFromFile_success() {
        Storage.appendToListFile(new ToDo("Fake task", true));
        Storage.appendToListFile(new ToDo("Fake task 2", true));
        TaskList taskList = Storage.loadTaskListFromFile();
        assertEquals(2, taskList.getSize());
        Storage.clearListFile();
    }

}

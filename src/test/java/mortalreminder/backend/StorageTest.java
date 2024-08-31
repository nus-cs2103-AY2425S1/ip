package mortalreminder.backend;

import java.io.IOException;

import mortalreminder.tasks.ToDo;
import mortalreminder.tasks.ToDoStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    @Test
    public void loadTaskListFromFile_emptyFile() {
        TaskList taskList = Storage.loadTaskListFromFile();
        assertEquals(0, taskList.getSize());
        Storage.clearListFile();
    }

    @Test
    public void loadTaskListFromFile_IOExceptionThrown_exceptionHandled() {
        try {
            Storage.appendToListFile(new ToDoStub("Fake Task"));
            TaskList taskList = Storage.loadTaskListFromFile();
            Storage.clearListFile();
            assertEquals(0, taskList.getSize());
        } catch (Exception e) {
            assertEquals(e.getClass(), IOException.class);
        }
    }

    @Test
    public void loadTaskListFromFile_success() throws IOException {
        Storage.appendToListFile(new ToDo("Fake task", true));
        Storage.appendToListFile(new ToDo("Fake task 2", true));
        TaskList taskList = Storage.loadTaskListFromFile();
        assertEquals(2, taskList.getSize());
        Storage.clearListFile();
    }

}

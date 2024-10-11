package mortalreminder.backend;

import mortalreminder.backend.tasklistmanager.TaskList;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.tasks.ToDo;
import mortalreminder.tasks.ToDoStub;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListStorageTest {

    @BeforeEach
    void setUp() throws MortalReminderException {
        TaskListStorage.clearListFile();
    }

    @AfterEach
    void tearDown() throws MortalReminderException {
        TaskListStorage.clearListFile();
    }

    @Test
    public void loadTaskListFromFile_emptyFile() throws MortalReminderException {
        TaskList taskList = TaskListStorage.loadTaskListFromFile();
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void loadTaskListFromFile_IOExceptionThrown_exceptionHandled() {
        try {
            TaskListStorage.appendToListFile(new ToDoStub("Fake Task"));
            TaskList taskList = TaskListStorage.loadTaskListFromFile();
            fail();
        } catch (Exception e) {
            assertEquals(MortalReminderException.getStorageFileCorruptedErrorMessage(), e.getMessage());
        }
    }

    @Test
    public void loadTaskListFromFile_success() throws MortalReminderException {
        TaskListStorage.appendToListFile(new ToDo("Fake task", true));
        TaskListStorage.appendToListFile(new ToDo("Fake task 2", true));
        TaskList taskList = TaskListStorage.loadTaskListFromFile();
        assertEquals(2, taskList.getSize());
    }

}

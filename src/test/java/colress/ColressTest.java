package colress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import colress.exception.FileCorruptedException;
import colress.storage.Storage;
import colress.tasklist.ColressTaskList;
import colress.tasklist.TaskList;
import colress.testutil.StorageStub;
import colress.testutil.TaskListStub;
import colress.testutil.UiStub;

public class ColressTest {
    @Test
    public void loadTasksTest() {
        Storage storage = new StorageStub();
        TaskList taskList = new TaskListStub();
        Colress colress = new Colress(storage, taskList);
        Ui ui = new UiStub(colress);
        colress.setUi(ui);

        // correct format
        TaskList expectedTaskList = new ColressTaskList(taskList.getTasks());
        assertEquals(expectedTaskList.retrieveTasks(), colress.loadTasks());

        // wrong format
        colress = new Colress("src/test/java/colress/testutil/wrongBraceFormat.txt");
        assertEquals(new FileCorruptedException().getMessage(), colress.loadTasks());
    }

    @Test
    public void getCommandTypeTest() {
        Storage storage = new StorageStub();
        TaskList taskList = new TaskListStub();

        // no error
        Colress colress = new Colress(storage, taskList, false, true);
        String expectedCommandType = "greet";
        assertEquals(expectedCommandType, colress.getCommandType());

        // has error
        colress = new Colress(storage, taskList, true, true);
        expectedCommandType = "error";
        assertEquals(expectedCommandType, colress.getCommandType());
    }

    @Test
    public void toggleModeTest() {
        Storage storage = new StorageStub();
        TaskList taskList = new TaskListStub();
        Colress colress = new Colress(storage, taskList, false, true);

        // Beginner to Advanced
        colress.toggleMode();
        assertFalse(colress.isBeginnerMode());

        // Advanced to Beginner
        colress = new Colress(storage, taskList, false, false);
        colress.toggleMode();
        assertTrue(colress.isBeginnerMode());
    }
}

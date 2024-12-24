package colress.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import colress.exception.FileCorruptedException;
import colress.tasklist.ColressTaskList;
import colress.tasklist.TaskList;
import colress.testutil.TaskListStub;

public class ColressStorageTest {
    @Test
    public void loadTask_correctFormat_success() {
        try {
            ColressStorage colressStorage = new ColressStorage("src/test/java/colress/testutil/correctFormat.txt");
            TaskList taskList = new ColressTaskList();
            colressStorage.loadTasks(taskList);
            TaskList expectedTaskList = new TaskListStub();
            assertEquals(expectedTaskList, taskList);
        } catch (IOException | FileCorruptedException e) {
            fail();
        }
    }

    @Test
    public void loadTask_wrongBraceFormat_exceptionThrown() {
        try {
            ColressStorage colressStorage = new ColressStorage("src/test/java/colress/testutil/wrongBraceFormat.txt");
            colressStorage.loadTasks(new ColressTaskList());
            fail();
        } catch (IOException e) {
            fail();
        } catch (FileCorruptedException e) {
            assertEquals("What is this?! The task file seems to be corrupted! "
                            + "Here, I'll create a new file for you.",
                    e.getMessage());
        }
    }

    @Test
    public void loadTask_wrongDateFormat_exceptionThrown() {
        try {
            ColressStorage colressStorage = new ColressStorage("src/test/java/colress/testutil/wrongDateFormat.txt");
            colressStorage.loadTasks(new ColressTaskList());
            fail();
        } catch (IOException e) {
            fail();
        } catch (FileCorruptedException e) {
            assertEquals("What is this?! The task file seems to be corrupted! "
                            + "Here, I'll create a new file for you.",
                    e.getMessage());
        }
    }

    @Test
    public void loadTask_wrongTimeFormat_exceptionThrown() {
        try {
            ColressStorage colressStorage = new ColressStorage("src/test/java/colress/testutil/wrongTimeFormat.txt");
            colressStorage.loadTasks(new ColressTaskList());
            fail();
        } catch (IOException e) {
            fail();
        } catch (FileCorruptedException e) {
            assertEquals("What is this?! The task file seems to be corrupted! "
                            + "Here, I'll create a new file for you.",
                    e.getMessage());
        }
    }
}

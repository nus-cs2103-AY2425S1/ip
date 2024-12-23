package colress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import colress.exception.FileCorruptedException;
import colress.storage.Storage;
import colress.tasklist.TaskList;
import colress.tasklist.TaskListable;
import colress.testutil.CorrectFormatTaskListStub;

public class StorageTest {
    @Test
    public void loadTask_correctFormat_success() {
        try {
            Storage storage = new Storage("src/test/java/colress/testutil/correctFormat.txt");
            TaskListable taskList = new TaskList();
            storage.loadTasks(taskList);
            TaskListable expectedTaskList = new CorrectFormatTaskListStub();
            assertEquals(expectedTaskList, taskList);
        } catch (IOException | FileCorruptedException e) {
            fail();
        }
    }

    @Test
    public void loadTask_wrongBraceFormat_exceptionThrown() {
        try {
            Storage storage = new Storage("src/test/java/colress/testutil/wrongBraceFormat.txt");
            storage.loadTasks(new TaskList());
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
            Storage storage = new Storage("src/test/java/colress/testutil/wrongDateFormat.txt");
            storage.loadTasks(new TaskList());
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
            Storage storage = new Storage("src/test/java/colress/testutil/wrongTimeFormat.txt");
            storage.loadTasks(new TaskList());
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

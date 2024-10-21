package kietwoforone.tasks;

import kietwoforone.exceptions.KieTwoForOneException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    TaskList taskList = new TaskList(new ArrayList<Task>(1));

    @Test
    public void deleteTask_exceptionThrown() {
        try {
            assertEquals("", taskList.deleteTask(1));
            fail();
        } catch (KieTwoForOneException e) {
            assertEquals("No task at index 1!", e.getMessage());
        }
    }

    @Test
    public void markTask_exceptionThrown() {
        try {
            assertEquals("", taskList.markTask(1));
            fail();
        } catch (KieTwoForOneException e) {
            assertEquals("Task does not exist!", e.getMessage());
        }
    }

    @Test
    public void unmarkTask_exceptionThrown() {
        try {
            assertEquals("", taskList.unmarkTask(1));
            fail();
        } catch (KieTwoForOneException e) {
            assertEquals("Task does not exist!", e.getMessage());
        }
    }
}

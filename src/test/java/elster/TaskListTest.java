package elster;

import elster.tasks.Task;
import elster.tasks.TaskStub;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    TaskList taskList;
    Task task;
    @BeforeEach
    void initialise() {
        taskList = new TaskList();
        for (int i = 0; i < 3; i++) {
            taskList.addToList(new TaskStub(Integer.toString(i)));
        }

        task = new TaskStub("3");
    }

    @Test
    void getTask() throws Elseption {
        taskList.addToList(task);

        assertEquals(task, taskList.getTask(4));
    }

    @Test
    public void getTask_indexOutOfBounds_exceptionThrown() {
        try {
            assertEquals(task, taskList.getTask(4));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Elseption thrown", e.getMessage());
        }
    }

    @Test
    void deleteTask() throws Elseption {
        taskList.addToList(task);

        assertEquals(4, taskList.getSize());
        assertEquals(task, taskList.deleteTask(4));
        assertEquals(3, taskList.getSize());
    }

    @Test
    public void deleteTask_indexOutOfBounds_exceptionThrown() {
        try {
            assertEquals(task, taskList.getTask(4));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Elseption thrown", e.getMessage());
        }
    }
}

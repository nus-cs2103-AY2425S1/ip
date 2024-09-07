package echo.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import echo.task.Task;
import echo.task.ToDo;

public class TaskListTest {

    @Test
    public void testGetTaskAndDelete() {
        TaskList taskList = new TaskList();
        String[] toDoArray = { "test" };
        ToDo expected = new ToDo(toDoArray);
        taskList.addTask(expected);
        Task actual = taskList.getTaskAndDelete("1");
        assertEquals(expected, actual);
        assertEquals(0, taskList.sizeOfTaskList());
    }

    @Test
    public void testGetTaskAndDelete_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            String[] toDoArray = { "test" };
            ToDo expected = new ToDo(toDoArray);
            taskList.addTask(expected);
            Task actual = taskList.getTaskAndDelete("2");
            fail();
        } catch (Exception e) {
            assertEquals("There is not enough task. "
                    + "\nPlease add more task or change another index.", e.getMessage());
        }
    }

    @Test
    public void testGetTask() {
        TaskList taskList = new TaskList();
        String[] toDoArray = { "test" };
        ToDo expected = new ToDo(toDoArray);
        taskList.addTask(expected);
        Task actual = taskList.getTask(0);
        assertEquals(expected, actual);
    }

}

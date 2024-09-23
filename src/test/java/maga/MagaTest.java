package maga;  //same package as the class being tested

import maga.exceptions.LoadTaskException;
import maga.task.Task;
import maga.task.TaskList;
import maga.task.TodoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MagaTest {

    @Test
    public void deleteTest() {
        TaskList taskList = new TaskList();
        try {
            taskList.addTask(new TodoTask(false, "Test 1"));
            taskList.addTask(new TodoTask(false, "Test 2"));
        } catch (LoadTaskException e) {
            System.out.print("Test failed!");
        }
        taskList.deleteTask(3); //invalid delete
        taskList.deleteTask(-1); //invalid delete
        taskList.deleteTask(1);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    public void markTest() {
        TodoTask todoTask = new TodoTask(false, "");
        todoTask.markAsDone();
        assertEquals("[X] ", todoTask.getStatusIcon());
        todoTask.markAsUndone();
        assertEquals("[ ] ", todoTask.getStatusIcon());
    }
}

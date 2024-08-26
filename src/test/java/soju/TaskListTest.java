package soju;

import org.junit.jupiter.api.Test;
import soju.tasks.Event;
import soju.tasks.Task;
import soju.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addRemoveTask_twoTasks_correctSize() {
        TaskList taskList = new TaskList();
        taskList.addTask(new TaskStub("Stub 1"));
        taskList.addTask(new TaskStub("Stub 2"));
        assertEquals(2, taskList.size());
        taskList.deleteTask(2);
        taskList.deleteTask(1);
        assertEquals(0, taskList.size());
    }
    @Test
    public void markUnmarkTask_oneTask_correct() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Todo");
        taskList.addTask(task);
        taskList.markTask(1);
        assertEquals("[T][X] Todo", task.toString());
        taskList.unmarkTask(1);
        assertEquals("[T][ ] Todo", task.toString());
    }
}

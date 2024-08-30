package task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void testAddTask() {
        ArrayList<Task> list = new ArrayList<>();

        TaskList taskList = new TaskList(list);
        ToDoTask todo = new ToDoTask("Buy milk");

        taskList.addTask(todo);
        assertEquals(1, taskList.getList().size());
    }
}

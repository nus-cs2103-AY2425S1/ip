package chatbaby;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test task");
        taskList.addTask(task);

        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTaskAt(0));
    }
}

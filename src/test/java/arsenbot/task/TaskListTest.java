package arsenbot.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Read book");
        taskList.addTask(task);

        assertEquals(1, taskList.getTasks().size());
        assertEquals("[T][ ] Read book", taskList.getTasks().get(0).toString());
    }

    @Test
    public void testGetTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Read book");
        Task task2 = new Deadline("Submit assignment", "2024-09-15 0800");
        taskList.addTask(task1);
        taskList.addTask(task2);

        assertEquals(2, taskList.getTasks().size());
        assertEquals("[T][ ] Read book", taskList.getTasks().get(0).toString());
        assertEquals("[D][ ] Submit assignment (by: Sep 15 2024, 8:00 AM)", taskList.getTasks().get(1).toString());
    }
}

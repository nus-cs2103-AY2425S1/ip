package friday.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void addTask_increasesSize() {
        TaskList tasks = new TaskList();
        int initialSize = tasks.getSize();
        tasks.addTask(new Todo("read book"));
        assertEquals(initialSize + 1, tasks.getSize());
    }

    @Test
    void deleteTask_removesCorrectTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("read book");
        taskList.addTask(task);
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    void getTask_returnsCorrectTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("read book");
        taskList.addTask(task);
        assertEquals(task, taskList.getTasks().get(0));
    }
}

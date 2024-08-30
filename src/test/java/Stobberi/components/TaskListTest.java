package stobberi.components;

import org.junit.jupiter.api.Test;
import stobberi.stobberiException.StobberiException;
import stobberi.task.Deadline;
import stobberi.task.Event;
import stobberi.task.Task;
import stobberi.task.Todo;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("homework", "27-08-2024 1900hrs");
        Task task3 = new Task("");
        try {
            task3 = new Event("meeting", "27-08-2024 1900hrs", "27-08-2024 2100hrs");
        } catch (StobberiException e) {
            System.out.println(e.getMessage());
        }
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        taskList.markTask(2);

        assertTrue(task2.getDone());
    }

}
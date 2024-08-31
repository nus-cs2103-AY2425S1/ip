package bestie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bestie.task.Task;
import bestie.task.Todo;

public class TaskListTest {
    @Test
    void addTask_normalInput_checkIfAddedCorrectly() {
        Task task = new Todo("read book");
        TaskList tasks = new TaskList();
        tasks.addTask(task);
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.getTask(0));
    }

    @Test
    void deleteTask_normalInput_checkIfTaskDeleted() {
        Task task = new Todo("read book");
        TaskList tasks = new TaskList();
        tasks.addTask(task);
        tasks.deleteTask(0);
        assertEquals(0, tasks.size());
    }
}

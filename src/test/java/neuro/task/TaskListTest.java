package neuro.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void getSize_correctSizeReturned() {
        ArrayList<Task> taskList = new ArrayList<>();
        TaskList emptyTasks = new TaskList(taskList);
        assertEquals(0, emptyTasks.getSize());

        taskList.add(new Todo("test"));
        TaskList nonEmptyTasks = new TaskList(taskList);
        assertEquals(1, nonEmptyTasks.getSize());
    }

    @Test
    public void addTask_taskAddedAndSizeIncreased() {
        ArrayList<Task> taskList = new ArrayList<>();
        TaskList tasks = new TaskList(taskList);
        tasks.addTask(new Todo("test"));
        assertEquals(1, tasks.getSize());
    }
}

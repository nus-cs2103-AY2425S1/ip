package tasklisttest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasklist.TaskList;
import task.Task;
import todo.ToDo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    private ArrayList<Task> taskList;

    @BeforeEach
    public void setUp() {
        taskList = new ArrayList<>();
    }

    /**
     * Tests that the size of task list increases when a ToDo is created
     * Tests that the name of the new task is correct
     */
    @Test
    public void testCreateToDo() {
        String taskName = "Read a book";
        int initialSize = taskList.size();

        TaskList.createToDo(taskName, taskList);

        assertEquals(initialSize + 1, taskList.size());
        assertEquals(taskName, taskList.get(0).getName());
    }

    /**
     * Tests that the size of the task list decreases by 1 when a task is deleted
     * Tests that the remaining tasks are correct
     */
    @Test
    public void testDeleteEvent() {
        ToDo task1 = new ToDo("Task 1");
        ToDo task2 = new ToDo("Task 2");
        taskList.add(task1);
        taskList.add(task2);
        int initialSize = taskList.size();

        TaskList.deleteEvent(0, taskList);

        assertEquals(initialSize - 1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }

    /**
     * Tests that deleting a task with an invalid index throws IndexOutOfBoundsException
     */
    @Test
    public void testDeleteEventWithInvalidIndex() {
        ToDo task = new ToDo("Task 1");
        taskList.add(task);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            TaskList.deleteEvent(2, taskList);
        });
    }
}

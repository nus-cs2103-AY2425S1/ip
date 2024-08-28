package bro.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        ArrayList<Task> initialTasks = new ArrayList<>();
        initialTasks.add(new TodoTask("Task 1"));
        initialTasks.add(new TodoTask("Task 2"));
        taskList = new TaskList(initialTasks);
    }

    @Test
    void test_delete_task_out_of_bounds() {
        int invalidIndex = 10;
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(invalidIndex));
    }
    @Test
    void test_mark_and_unmark_test() throws Exception {
        Task markedTask = taskList.markTask(0);
        assertTrue(markedTask.toString().contains("[X]"), "Task should be marked as completed.");
        Task unmarkedTask = taskList.unmarkTask(0);
        assertTrue(unmarkedTask.toString().contains("[]"), "Task should be unmarked and incomplete.");
    }
}

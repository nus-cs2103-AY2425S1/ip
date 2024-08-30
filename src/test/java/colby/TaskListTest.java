package colby;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    @Test
    public void getTask_success() {
        Task task1 = new ToDo("quiz");
        Task task2 = new Deadline("submission", "2024/09/09");
        Task task3 = new Event("meeting", "Monday 2pm", "4pm");
        Task task4 = new ToDo("read book");
        TaskList taskList = new TaskList(Arrays.asList(task1, task2, task3, task4));

        assertEquals(task1, taskList.getTask(0));
        assertEquals(task3, taskList.getTask(2));
    }

    @Test
    public void getTask_exceptionThrown() {
            Task task1 = new ToDo("quiz");
            TaskList taskList = new TaskList(Arrays.asList(task1));

            assertThrows(IndexOutOfBoundsException.class, () -> {
                taskList.getTask(1);
            });
    }
}
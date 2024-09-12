package yap.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import yap.storage.Storage;

public class TaskListTest {
    @Test
    public void markTask_success() throws IndexOutOfBoundsException {
        Task unmarkedTask1 = new Task("task1");
        Task unmarkedTask2 = new Task("task2");
        Task unmarkedTask3 = new Task("task3");

        TaskList taskList = new TaskList(new Storage("", ""));
        taskList.addTask(unmarkedTask1);
        taskList.addTask(unmarkedTask2);
        taskList.addTask(unmarkedTask3);

        taskList.markTask(1);
        assertEquals("[X] task2", unmarkedTask2.toString());
    }

    @Test
    public void markTask_outOfBounds_failure() {
        try {
            Task unmarkedTask1 = new Task("task1");
            Task unmarkedTask2 = new Task("task2");
            Task unmarkedTask3 = new Task("task3");

            TaskList taskList = new TaskList(new Storage("", ""));
            taskList.addTask(unmarkedTask1);
            taskList.addTask(unmarkedTask2);
            taskList.addTask(unmarkedTask3);

            taskList.markTask(3);
            fail();
        } catch (IndexOutOfBoundsException exception) {
            return;
        }
    }
}


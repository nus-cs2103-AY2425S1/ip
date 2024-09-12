package snah;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import snah.task.Deadline;
import snah.task.Event;
import snah.task.Task;

public class TaskListTest {

    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
        taskList.add(new Task("task 1"));
        assertEquals(1, taskList.size());
        taskList.add(new Task("task 2"));
        assertEquals(2, taskList.size());
    }

    @Test
    public void searchTaskTest() {
        TaskList taskList = new TaskList();
        taskList.add(new Task("task 1"));
        taskList.add(new Deadline("deadline", "2024-09-09"));
        taskList.add(new Event("event", "from", "task 3"));

        assertEquals(3, taskList.size());
        assertEquals(1, taskList.search("task 1").size());
        assertEquals(2, taskList.search("task").size());
        assertEquals(0, taskList.search("task 4").size());
    }
}

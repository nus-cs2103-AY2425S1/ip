package snah;

import org.junit.jupiter.api.Test;

import snah.task.Deadline;
import snah.task.Event;
import snah.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasklistTest {

    @Test
    public void addTaskTest() {
        Tasklist tasklist = new Tasklist();
        assertEquals(0, tasklist.size());
        tasklist.add(new Task("task 1"));
        assertEquals(1, tasklist.size());
        tasklist.add(new Task("task 2"));
        assertEquals(2, tasklist.size());
    }

    @Test
    public void searchTaskTest() {
        Tasklist tasklist = new Tasklist();
        tasklist.add(new Task("task 1"));
        tasklist.add(new Deadline("deadline", "2024-09-09"));
        tasklist.add(new Event("event", "from", "task 3"));

        assertEquals(3, tasklist.size());
        assertEquals(1, tasklist.search("task 1").size());
        assertEquals(2, tasklist.search("task").size());
        assertEquals(0, tasklist.search("task 4").size());
    }
}

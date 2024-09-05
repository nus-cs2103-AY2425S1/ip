package muffin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        // Tests add and get functionality of TaskList class
        TaskList list = new TaskList(new ArrayList<Task>());
        Todo t = new Todo("submit assignment");
        list.add(t);
        assertEquals(t, list.get(0));
    }

    @Test
    public void markTest() {
        TaskList list = new TaskList(new ArrayList<Task>());
        Deadline d = new Deadline("submit assignment", "2024-09-01");
        list.add(d);
        list.mark(0);
        assertEquals(d.isDone, list.get(0).isDone);
    }
}

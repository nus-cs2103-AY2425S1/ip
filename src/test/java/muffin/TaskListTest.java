package muffin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        // Tests add and get functionality of TaskList class
        TaskList list = new TaskList();
        Todo t = new Todo("submit assignment");
        list.add(list.length(), t);
        assertEquals(t, list.get(0));
    }

    @Test
    public void markTest() {
        TaskList list = new TaskList();
        Deadline d = new Deadline("submit assignment", "2024-09-01");
        list.add(list.length(), d);
        list.mark(0);
        assertEquals(d.isDone, list.get(0).isDone);
    }
}

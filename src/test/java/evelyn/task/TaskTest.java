package evelyn.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {

    @Test
    public void uncheckedTodoTest() {
        Todo todo = new Todo("Test 1", false);
        assertEquals("[T][ ] Test 1", todo.toString());
    }

    @Test
    public void checkedTodoTest() {
        Todo todo = new Todo("Test 2", true);
        assertEquals("[T][X] Test 2", todo.toString());
    }

    @Test
    public void uncheckedDeadlineTest() {
        Deadline deadline = new Deadline("Test 3", "2020-03-11", false);
        assertEquals("[D][ ] Test 3 (by: 2020-03-11)", deadline.toString());
    }

    @Test
    public void checkedDeadlineTest() {
        Deadline deadline = new Deadline("Test 4", "2020-03-11", true);
        assertEquals("[D][X] Test 4 (by: 2020-03-11)", deadline.toString());
    }

    @Test
    public void uncheckedEventTest() {
        Event event = new Event("Test 5", "2020-04-04", "2020-04-04", false);
        assertEquals("[E][ ] Test 5 (from: 2020-04-04 to: 2020-04-04)", event.toString());
    }

    @Test
    public void checkedEventTest() {
        Event event = new Event("Test 6", "2020-04-04", "2020-04-04", true);
        assertEquals("[E][X] Test 6 (from: 2020-04-04 to: 2020-04-04)", event.toString());
    }
}

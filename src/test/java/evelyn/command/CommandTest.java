package evelyn.command;

import evelyn.task.Deadline;
import evelyn.task.Event;
import evelyn.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {

    @Test
    public void checkedTodoTest() {
        Todo todo = new Todo("Test 2", true);
        assertEquals("[T][X] Test 2", todo.toString());
    }

    @Test
    public void checkedDeadlineTest() {
        Deadline deadline = new Deadline("Test 4", "2020-03-11", true);
        assertEquals("[D][X] Test 4 (by: 2020-03-11)", deadline.toString());
    }

    @Test
    public void checkedEventTest() {
        Event event = new Event("Test 6", "2020-04-04", "2020-04-04", true);
        assertEquals("[E][X] Test 6 (from: 2020-04-04 to: 2020-04-04)", event.toString());
    }
}

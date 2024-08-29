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
    public void uncheckedEventTest() {
        Event event = new Event("Test 3", "2020-03-11", "2020-03-11", false);
        assertEquals("[E][ ] Test 3 from");
    }
}

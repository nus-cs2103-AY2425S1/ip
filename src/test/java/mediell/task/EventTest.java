package mediell.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void init_success(){
        Event event = new Event("task", LocalDate.parse("2023-01-01"), LocalDate.parse("2024-01-02"));
        assertEquals("[E][ ] task (from: 2023-01-01 to: 2024-01-02)", event.toString());
    }

    @Test
    public void taskToStorageFormat_success() {
        // base case
        Event event = new Event("task", LocalDate.parse("2023-01-01"), LocalDate.parse("2024-01-02"));
        assertEquals("E|2023-01-01|2024-01-02|0|task", event.taskToStorageFormat());

        // a marked task
        event.markCompleted();
        assertEquals("E|2023-01-01|2024-01-02|1|task", event.taskToStorageFormat());
    }

    @Test
    public void compareTo_success() {
        // base case where event1 < event2
        Event event1 = new Event("task", LocalDate.parse("2023-01-01"), LocalDate.parse("2024-01-02"));
        Event event2 = new Event("task", LocalDate.parse("2023-01-02"), LocalDate.parse("2024-01-02"));
        Event event3 = new Event("task", LocalDate.parse("2023-01-02"), LocalDate.parse("2024-01-02"));

        assertEquals(-1, event1.compareTo(event2));

        // base case where event2 > event1
        assertEquals(1, event2.compareTo(event1));

        // base case where event2 = event3
        assertEquals(0, event2.compareTo(event3));
    }

    @Test
    public void compareTo_todo_success() {
        // base case
        Event event = new Event("task", LocalDate.parse("2023-01-01"), LocalDate.parse("2024-01-02"));
        ToDo todo = new ToDo("task");

        // all events are > todo
        assertEquals(1, event.compareTo(todo));
    }

    @Test
    public void compareTo_deadline_success() {
        // base case
        Event event = new Event("task", LocalDate.parse("2023-01-02"), LocalDate.parse("2024-01-02"));
        Deadline deadline1 = new Deadline("task", LocalDate.parse("2023-01-01"));
        Deadline deadline2 = new Deadline("task", LocalDate.parse("2023-01-02"));
        Deadline deadline3 = new Deadline("task", LocalDate.parse("2023-01-03"));

        // event > deadline1
        assertEquals(1, event.compareTo(deadline1));

        // event = deadline2
        assertEquals(0, event.compareTo(deadline2));

        // event < deadline3
        assertEquals(-1, event.compareTo(deadline3));
    }
}

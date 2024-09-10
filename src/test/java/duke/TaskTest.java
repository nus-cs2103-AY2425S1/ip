package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void tasksTest() {
        Todo todo = new Todo("borrow book");
        todo.setDone(true);
        Deadline deadline = new Deadline("Finish CS2103", LocalDate.parse("2021-12-23"));
        Event event = new Event("Stargazing", "Mon 2pm", "Tues 4pm");
        assertEquals("[T][X] borrow book", todo.toString());
        assertEquals("[D][ ] Finish CS2103 (by: Dec 23 2021)", deadline.toString());
        assertEquals("[E][ ] Stargazing (from: Mon 2pm to: Tues 4pm)", event.toString());
    }
}

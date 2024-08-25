package moimoi.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {

    String description = "dummy";
    LocalDateTime datetime = LocalDateTime.parse("2024-08-24 12:00",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    @Test
    public void testMarkUnmark() {

        Deadline deadline = new Deadline(this.description, this.datetime);
        assertEquals(" ", deadline.getStatusIcon());

        deadline.mark();
        assertEquals("X", deadline.getStatusIcon());

        deadline.mark();
        assertEquals("X", deadline.getStatusIcon());

        deadline.unmark();
        assertEquals(" ", deadline.getStatusIcon());

        deadline.unmark();
        assertEquals(" ", deadline.getStatusIcon());

    }

    @Test
    public void occurringOn_occurringDate_returnsTrue() {
        Deadline deadline = new Deadline(this.description, this.datetime);
        assertTrue(deadline.occursOn(LocalDate.parse("2024-08-24")));
    }

    @Test
    public void occurringOn_notOccurringDate_returnsFalse() {
        Deadline deadline = new Deadline(this.description, this.datetime);
        assertFalse(deadline.occursOn(LocalDate.parse("2024-07-17")));
    }

    @Test
    public void hasKeyword() {
        Todo todo = new Todo(this.description);
        assertTrue(todo.hasKeyword("dum"));
        assertTrue(todo.hasKeyword(""));
        assertFalse(todo.hasKeyword("dummies"));
        assertFalse(todo.hasKeyword("?"));
        assertFalse(todo.hasKeyword("dummy "));
    }

    @Test
    public void testStringUI() {
        Deadline deadline = new Deadline(this.description, this.datetime);
        assertEquals("[D][ ] " + this.description + " (by: "
                + this.datetime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ")",
                deadline.stringUI());
    }

    @Test
    public void testStringStorage() {
        Deadline deadline = new Deadline(this.description, this.datetime);
        deadline.mark();
        assertEquals("D | X | " + this.description + " | "
                + this.datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                deadline.stringStorage());
    }

}

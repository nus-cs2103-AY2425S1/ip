package moimoi.util.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private String description = "dummy";
    private LocalDateTime datetime = LocalDateTime.parse("2024-08-24 12:00",
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
        Deadline deadlineWithoutWhiteSpace = new Deadline(this.description, this.datetime);
        Deadline deadlineWithWhiteSpace = new Deadline(this.description + " " + this.description,
                this.datetime);

        assertTrue(deadlineWithoutWhiteSpace.hasKeyword("dummy"));
        assertTrue(deadlineWithoutWhiteSpace.hasKeyword("Dum"));
        assertTrue(deadlineWithWhiteSpace.hasKeyword(" "));

        assertFalse(deadlineWithoutWhiteSpace.hasKeyword("dummies"));
        assertFalse(deadlineWithoutWhiteSpace.hasKeyword("?"));
        assertFalse(deadlineWithoutWhiteSpace.hasKeyword("dummy "));
        assertFalse(deadlineWithoutWhiteSpace.hasKeyword(" "));
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

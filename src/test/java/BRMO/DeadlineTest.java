package BRMO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import BRMO.task.Deadline;

public class DeadlineTest {
    @Test
    public void testDeadlineCreation() throws ParseException {
        Deadline deadline = new Deadline("Read a book", "13-12-2025");
        assertEquals("[D][ ] Read a book (by: 13 Dec 2025)", deadline.toString());
        assertEquals("D | 0 | Read a book | 13-12-2025", deadline.toText());
    }

    @Test
    public void testMarkAsDone() throws ParseException {
        Deadline deadline = new Deadline("Read a book", "13-12-2025");
        
        for (int i = 0; i < 2; i++) {
            assertEquals("[D][ ] Read a book (by: 13 Dec 2025)", deadline.toString());
            assertEquals("D | 0 | Read a book | 13-12-2025", deadline.toText());
            deadline.mark();

            assertEquals("[D][X] Read a book (by: 13 Dec 2025)", deadline.toString());
            assertEquals("D | 1 | Read a book | 13-12-2025", deadline.toText());
            deadline.unmark();
        }
    }
}
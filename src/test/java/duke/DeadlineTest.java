package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class DeadlineTest {

    @Test
    public void getWriteFormatTest() {
        Deadline deadline = new Deadline("complete 2103 week 4 tasks", LocalDate.parse("2024-06-10"));

        //test case 1
        deadline.getWriteFormat();
        assertEquals("D , 0 , complete 2103 week 4 tasks , 2024-06-10", deadline.getWriteFormat());

        //test case 2
        deadline.setDeadline(LocalDate.parse("2025-01-01"));
        deadline.setDone(true);
        assertEquals("D , 1 , complete 2103 week 4 tasks , 2025-01-01", deadline.getWriteFormat());
    }

    @Test
    public void toStringTest() {
        //test case 1
        Deadline deadline1 = new Deadline("Complete CS2103 week 5 tasks", LocalDate.parse("2025-01-01"));
        assertEquals(deadline1.toString(), "[D][ ] Complete CS2103 week 5 tasks (by: Jan 1 2025)");
        deadline1.setDone(true);
        assertEquals(deadline1.toString(), "[D][X] Complete CS2103 week 5 tasks (by: Jan 1 2025)");
    }
}

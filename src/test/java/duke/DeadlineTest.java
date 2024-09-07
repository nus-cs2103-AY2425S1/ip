package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {

    @Test
    public void testGetWriteFormat() {
        Deadline deadline = new Deadline("complete 2103 week 4 tasks", LocalDate.parse("2024-06-10"));

        //test case 1
        deadline.getWriteFormat();
        assertEquals("D , 0 , complete 2103 week 4 tasks , 2024-06-10", deadline.getWriteFormat());

        //test case
        deadline.setDeadline(LocalDate.parse("2025-01-01"));
        deadline.setDone(true);
        assertEquals("D , 1 , complete 2103 week 4 tasks , 2025-01-01", deadline.getWriteFormat());
    }
}

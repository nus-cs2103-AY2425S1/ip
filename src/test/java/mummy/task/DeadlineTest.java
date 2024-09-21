package mummy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void getDueBy_shouldReturnCorrectString() {
        Deadline deadline = new Deadline("Finish assignment", "2022-12-31");
        assertEquals("2022-12-31", deadline.getDueBy());
    }

    @Test
    public void getDueByLocalDate_shouldReturnCorrectDate() {
        Deadline deadline = new Deadline("Finish assignment", "2022-12-31");
        assertEquals(LocalDate.parse("2022-12-31"), deadline.getDueByLocalDate());
    }

    @Test
    public void getDueByLocalDate_withInvalidFormat_shouldReturnNull() {
        Deadline deadline = new Deadline("Finish assignment", "31-12-2022");
        assertNull(deadline.getDueByLocalDate());
    }

    @Test
    public void toFileRecord_shouldReturnCorrectFileRecord() {
        Deadline deadline = new Deadline("Finish assignment", "2022-12-31");
        assertEquals("D | 0 | Finish assignment | 2022-12-31", deadline.toFileRecord());
    }

    @Test
    public void toString_shouldReturnCorrectString() {
        Deadline deadline = new Deadline("Finish assignment", "2022-12-31");
        assertEquals("[D][ ] Finish assignment (by: 2022-12-31)", deadline.toString());
    }
}

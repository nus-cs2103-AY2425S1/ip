package phenex.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


/**
 * Unit tests for the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests that a Deadline correctly detects overlap.
     */
    @Test
    public void overlap_overlappingDeadline_true() {
        LocalDate localDate = LocalDate.now();
        Deadline deadline = new Deadline("test", localDate);
        Deadline deadlineOverlapping = new Deadline("test2", localDate);
        assertEquals(true, deadline.overlapsWith(deadlineOverlapping.localDate));
    }

    /**
     * Tests that a Deadline correctly detects no overlap.
     */
    @Test
    public void overlap_nonOverlappingDeadline_false() {
        LocalDate localDate = LocalDate.parse("2020-01-01");
        LocalDate localDate2 = LocalDate.parse("2021-01-01");
        Deadline deadline = new Deadline("test", localDate);
        Deadline deadlineNoOverlap = new Deadline("test2", localDate2);
        assertEquals(false, deadline.overlapsWith(deadlineNoOverlap.localDate));
    }
}

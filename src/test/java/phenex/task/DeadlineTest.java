package phenex.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import phenex.task.Task.TaskType;


/**
 * Unit tests for the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests that two overlapping Deadlines correctly return true.
     */
    @Test
    public void overlaps_withSameDate_returnsTrue() {
        LocalDate localDate = LocalDate.now();
        Deadline deadline1 = new Deadline("Task 1", localDate, Task.TaskType.OTHERS);
        Deadline deadline2 = new Deadline("Task 2", localDate, TaskType.RECREATIONAL);
        assertTrue(deadline1.overlapsWith(deadline2.localDate));
    }

    /**
     * Tests that two non-overlapping Deadlines correctly return false.
     */
    @Test
    public void overlaps_withDifferentDates_returnsFalse() {
        LocalDate localDate1 = LocalDate.parse("2020-01-01");
        LocalDate localDate2 = LocalDate.parse("2021-01-01");
        Deadline deadline1 = new Deadline("Task 1", localDate1, TaskType.OTHERS);
        Deadline deadline2 = new Deadline("Task 2", localDate2, TaskType.RECREATIONAL);
        assertFalse(deadline1.overlapsWith(deadline2.localDate));
    }

    /**
     * Tests that two adjacent Deadlines correctly return false (no overlap).
     */
    @Test
    public void overlaps_withAdjacentDates_returnsFalse() {
        LocalDate localDate1 = LocalDate.parse("2020-01-01");
        LocalDate localDate2 = LocalDate.parse("2020-01-02");
        Deadline deadline1 = new Deadline("Task 1", localDate1, TaskType.OTHERS);
        Deadline deadline2 = new Deadline("Task 2", localDate2, TaskType.OTHERS);
        assertFalse(deadline1.overlapsWith(deadline2.localDate));
    }

    /**
     * Tests that a Deadline does not overlap with null date (returns false).
     */
    @Test
    public void overlaps_withNullDate_returnsFalse() {
        LocalDate localDate = LocalDate.parse("2020-01-01");
        Deadline deadline1 = new Deadline("Task 1", localDate, TaskType.PRODUCTIVE);
        assertFalse(deadline1.overlapsWith(null));
    }
}

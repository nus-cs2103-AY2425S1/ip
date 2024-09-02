package spiderman;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void testDeadlineTaskCanBeCreated() {
        LocalDate date = LocalDate.of(2020, 1, 25); // Example date
        Deadline deadline = new Deadline("Finish Assignment", date);
        assertEquals("Finish Assignment", deadline.getDescription(), "The description should match the input");
        assertEquals(date, deadline.by, "The 'by' date should match the input date");
    }

    @Test
    public void testMarkingDeadlineAsDone() {
        // Test marking a Deadline task as done
        LocalDate date = LocalDate.of(2020, 1, 25);
        Deadline deadline = new Deadline("Finish assignment", date);
        deadline.markAsDone();
        assertTrue(deadline.isTaskDone());
    }

    @Test
    public void testMarkingDeadlineAsNotDone() {
        // Test marking a Deadline task as not done
        LocalDate date = LocalDate.of(2020, 1, 25);
        Deadline deadline = new Deadline("Finish assignment", date);
        deadline.markAsDone();
        deadline.markAsNotDone();
        assertFalse(deadline.isTaskDone());
    }

}

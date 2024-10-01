package garfield.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DeadlineTest {
    private Deadline deadline;

    @BeforeEach
    void setUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.deadline = new Deadline("Submit report",
                LocalDateTime.parse("2024-03-25 23:59", formatter));
    }

    @Test
    void constructor_validDescriptionAndDeadline_taskInitialized() {
        assertNotNull(deadline);
        assertEquals("Submit report", deadline.getTaskDescription());
        assertEquals("[D][ ] Submit report (by: Mar 25 2024 11:59PM)", deadline.toString());
    }

    @Test
    void markAsDone_taskMarkedDone_statusUpdated() {
        deadline.markAsDone();
        assertEquals("[D][X] Submit report (by: Mar 25 2024 11:59PM)", deadline.toString());
    }

    @Test
    void markAsUndone_taskPreviouslyMarkedDone_statusReverted() {
        deadline.markAsDone(); // Mark as done first
        deadline.markAsUndone();
        assertEquals("[D][ ] Submit report (by: Mar 25 2024 11:59PM)", deadline.toString());
    }

    @Test
    void toSaveRepresentation_taskNotDone_correctSaveFormat() {
        assertEquals("D | 0 | Submit report | 2024-03-25 23:59", deadline.toSaveRepresentation());
    }

    @Test
    void toSaveRepresentation_taskDone_correctSaveFormat() {
        deadline.markAsDone();
        assertEquals("D | 1 | Submit report | 2024-03-25 23:59", deadline.toSaveRepresentation());
    }
}

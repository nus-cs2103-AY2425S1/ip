package bestie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @BeforeAll
    public static void setUp() {
        Locale.setDefault(new Locale("en", "SG"));
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
    }

    @Test
    void constructor_normalInput_correctDescription() {
        Deadline deadline = new Deadline("submit tutorial", "2024-08-29 1900", Priority.HIGH);
        assertEquals("submit tutorial", deadline.getDescription());
        assertEquals("[D][ ] submit tutorial (by: Aug 29 2024 7:00 pm), priority: HIGH", deadline.toString());
    }

    @Test
    void toSaveFormat_normalInput_savedCorrectly() {
        Deadline deadline = new Deadline("submit tutorial", "2024-08-29 1900", Priority.HIGH);
        assertEquals("D | 0 | submit tutorial | 2024-08-29 1900 | HIGH", deadline.toSaveFormat());
    }

    @Test
    void toSaveFormat_completedTask_savedCorrectly() {
        Deadline deadline = new Deadline("submit tutorial", "2024-08-29 1900", Priority.LOW);
        deadline.isDone = true;
        assertEquals("D | 1 | submit tutorial | 2024-08-29 1900 | LOW", deadline.toSaveFormat());
    }

    @Test
    void toSaveFormat_differentPriority_savedCorrectly() {
        Deadline highPriorityDeadline = new Deadline("submit tutorial", "2024-08-29 1900", Priority.HIGH);
        assertEquals("D | 0 | submit tutorial | 2024-08-29 1900 | HIGH", highPriorityDeadline.toSaveFormat());
        Deadline mediumPriorityDeadline = new Deadline("submit tutorial",
                "2024-08-29 1900", Priority.MEDIUM);
        assertEquals("D | 0 | submit tutorial | 2024-08-29 1900 | MEDIUM",
                mediumPriorityDeadline.toSaveFormat());
        Deadline lowPriorityDeadline = new Deadline("submit tutorial",
                "2024-08-29 1900", Priority.LOW);
        assertEquals("D | 0 | submit tutorial | 2024-08-29 1900 | LOW",
                lowPriorityDeadline.toSaveFormat());
    }

}

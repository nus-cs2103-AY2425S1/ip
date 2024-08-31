package TrackBot.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    private Deadline deadline;

    @BeforeEach
    public void setUp() {
        deadline = new Deadline("Finish iP project", "2024-12-01");
    }

    @Test
    public void test_getBy() {
        assertEquals("Dec 1 2024", deadline.getBy());
    }

    @Test
    public void test_toStorageFormat() {
        assertEquals("D | 0 | Finish iP project | Dec 1 2024", deadline.toStorageFormat());
        deadline.mark();
        assertEquals("D | 1 | Finish iP project | Dec 1 2024", deadline.toStorageFormat());
    }

    @Test
    public void test_toString() {
        assertEquals("[D][ ] Finish iP project (by: Dec 1 2024)", deadline.toString());
        deadline.mark();
        assertEquals("[D][X] Finish iP project (by: Dec 1 2024)", deadline.toString());
    }

    @Test
    public void test_deadlineWithDifferentDateFormat() {
        Deadline deadline1 = new Deadline("Project due", "12-01-2024");
        assertEquals("Dec 1 2024", deadline1.getBy());
        assertEquals("[D][ ] Project due (by: Dec 1 2024)", deadline1.toString());

        Deadline deadline2 = new Deadline("Zoom meeting", "01-Dec-2024");
        assertEquals("Dec 1 2024", deadline2.getBy());
        assertEquals("[D][ ] Zoom meeting (by: Dec 1 2024)", deadline2.toString());

        Deadline deadline3 = new Deadline("Homework", "2024-12-01 23:59");
        assertEquals("Dec 1 2024 23:59", deadline3.getBy());
        assertEquals("[D][ ] Homework (by: Dec 1 2024 23:59)", deadline3.toString());
    }
}

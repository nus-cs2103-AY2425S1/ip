package ned.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ned.Ui;
import ned.exceptions.NedException;


public class DeadlineTest {
    @Test
    public void createDeadline_validDescriptionValidByDate_deadlineObjectCreated() throws NedException {
        assertEquals(Deadline.createDeadline("read", "2024-08-20", false),
                new Deadline("read", "2024-08-20", false));
    }

    @Test
    public void createDeadline_validDescriptionInvalidByDate_nedExceptionThrown() {
        try {
            Deadline.createDeadline("read", "2024/08/20", false);
        } catch (NedException e) {
            assertEquals("M'lord, the time formatting in /by does not follow ISO 8601 (yyyy-mm-dd). Here "
                            + "are examples of valid timings:\n"
                            + Ui.INDENTATIONS + "2015-08-04\n" + Ui.INDENTATIONS + "2015-08-04T10:11:30",
                    e.getMessage());
        }
    }

    @Test
    public void createDeadline_invalidDescription_nedExceptionThrown() {
        try {
            Deadline.createDeadline("", "2024/08/20", false);
        } catch (NedException e) {
            assertEquals("M'lord, this saved deadline task has no task description!", e.getMessage());
        }
    }

    @Test
    public void createDeadline_validDescriptionEmptyByDate_nedExceptionThrown() {
        try {
            Deadline.createDeadline("read", "", false);
        } catch (NedException e) {
            assertEquals("M'lord, this saved deadline task has no due date!", e.getMessage());
        }
    }
}

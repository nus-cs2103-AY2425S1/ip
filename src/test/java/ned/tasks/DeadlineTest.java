package ned.tasks;

import ned.Ui;
import ned.exceptions.NedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void createDeadline_validDescription_validByDate_DeadlineObjectCreated() throws NedException {
        assertEquals(Deadline.createDeadline("read", "2024-08-20", false),
                new Deadline("read", "2024-08-20", false));
    }

    @Test
    public void createDeadline_validDescription_invalidByDate_NedExceptionThrown() {
        try {
            Deadline.createDeadline("read", "2024/08/20", false);
        } catch (NedException e) {
            assertEquals("M'lord, the time formatting in /by does not follow ISO 8601 (yyyy-mm-dd). Here " +
                            "are " +
                            "examples of" +
                            " " +
                            "valid " +
                            "timings:\n" + Ui.INDENTATIONS + "2015-08-04\n" + Ui.INDENTATIONS + "2015-08-04T10:11:30"
                    , e.getMessage());
        }
    }

    @Test
    public void createDeadline_invalidDescription_NedExceptionThrown() {
        try {
            Deadline.createDeadline("", "2024/08/20", false);
        } catch (NedException e) {
            assertEquals("M'lord, this saved deadline task has no task description!", e.getMessage());
        }
    }

    public void createDeadline_validDescription_emptyByDate_NedExceptionThrown() {
        try {
            Deadline.createDeadline("read", "", false);
        } catch (NedException e) {
            assertEquals("M'lord, this saved deadline task has no due date!", e.getMessage());
        }
    }


}

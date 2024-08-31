package yapper.task;

import org.junit.jupiter.api.Test;
import yapper.exception.YapperException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void deadlineCreation_correctDescriptionAndDate_success() throws YapperException {
        Deadline deadline = new Deadline("Submit report", "2024-10-10 1800");
        assertEquals("Submit report", deadline.getDescription());
    }

    @Test
    public void deadlineToString_notDone_correctString() throws YapperException {
        Deadline deadline = new Deadline("Submit report", "2024-10-10 1800");
        String expectedOutput = "[D][ ] Submit report (by: Oct 10 2024, 6:00pm)";
        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void deadlineToString_done_correctString() throws YapperException {
        Deadline deadline = new Deadline("Submit report", "2024-10-10 1800");
        deadline.markAsDone();
        String expectedOutput = "[D][X] Submit report (by: Oct 10 2024, 6:00pm)";
        assertEquals(expectedOutput, deadline.toString());
    }
}

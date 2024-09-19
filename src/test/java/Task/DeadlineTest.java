package Task;
import org.junit.jupiter.api.Test;
import Task.TaskCreationException;
import Task.Deadline;
import Task.Task.TaskType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void readTaskTest() {
        try {
            String expectedFormattedDate = "02/12/2019 1800";
            Deadline task = Deadline.of("deadline return book /by 2/12/2019 1800", TaskType.D);
            assertEquals(expectedFormattedDate, task.getDeadline());
        } catch (TaskCreationException e) {
            fail("TaskCreationException was thrown during the test.", e);
        }
    }

    @Test
    public void getTaskDescriptionTest() {
        try {
            Deadline task = Deadline.of("deadline return book /by 2/12/2019 1800", TaskType.D);
            assertEquals("deadline return book", task.getTaskDescription());
        } catch (TaskCreationException e) {
            fail("TaskCreationException was thrown during the test.", e);
        }
    }
}
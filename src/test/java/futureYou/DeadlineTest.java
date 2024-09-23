package futureyou;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import futureyou.task.Deadline;

/**
 * Tests for the {@link Deadline} class.
 */
public class DeadlineTest {
    @Test
    public void deadline_normalInput_writtenCorrectly() {
        String taskName = "Submit 2103";

        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm");
        LocalDateTime deadline = LocalDateTime.of(2024, 9, 05, 14, 0);
        Deadline newDeadlineTask = new Deadline(taskName, deadline);

        assertEquals(deadline.format(format), newDeadlineTask.getDeadline());
    }

}
